package com.gripster.servlets;

import com.gripster.dao.CodigoAccesoDAO;
import com.gripster.dao.UsuarioDAO;
import com.gripster.model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/SvVerificarCodigo")
public class SvVerificarCodigo extends HttpServlet {

    private final CodigoAccesoDAO codigoDAO = new CodigoAccesoDAO();
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String correo = request.getParameter("correo");
        String codigo = request.getParameter("codigo");

        if (correo == null || codigo == null || correo.isEmpty() || codigo.isEmpty()) {
            request.setAttribute("error", "Correo o código faltante.");
            request.setAttribute("correo", correo);
            request.getRequestDispatcher("verificar_codigo.jsp").forward(request, response);
            return;
        }

        boolean valido = codigoDAO.verificarCodigo(correo, codigo);

        if (!valido) {
            request.setAttribute("error", "Código incorrecto o expirado.");
            request.setAttribute("correo", correo);
            request.getRequestDispatcher("verificar_codigo.jsp").forward(request, response);
            return;
        }

        // Expirar el código para que no se use otra vez
        codigoDAO.marcarComoExpirado(correo, codigo);

        // Obtener usuario
        Usuario usuario = usuarioDAO.obtenerUsuarioPorCorreo(correo);
        if (usuario == null) {
            request.setAttribute("error", "Usuario no encontrado.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        // Guardar usuario en sesión
        HttpSession session = request.getSession(true);
        session.setAttribute("usuarioLogueado", usuario);

        // Redirigir a panel de usuario
        response.sendRedirect("mis_compras.jsp");
    }
}
