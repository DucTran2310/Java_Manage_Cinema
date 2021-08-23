/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import alert.AlertBox;
import com.jfoenix.controls.JFXButton;
import dao.PhongChieuDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.PhongChieu;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class DanhSachPhongChieuController implements Initializable {

    @FXML
    private AnchorPane acpDanhSachPhongChieu;
    @FXML
    private ImageView deleteIcon;
    @FXML
    private ImageView repairIcon;
    @FXML
    private TableView<PhongChieu> tbvDanhSachPhongChieu;
    @FXML
    private TableColumn<PhongChieu, Integer> clID;
    @FXML
    private TableColumn<PhongChieu, String> clTenPhong;
    @FXML
    private TableColumn<PhongChieu, Integer> clSoGheDoc;
    @FXML
    private TableColumn<PhongChieu, Integer> clSoGheNgang;
    
    private PhongChieuDAO pcDAO;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXButton btnRepairRoom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        pcDAO = new PhongChieuDAO();// khai bao doi tuong DAO truy xuat du lieu tu database
        try {
            showTableViewPhongChieu();
        } catch (SQLException ex) {
            Logger.getLogger(DanhSachPhongChieuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    


    
    public void showTableViewPhongChieu() throws SQLException {
        clID.setCellValueFactory(new PropertyValueFactory<>("MaPhong"));
        clTenPhong.setCellValueFactory(new PropertyValueFactory<>("TenPhong"));
        clSoGheNgang.setCellValueFactory(new PropertyValueFactory<>("SoGheNgang"));
        clSoGheDoc.setCellValueFactory(new PropertyValueFactory<>("SoGheDoc"));
        
        ObservableList<PhongChieu> listPhongChieu = FXCollections.observableArrayList();
        listPhongChieu = pcDAO.selectAll();
        tbvDanhSachPhongChieu.setItems(listPhongChieu);
    }

    @FXML
    private void xoaPhong(ActionEvent event) {
    }

    @FXML
    private void chinhSuaPhong(ActionEvent event) throws IOException {
        PhongChieu selected = tbvDanhSachPhongChieu.getSelectionModel().getSelectedItem();
        if (selected == null){
            AlertBox.show(Alert.AlertType.INFORMATION, "Thông báo", "Chưa chọn phim để chỉnh sửa");
        }else{
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Ban_Ve.fxml"));
                Parent root = (Parent) loader.load();
                BanVeController controller = loader.getController();
                acpDanhSachPhongChieu.getChildren().setAll(root);
            } catch (Exception e) {
                System.out.println("unable to load tab");
                e.printStackTrace();
            }
        }
    }
}
