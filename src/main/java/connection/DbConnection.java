package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static DbConnection instance;
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/parserdb";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    private DbConnection() {
        try{
            Class.forName(DRIVER);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static Connection getConnection()throws SQLException {
        if(instance == null){
            instance = new DbConnection();
        }
        try {
            return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        }catch (SQLException e){
            throw e;
        }
    }
    public static void close(Connection connection){
        try {
            if(connection != null){
                connection.close();
                connection = null;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
