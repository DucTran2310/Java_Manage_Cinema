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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.AnchorPane;
import model.PhanQuyen;
import dao.PhanQuyenDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class PhanQuyenController implements Initializable {

    @FXML
    private AnchorPane acpPhanQuyen;
    @FXML
    private JFXButton btnCreate;
    @FXML
    private TableColumn<PhanQuyen, String> clTen;
    @FXML
    private TableColumn<PhanQuyen, String> clChucVu;
    @FXML
    private TableColumn<PhanQuyen, String> clQuyen;
    
    private PhanQuyenDAO pqDAO;
    @FXML
    private TableView<PhanQuyen> tableQuyen;
    @FXML
    private JFXButton btnEdit;
    @FXML
    private JFXButton btnDelete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        pqDAO = new PhanQuyenDAO();// khai bao doi tuong DAO truy xuat du lieu tu database
        try {
            showTableViewPhanQuyen();
        } catch (SQLException ex) {
            Logger.getLogger(DanhSachNhanVienController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void create(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Chinh_Sua_Chuc_Vu.fxml"));
        Parent root = (Parent) loader.load();
        ChinhSuaChucVucontroller controller = loader.getController();
        controller.setThemChucVuMoi();
        acpPhanQuyen.getChildren().setAll(root);
    }

    
    public void showTableViewPhanQuyen() throws SQLException{
        clTen.setCellValueFactory(new PropertyValueFactory<>("HoTen"));//todo
        clChucVu.setCellValueFactory(new PropertyValueFactory<>("ChucVu"));
        clQuyen.setCellValueFactory(new PropertyValueFactory<>("Quyen"));
     
        ObservableList<PhanQuyen> listPhanQuyen = FXCollections.observableArrayList();
        listPhanQuyen = pqDAO.selectAll();
        tableQuyen.setItems(listPhanQuyen);
    }

    @FXML
    private void edit(ActionEvent event) {
    }

    @FXML
    private void delete(ActionEvent event) {
    }
}
