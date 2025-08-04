package com.gripster.servlets;

import com.gripster.dao.SesionesDAO;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "SvCookieSession", urlPatterns = {"/SvCookieSession"})
public class SvCookieSession extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession httpSession = request.getSession(true);
        SesionesDAO sesionesDAO = new SesionesDAO();
        String sessionId = null;
        Cookie[] cookies = request.getCookies();

        // Buscar cookie existente
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("gripsterSessionId".equals(cookie.getName())) {
                    sessionId = cookie.getValue();
                    break;
                }
            }
        }

        // Validar o crear sesión
        if (sessionId == null || !sesionesDAO.sessionExists(sessionId)) {
            sessionId = UUID.randomUUID().toString();
            sesionesDAO.createSession(sessionId); // Guardar en BD
            
            // Crear cookie
            Cookie newCookie = new Cookie("gripsterSessionId", sessionId);
            newCookie.setPath("/");
            newCookie.setMaxAge(3 * 24 * 60 * 60); // 3 días (coincide con BD)
            response.addCookie(newCookie);
            
            // Vincular sesión HTTP con BD
            httpSession.setAttribute("sessionId", sessionId);
        }

        response.setContentType("application/json");
        response.getWriter().write("{\"sessionId\": \"" + sessionId + "\"}");
    }
}