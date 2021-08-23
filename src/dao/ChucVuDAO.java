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
import model.ChucVu;

/**
 *
 * @author ASUS
 */
public class ChucVuDAO {
    private ConnectDB connect;//doi tuong connect to databasse
    private PreparedStatement ps;//cau lenh truy van
    private ResultSet rs;//luu ket qua truy van
    
    public ChucVuDAO(){
        connect = new ConnectDB();
    }
    
    //ObservableList là 1 tập hợp các đối tượng để theo dõi khi có sự thay đổi xảy ra trên tập hợp.
    public ObservableList<ChucVu> selectAll() throws SQLException{
        
        ObservableList<ChucVu> listLichChieu = FXCollections.observableArrayList();
        try{
            connect.openConnect();
            String sql = "SELECT * from chucvu ";
            //Sử dụng để truy vấn các SQL động hoặc có tham số
            ps = connect.getConnect().prepareCall(sql);
            //Trả về một đối tượng ResultSet khi bạn thực thi câu lệnh SELECT.
            rs = ps.executeQuery();
            ChucVu cv;
            while(rs.next()){
                cv = new ChucVu(rs.getInt("MaChucVu"),rs.getString("TenChucVU"));
                listLichChieu.add(cv);
            }
        }catch(SQLException e){
            //printStack sẽ giúp phát hiện phương thức nào gây ra lỗi
            e.printStackTrace();
        }finally{
            connect.closeConnect();
        }
        return listLichChieu;
    }
    
    
}
