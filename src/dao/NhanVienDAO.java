/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//DAO là data access object 
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.NhanVien;
import model.NhanVien_demo;
/**
 *
 * @author ASUS
 */
public class NhanVienDAO {
    private ConnectDB connect;//doi tuong connect to databasse
    private PreparedStatement ps;//cau lenh truy van
    private ResultSet rs;//luu ket qua truy van
    
    public NhanVienDAO(){
        connect = new ConnectDB();
    }
    
    //ObservableList là 1 tập hợp các đối tượng để theo dõi khi có sự thay đổi xảy ra trên tập hợp.
    public ObservableList<NhanVien_demo> selectAll() throws SQLException{
        
        ObservableList listNhanVien = FXCollections.observableArrayList();
        try{
            connect.openConnect();
            String sql = "SELECT MaNV,HoTen,Email,SoDienThoai,DiaChi,TenChucVu,role from nhanvien join chucvu using(MaChucVu)";
            //Sử dụng để truy vấn các SQL động hoặc có tham số
            ps = connect.getConnect().prepareCall(sql);
            //Trả về một đối tượng ResultSet khi bạn thực thi câu lệnh SELECT.
            rs = ps.executeQuery();
            NhanVien_demo nv;
            while(rs.next()){
                nv = new NhanVien_demo(rs.getInt("MaNV"), rs.getString("HoTen"),  rs.getString("Email"),
                        rs.getString("SoDienThoai"), rs.getString("TenChucVu"), rs.getString("DiaChi"),
                        0,rs.getBoolean("role"));
                listNhanVien.add(nv);
            }
        }catch(SQLException e){
            //printStack sẽ giúp phát hiện phương thức nào gây ra lỗi
            e.printStackTrace();
        }finally{
            connect.closeConnect();
        }
        return listNhanVien;
    }
    
    public void insertNhanVien (NhanVien_demo nv) throws SQLException{
        try{
            String sql = "INSERT INTO NhanVien (HoTen,Email,SoDienThoai,DiaChi,MaChucVu) VALUES (?,?,?,?,?)";
            connect.openConnect();
            ps = connect.getConnect().prepareCall(sql);
            ps.setString(1, nv.getHoTen());
            ps.setString(2, nv.getEmail());
            ps.setString(3, nv.getSoDienThoai());
            ps.setString(4, nv.getDiaChi());
            ps.setInt(5, nv.getMaChucVu());
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            connect.closeConnect();
        }
    }
    
    public void updatePhim (String hoTen, String email, String soDienThoai, String diaChi, int maChucVu, int maNV) throws SQLException{
        try{
            String sql = "UPDATE nhanvien SET HoTen = ?, Email = ?, SoDienThoai = ?, DiaChi = ?, MaChucVu = ?  WHERE MaNV = ?";
            connect.openConnect();
            ps = connect.getConnect().prepareCall(sql);
            ps.setString(1, hoTen);
            ps.setString(2, email);
            ps.setString(3, soDienThoai);
            ps.setString(4, diaChi);
            ps.setInt(5, maChucVu);
            ps.setInt(6, maNV);
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            connect.closeConnect();
        }
    }
    
     public void updateNhanVien (boolean role, int maNV) throws SQLException{
        try{
            String sql = "UPDATE nhanvien SET role = ? WHERE MaNV = ?";
            connect.openConnect();
            ps = connect.getConnect().prepareCall(sql);
            ps.setBoolean(1, role);
            ps.setInt(2, maNV);
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            connect.closeConnect();
        }
    }
    
    public void deleteNhanVien (NhanVien_demo nhanvien) throws SQLException{
        try{
            String sql = "DELETE FROM nhanvien WHERE MaNV = ?";
            connect.openConnect();
            ps = connect.getConnect().prepareCall(sql);
            ps.setInt(1, nhanvien.getMaNV());
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            connect.closeConnect();
        }
    }
    
    public int getMaNVBytTenNV(String tenNV) throws SQLException{
        int maNV = 0;
        
        try{
            String sql = "select manv from nhanvien where hoten = ?";
            connect.openConnect();
            ps = connect.getConnect().prepareCall(sql);
            ps.setString(1, tenNV);
            rs = ps.executeQuery();
            if (rs.next()) {
                maNV = rs.getInt("manv");
            }
                                      
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            connect.closeConnect();
        }
        return maNV;
    }
    
    public int getMaCVBytTenCV(String tenCV) throws SQLException{
        int maCV = 0;
        
        try{
            String sql = "select machucvu from chucvu where tenchucvu = ?";
            connect.openConnect();
            ps = connect.getConnect().prepareCall(sql);
            ps.setString(1, tenCV);
            rs = ps.executeQuery();
            if (rs.next()) {
                maCV = rs.getInt("machucvu");
            }
                                      
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            connect.closeConnect();
        }
        return maCV;
    }
}
