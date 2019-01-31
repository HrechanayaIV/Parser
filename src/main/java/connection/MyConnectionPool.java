package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class MyConnectionPool {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/parserdb";
    private static final String USER = "root";
    private static final String PASSWORD = "password";
    public static final int DEFAULT_POOL_SIZE = 5;
    private static MyConnectionPool instance = null;

    private static BlockingQueue<Connection> connectionQueue;

    private MyConnectionPool() throws SQLException {
        try{
            Class.forName(DRIVER);
        }catch (Exception e){
            e.printStackTrace();
        }
        connectionQueue = new ArrayBlockingQueue<>(DEFAULT_POOL_SIZE);

        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            connectionQueue.offer(connection);
        }
    }

    //public static void init() throws SQLException {
    //    if (instance == null) {
    //        instance = new MyConnectionPool();
    //    }
    //}

    public static void dispose() throws SQLException {
        if (instance != null) {
            instance.clearConnectionQueue();
            instance = null;
        }
    }

    public static MyConnectionPool getInstance() throws SQLException {
        if (instance == null) {
            instance = new MyConnectionPool();
        }
        return instance;
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = connectionQueue.take();
        } catch (InterruptedException e) {

        }
        return connection;
    }

    public void closeConnection(Connection connection) {
        try {
            if (!connection.isClosed()) {
                if (!connectionQueue.offer(connection)) {
                }
            }
        } catch (SQLException e) {
        }
    }

    private void clearConnectionQueue() throws SQLException {
        Connection connection;
        while ((connection = connectionQueue.poll()) != null) {
            if (!connection.getAutoCommit()) {
                connection.commit();
            }
            connection.close();
        }
    }
}