package util;

import java.sql.*;

public class ConnectionPool2 {

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String dbName = "FoodTruckMania";
        String userName = "nikhil";
        String password = "Uncc2017";
        String hostname = "uncc-anderson.database.windows.net";
        String port = "1433";
        String jdbcUrl = "jdbc:sqlserver://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;
        return DriverManager.getConnection(jdbcUrl);
    }
}