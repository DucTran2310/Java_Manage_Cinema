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
import model.PhongChieu;

/**
 *
 * @author ASUS
 */
public class PhongChieuDAO {
    private ConnectDB connect;//doi tuong connect to databasse
    private PreparedStatement ps;//cau lenh truy van
    private ResultSet rs;//luu ket qua truy van
    
    public PhongChieuDAO(){
        connect = new ConnectDB();
    }
    
    //ObservableList là 1 tập hợp các đối tượng để theo dõi khi có sự thay đổi xảy ra trên tập hợp.
    public ObservableList<PhongChieu> selectAll() throws SQLException{
        
        ObservableList listPhongChieu = FXCollections.observableArrayList();
        try{
            connect.openConnect();
            String sql = "select * from PhongChieu";
            //Sử dụng để truy vấn các SQL động hoặc có tham số
            ps = connect.getConnect().prepareCall(sql);
            //Trả về một đối tượng ResultSet khi bạn thực thi câu lệnh SELECT.
            rs = ps.executeQuery();
            PhongChieu pc;
            while(rs.next()){
                pc = new PhongChieu(rs.getInt("MaPhong"), rs.getString("TenPhong"),  rs.getInt("SoGheNgang"),
                        rs.getInt("SoGheDoc"));
                listPhongChieu.add(pc);
            }
        }catch(SQLException e){
            //printStack sẽ giúp phát hiện phương thức nào gây ra lỗi
            e.printStackTrace();
        }finally{
            connect.closeConnect();
        }
        return listPhongChieu;
    }
    
    public PhongChieu getPhongByMaPhong(int maPhong) throws SQLException {
         try{
            String sql = "select * from phongchieu where MaPhong = ?";
            connect.openConnect();
            ps = connect.getConnect().prepareCall(sql);
            ps.setInt(1, maPhong);
            rs = ps.executeQuery();
            
            PhongChieu phongChieu;
            if(rs.next()){
                phongChieu = new PhongChieu(rs.getInt("MaPhong"), rs.getString("TenPhong"),  rs.getInt("SoGheNgang"),
                        rs.getInt("SoGheDoc"));
             return phongChieu;
         }
                    
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            connect.closeConnect();
        }
        return null;
    }
    
    public int getMaPhongbyTenPhong(String tenPhong) throws SQLException{
        int maPhong = 0;
        try {
            String sql = "select maphong from phongchieu where tenphong = ?";
            connect.openConnect();
            ps = connect.getConnect().prepareCall(sql);
            ps.setString(1, tenPhong);
            rs = ps.executeQuery();
            
            if(rs.next()){
                maPhong = rs.getInt("maphong");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            connect.closeConnect();
        }
        return maPhong;
    }
    
    public ObservableList<String> getTenPhongChieu() throws SQLException{
        ObservableList<String> listTenPhongChieu = FXCollections.observableArrayList();
        try {
            String sql = "select tenphong from phongchieu";
            connect.openConnect();
            ps = connect.getConnect().prepareCall(sql);
            rs = ps.executeQuery();
            
            String tenphim = null;
            while (rs.next()){
                tenphim = rs.getString("tenphong");
                listTenPhongChieu.add(tenphim);
            }
        }catch(SQLException e){
            
        }finally{
            connect.closeConnect();
        }
        return listTenPhongChieu;
    }
}
