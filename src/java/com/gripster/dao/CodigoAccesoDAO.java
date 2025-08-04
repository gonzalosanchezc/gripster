package com.gripster.dao;

import com.gripster.model.CodigoAcceso;
import com.gripster.connection.DatabaseConnectionManager;

import java.sql.*;
import java.util.Random;

public class CodigoAccesoDAO {

    // Generar un código aleatorio de 6 dígitos
    public String generarCodigo() {
        Random random = new Random();
        int codigo = 100000 + random.nextInt(900000); // 100000 - 999999
        return String.valueOf(codigo);
    }

    // Insertar un nuevo código
    public boolean insertarCodigo(String correo, String codigo) {
        String sql = "INSERT INTO Codigos_Acceso (correo, codigo) VALUES (?, ?)";
        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, correo);
            stmt.setString(2, codigo);
            return stmt.executeUpdate() == 1;

        } catch (SQLException e) {
            System.err.println("Error al insertar código de acceso: " + e.getMessage());
            return false;
        }
    }

    // Verificar si el código es válido y no expirado
    public boolean verificarCodigo(String correo, String codigo) {
        String sql = "SELECT * FROM Codigos_Acceso WHERE correo = ? AND codigo = ? AND expirado = false";
        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, correo);
            stmt.setString(2, codigo);
            ResultSet rs = stmt.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            System.err.println("Error al verificar código de acceso: " + e.getMessage());
            return false;
        }
    }

    // Marcar un código como expirado
    public void marcarComoExpirado(String correo, String codigo) {
        String sql = "UPDATE Codigos_Acceso SET expirado = true WHERE correo = ? AND codigo = ?";
        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, correo);
            stmt.setString(2, codigo);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al expirar código: " + e.getMessage());
        }
    }

    // Obtener el último código no expirado para un correo
    public CodigoAcceso obtenerUltimoCodigoNoExpirado(String correo) {
        String sql = "SELECT * FROM Codigos_Acceso WHERE correo = ? AND expirado = false ORDER BY fecha_generacion DESC LIMIT 1";
        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, correo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new CodigoAcceso(
                        rs.getInt("id_codigo"),
                        rs.getString("correo"),
                        rs.getString("codigo"),
                        rs.getTimestamp("fecha_generacion"),
                        rs.getBoolean("expirado")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener último código: " + e.getMessage());
        }
        return null;
    }
}
