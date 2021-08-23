/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;

/**
 *
 * @author ASUS
 */
public class TestConnect {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        
        ConnectDB connect = new ConnectDB();
        if (connect.openConnect()){
            System.out.println("Connected");
        }
        connect.closeConnect();
    }
    
}
