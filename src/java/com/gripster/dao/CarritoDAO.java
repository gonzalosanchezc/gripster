/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.gripster.dao;

import com.gripster.connection.DatabaseConnectionManager;
import com.gripster.model.Carrito;
import com.gripster.model.Llanta;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jobog
 */
public class CarritoDAO {

    // Add a tire to the cart or increase quantity if it already exists
    public boolean addToCart(String sessionId, int tireId, int quantity) 
    {
    //System.out.println("==> [DAO] addToCart: sessionId=" + sessionId + ", tireId=" + tireId + ", quantity=" + quantity);
    
    String checkSql = "SELECT cantidad FROM carrito WHERE id_sesion = ? AND id_llanta = ?";
    String updateSql = "UPDATE Carrito SET cantidad = cantidad + ? WHERE id_sesion = ? AND id_llanta = ?";
    String insertSql = "INSERT INTO Carrito (id_sesion, id_llanta, cantidad) VALUES (?, ?, ?)";

    try (Connection conn = DatabaseConnectionManager.getConnection()) {
        PreparedStatement checkStmt = conn.prepareStatement(checkSql);
        checkStmt.setString(1, sessionId);
        checkStmt.setInt(2, tireId);
        ResultSet rs = checkStmt.executeQuery();

        if (rs.next()) {
            System.out.println("📝 Llanta ya en el carrito. Actualizando cantidad...");
            try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                updateStmt.setInt(1, quantity);
                updateStmt.setString(2, sessionId);
                updateStmt.setInt(3, tireId);
                int rowsUpdated = updateStmt.executeUpdate();
                System.out.println("✅ Filas actualizadas: " + rowsUpdated);
                return rowsUpdated > 0;
            }
        } else {
            System.out.println("🆕 Llanta no está en el carrito. Insertando nueva...");
            try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                insertStmt.setString(1, sessionId);
                insertStmt.setInt(2, tireId);
                insertStmt.setInt(3, quantity);
                int rowsInserted = insertStmt.executeUpdate();
                System.out.println("✅ Filas insertadas: " + rowsInserted);
                return rowsInserted > 0;
            }
        }
    } catch (SQLException e) {
        System.out.println("❌ Error en addToCart:");
        e.printStackTrace();
        return false;
    }
}


    // Retrieve all cart items for a given session
    public List<Carrito> getCartBySession(String sessionId) {
    List<Carrito> cartItems = new ArrayList<>();
    String sql = "SELECT c.*, l.*, m.nombre AS marca_nombre, mo.nombre AS modelo_nombre " +
                 "FROM Carrito c " +
                 "JOIN Llantas l ON c.id_llanta = l.id_llanta " +
                 "JOIN Marcas m ON l.id_marca = m.id_marca " +
                 "JOIN Modelos mo ON l.id_modelo = mo.id_modelo " +
                 "WHERE c.id_sesion = ?";
    // Resto del código igual...


    try (Connection conn = DatabaseConnectionManager.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setString(1, sessionId);
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                // Crear objeto Llanta con todos los datos
                Llanta llanta = new Llanta(
                    rs.getInt("l.id_llanta"),
                    rs.getInt("l.id_tipo"),
                    rs.getInt("l.id_categoria"),
                    rs.getString("marca_nombre"),
                    rs.getString("modelo_nombre"),
                    rs.getInt("l.ancho"),
                    rs.getInt("l.perfil"),
                    rs.getInt("l.diametro"),
                    rs.getDouble("l.precio"),
                    rs.getInt("l.stock"),
                    rs.getString("l.imagen")
                );
                
                // Crear objeto Carrito con la Llanta asociada
                Carrito item = new Carrito(
                    rs.getInt("c.id_carrito"),
                    rs.getString("c.id_sesion"),
                    llanta,
                    rs.getInt("c.cantidad"),
                    rs.getTimestamp("c.fecha_agregado")
                );
                cartItems.add(item);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return cartItems;
}

    // Update the quantity of a cart item
    public boolean updateQuantity(String sessionId, int tireId, int newQuantity) 
    {
        String sql = "UPDATE Carrito SET cantidad = ? WHERE id_sesion = ? AND id_llanta = ?";

        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) 
        {

            stmt.setInt(1, newQuantity);
            stmt.setString(2, sessionId);
            stmt.setInt(3, tireId);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Remove an item from the cart
    public boolean removeFromCart(String sessionId, int tireId) 
    {
        String sql = "DELETE FROM Carrito WHERE id_sesion = ? AND id_llanta = ?";

        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, sessionId);
            stmt.setInt(2, tireId);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Clear the entire cart for a session
    public boolean clearCart(String sessionId) {
        String sql = "DELETE FROM Carrito WHERE id_sesion = ?";

        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, sessionId);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}