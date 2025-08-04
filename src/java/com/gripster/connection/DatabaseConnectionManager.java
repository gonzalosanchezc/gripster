package com.gripster.connection;

import java.lang.reflect.*;
import java.sql.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class DatabaseConnectionManager {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/gripster_llantas";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "123456789";
    private static final int MAX_POOL_SIZE = 10; //Number of connections available
    private static final long CONNECTION_TIMEOUT_SECONDS = 5;
    private static final ArrayBlockingQueue<Connection> CONNECTION_POOL = new ArrayBlockingQueue<>(MAX_POOL_SIZE);

    static {
        initializeConnectionPool();
    }

    private static void initializeConnectionPool() 
    {
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            for (int i = 0; i < MAX_POOL_SIZE; i++) 
            {
                CONNECTION_POOL.add(createNewConnection());
            }
            Runtime.getRuntime().addShutdownHook(new Thread(DatabaseConnectionManager::closeAllConnections));
        } catch (ClassNotFoundException | SQLException e) 
        {
            throw new RuntimeException("Failed to initialize connection pool", e);
        }
    }

    private static Connection createNewConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public static Connection getConnection() {
        try {
            Connection rawConnection = CONNECTION_POOL.poll(CONNECTION_TIMEOUT_SECONDS, TimeUnit.SECONDS);
            if (rawConnection == null) {
                throw new RuntimeException("No available connections in the pool.");
            }

            if (!rawConnection.isValid(2)) {
                rawConnection.close();
                rawConnection = createNewConnection();
            }

            return wrapConnection(rawConnection);
        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException("Error retrieving database connection", e);
        }
    }

    private static Connection wrapConnection(final Connection connection) {
        return (Connection) Proxy.newProxyInstance(
                DatabaseConnectionManager.class.getClassLoader(),
                new Class[]{Connection.class},
                new InvocationHandler() {
            private boolean closed = false;

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if ("close".equals(method.getName())) {
                    if (!closed) {
                        closed = true;
                        releaseConnection(connection);
                    }
                    return null;
                }
                if ("isClosed".equals(method.getName())) {
                    return closed;
                }
                if (closed) {
                    throw new SQLException("Connection is already closed.");
                }
                return method.invoke(connection, args);
            }
        });
    }

    public static void releaseConnection(Connection connection) {
        try {
            if (connection != null) {
                if (!connection.isClosed() && connection.isValid(2)) {
                    CONNECTION_POOL.offer(connection);
                } else {
                    connection.close();
                    CONNECTION_POOL.offer(createNewConnection());
                }
            }
        } catch (SQLException e) {
            System.err.println("Error returning connection to pool: " + e.getMessage());
        }
    }

    public static void closeAllConnections() {
        CONNECTION_POOL.forEach(conn -> {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        });
        CONNECTION_POOL.clear();
    }
}
