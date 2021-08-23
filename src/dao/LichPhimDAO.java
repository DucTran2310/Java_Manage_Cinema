/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.LichChieuPhim;
import model.LichChieuPhim_demo;



/**
 *
 * @author ASUS
 */
public class LichPhimDAO {
    private ConnectDB connect;//doi tuong connect to databasse
    private PreparedStatement ps;//cau lenh truy van
    private ResultSet rs;//luu ket qua truy van
    
    public LichPhimDAO(){
        connect = new ConnectDB();
    }
    
    //ObservableList là 1 tập hợp các đối tượng để theo dõi khi có sự thay đổi xảy ra trên tập hợp.
    public ObservableList<LichChieuPhim_demo> selectAll() throws SQLException{
        
        ObservableList<LichChieuPhim_demo> listLichChieu = FXCollections.observableArrayList();
        try{
            connect.openConnect();
            String sql = "SELECT malichphim, tenphim, tenphong, ngaychieu, thoigianbatdau, thoigianketthuc from lichphim join phongchieu using (maphong) join phim using (maphim)";
            //Sử dụng để truy vấn các SQL động hoặc có tham số
            ps = connect.getConnect().prepareCall(sql);
            //Trả về một đối tượng ResultSet khi bạn thực thi câu lệnh SELECT.
            rs = ps.executeQuery();
            LichChieuPhim_demo lcp;
            while(rs.next()){
                lcp = new LichChieuPhim_demo(rs.getInt("MaLichPhim"),rs.getString("tenphim"),rs.getString("tenphong"),0,0,rs.getDate("NgayChieu"), rs.getString("ThoiGianBatDau"), rs.getString("ThoiGianKetThuc"));
                listLichChieu.add(lcp);
            }
        }catch(SQLException e){
            //printStack sẽ giúp phát hiện phương thức nào gây ra lỗi
            e.printStackTrace();
        }finally{
            connect.closeConnect();
        }
        return listLichChieu;
    }
    //SELECT MaLichPhim FROM cinema.lichphim where (MaPhong = 1 and  ThoiGianBatDau = "18:30:00");
    public LichChieuPhim getLichPhimbyMaPhim(int maPhim) {
        try {
            String sql = "select * from lichphim where MaPhim = ?";
            connect.openConnect();
            ps = connect.getConnect().prepareCall(sql);
            ps.setInt(1, maPhim);
            rs = ps.executeQuery();
            
            LichChieuPhim lichchieuphim;
            if(rs.next()){
                lichchieuphim = new LichChieuPhim(rs.getInt("MaLichPhim"), rs.getInt("MaPhim"),rs.getInt("MaPhong"), rs.getDate("NgayChieu"), rs.getString("ThoiGianBatDau"), rs.getString("ThoiGianKetThuc"));
                return lichchieuphim;
            }
            
        } catch (SQLException e) {
        }
        return null;
    }
    //
     public int getLichPhimbyMaPhongAndThoiGianBatDau(int maPhong,String thoiGianBatDau) {
        try {
            String sql = "SELECT MaLichPhim FROM cinema.lichphim where (MaPhong = ? and  ThoiGianBatDau = ?)";
            connect.openConnect();
            ps = connect.getConnect().prepareCall(sql);
            ps.setInt(1, maPhong);
            ps.setString(2, thoiGianBatDau);
            rs = ps.executeQuery();
            
            LichChieuPhim lichchieuphim;
            if(rs.next()){
                int malichphim = rs.getInt("MaLichPhim");
                return malichphim;
            }
            
        } catch (SQLException e) {
        }
        return -1;
    }
    
    public void insertLichPhim (LichChieuPhim_demo lichchieuphim) throws SQLException{
        try{
            String sql = "INSERT INTO lichphim (MaPhim, MaPhong, NgayChieu, ThoiGianBatDau, ThoiGianKetThuc) VALUES (?,?,?,?,?)";
            connect.openConnect();
            ps = connect.getConnect().prepareCall(sql);
            ps.setInt(1, lichchieuphim.getMaPhim());
            ps.setInt(2, lichchieuphim.getMaPhong());
            ps.setDate(3,lichchieuphim.getNgayChieu());
            ps.setString(4, lichchieuphim.getThoiGianBatDau());
            ps.setString(5, lichchieuphim.getThoiGianKetThuc());
            ps.executeUpdate();//dung cho CRUD
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            connect.closeConnect();
        }
    }
    
    public void updateLichPhim (int maPhim, int maPhong, Date ngayChieu, Time thoiGianBatDau, Time thoiGianKetThuc, int maLichPhim) throws SQLException{
        try{
            String sql = "UPDATE lichphim SET Maphim = ?, MaPhong = ?, NgayChieu = ?, ThoiGianBatDau = ?, ThoiGianKetThuc = ? WHERE MaLichPhim = ?";
            connect.openConnect();
            ps = connect.getConnect().prepareCall(sql);
            ps.setInt(1, maPhim);
            ps.setInt(2, maPhong);
            ps.setDate(3, ngayChieu);
            ps.setTime(4, thoiGianBatDau);
            ps.setTime(5, thoiGianKetThuc);
            ps.setInt(6, maLichPhim);
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            connect.closeConnect();
        }
    }
    
    public void deleteLichPhim (LichChieuPhim_demo lichphim) throws SQLException{
        try{
            String sql = "DELETE FROM lichphim WHERE MaLichPhim = ?";
            connect.openConnect();
            ps = connect.getConnect().prepareCall(sql);
            ps.setInt(1, lichphim.getMaLichPhim());
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            connect.closeConnect();
        }
    }
    
    
}
