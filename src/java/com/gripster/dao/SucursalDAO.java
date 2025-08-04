package com.gripster.dao;

import com.gripster.connection.DatabaseConnectionManager;
import com.gripster.model.Sucursal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SucursalDAO {

    public List<Sucursal> obtenerTodas() {
        List<Sucursal> sucursales = new ArrayList<>();
        String sql = "SELECT * FROM Sucursales";

        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Sucursal s = new Sucursal(
                    rs.getInt("id_sucursal"),
                    rs.getString("nombre"),
                    rs.getString("direccion"),
                    rs.getString("colonia"),
                    rs.getString("alcaldia"),
                    rs.getString("telefono")
                );
                sucursales.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sucursales;
    }

    public Sucursal obtenerSucursalPorId(int idSucursal) {
        String sql = "SELECT * FROM Sucursales WHERE id_sucursal = ?";

        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idSucursal);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Sucursal(
                    rs.getInt("id_sucursal"),
                    rs.getString("nombre"),
                    rs.getString("direccion"),
                    rs.getString("colonia"),
                    rs.getString("alcaldia"),
                    rs.getString("telefono")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}