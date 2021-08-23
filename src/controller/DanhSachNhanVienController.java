/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import alert.AlertBox;
import com.jfoenix.controls.JFXButton;
import dao.NhanVienChinhSua;
import dao.NhanVienDAO;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import model.NhanVien;
import model.NhanVien_demo;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class DanhSachNhanVienController implements Initializable {

    @FXML
    private AnchorPane acpDanhSachNhanVien;
    @FXML
    private TableView<NhanVien_demo> nhanVienTable;//Object model
    @FXML
    private JFXButton saveBtn;
    @FXML
    private ImageView addIcon;
    @FXML
    private ImageView activeIcon;
    @FXML
    private JFXButton editUserBtn;
    @FXML
    private ImageView repairIcon;
    @FXML
    private TableColumn<NhanVien_demo, Integer> clID;//todo
    @FXML
    private TableColumn<NhanVien_demo, String> clName;
    @FXML
    private TableColumn<NhanVien_demo, String> clEmaiil;
    @FXML
    private TableColumn<NhanVien_demo, String> clPhone;
    @FXML
    private TableColumn<NhanVien_demo, String> clPosition;
    @FXML
    private TableColumn<NhanVien_demo, String> clAddress;
    @FXML
    private TableColumn<NhanVien_demo, Boolean> clActivated;
    private NhanVienDAO nvDAO;
    @FXML
    private JFXButton btnChangeStatus;
    @FXML
    private JFXButton deleteBtn1;
    @FXML
    private ImageView deleteIcon;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        nvDAO = new NhanVienDAO();// khai bao doi tuong DAO truy xuat du lieu tu database
        try {
            showTableViewStaff();
        } catch (SQLException ex) {
            Logger.getLogger(DanhSachNhanVienController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void createUser(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Chinh_Sua_Nhan_Vien.fxml"));
        Parent root = (Parent) loader.load();
	ChinhSuaNhanVienController controller = loader.getController();
		controller.setNhanVienThemMoi();
		acpDanhSachNhanVien.getChildren().setAll(root);
    }

    @FXML
    private void changeStatusUser(ActionEvent event) throws SQLException {
        NhanVien_demo selected = nhanVienTable.getSelectionModel().getSelectedItem();
        if(selected != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	        alert.setTitle("Xác nhận");
	        alert.setHeaderText("Bạn muốn thay đổi trạng thái của nhân viên này");
	        
	        ButtonType buttonTypeYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
	        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
	        
	        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeCancel);

	        Optional<ButtonType> result = alert.showAndWait();
                
                if (result.get() == buttonTypeYes){
                    selected.setDangHoatDong(!selected.isDangHoatDong());
                }
        }
    }

    @FXML
    private void editUser(ActionEvent event) {
        NhanVien_demo selected = nhanVienTable.getSelectionModel().getSelectedItem();
        
        if (selected == null){
            AlertBox.show(Alert.AlertType.INFORMATION, "Thông báo", "Chưa chọn nhân viên để chỉnh sửa");
        }else{
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Chinh_Sua_Nhan_Vien.fxml"));
                Parent root = (Parent) loader.load();
                ChinhSuaNhanVienController controller = loader.getController();
                NhanVienChinhSua.maNV = selected.getMaNV();
                controller.setNhanVienChinhSua(selected);
                acpDanhSachNhanVien.getChildren().setAll(root);
            } catch (Exception e) {
                System.out.println("unable to load tab");
                e.printStackTrace();
            }
        }
    }
    
    
    public void showTableViewStaff() throws SQLException{
        clID.setCellValueFactory(new PropertyValueFactory<>("MaNV"));
        clName.setCellValueFactory(new PropertyValueFactory<>("HoTen"));//todo
        clEmaiil.setCellValueFactory(new PropertyValueFactory<>("Email"));
        clPhone.setCellValueFactory(new PropertyValueFactory<>("SoDienThoai"));
        clPosition.setCellValueFactory(new PropertyValueFactory<>("TenChucVu"));
        clAddress.setCellValueFactory(new PropertyValueFactory<>("DiaChi"));
        clActivated.setCellValueFactory(new PropertyValueFactory<>("DangHoatDong"));
        
        clActivated.setCellFactory(tablecell -> new TableCell<NhanVien_demo, Boolean>(){
            @Override
            protected void updateItem(Boolean active, boolean empty) {
                super.updateItem(active, empty); //To change body of generated methods, choose Tools | Templates.
                if (empty){
                    setText(null);
                }else{
                    if (active){
                        setText("Đang hoạt động");
                    }else{
                        setText("Không hoạt động");
                    }
                }
            }
            
        
        });
        
        ObservableList<NhanVien_demo> listNhanVien = FXCollections.observableArrayList();
        listNhanVien = nvDAO.selectAll();
        nhanVienTable.setItems(listNhanVien);
    }

    @FXML
    private void delete(ActionEvent event) throws SQLException {
        NhanVien_demo selected = nhanVienTable.getSelectionModel().getSelectedItem();
        if (selected !=  null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Xác nhận");
            alert.setHeaderText("Bạn muốn xóa nhân viên này?");
            
            ButtonType buttonTypeYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
	        
	        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeCancel);

	        Optional<ButtonType> result = alert.showAndWait();

	        if (result.get()== buttonTypeYes)
                {
                                NhanVien_demo nv = new NhanVien_demo();
                                nvDAO.deleteNhanVien(selected);
				Alert alert1 = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Thông báo");
                                alert.setHeaderText("Xóa nhân viên thành công");
                                alert.showAndWait();
	        }
		}
		else {
			Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Thông báo");
                        alert.setHeaderText("Chưa chọn nhân viên nào");
                        alert.showAndWait();
		}
        showTableViewStaff();
        }
}
