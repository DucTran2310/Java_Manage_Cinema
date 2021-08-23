/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Quan_Ly_Thuc_An;

/**
 *
 * @author ASUS
 */
public class QuanLyThucAn_DAO {
    private ConnectDB connect;//doi tuong connect to databasse
    private PreparedStatement ps;//cau lenh truy van
    private ResultSet rs;//luu ket qua truy van
    
    public QuanLyThucAn_DAO(){
        connect = new ConnectDB();
    }
    
    public ObservableList<Quan_Ly_Thuc_An> selectAll() throws SQLException{
        ObservableList listThucAn = FXCollections.observableArrayList();
        try{
            connect.openConnect();
            String sql = "select * from thucan";
            ps = connect.getConnect().prepareCall(sql);
            rs = ps.executeQuery();
            Quan_Ly_Thuc_An qlta;
            while(rs.next()){
                qlta = new Quan_Ly_Thuc_An(rs.getInt("MaThucAn"), rs.getString("TenThucAn"),  rs.getInt("GiaTA"));
                listThucAn.add(qlta);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            connect.closeConnect();
        }
        return listThucAn;
    }
    
    public void insertThucAn (Quan_Ly_Thuc_An thucAn) throws SQLException{
        try{
            String sql = "INSERT INTO ThucAn(TenThucAn,GiaTA) VALUES (?,?)";
            connect.openConnect();
            ps = connect.getConnect().prepareCall(sql);
            ps.setString(1, thucAn.getTenThucAn());
            ps.setInt(2, thucAn.getGiaTA());
            ps.executeUpdate();//dung cho CRUD
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            connect.closeConnect();
        }
    }
    
    public void updateThucAn (String tenThucAn, int giaThucAn, int maThucAn) throws SQLException{
        try{
            String sql = "UPDATE thucan SET TenThucAn = ?, GiaTA = ?  WHERE MaThucAn = ?";
            connect.openConnect();
            ps = connect.getConnect().prepareCall(sql);
            ps.setString(1, tenThucAn);
            ps.setInt(2, giaThucAn);
            ps.setInt(3, maThucAn);
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            connect.closeConnect();
        }
    }
    
    public void deleteThucAn (Quan_Ly_Thuc_An thucAn) throws SQLException{
        try{
            String sql = "DELETE FROM thucan WHERE MaThucAn = ?";
            connect.openConnect();
            ps = connect.getConnect().prepareCall(sql);
//            ps.setString(1, thucAn.getTenThucAn());
//            ps.setInt(2, thucAn.getGiaTA());
            ps.setInt(1, thucAn.getMaThucAn());
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            connect.closeConnect();
        }
    }
    
    
}
