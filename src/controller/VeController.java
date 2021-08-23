/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import dao.GheDAO;
import dao.LichPhimDAO;
import dao.PhimDAO;
import dao.PhongChieuDAO;
import dao.SeatDAO;
import dao.VeDAO;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import model.DanhSachGhe;
import model.LichChieuPhim_demo;
import model.Phim;
import model.PhongChieu;
import model.Seat;
import model.Ve;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class VeController implements Initializable {

    @FXML
    private TableColumn<veDTO, Integer> clMaVe;
    @FXML
    private TableColumn<veDTO, Date> clNgayBan;
    @FXML
    private TableColumn<veDTO, String> clTenPhim;
    @FXML
    private TableColumn<veDTO, String> clTenPhong;
    @FXML
    private TableColumn<veDTO, String> clTenGhe;
    @FXML
    private TableColumn<veDTO, String> clNgayChieu;
    @FXML
    private TableColumn<veDTO, Integer> clGia;
    @FXML
    private JFXButton editUserBtn;
    @FXML
    private ImageView repairIcon;

    private VeDAO veDAO;
    @FXML
    private TableView<veDTO> VeTable;
    @FXML
    private TableColumn<veDTO, String> clViTri;
    ObservableList tickets = FXCollections.observableArrayList();

    ArrayList<Ve> list_ve = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        veDAO = new VeDAO();// khai bao doi tuong DAO truy xuat du lieu tu database
        list_ve = veDAO.getAllVeDESC();
        for (Ve ve : list_ve) {
            try {
                veDTO veDTO = new veDTO();
                veDTO.setMaVe(ve.getMaVe());
                veDTO.setNgayBan(ve.getNgayBan());
                veDTO.setGia(ve.getGiaVe());
                veDTO.setViTri(getSeatName(ve.getSeat_id()));
                veDTO.setLoaiGhe(getLoaiGhe(ve.getMaGhe()));
                veDTO.setTenPhim(getPhimName(ve.getMaPhim()));
                veDTO.setPhong(getRoomName(ve.getMaPhong()));
                veDTO.setNgayChieu(getNgayChieu(ve.getMaLich()));
                tickets.add(veDTO);
            } catch (SQLException ex) {
                Logger.getLogger(VeController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        showTableViewVe(tickets);
    }

    public String getSeatName(int seat_id) throws SQLException {
        SeatDAO seatDAO = new SeatDAO();
        ObservableList<Seat> seats = seatDAO.selectAll();
        for (Seat seat : seats) {
            if (seat.getSeatID() == seat_id) {
                return seat.getSeatName();
            }
        }
        return "";
    }

    public String getLoaiGhe(int ma_ghe) throws SQLException {
        GheDAO gheDAO = new GheDAO();
        ObservableList<DanhSachGhe> seats = gheDAO.selectAll();
        for (DanhSachGhe seat : seats) {
            if (seat.getMaGhe() == ma_ghe) {
                return seat.getLoaiGhe();
            }
        }
        return "";
    }

    public String getNgayChieu(int lich_id) throws SQLException {
        LichPhimDAO lichPhimDAO = new LichPhimDAO();
        ObservableList<LichChieuPhim_demo> chieuPhim_demos = lichPhimDAO.selectAll();
        for (LichChieuPhim_demo lcp : chieuPhim_demos) {
            if (lcp.getMaLichPhim() == lich_id) {
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String datetime = dateFormat.format(lcp.getNgayChieu());
                datetime += " " + lcp.getThoiGianBatDau();
                return datetime;
            }
        }
        return "";
    }

    public String getPhimName(int phim_id) throws SQLException {
        PhimDAO phimDAO = new PhimDAO();
        ObservableList<Phim> phims = phimDAO.selectAll();
        for (Phim phim : phims) {
            if (phim.getMaPhim() == phim_id) {
                return phim.getTenPhim();
            }
        }
        return "";
    }

    public String getRoomName(int phong_id) throws SQLException {
        PhongChieuDAO phongChieuDAO = new PhongChieuDAO();
        ObservableList<PhongChieu> phongchieus = phongChieuDAO.selectAll();
        for (PhongChieu phong : phongchieus) {
            if (phong.getMaPhong() == phong_id) {
                return phong.getTenPhong();
            }
        }
        return "";
    }

    @FXML
    private void editUser(ActionEvent event) {
    }

    public void showTableViewVe(ObservableList tickets) {
        clMaVe.setCellValueFactory(new PropertyValueFactory<>("MaVe"));
        clNgayBan.setCellValueFactory(new PropertyValueFactory<>("NgayBan"));//todo
        clTenPhim.setCellValueFactory(new PropertyValueFactory<>("TenPhim"));
        clTenPhong.setCellValueFactory(new PropertyValueFactory<>("Phong"));
        clTenGhe.setCellValueFactory(new PropertyValueFactory<>("LoaiGhe"));
        clNgayChieu.setCellValueFactory(new PropertyValueFactory<>("NgayChieu"));
        clGia.setCellValueFactory(new PropertyValueFactory<>("Gia"));
        clViTri.setCellValueFactory(new PropertyValueFactory<>("ViTri"));

        VeTable.setItems(tickets);
    }

    public class veDTO {

        private int MaVe;
        private Date NgayBan;
        private String TenPhim;
        private String Phong;
        private String LoaiGhe;
        private String NgayChieu;
        private int Gia;
        private String ViTri;

        public veDTO() {
        }

        public veDTO(int MaVe, Date NgayBan, String TenPhim, String Phong, String LoaiGhe, String NgayChieu, int Gia, String ViTri) {
            this.MaVe = MaVe;
            this.NgayBan = NgayBan;
            this.TenPhim = TenPhim;
            this.Phong = Phong;
            this.LoaiGhe = LoaiGhe;
            this.NgayChieu = NgayChieu;
            this.Gia = Gia;
            this.ViTri = ViTri;
        }

        public int getMaVe() {
            return MaVe;
        }

        public void setMaVe(int MaVe) {
            this.MaVe = MaVe;
        }

        public Date getNgayBan() {
            return NgayBan;
        }

        public void setNgayBan(Date NgayBan) {
            this.NgayBan = NgayBan;
        }

        public String getTenPhim() {
            return TenPhim;
        }

        public void setTenPhim(String TenPhim) {
            this.TenPhim = TenPhim;
        }

        public String getPhong() {
            return Phong;
        }

        public void setPhong(String Phong) {
            this.Phong = Phong;
        }

        public String getLoaiGhe() {
            return LoaiGhe;
        }

        public void setLoaiGhe(String LoaiGhe) {
            this.LoaiGhe = LoaiGhe;
        }

        public String getNgayChieu() {
            return NgayChieu;
        }

        public void setNgayChieu(String NgayChieu) {
            this.NgayChieu = NgayChieu;
        }

        public int getGia() {
            return Gia;
        }

        public void setGia(int Gia) {
            this.Gia = Gia;
        }

        public String getViTri() {
            return ViTri;
        }

        public void setViTri(String ViTri) {
            this.ViTri = ViTri;
        }

    }
}
