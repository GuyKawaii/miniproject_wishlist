package com.example.miniproject_wishlist.Repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    private static String hostname;

    private static String username;

    private static String password;

    private static Connection conn;

    public static Connection getConn() {
        if(conn != null){
            return conn;
        }
        hostname = "jdbc:mysql://alberto.mysql.database.azure.com :3306/kea";
        username = "Workswell";
        password = "Th1sBetterW0rk!";
        try {
            conn = DriverManager.getConnection(hostname, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
