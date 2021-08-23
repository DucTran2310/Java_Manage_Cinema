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
import model.PhanQuyen;

/**
 *
 * @author ASUS
 */
public class PhanQuyenDAO {
    
    private ConnectDB connect;//doi tuong connect to databasse
    private PreparedStatement ps;//cau lenh truy van
    private ResultSet rs;//luu ket qua truy van
    
    public PhanQuyenDAO(){
        connect = new ConnectDB();
    }
    
    //ObservableList là 1 tập hợp các đối tượng để theo dõi khi có sự thay đổi xảy ra trên tập hợp.
    public ObservableList<PhanQuyen> selectAll() throws SQLException{
        
        ObservableList listPhanQuyen = FXCollections.observableArrayList();
        try{
            connect.openConnect();
            String sql = "select HoTen, TenChucVu, Quyen\n" 
                    +"from role_user r\n" 
                    +"join nhanvien using (manv)\n" 
                    +"join chucvu c on c.machucvu = r.MaChucVu;";
            //Sử dụng để truy vấn các SQL động hoặc có tham số
            ps = connect.getConnect().prepareCall(sql);
            //Trả về một đối tượng ResultSet khi bạn thực thi câu lệnh SELECT.
            rs = ps.executeQuery();
            PhanQuyen pq;
            while(rs.next()){
                pq = new PhanQuyen(rs.getString("HoTen"),  rs.getString("TenChucVu"),
                        rs.getString("Quyen"));
                listPhanQuyen.add(pq);
            }
        }catch(SQLException e){
            //printStack sẽ giúp phát hiện phương thức nào gây ra lỗi
            e.printStackTrace();
        }finally{
            connect.closeConnect();
        }
        return listPhanQuyen;
    }
    
}
