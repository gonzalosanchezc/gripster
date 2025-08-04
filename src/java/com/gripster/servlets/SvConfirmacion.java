package com.gripster.servlets;

import com.gripster.dao.VentaDAO;
import com.gripster.dao.SucursalDAO;
import com.gripster.dao.LlantasDAO;
import com.gripster.model.Venta;
import com.gripster.model.DetalleVenta;
import com.gripster.model.Sucursal;
import com.gripster.model.Llanta;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvConfirmacion", urlPatterns = {"/SvConfirmacion"})
public class SvConfirmacion extends HttpServlet {

    private final VentaDAO ventaDAO = new VentaDAO();
    private final SucursalDAO sucursalDAO = new SucursalDAO();
    private final LlantasDAO llantasDAO = new LlantasDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String codigoVenta = request.getParameter("codigo");
        
        if (codigoVenta == null || codigoVenta.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Código de venta no proporcionado");
            return;
        }
        
        // Obtener datos de la venta
        Venta venta = ventaDAO.obtenerVentaPorCodigo(codigoVenta);
        if (venta == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Venta no encontrada");
            return;
        }
        
        // Obtener detalles de la venta
        List<DetalleVenta> detalles = ventaDAO.obtenerDetallesVenta(venta.getIdVenta());
        
        // Obtener datos de la sucursal
        Sucursal sucursal = sucursalDAO.obtenerSucursalPorId(venta.getIdSucursal());
        
        // Obtener información de las llantas
        Map<Integer, Llanta> llantasMap = new HashMap<>();
        for (DetalleVenta detalle : detalles) {
            Llanta llanta = llantasDAO.getLlantaById(detalle.getIdLlanta());
            if (llanta != null) {
                llantasMap.put(detalle.getIdLlanta(), llanta);
            }
        }
        
        // Pasar datos al JSP
        request.setAttribute("venta", venta);
        request.setAttribute("detalles", detalles);
        request.setAttribute("sucursal", sucursal);
        request.setAttribute("llantasMap", llantasMap);
        
        request.getRequestDispatcher("confirmacion.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }
}