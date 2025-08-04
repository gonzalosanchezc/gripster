package com.gripster.servlets;

import com.gripster.dao.*;
import com.gripster.model.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.*;

@WebServlet("/SvCheckout")
public class SvCheckout extends HttpServlet {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final VentaDAO ventaDAO = new VentaDAO();
    private final CarritoDAO carritoDAO = new CarritoDAO();
    private final SucursalDAO sucursalDAO = new SucursalDAO();
    private final SesionesDAO sesionesDAO = new SesionesDAO();

    private static final Logger logger = Logger.getLogger(SvCheckout.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sessionId = getSessionIdFromCookie(request);
        if (sessionId == null || !sesionesDAO.sessionExists(sessionId)) {
            response.sendRedirect("SvCookieSession");
            return;
        }

        List<Carrito> items = carritoDAO.getCartBySession(sessionId);
        if (items == null || items.isEmpty()) {
            response.sendRedirect("cart.jsp");
            return;
        }

        // Calcular totales
        double subtotal = items.stream()
                .mapToDouble(item -> item.getLlanta().getPrecio() * item.getCantidad())
                .sum();
        double iva = subtotal * 0.16; // 16% de IVA
        double total = subtotal + iva;

        // Pasar datos al JSP
        request.setAttribute("cartItems", items);
        request.setAttribute("subtotal", subtotal);
        request.setAttribute("iva", iva);
        request.setAttribute("total", total);

        List<Sucursal> sucursales = sucursalDAO.obtenerTodas();
        request.setAttribute("sucursales", sucursales);

        request.getRequestDispatcher("checkout.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String sessionId = getSessionIdFromCookie(request);
            if (sessionId == null || !sesionesDAO.sessionExists(sessionId)) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Sesión inválida");
                return;
            }

            String nombre = request.getParameter("nombre");
            String correo = request.getParameter("correo");
            String telefono = request.getParameter("telefono");
            int idSucursal = Integer.parseInt(request.getParameter("idSucursal"));

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = dateFormat.parse(request.getParameter("fechaCita"));
            java.sql.Date fechaCita = new java.sql.Date(parsedDate.getTime());

            // Validación: fecha dentro de los próximos 7 días
            Calendar hoy = Calendar.getInstance();
            Calendar fechaLimite = (Calendar) hoy.clone();
            fechaLimite.add(Calendar.DAY_OF_MONTH, 7);

            if (parsedDate.before(hoy.getTime()) || parsedDate.after(fechaLimite.getTime())) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "La fecha debe estar dentro de los próximos 7 días.");
                return;
            }

            Usuario usuario = usuarioDAO.obtenerUsuarioPorCorreo(correo);
            if (usuario == null) {
                usuario = new Usuario(nombre, correo, telefono);
                int idUsuario = usuarioDAO.insertarUsuario(usuario);
                if (idUsuario == -1) {
                    throw new SQLException("No se pudo crear el usuario");
                }
                usuario.setIdUsuario(idUsuario);
            }

            List<Carrito> items = carritoDAO.getCartBySession(sessionId);
            if (items == null || items.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Carrito vacío");
                return;
            }

            double subtotal = items.stream()
                    .mapToDouble(item -> item.getLlanta().getPrecio() * item.getCantidad())
                    .sum();
            double iva = subtotal * 0.16;
            double total = subtotal + iva;

            // Crear objeto venta
            Venta venta = new Venta(
                    0,
                    generarCodigoVenta(),
                    usuario.getIdUsuario(),
                    sessionId,
                    idSucursal,
                    fechaCita,
                    null,
                    "transferencia",
                    null,
                    "pendiente",
                    total, // Aquí ya va con IVA incluido
                    new Timestamp(System.currentTimeMillis())
            );

            // Guardar venta en la base de datos
            int idVenta = ventaDAO.guardarVenta(venta);
            if (idVenta == -1) {
                throw new SQLException("No se pudo crear la venta");
            }

            // Crear y guardar detalles de venta
            List<DetalleVenta> detalles = new ArrayList<>();
            for (Carrito item : items) {
                DetalleVenta detalle = new DetalleVenta();
                detalle.setIdVenta(idVenta);
                detalle.setIdLlanta(item.getLlanta().getIdLlanta());
                detalle.setCantidad(item.getCantidad());
                detalle.setPrecioUnitario(item.getLlanta().getPrecio());
                detalles.add(detalle);
            }

            if (!ventaDAO.guardarDetalles(idVenta, detalles)) {
                throw new SQLException("Error al guardar detalles de venta");
            }

            // Limpiar carrito y redirigir al servlet de confirmación
            carritoDAO.clearCart(sessionId);
            response.sendRedirect("SvConfirmacion?codigo=" + venta.getCodigoVenta());

        } catch (ParseException e) {
            logger.log(Level.WARNING, "Formato de fecha inválido", e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Formato de fecha inválido");
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, "ID de sucursal inválido", e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Sucursal inválida");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error de base de datos", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private String generarCodigoVenta() {
        return "GRP-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    private String getSessionIdFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("gripsterSessionId".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
