package com.gripster.servlets;

import com.gripster.dao.CarritoDAO;
import com.gripster.dao.SesionesDAO;
import com.gripster.model.Carrito;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SvCarrito", urlPatterns = {"/SvCarrito"})
public class SvCarrito extends HttpServlet {
    private final CarritoDAO carritoDAO = new CarritoDAO();
    private final SesionesDAO sesionesDAO = new SesionesDAO();

    // Maneja peticiones POST (agregar, actualizar, eliminar del carrito)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String sessionId = getSessionIdFromCookie(request);
        if (sessionId == null || !sesionesDAO.sessionExists(sessionId)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Sesión inválida");
            return;
        }

        String action = request.getParameter("action");
        String tireIdParam = request.getParameter("tireId");
        String quantityParam = request.getParameter("quantity");

        if (action == null || tireIdParam == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parámetros faltantes");
            return;
        }

        try {
            int tireId = Integer.parseInt(tireIdParam);
            int quantity = (quantityParam != null) ? Integer.parseInt(quantityParam) : 1;

            boolean success = switch (action) {
                case "add" -> carritoDAO.addToCart(sessionId, tireId, quantity);
                case "update" -> carritoDAO.updateQuantity(sessionId, tireId, quantity);
                case "remove" -> carritoDAO.removeFromCart(sessionId, tireId);
                case "clear" -> carritoDAO.clearCart(sessionId);
                default -> false;
            };

            if (success) {
                response.sendRedirect("SvCarrito"); // Redirigir a GET para mostrar carrito actualizado
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error en el carrito");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido");
        }
    }

    // Maneja peticiones GET (mostrar contenido del carrito)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String sessionId = getSessionIdFromCookie(request);
        if (sessionId == null || !sesionesDAO.sessionExists(sessionId)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Sesión inválida");
            return;
        }

        // Obtener los ítems del carrito desde la base de datos
        List<Carrito> cartItems = carritoDAO.getCartBySession(sessionId);

        // Enviar los ítems al JSP
        request.setAttribute("cartItems", cartItems);

        // Mostrar la vista del carrito
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }

    // Extraer el sessionId de la cookie personalizada
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
