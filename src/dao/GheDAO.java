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
import javafx.scene.control.TableView;
import model.DanhSachGhe;


/**
 *
 * @author ASUS
 */
public class GheDAO {
    private ConnectDB connect;//doi tuong connect to databasse
    private PreparedStatement ps;//cau lenh truy van
    private ResultSet rs;//luu ket qua truy van
    final ObservableList<DanhSachGhe> data = FXCollections.observableArrayList();
    TableView<DanhSachGhe> table;
            
    public GheDAO(){
        connect = new ConnectDB();
    }
    ObservableList listVe = FXCollections.observableArrayList();
    //ObservableList là 1 tập hợp các đối tượng để theo dõi khi có sự thay đổi xảy ra trên tập hợp.
    public ObservableList<DanhSachGhe> selectAll() throws SQLException{
        
        
        try{
            connect.openConnect();
            String sql = "select * from Ghe";
            //Sử dụng để truy vấn các SQL động hoặc có tham số
            ps = connect.getConnect().prepareCall(sql);
            //Trả về một đối tượng ResultSet khi bạn thực thi câu lệnh SELECT.
            rs = ps.executeQuery();
            DanhSachGhe dsv;
            while(rs.next()){
                dsv = new DanhSachGhe(rs.getInt("MaGhe"), rs.getString("LoaiGhe"),  rs.getInt("GiaGhe"), rs.getBoolean("status"));
                listVe.add(dsv);
            }
        }catch(SQLException e){
            //printStack sẽ giúp phát hiện phương thức nào gây ra lỗi
            e.printStackTrace();
        }finally{
            connect.closeConnect();
        }
        return listVe;
    }
    
    
    public void updateGhe (int maGhe, int giaGhe) throws SQLException{
        try{
            String sql = "UPDATE ghe SET GiaGhe = ? WHERE MaGhe = ?";
            connect.openConnect();
            ps = connect.getConnect().prepareCall(sql);
            ps.setInt(1, giaGhe);
            ps.setInt(2, maGhe);
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            connect.closeConnect();
        }
    }
    
    public void Refresh() throws SQLException{
        data.clear();
        try{
            connect.openConnect();
            String sql = "select * from Ghe";
            //Sử dụng để truy vấn các SQL động hoặc có tham số
            ps = connect.getConnect().prepareStatement(sql);
            //Trả về một đối tượng ResultSet khi bạn thực thi câu lệnh SELECT.
            rs = ps.executeQuery();
            DanhSachGhe dsv;
            while(rs.next()){
                dsv = new DanhSachGhe(rs.getInt("MaGhe"), rs.getString("LoaiGhe"),  rs.getInt("GiaGhe"), rs.getBoolean("status"));
                table.setItems(data);
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            //printStack sẽ giúp phát hiện phương thức nào gây ra lỗi
            e.printStackTrace();
        }finally{
            connect.closeConnect();
        }
    }
}
