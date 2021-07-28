package DataBaseConnection;

import CustomException.ExceptionHandler;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {

    private static final int INIT_POOL_SIZE = 16;
    private static final int MAX_TIMEOUT = 2;

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/employeeControl";
    private static final String USER = "postgres";
    private static final String PASS = "rty456";

    private final List<Connection> connectionPool;
    private final List<Connection> usedConnections = new ArrayList<>();


    @SneakyThrows
    public static ConnectionPool create() {
        try {
            List<Connection> pool = new ArrayList<>(INIT_POOL_SIZE);
            for (int i = 0; i < INIT_POOL_SIZE; i++) {
                pool.add(createConnection());
            }
            return new ConnectionPool(pool);

        } catch (Exception e) {
            throw new ExceptionHandler("The class in which the error was flown: " + ConnectionPool.class + ". In create method.",new IllegalStateException());
        }
    }

    private ConnectionPool(List<Connection> connectionPool) {
        this.connectionPool = connectionPool;
    }


    public Connection getConnection() throws SQLException, ExceptionHandler {
        if (connectionPool.isEmpty()) {
            if (usedConnections.size() < INIT_POOL_SIZE) {
                connectionPool.add(createConnection());
            } else {
                throw new ExceptionHandler("The class in which the error was flown: " + ConnectionPool.class + ". Reached max poll size",new RuntimeException());
            }
        }
        Connection connection = connectionPool.remove(connectionPool.size() - 1);
        if (!connection.isValid(MAX_TIMEOUT)) {
            connection = createConnection();
        }
        usedConnections.add(connection);
        return connection;
    }

    public boolean closeConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }

    private static Connection createConnection()
            throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public void shutdown() throws SQLException {

        usedConnections.forEach(this::closeConnection);
        for (Connection c : connectionPool)
            c.close();

        connectionPool.clear();
    }
}