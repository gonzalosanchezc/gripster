package com.gripster.servlets;

import com.gripster.dao.CodigoAccesoDAO;
import com.gripster.dao.UsuarioDAO;
import com.gripster.model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/SvGenerarCodigo")
public class SvGenerarCodigo extends HttpServlet {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final CodigoAccesoDAO codigoDAO = new CodigoAccesoDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String correo = request.getParameter("correo");

        if (correo == null || correo.isEmpty()) {
            request.setAttribute("error", "Debes ingresar tu correo.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        // Validar si el usuario ha hecho compras (existe en base de datos)
        Usuario usuario = usuarioDAO.obtenerUsuarioPorCorreo(correo);
        if (usuario == null) {
            request.setAttribute("error", "Este correo no está registrado.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        // Generar código y guardarlo en la base de datos
        String codigo = codigoDAO.generarCodigo();
        boolean exito = codigoDAO.insertarCodigo(correo, codigo);

        if (!exito) {
            request.setAttribute("error", "Error al generar código. Intenta de nuevo.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            request.setAttribute("correo", correo);
            request.setAttribute("mensaje", "Se ha enviado un código a tu correo. Revisa tu bandeja.");
            request.getRequestDispatcher("verificar_codigo.jsp").forward(request, response);
        }
    }
}
