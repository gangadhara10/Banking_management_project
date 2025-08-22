package banking_management_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JavaConnect {

    public static Connection ConnectDb() throws ClassNotFoundException, SQLException {
        // Load MySQL JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // MySQL connection URL
        String url = "jdbc:mysql://localhost:3306/banking_accounts"; // database name = bank
        String user = "root"; // MySQL username
        String password = "123456"; // MySQL password

        // Return the connection
        return DriverManager.getConnection(url, user, password);
    }
}