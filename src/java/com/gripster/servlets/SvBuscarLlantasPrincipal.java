package com.gripster.servlets;

import com.gripster.dao.LlantasDAO;
import com.gripster.model.Llanta;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet para manejar la búsqueda de llantas.
 */
@WebServlet("/SvBuscarLlantasPrincipal")
public class SvBuscarLlantasPrincipal extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int width = Integer.parseInt(request.getParameter("ancho"));
            int profile = Integer.parseInt(request.getParameter("perfil"));
            int diameter = Integer.parseInt(request.getParameter("diametro"));

            LlantasDAO tireDAO = new LlantasDAO();
            List<Llanta> tires = tireDAO.searchTires(width, profile, diameter);

            request.setAttribute("width", width);
            request.setAttribute("profile", profile);
            request.setAttribute("diameter", diameter);

            request.setAttribute("tires", tires);

            request.getRequestDispatcher("resultados_busqueda.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendRedirect("index.html?error=Datos inválidos");
        }
    }
}