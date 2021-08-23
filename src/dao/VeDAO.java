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
import model.Ve;

/**
 *
 * @author ASUS
 */
public class VeDAO {

    private ConnectDB connect;//doi tuong connect to databasse
    private PreparedStatement ps;//cau lenh truy van
    private ResultSet rs;//luu ket qua truy van

    public VeDAO() {
        connect = new ConnectDB();
    }

    //ObservableList là 1 tập hợp các đối tượng để theo dõi khi có sự thay đổi xảy ra trên tập hợp.
//    public ObservableList<Ve> selectAll() throws SQLException{
//        
//        ObservableList listVe = FXCollections.observableArrayList();
//        try{
//            connect.openConnect();
//            String sql = "select mave, ngayban, tenphim, tenphong, loaighe, ngaychieu, giave, hoten\n" 
//                    +"from ve\n" 
//                    +"join phongchieu using (maphong)\n" 
//                    +"join phim using (maphim)\n" 
//                    +"join ghe using (maghe)\n" 
//                    +"join lichphim using (malichphim)\n" 
//                    +"join nhanvien using (manv)";
//            //Sử dụng để truy vấn các SQL động hoặc có tham số
//            ps = connect.getConnect().prepareCall(sql);
//            //Trả về một đối tượng ResultSet khi bạn thực thi câu lệnh SELECT.
//            rs = ps.executeQuery();
//            Ve ve;
//            while(rs.next()){
//                ve = new Ve(rs.getInt("MaVe"), rs.getDate("NgayBan"),  rs.getString("TenPhim"),
//                        rs.getString("TenPhong"), rs.getString("LoaiGhe"), rs.getString("NgayChieu"),  rs.getInt("GiaVe"));
//                listVe.add(ve);
//            }
//        }catch(SQLException e){
//            //printStack sẽ giúp phát hiện phương thức nào gây ra lỗi
//            e.printStackTrace();
//        }finally{
//            connect.closeConnect();
//        }
//        return listVe;
//    }
    public void insertVe(Ve ve) throws SQLException {
        String sql = "insert into ve (NgayBan, MaPhim, MaPhong, Maghe, MaLichPhim, GiaVe, seat_id) values (?,?,?,?,?,?,?) ";
        try {
            connect.openConnect();
            ps = connect.getConnect().prepareCall(sql);
            ps.setDate(1, ve.getNgayBan());
            ps.setInt(2, ve.getMaPhim());
            ps.setInt(3, ve.getMaPhong());
            ps.setInt(4, ve.getMaGhe());
            ps.setInt(5, ve.getMaLich());
            ps.setInt(6, ve.getGiaVe());
            ps.setInt(7, ve.getSeat_id());
            ps.executeUpdate();
        } catch (SQLException e) {
        } finally {
            connect.closeConnect();
        }
    }
    // viêt select này cho anh đi :)) anh đi cv 5p biết viết kh ?
    // comBoBoxNgayChieue thay r a

    public ArrayList<Ve> getAllVeDESC() {
        try {
            ArrayList<Ve> list_ve = new ArrayList<>();
            String sql = "Select * From ve ORDER BY MaVe DESC";
            connect.openConnect();
            ps = connect.getConnect().prepareCall(sql);
            rs = ps.executeQuery();
            System.out.println(ps);
            Ve ve;
            while (rs.next()) {
                ve = new Ve(rs.getInt("MaVe"), rs.getDate("NgayBan"), rs.getInt("MaPhim"), rs.getInt("MaPhong"),
                        rs.getInt("GiaVe"), rs.getInt("MaGhe"), rs.getInt("MaLichPhim"), rs.getInt("seat_id"));
                list_ve.add(ve);
            }
            connect.closeConnect();
            return list_ve;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Ve> getVebyMaPhongAndMaLichPhim(int maPhong, int maLichPhim) {
        try {
            ArrayList<Ve> list_ve = new ArrayList<>();
            String sql = "Select * From ve where (MaPhong =?  AND MaLichPhim =?);";
            connect.openConnect();
            ps = connect.getConnect().prepareCall(sql);
            ps.setInt(1, maPhong);
            ps.setInt(2, maLichPhim);
            rs = ps.executeQuery();
            System.out.println(ps);
            Ve ve;
            while (rs.next()) {
                ve = new Ve(rs.getInt("MaVe"), rs.getDate("NgayBan"), rs.getInt("MaPhim"), rs.getInt("MaPhong"),
                        rs.getInt("GiaVe"), rs.getInt("MaGhe"), rs.getInt("MaLichPhim"), rs.getInt("seat_id"));
                list_ve.add(ve);
            }
            connect.closeConnect();
            return list_ve;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
