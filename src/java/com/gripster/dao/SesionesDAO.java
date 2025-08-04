package com.gripster.dao;

import com.gripster.connection.DatabaseConnectionManager;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SesionesDAO {
    private static final Logger logger = Logger.getLogger(SesionesDAO.class.getName());

    public boolean createSession(String idSesion) {
        String sql = "INSERT INTO Sesiones (id_sesion) VALUES (?)";
        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idSesion);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al crear sesión", e);
            return false;
        }
    }

    public boolean sessionExists(String idSesion) {
        String sql = "SELECT 1 FROM Sesiones WHERE id_sesion = ?";
        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idSesion);
            return stmt.executeQuery().next();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al verificar sesión", e);
            return false;
        }
    }

    public boolean deleteSession(String idSesion) {
        String sql = "DELETE FROM Sesiones WHERE id_sesion = ?";
        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idSesion);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al eliminar sesión", e);
            return false;
        }
    }
}