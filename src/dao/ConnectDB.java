/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ASUS
 */
public class ConnectDB {

    private Connection connect;

    public ConnectDB() {
        connect = null;
    }

    public boolean openConnect() throws SQLException {
        if (connect == null) {
            System.setProperty("com.mysql.jdbc.Driver", "");
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema?characterEncoding=UTF-8", "root", "");
            return true;
        }
        return false;
    }

    public void closeConnect() throws SQLException {
        if (connect != null) {
            connect.close();
            connect = null;
        }
    }

    public Connection getConnect() {
        return connect;
    }

    public void setConnect(Connection connect) {
        this.connect = connect;
    }
    
    

}
