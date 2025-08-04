package com.gripster.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Utility class for testing database connectivity.
 * It verifies if the connection pool works and if a basic query can be executed.
 */
public class DatabaseConnectionTester {

    public static void main(String[] args) {
        testDatabaseConnection();
    }

    public static void testDatabaseConnection() {
        String query = "SELECT COUNT(*) AS total_tires FROM Llantas";

        // Try-with-resources: automatically closes resources
        try (
            Connection connection = DatabaseConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery()
        ) {
            System.out.println("✅ Successfully connected to the database.");

            if (resultSet.next()) {
                int totalTires = resultSet.getInt("total_tires");
                System.out.println("📦 Total tires in the 'Llantas' table: " + totalTires);
            }

        } catch (SQLException e) {
            System.err.println("❌ SQL error while testing connection: " + e.getMessage());
        } catch (RuntimeException e) {
            System.err.println("❌ Runtime error (possibly connection pool issue): " + e.getMessage());
        } finally {
            // Not required to manually close resources due to try-with-resources
            DatabaseConnectionManager.closeAllConnections(); // Optional: for manual shutdown tests
        }
    }
}
