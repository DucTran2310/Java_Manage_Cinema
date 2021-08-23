/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ASUS
 */
public class DBConnectionFactory {
    private static final String DATABASE_NAME = "cinema";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/" + DATABASE_NAME + "?serverTimezone=UTC&useSSL=false";
    private static final String DATABASE_USER_NAME = "root";
    private static final String DATABASE_PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER_NAME, DATABASE_PASSWORD);
        return conn;
    }
    
}
