/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Phim;

/**
 *
 * @author ASUS
 */
public class PhimDAO {
    private final ConnectDB connect;//doi tuong connect to databasse
    private PreparedStatement ps;//cau lenh truy van
    private ResultSet rs;//luu ket qua truy van
    
    public PhimDAO(){
        connect = new ConnectDB();
    }
    
    //ObservableList là 1 tập hợp các đối tượng để theo dõi khi có sự thay đổi xảy ra trên tập hợp.
    public ObservableList<Phim> selectAll() throws SQLException{
        
        ObservableList listPhim = FXCollections.observableArrayList();
        try{
            connect.openConnect();
            String sql = "select * from phim";
            //Sử dụng để truy vấn các SQL động hoặc có tham số
            ps = connect.getConnect().prepareCall(sql);
            //Trả về một đối tượng ResultSet khi bạn thực thi câu lệnh SELECT.
            rs = ps.executeQuery();
            Phim p;
            while(rs.next()){
                p = new Phim(rs.getInt("MaPhim"), rs.getString("TenPhim"), rs.getInt("ThoiLuong"), rs.getString("ThongTinPhim"),
                        rs.getTimestamp("ThoiGianKhoiTao"),rs.getTimestamp("ThoiGianCapNhat"), rs.getString("image"),rs.getInt("MaTL"));
                listPhim.add(p);
            }
        }catch(SQLException e){
            //printStack sẽ giúp phát hiện phương thức nào gây ra lỗi
            e.printStackTrace();
        }finally{
            connect.closeConnect();
        }
        return listPhim;
    }
    
    public void insertPhim (Phim phim) throws SQLException{
        try{
            String sql = "INSERT INTO Phim(TenPhim,ThoiLuong,ThongTinPhim,ThoiGianKhoiTao,ThoiGianCapNhat,MaTL,image) VALUES (?,?,?,?,?,?,?)";
            connect.openConnect();
            ps = connect.getConnect().prepareCall(sql);
            ps.setString(1, phim.getTenPhim());
            ps.setInt(2, phim.getThoiLuong());
            ps.setString(3, phim.getThongTinPhim());
            ps.setTimestamp(4, phim.getThoiGianKhoiTao());
            ps.setTimestamp(5, phim.getThoiGianCapNhat());
            ps.setInt(6, phim.getMaTL());
            ps.setString(7, phim.getImage());
            ps.executeUpdate();//dung cho CRUD
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            connect.closeConnect();
        }
    }
    
    public void updatePhim (String tenPhim, int thoiLuong, String thongTinPhim, Timestamp thoiGianCapNhat, String image, int maPhim) throws SQLException{
        try{
            String sql = "UPDATE phim SET TenPhim = ?, ThoiLuong = ?, ThongTinPhim = ?, ThoiGianCapNhat = ?, image = ?  WHERE MaPhim = ?";
            connect.openConnect();
            ps = connect.getConnect().prepareCall(sql);
            ps.setString(1, tenPhim);
            ps.setInt(2, thoiLuong);
            ps.setString(3, thongTinPhim);
            ps.setTimestamp(4, thoiGianCapNhat);
            ps.setString(5, image);
            ps.setInt(6, maPhim);
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            connect.closeConnect();
        }
    }
    
    public void deletePhim (Phim phim) throws SQLException{
        try{
            String sql = "DELETE FROM phim WHERE MaPhim = ?";
            connect.openConnect();
            ps = connect.getConnect().prepareCall(sql);
            ps.setInt(1, phim.getMaPhim());
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            connect.closeConnect();
        }
    }
    
    public Phim getPhimByMaPhim(int maPhim) throws SQLException {
         try{
            String sql = "select * from phim where MaPhim = ?";
            connect.openConnect();
            ps = connect.getConnect().prepareCall(sql);
            ps.setInt(1, maPhim);
            rs = ps.executeQuery();
            
            Phim phim;
            if(rs.next()){
                phim = new Phim(rs.getInt("MaPhim"), rs.getString("TenPhim"), rs.getInt("ThoiLuong"), rs.getString("ThongTinPhim"),
                        rs.getTimestamp("ThoiGianKhoiTao"),rs.getTimestamp("ThoiGianCapNhat"), rs.getString("image"),rs.getInt("MaTL"));
             return phim;
         }
                    
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            connect.closeConnect();
        }
        return null;
    }
    
    public Phim getTenPhimByMaPhim(int maPhim) throws SQLException {
         try{
            String sql = "select tenphim from phim where MaPhim = ?";
            connect.openConnect();
            ps = connect.getConnect().prepareCall(sql);
            ps.setInt(1, maPhim);
            rs = ps.executeQuery();
            
            Phim phim;
            if(rs.next()){
                phim = new Phim(rs.getInt("MaPhim"), rs.getString("TenPhim"), rs.getInt("ThoiLuong"), rs.getString("ThongTinPhim"),
                        rs.getTimestamp("ThoiGianKhoiTao"),rs.getTimestamp("ThoiGianCapNhat"), rs.getString("image"),rs.getInt("MaTL"));
             return phim;
         }
                    
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            connect.closeConnect();
        }
        return null;
    }
    
    
    
    public int getMaPhimbyTenPhim(String tenPhim) throws SQLException{
        int maPhim =0;
        try {
            String sql = "select maphim from phim where tenphim = ?";
            connect.openConnect();
            ps = connect.getConnect().prepareCall(sql);
            ps.setString(1, tenPhim);
            rs = ps.executeQuery();
            
            if(rs.next()){
                maPhim = rs.getInt("maphim");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            connect.closeConnect();
        }
        return maPhim;
    }
    
    public int getMaTLBytTenTL(String tenTL) throws SQLException{
        int maTL = 0;
        
        try{
            String sql = "select matl from theloai where tentl = ?";
            connect.openConnect();
            ps = connect.getConnect().prepareCall(sql);
            ps.setString(1, tenTL);
            rs = ps.executeQuery();
            if (rs.next()) {
                maTL = rs.getInt("matl");
            }
                                      
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            connect.closeConnect();
        }
        return maTL;
    }
    
    public ObservableList<String> getTenPhim() throws SQLException{
        ObservableList<String> listTenPhim = FXCollections.observableArrayList();
        try {
            String sql = "select tenphim from phim";
            connect.openConnect();
            ps = connect.getConnect().prepareCall(sql);
            rs = ps.executeQuery();
            
            String tenphim = null;
            while (rs.next()){
                tenphim = rs.getString("tenphim");
                listTenPhim.add(tenphim);
            }
        }catch(SQLException e){
            
        }finally{
            connect.closeConnect();
        }
        return listTenPhim;
    }
    
}
