/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import dao.PhimDAO;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.Phim;
import alert.AlertBox;
import dao.PhimChinhSua;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;


/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class DanhSachPhimController implements Initializable {

    @FXML
    private AnchorPane acpDanhSachPhim;
    @FXML
    private TableView<Phim> filmTable;
    @FXML
    private JFXButton createBtn;
    @FXML
    private ImageView addIcon;
    @FXML
    private JFXButton deleteBtn;
    @FXML
    private ImageView deleteIcon;
    @FXML
    private JFXButton repairBtn;
    @FXML
    private ImageView repairIcon;
    @FXML
    private TableColumn<Phim, Integer> clID;
    @FXML
    private TableColumn<Phim, String> clTenPhim;
    @FXML
    private TableColumn<Phim, Integer> clThoiLuong1;
    @FXML
    private TableColumn<Phim, String> clThongTinPhim;
    @FXML
    private TableColumn<Phim, String> clThoiGianKhoiTao;
    @FXML
    private TableColumn<Phim, String> clThoiGianCapNhat;
    
    private PhimDAO phimDAO;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        phimDAO = new PhimDAO();// khai bao doi tuong DAO truy xuat du lieu tu database
        try {
            showTableViewPhim();
        } catch (SQLException ex) {
            Logger.getLogger(DanhSachPhongChieuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void showTableViewPhim() throws SQLException {
        clID.setCellValueFactory(new PropertyValueFactory<>("MaPhim"));
        clTenPhim.setCellValueFactory(new PropertyValueFactory<>("TenPhim"));
        clThoiLuong1.setCellValueFactory(new PropertyValueFactory<>("ThoiLuong"));
        clThongTinPhim.setCellValueFactory(new PropertyValueFactory<>("ThongTinPhim"));
        clThoiGianKhoiTao.setCellValueFactory(new PropertyValueFactory<>("ThoiGianKhoiTao"));
        clThoiGianCapNhat.setCellValueFactory(new PropertyValueFactory<>("ThoiGianCapNhat"));
        
        
        ObservableList<Phim> listPhim = FXCollections.observableArrayList();
        listPhim = phimDAO.selectAll();
        filmTable.setItems(listPhim);
    }

    @FXML
    private void create(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Chinh_Sua_Phim.fxml"));
        Parent root = (Parent) loader.load();
	ChinhSuaPhimController controller = loader.getController();
	controller.setThemMoiPhim();
	acpDanhSachPhim.getChildren().setAll(root);
    }
    
    @FXML
    private void repair(ActionEvent event) throws IOException {
        Phim selected = filmTable.getSelectionModel().getSelectedItem();
        
        if (selected == null){
            AlertBox.show(Alert.AlertType.INFORMATION, "Thông báo", "Chưa chọn phim để chỉnh sửa");
        }else{
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Chinh_Sua_Phim.fxml"));
                Parent root = (Parent) loader.load();
                ChinhSuaPhimController controller = loader.getController();
                PhimChinhSua.maPhim = selected.getMaPhim();
                controller.setChinhSuaPhim(selected);
                acpDanhSachPhim.getChildren().setAll(root);
            } catch (Exception e) {
                System.out.println("unable to load tab");
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void delete(ActionEvent event) throws SQLException {
        Phim selected = filmTable.getSelectionModel().getSelectedItem();
        if (selected !=  null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Xác nhận");
            alert.setHeaderText("Bạn muốn xóa phim này?");
            
            ButtonType buttonTypeYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
	        
	        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeCancel);

	        Optional<ButtonType> result = alert.showAndWait();

	        if (result.get()== buttonTypeYes)
                {
                                Phim phim = new Phim();
                                phimDAO.deletePhim(selected);
				Alert alert1 = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Thông báo");
                                alert.setHeaderText("Xóa phim thành công ");
                                alert.showAndWait();
	        }
		}
		else {
			Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Thông báo");
                        alert.setHeaderText("Chưa chọn phim nào");
                        alert.showAndWait();
		}
        showTableViewPhim();
        }
}

    
    
  
