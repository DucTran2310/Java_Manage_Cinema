/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import dao.PhimDangChon;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.Ve;
import controller.BanVeController.Ticket;
import dao.SeatDAO;
import dao.VeDAO;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import model.Seat;
import alert.AlertBox;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class DatVeController implements Initializable {

    @FXML
    private Label tfTenPhong;
    @FXML
    private JFXButton btnXacNhan;
    @FXML
    private TableView<Ve> tbvDatVe;
    @FXML
    private JFXButton btnhuy;
    @FXML
    private Label tfTong;
    @FXML
    private Label tfTenPhim;
    @FXML
    private Label tfThoiLuong;
    @FXML
    private Label tfGioChieu;
    @FXML
    private Label tfNgayChieu;
    @FXML
    private TableColumn<Ticket, Integer> clMaVe;
    @FXML
    private TableColumn<Ticket, Date> clNgayBan;
    @FXML
    private TableColumn<Ticket, String> clTenPhim;
    @FXML
    private TableColumn<Ticket, String> clTenPhong;
    @FXML
    private TableColumn<Ticket, String> clGhe;
    @FXML
    private TableColumn<Ticket, String> clNgayChieu;
    @FXML
    private TableColumn<Ticket, Integer> clGia;
    @FXML
    private TableColumn<Ticket, String> clViTri;

    ObservableList tickets;
    public  static  boolean flag = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        flag = false;
        tickets = BanVeController.tickets;
        showTableView(tickets);
        int totalPrice = 0;
        for (int i = 0; i < tickets.size(); i++) {
            Ticket ticket = (Ticket) tickets.get(i);
            totalPrice += ticket.getGia();
        }
        tfTong.setText(String.valueOf(totalPrice));
        if (!tickets.isEmpty()) {
            Ticket ticket = (Ticket) tickets.get(0);
            tfTenPhong.setText(ticket.getPhong());
            tfTenPhim.setText(ticket.getTenPhim());
            String[] part = ticket.getNgayChieu().split(" ");
            tfNgayChieu.setText(part[0]);
            tfGioChieu.setText(part[1]);
            tfThoiLuong.setText(BanVeController.thoiluong);
        }
//        stage = (Stage) tfTenPhim.getScene().getWindow();
//        Ve ve = (Ve) stage.getUserData();
        // System.out.println(ve.getMaPhim()+ " " + ve.getMaGhe());
    }

    public void showTableView(ObservableList ticket) {
        clMaVe.setCellValueFactory(new PropertyValueFactory<>("MaVe"));
        clNgayBan.setCellValueFactory(new PropertyValueFactory<>("NgayBan"));
        clTenPhim.setCellValueFactory(new PropertyValueFactory<>("TenPhim"));
        clTenPhong.setCellValueFactory(new PropertyValueFactory<>("Phong"));
        clGhe.setCellValueFactory(new PropertyValueFactory<>("LoaiGhe"));
        clNgayChieu.setCellValueFactory(new PropertyValueFactory<>("NgayChieu"));
        clGia.setCellValueFactory(new PropertyValueFactory<>("Gia"));
        clViTri.setCellValueFactory(new PropertyValueFactory<>("ViTri"));
        tbvDatVe.setItems(ticket);
    }

    @FXML
    private void clickXacNhan(ActionEvent event) throws SQLException, IOException {
        SeatDAO seatDAO = new SeatDAO();
        ObservableList<Seat> seats = seatDAO.selectAll();

        for (int i = 0; i < tickets.size(); i++) {
            Ticket ticket = (Ticket) tickets.get(i);
            int seat_id = 0;
            for (Seat seat : seats) {
                if (seat.getSeatName().equalsIgnoreCase(ticket.getViTri())) {
                    seat_id = seat.getSeatID();
                    break;
                }
            }
            int maghe = 0;
            switch (ticket.getLoaiGhe()) {
                case "Ghế Thường":
                    maghe = 1;
                    break;
                case "Ghế VIP":
                    maghe = 2;
                    break;
                case "Ghế Sweetbox":
                    maghe = 3;
                    break;
            }
            Ve ve = new Ve();
            VeDAO veDAO = new VeDAO();
            ve.setMaVe(ticket.getMaVe());
            ve.setGiaVe(ticket.getGia());
            ve.setNgayBan(ticket.getNgayBan());
            ve.setMaPhim(BanVeController.maPhim);
            ve.setMaLich(BanVeController.maLichChieu);
            ve.setMaPhong(BanVeController.maPhong);
            ve.setMaGhe(maghe);
            ve.setSeat_id(seat_id);
            veDAO.insertVe(ve);
        }
        tickets.clear();
        alert.AlertBox.show(Alert.AlertType.INFORMATION, "Thông báo", "Đặt vé thành công");
        flag = true;
        Stage stage = (Stage) btnXacNhan.getScene().getWindow();
        stage.close();

    }

    @FXML
    private void clickHuy(ActionEvent event) {
        Stage stage = (Stage) btnhuy.getScene().getWindow();
        stage.close();
    }

}
