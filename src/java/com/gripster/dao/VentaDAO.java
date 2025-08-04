package com.gripster.dao;

import com.gripster.connection.DatabaseConnectionManager;
import com.gripster.model.Venta;
import com.gripster.model.DetalleVenta;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VentaDAO {

    public int guardarVenta(Venta venta) {
        String sql = "INSERT INTO Ventas (codigo_venta, id_usuario, id_sesion, id_sucursal, fecha_cita, hora_cita, metodo_pago, comprobante_pago, estado, total) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, venta.getCodigoVenta());
            stmt.setInt(2, venta.getIdUsuario());
            stmt.setString(3, venta.getIdSesion());
            stmt.setInt(4, venta.getIdSucursal());
            stmt.setDate(5, venta.getFechaCita());
            stmt.setTime(6, venta.getHoraCita());
            stmt.setString(7, venta.getMetodoPago());
            stmt.setString(8, venta.getComprobantePago());
            stmt.setString(9, venta.getEstado());
            stmt.setDouble(10, venta.getTotal());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean guardarDetalles(int idVenta, List<DetalleVenta> detalles) {
        String sql = "INSERT INTO Detalle_Venta (id_venta, id_llanta, cantidad, precio_unitario) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (DetalleVenta d : detalles) {
                stmt.setInt(1, idVenta);
                stmt.setInt(2, d.getIdLlanta());
                stmt.setInt(3, d.getCantidad());
                stmt.setDouble(4, d.getPrecioUnitario());
                stmt.addBatch();
            }

            int[] result = stmt.executeBatch();
            return result.length == detalles.size();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Venta obtenerVentaPorCodigo(String codigoVenta) {
        String sql = "SELECT * FROM Ventas WHERE codigo_venta = ?";
        
        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, codigoVenta);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Venta venta = new Venta();
                venta.setIdVenta(rs.getInt("id_venta"));
                venta.setCodigoVenta(rs.getString("codigo_venta"));
                venta.setIdUsuario(rs.getInt("id_usuario"));
                venta.setIdSesion(rs.getString("id_sesion"));
                venta.setIdSucursal(rs.getInt("id_sucursal"));
                venta.setFechaCita(rs.getDate("fecha_cita"));
                venta.setHoraCita(rs.getTime("hora_cita"));
                venta.setMetodoPago(rs.getString("metodo_pago"));
                venta.setComprobantePago(rs.getString("comprobante_pago"));
                venta.setEstado(rs.getString("estado"));
                venta.setTotal(rs.getDouble("total"));
                venta.setFechaRegistro(rs.getTimestamp("fecha_registro"));
                
                return venta;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<DetalleVenta> obtenerDetallesVenta(int idVenta) {
        String sql = "SELECT * FROM Detalle_Venta WHERE id_venta = ?";
        
        List<DetalleVenta> detalles = new ArrayList<>();
        
        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idVenta);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                DetalleVenta detalle = new DetalleVenta();
                detalle.setIdDetalle(rs.getInt("id_detalle"));
                detalle.setIdVenta(rs.getInt("id_venta"));
                detalle.setIdLlanta(rs.getInt("id_llanta"));
                detalle.setCantidad(rs.getInt("cantidad"));
                detalle.setPrecioUnitario(rs.getDouble("precio_unitario"));
                
                detalles.add(detalle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detalles;
    }
}