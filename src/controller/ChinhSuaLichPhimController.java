/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTimePicker;
import dao.LichPhimChinhSua;
import dao.PhimDAO;
import dao.PhongChieuDAO;
import dao.LichPhimDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.LichChieuPhim_demo;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ChinhSuaLichPhimController implements Initializable {

    @FXML
    private AnchorPane acpChinhSuaLichPhim;
    @FXML
    private JFXButton saveBtn;
    @FXML
    private JFXButton cancelBtn;
    @FXML
    private JFXComboBox<String> tenPhimComboBox;
    @FXML
    private JFXComboBox<String> tenPhongComboBox;
    @FXML
    private DatePicker dateStartPicker;
    @FXML
    private Label themHoacChinhSuaLabel;

    private LichChieuPhim_demo lichPhimselected;

    private PhimDAO phimDAO;
    private PhongChieuDAO phongChieuDAO;
    private LichPhimDAO lichphimDAO;

    @FXML
    private TextField tfThoiGianBatDau;
    @FXML
    private TextField tfThoiGianKetThuc;
    @FXML
    private JFXTimePicker tfTimeStart;
    @FXML
    private JFXTimePicker tfTimeEnd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tenPhimComboBox.setStyle("-fx-font: 18px \"Serif\"; -fx-background-color:#ffffff");
    	tenPhongComboBox.setStyle("-fx-font: 18px \"Serif\"; -fx-background-color:#ffffff");
        tfTimeStart.setStyle("-fx-font: 18px \"Serif\"; -fx-background-color:#ffffff");
        tfTimeEnd.setStyle("-fx-font: 18px \"Serif\"; -fx-background-color:#ffffff");
        
        phimDAO = new PhimDAO();
        
        try {
            phongChieuDAO = new PhongChieuDAO();
            lichphimDAO = new LichPhimDAO();
            // TODO
//        listPhong = FXCollections.observableArrayList(PhongChieu.getTenPhong());
            showValueComboBox();
        } catch (SQLException ex) {
            Logger.getLogger(ChinhSuaLichPhimController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setThemMoiLichPhim() {
        themHoacChinhSuaLabel.setText("Thêm mới lịch phim");
        //setDisable để vô hiệu hóa
//    	dateStartPicker.setDisable(true);
        //Ngày bđ là ngày hiện tại
        dateStartPicker.setValue(LocalDate.now());
    }

    public void setChinhSuaLichPhim(LichChieuPhim_demo selected) throws SQLException {
        
           lichPhimselected = selected;
           themHoacChinhSuaLabel.setText("Chỉnh sửa lịch phim "+LichPhimChinhSua.maLichPhim);
           
//        tenPhimComboBox.getSelectionModel().select(phimDAO.getPhimByMaPhim(selected.getMaPhim()).getTenPhim());
//        tenPhongComboBox.getSelectionModel().select(phongChieuDAO.getPhongByMaPhong(selected.getMaPhong()).getTenPhong());
//          LocalDate date = LocalDate.of(timeBatDau.getYear(),timeBatDau.getMonth(),timeBatDau.getDayOfMonth());
          dateStartPicker.setValue(LocalDate.now());
//        suatChieuComboBox.getSelectionModel().select(suatchieuDAO.getSuatByMaSuat(selected.getMaSuat()).getThoiGianBatDau());
        
    }

    

    

    public void showValueComboBox() throws SQLException {
        ObservableList<String> listTenPhim = FXCollections.observableArrayList();
        ObservableList<String> listPhongChieu = FXCollections.observableArrayList();

        listPhongChieu = phongChieuDAO.getTenPhongChieu();
        listTenPhim = phimDAO.getTenPhim();

        tenPhongComboBox.getItems().addAll(listPhongChieu);
        tenPhimComboBox.getItems().addAll(listTenPhim);
    }
    

    @FXML
    private void save(ActionEvent event) throws IOException, SQLException{
        String str = "Bạn chưa nhập đủ thông tin";
        if (themHoacChinhSuaLabel.getText().equals("Thêm mới lịch phim")) {
            if (tenPhimComboBox.getSelectionModel().getSelectedItem() != null && tenPhongComboBox.getSelectionModel().getSelectedItem() != null) {

                LichChieuPhim_demo lcphim = new LichChieuPhim_demo();

                int maPhim = phimDAO.getMaPhimbyTenPhim(tenPhimComboBox.getValue());
                int maPhong = phongChieuDAO.getMaPhongbyTenPhong(tenPhongComboBox.getValue());

                LocalDate ngayChieu = dateStartPicker.getValue();
                Date dt2 = Date.valueOf(ngayChieu);
                
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                LocalTime timeStart = tfTimeStart.getValue();
                LocalTime timeEnd = tfTimeEnd.getValue();
                String timeString = timeStart.format(formatter);
                String timeString1 = timeEnd.format(formatter);
                
                
//                LocalTime thoiGianBatDau = tfTimeStart.getValue();
//                String thoiGianKetThuc = tfThoiGianKetThuc.getText();

                

                lcphim.setMaPhim(maPhim);
                lcphim.setNgayChieu(dt2);
                lcphim.setMaPhong(maPhong);
                lcphim.setThoiGianBatDau(timeString);
                lcphim.setThoiGianKetThuc(timeString1);
                lichphimDAO.insertLichPhim(lcphim);
                
                showAlertAndChangeScene("Thêm lịch phim thành công");

            } else {
                showAlert(str);
            }
        }else{
            if (tenPhimComboBox.getSelectionModel().getSelectedItem() != null && tenPhongComboBox.getSelectionModel().getSelectedItem() != null) {
                int maLichPhim = LichPhimChinhSua.maLichPhim;
//                String tenPhim = tenPhimComboBox.getValue();
                int maPhim = phimDAO.getMaPhimbyTenPhim(tenPhimComboBox.getValue());
                int maPhong = phongChieuDAO.getMaPhongbyTenPhong(tenPhongComboBox.getValue());// viet method getMaPhongByTenPhong
                LocalDate ngayChieu1 = dateStartPicker.getValue();
                Date ngayChieu = Date.valueOf(ngayChieu1);
                LocalTime thoiGianBatDau1 = tfTimeStart.getValue();
                Time thoiGianBatDau = Time.valueOf(thoiGianBatDau1);
                LocalTime thoiGianKetThuc1 = tfTimeEnd.getValue();
                Time thoiGianKetThuc = Time.valueOf(thoiGianKetThuc1);
                
                
                lichphimDAO.updateLichPhim(maPhim, maPhong, ngayChieu, thoiGianBatDau, thoiGianKetThuc, maLichPhim);
                showAlertAndChangeScene("Chỉnh sửa lịch phim thành công");
            }else {
                showAlert(str);
            }
        }
    }

    @FXML
    private void cancel(ActionEvent event) throws IOException {
        //Alert hủy bỏ
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Cảnh Báo");
        alert.setHeaderText("Bạn có chắc muốn hủy thay đổi");
        alert.setContentText("Thông tin sẽ không được lưu khi bạn hủy.");

        //Tạo các button sẽ hiển thị
        ButtonType buttonTypeYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();

        //Quay về trang danh sách nhân viên
        if (result.get() == buttonTypeYes) {
            Node DanhSachLichPhimPane = (Node) FXMLLoader.load(getClass().getResource("/view/Quan_Ly_Lich_Phim.fxml"));
            //getChildren là liệt kê các thành phần con 
            //setAll là xóa tất cả phần tử hiện có và thay thế bằng các phần tử trong phương thức setAll
            acpChinhSuaLichPhim.getChildren().setAll(DanhSachLichPhimPane);
        }
    }
    
    public void showAlert(String str) {
	Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Thông báo");
        alert.setHeaderText(str);
        alert.setContentText("Xác nhận");

        ButtonType buttonTypeCancel = new ButtonType("OK", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeCancel);
        Optional<ButtonType> result = alert.showAndWait();
    }
    
    public void showAlertAndChangeScene(String str) throws IOException {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(str);
        
        ButtonType buttonTypeYes = new ButtonType("OK", ButtonBar.ButtonData.YES);
        
        alert.getButtonTypes().setAll(buttonTypeYes);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get()== buttonTypeYes)
        {	
        	Node DanhSachPhimPane= (Node) FXMLLoader.load(getClass().getResource("/view/Quan_Ly_Lich_Phim.fxml"));
        	acpChinhSuaLichPhim.getChildren().setAll(DanhSachPhimPane);
        }
	}

    @FXML
    private void comboAction(ActionEvent event) {
    }

}
