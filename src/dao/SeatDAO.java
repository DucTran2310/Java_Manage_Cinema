/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Seat;

/**
 *
 * @author ASUS
 */
public class SeatDAO {
    
    private ConnectDB connect;//doi tuong connect to databasse
    private PreparedStatement ps;//cau lenh truy van
    private ResultSet rs;//luu ket qua truy van
    
    public SeatDAO(){
        connect = new ConnectDB();
    }
     
    
    public ObservableList<Seat> selectAll() throws SQLException{
        
        ObservableList<Seat> listSeats = FXCollections.observableArrayList();
        try{
            connect.openConnect();
            String sql = "SELECT * from seat ";
            //Sử dụng để truy vấn các SQL động hoặc có tham số
            ps = connect.getConnect().prepareCall(sql);
            //Trả về một đối tượng ResultSet khi bạn thực thi câu lệnh SELECT.
            rs = ps.executeQuery();
            Seat seat;
            while(rs.next()){
                seat = new Seat(rs.getInt("seat_id"), rs.getString("seat_name"), rs.getInt("maghe"), rs.getBoolean("is_book"));
                listSeats.add(seat);
            }
        }catch(SQLException e){
            //printStack sẽ giúp phát hiện phương thức nào gây ra lỗi
            e.printStackTrace();
        }finally{
            connect.closeConnect();
        }
        return listSeats;
    }
    
    
}
