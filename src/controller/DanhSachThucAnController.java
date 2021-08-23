/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import dao.QuanLyThucAn_DAO;
import dao.ThucAnChinhSua;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import model.Quan_Ly_Thuc_An;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class DanhSachThucAnController implements Initializable {

    @FXML
    private AnchorPane acpThucAn;
    @FXML
    private JFXButton createBtn;
    @FXML
    private ImageView addIcon;
    @FXML
    private JFXButton deleteBtn;
    @FXML
    private ImageView deleteIcon;
    @FXML
    private ImageView repairIcon;
    @FXML
    private TableView<Quan_Ly_Thuc_An> foodTable;
    @FXML
    private TableColumn<Quan_Ly_Thuc_An, Integer> clID;
    @FXML
    private TableColumn<Quan_Ly_Thuc_An, String> clNameFood;
    @FXML
    private TableColumn<Quan_Ly_Thuc_An, Float> clPrice;
    
    private QuanLyThucAn_DAO qlta_DAO;
    
    private ObservableList<Quan_Ly_Thuc_An> cttaList;
    
    @FXML
    private JFXButton btnChinhSuaThucAn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        qlta_DAO = new QuanLyThucAn_DAO();// khai bao doi tuong DAO truy xuat du lieu tu database
        try {
            showTableViewFood();
        } catch (SQLException ex) {
            Logger.getLogger(DanhSachThucAnController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void create(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Chinh_Sua_Thuc_An.fxml"));
        Parent root = (Parent) loader.load();
	ChinhSuaThucAnController controller = loader.getController();
	controller.setThemMoiThucAn();
	acpThucAn.getChildren().setAll(root);
    }

    

    
    public void showTableViewFood() throws SQLException {
        clID.setCellValueFactory(new PropertyValueFactory<>("MaThucAn"));
        clNameFood.setCellValueFactory(new PropertyValueFactory<>("TenThucAn"));
        clPrice.setCellValueFactory(new PropertyValueFactory<>("GiaTA"));
        
        ObservableList<Quan_Ly_Thuc_An> listNhanVien = FXCollections.observableArrayList();
        listNhanVien = qlta_DAO.selectAll();
        foodTable.setItems(listNhanVien);
    }

    @FXML
    private void handleChinhSuaThucAn(ActionEvent event) throws IOException {
        
        Quan_Ly_Thuc_An thucAn = foodTable.getSelectionModel().getSelectedItem();
        
        if (thucAn != null)
		{
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Chinh_Sua_Thuc_An.fxml"));
                ThucAnChinhSua.maThucAn = thucAn.getMaThucAn();
    		Parent root = (Parent) loader.load();
    		ChinhSuaThucAnController controller = loader.getController();
    		controller.setChinhSuaThucAn(thucAn);
    		acpThucAn.getChildren().setAll(root);
		}
    	else {
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Thông báo");
                        alert.setHeaderText("Chưa chọn thức ăn nào");
                        alert.showAndWait();
        }
    }
    
    
    
    @FXML
    private void delete(ActionEvent event) throws SQLException {
        Quan_Ly_Thuc_An selected = foodTable.getSelectionModel().getSelectedItem();
        
        if (selected !=  null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Xác nhận");
            alert.setHeaderText("Bạn muốn xóa thức ăn này?");
            
            ButtonType buttonTypeYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
	        
	        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeCancel);

	        Optional<ButtonType> result = alert.showAndWait();

	        if (result.get()== buttonTypeYes)
                {
                                Quan_Ly_Thuc_An thucAn = new Quan_Ly_Thuc_An();
                                qlta_DAO.deleteThucAn(selected);
				Alert alert1 = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Thông báo");
                                alert.setHeaderText("Xóa thành công thức ăn ");
                                alert.showAndWait();
	        }
		}
		else {
			Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Thông báo");
                        alert.setHeaderText("Chưa chọn thức ăn nào");
                        alert.showAndWait();
		}
        showTableViewFood();
        }
    
    
    }
    

