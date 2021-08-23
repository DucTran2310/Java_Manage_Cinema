/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import controller.BanThucAnController.Food;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class DatThucAnController implements Initializable {

    @FXML
    private AnchorPane acpDatThucAn;
    @FXML
    private JFXButton btnXacNhan;
    @FXML
    private TableView<?> tbvDatThucAn;
    @FXML
    private TableColumn<?, ?> tbclTenThucAn;
    @FXML
    private TableColumn<?, ?> tbclSoLuong;
    @FXML
    private TableColumn<?, ?> tbclDonGia;
    @FXML
    private TableColumn<?, ?> tbclThanhTien;
    @FXML
    private JFXButton btnhuy;
    @FXML
    private Label lbTong;
    int totalPrice = 0;
    ObservableList foodOrders = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList fs = BanThucAnController.foods;
        foodOrders.clear();
        for (int i = 0; i < fs.size(); i++) {
            Food food = (Food) fs.get(i);
            if (food.getSoluong() > 0) {
                FoodOrder foodOrder = new FoodOrder(food.getName(), food.getGia(), food.getSoluong(), (food.getSoluong() * food.getGia()));
                totalPrice+= (food.getSoluong() * food.getGia());
                foodOrders.add(foodOrder);
            }
        }
        showTableViewFood(foodOrders);
        lbTong.setText(String.valueOf(totalPrice));
    }
        public void showTableViewFood(ObservableList<Food> foods) {
        tbclTenThucAn.setCellValueFactory(new PropertyValueFactory<>("tenThucAn"));
        tbclDonGia.setCellValueFactory(new PropertyValueFactory<>("donGia"));
        tbclSoLuong.setCellValueFactory(new PropertyValueFactory<>("soLuong"));
        tbclThanhTien.setCellValueFactory(new PropertyValueFactory<>("thanhTien"));
        tbvDatThucAn.setItems(foodOrders);
    }
    @FXML
    private void clickXacNhan(ActionEvent event) throws IOException {
        ObservableList fs = BanThucAnController.foods;
        for (int i = 0; i < fs.size(); i++) {
            Food food = (Food) fs.get(i);
            if (food.getSoluong() > 0) {
                food.setSoluong(0);
            }
        }
        alert.AlertBox.show(Alert.AlertType.INFORMATION, "Thông báo", "Đặt thức ăn thành công");
        
        Stage stage = (Stage) btnXacNhan.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void clickHuy(ActionEvent event) throws IOException {

        Stage stage = (Stage) btnhuy.getScene().getWindow();
        stage.close();
    }

    public class FoodOrder {

        private String tenThucAn;
        private int donGia;
        private int soLuong;
        private int thanhTien;

        public FoodOrder() {
        }

        public FoodOrder(String tenThucAn, int donGia, int soLuong, int thanhTien) {
            this.tenThucAn = tenThucAn;
            this.donGia = donGia;
            this.soLuong = soLuong;
            this.thanhTien = thanhTien;
        }

        public String getTenThucAn() {
            return tenThucAn;
        }

        public void setTenThucAn(String tenThucAn) {
            this.tenThucAn = tenThucAn;
        }

        public int getDonGia() {
            return donGia;
        }

        public void setDonGia(int donGia) {
            this.donGia = donGia;
        }

        public int getSoLuong() {
            return soLuong;
        }

        public void setSoLuong(int soLuong) {
            this.soLuong = soLuong;
        }

        public int getThanhTien() {
            return thanhTien;
        }

        public void setThanhTien(int thanhTien) {
            this.thanhTien = thanhTien;
        }

    }

}
