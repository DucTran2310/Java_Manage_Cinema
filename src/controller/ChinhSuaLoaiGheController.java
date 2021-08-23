/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import dao.ChinhSuaGhe;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import dao.GheDAO;
import java.sql.SQLException;
import java.util.Optional;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.DanhSachGhe;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ChinhSuaLoaiGheController implements Initializable {

    @FXML
    private JFXButton btnhuy;
    @FXML
    private Label lbTenLoaiGhe;
    @FXML
    private TextField tfGia;

    private GheDAO gheDAO;

    final ObservableList<DanhSachGhe> data = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        gheDAO = new GheDAO();
        tfGia.setText("" + ChinhSuaGhe.GiaGhe);
        lbTenLoaiGhe.setText(ChinhSuaGhe.LoaiGhe);
        //Listener check chi cho phep nhap so khong cho nhap chu cai
        tfGia.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                tfGia.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    Stage scene;
    TableView<DanhSachGhe> LoaiGheTable;
    ObservableList<DanhSachGhe> LoaiGheList;

    @FXML
    private void clickXacNhan(ActionEvent event) throws SQLException {

        if (tfGia.getText() != null && !tfGia.getText().equals("")) {

            String tenGhe = lbTenLoaiGhe.getText();
            int giaGhe = Integer.parseInt(tfGia.getText());
            int maGhe = ChinhSuaGhe.MaGhe;

            gheDAO.updateGhe(maGhe, giaGhe);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Chỉnh sửa thành công");
            alert.showAndWait();
//            gheDAO.Refresh();
//            LoaiGheList = FXCollections.observableArrayList();
//            LoaiGheTable.setItems(LoaiGheList);
//            LoaiGheTable.refresh();

            Stage stage = (Stage) btnhuy.getScene().getWindow();
            stage.close();

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Bạn chưa nhập đủ thông tin");
            alert.setContentText("Xác nhận");

            ButtonType buttonTypeCancel = new ButtonType("OK", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(buttonTypeCancel);
            Optional<ButtonType> result = alert.showAndWait();
        }

    }

    @FXML
    private void clickHuy(ActionEvent event) {
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
            Stage stage = (Stage) btnhuy.getScene().getWindow();
            stage.close();
        }
    }

}
