/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import dao.ChinhSuaGhe;
import dao.GheDAO;
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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.DanhSachGhe;


/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class DanhSachLoaiGheController implements Initializable {

    @FXML
    private AnchorPane acpLoaiGhe;
    @FXML
    private TableView<DanhSachGhe> LoaiGheTable;
    @FXML
    private JFXButton ChinhSuaLoaiGhe;
    @FXML
    private TableColumn<DanhSachGhe, Integer> clID;
    @FXML
    private TableColumn<DanhSachGhe, String> clTenLoaiGhe;
    @FXML
    private TableColumn<DanhSachGhe, Float> clGia;
    
    private GheDAO veDAO;
    
    private ObservableList<DanhSachGhe> LoaiGheList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        veDAO = new GheDAO();// khai bao doi tuong DAO truy xuat du lieu tu database
        try {
            showTableViewVe();
        } catch (SQLException ex) {
            Logger.getLogger(DanhSachNhanVienController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }    
    
    public void refreshTable() {
//        LoaiGheList.removeAll(LoaiGheList);
//        LoaiGheList = FXCollections.observableArrayList();
//        System.out.print(LoaiGheList.get(2).getGiaGhe());
//        LoaiGheTable.setItems(LoaiGheList);
        LoaiGheTable.refresh();
        
    }

    @FXML
    private void clickChinhSuaLoaiGhe(ActionEvent event) throws IOException {

        
        DanhSachGhe selected = LoaiGheTable.getSelectionModel().getSelectedItem();
        
        if (selected != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Chinh_Sua_Loai_ghe.fxml"));
            ChinhSuaGhe.LoaiGhe = selected.getLoaiGhe();
            ChinhSuaGhe.MaGhe = selected.getMaGhe();
            ChinhSuaGhe.GiaGhe = selected.getGiaGhe();
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            if (true) {
                stage.initModality(Modality.APPLICATION_MODAL);
            }
            stage.setScene(scene);
            stage.show();
            
            
        } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Thông báo");
                        alert.setHeaderText("Chưa chọn loại ghế nào");
                        alert.showAndWait();
        }
    }
    
    public void showTableViewVe() throws SQLException {
        clID.setCellValueFactory(new PropertyValueFactory<>("MaGhe"));
        clTenLoaiGhe.setCellValueFactory(new PropertyValueFactory<>("LoaiGhe"));
        clGia.setCellValueFactory(new PropertyValueFactory<>("GiaGhe"));
        
        ObservableList<DanhSachGhe> listVe = FXCollections.observableArrayList();
        listVe = veDAO.selectAll();
        LoaiGheTable.setItems(listVe);
    }
    
}
