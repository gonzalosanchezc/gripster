package com.gripster.dao;

import com.gripster.model.Llanta;
import com.gripster.connection.DatabaseConnectionManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * DAO para manejar operaciones relacionadas con las llantas.
 */
public class LlantasDAO {
    private static final Logger logger = Logger.getLogger(LlantasDAO.class.getName());

    /**
     * Busca llantas por ancho, perfil y diámetro.
     */
    public List<Llanta> searchTires(int width, int profile, int diameter) {
        List<Llanta> tires = new ArrayList<>();
        String query = "SELECT l.*, m.nombre AS marca_nombre, mo.nombre AS modelo_nombre " +
                       "FROM Llantas l " +
                       "JOIN Marcas m ON l.id_marca = m.id_marca " +
                       "JOIN Modelos mo ON l.id_modelo = mo.id_modelo " +
                       "WHERE l.ancho = ? AND l.perfil = ? AND l.diametro = ?";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, width);
            statement.setInt(2, profile);
            statement.setInt(3, diameter);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                tires.add(mapLlanta(resultSet));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al buscar llantas", e);
        }
        return tires;
    }

    /**
     * Busca una llanta por ID.
     */
    public Llanta getLlantaById(int idLlanta) {
        String query = "SELECT l.*, m.nombre AS marca_nombre, mo.nombre AS modelo_nombre " +
                       "FROM Llantas l " +
                       "JOIN Marcas m ON l.id_marca = m.id_marca " +
                       "JOIN Modelos mo ON l.id_modelo = mo.id_modelo " +
                       "WHERE l.id_llanta = ?";
        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idLlanta);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapLlanta(rs);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al obtener llanta por ID", e);
        }
        return null;
    }

    /**
     * Mapea un ResultSet a un objeto Llanta.
     */
    private Llanta mapLlanta(ResultSet rs) throws SQLException {
        return new Llanta(
            rs.getInt("id_llanta"),
            rs.getInt("id_tipo"),
            rs.getInt("id_categoria"),
            rs.getString("marca_nombre"),
            rs.getString("modelo_nombre"),
            rs.getInt("ancho"),
            rs.getInt("perfil"),
            rs.getInt("diametro"),
            rs.getDouble("precio"),
            rs.getInt("stock"),
            rs.getString("imagen")
        );
    }
}
