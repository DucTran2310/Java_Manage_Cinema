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
        themHoacChinhSuaLabel.setText("Th??m m???i l???ch phim");
        //setDisable ????? v?? hi???u h??a
//    	dateStartPicker.setDisable(true);
        //Ng??y b?? l?? ng??y hi???n t???i
        dateStartPicker.setValue(LocalDate.now());
    }

    public void setChinhSuaLichPhim(LichChieuPhim_demo selected) throws SQLException {
        
           lichPhimselected = selected;
           themHoacChinhSuaLabel.setText("Ch???nh s???a l???ch phim "+LichPhimChinhSua.maLichPhim);
           
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
        String str = "B???n ch??a nh???p ????? th??ng tin";
        if (themHoacChinhSuaLabel.getText().equals("Th??m m???i l???ch phim")) {
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
                
                showAlertAndChangeScene("Th??m l???ch phim th??nh c??ng");

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
                showAlertAndChangeScene("Ch???nh s???a l???ch phim th??nh c??ng");
            }else {
                showAlert(str);
            }
        }
    }

    @FXML
    private void cancel(ActionEvent event) throws IOException {
        //Alert h???y b???
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("C???nh B??o");
        alert.setHeaderText("B???n c?? ch???c mu???n h???y thay ?????i");
        alert.setContentText("Th??ng tin s??? kh??ng ???????c l??u khi b???n h???y.");

        //T???o c??c button s??? hi???n th???
        ButtonType buttonTypeYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();

        //Quay v??? trang danh s??ch nh??n vi??n
        if (result.get() == buttonTypeYes) {
            Node DanhSachLichPhimPane = (Node) FXMLLoader.load(getClass().getResource("/view/Quan_Ly_Lich_Phim.fxml"));
            //getChildren l?? li???t k?? c??c th??nh ph???n con 
            //setAll l?? x??a t???t c??? ph???n t??? hi???n c?? v?? thay th??? b???ng c??c ph???n t??? trong ph????ng th???c setAll
            acpChinhSuaLichPhim.getChildren().setAll(DanhSachLichPhimPane);
        }
    }
    
    public void showAlert(String str) {
	Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Th??ng b??o");
        alert.setHeaderText(str);
        alert.setContentText("X??c nh???n");

        ButtonType buttonTypeCancel = new ButtonType("OK", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeCancel);
        Optional<ButtonType> result = alert.showAndWait();
    }
    
    public void showAlertAndChangeScene(String str) throws IOException {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Th??ng b??o");
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
