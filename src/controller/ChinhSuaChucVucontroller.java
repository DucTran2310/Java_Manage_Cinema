/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import dao.NhanVienDAO;
import dao.PhanQuyenDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.PhanQuyen;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ChinhSuaChucVucontroller implements Initializable {

    @FXML
    private AnchorPane acpChinhSuaChucVu;
    @FXML
    private TextField newPositionName;
    @FXML
    private TextArea describePosition;
    @FXML
    private JFXButton saveBtn;
    private JFXButton deleteBtn;
    @FXML
    private Label themHoacChinhSuaLabel;
    @FXML
    private JFXButton cancelBtn;
    private ImageView deleteIcon;
    @FXML
    private JFXComboBox<String> tenNVComBoBox;
    
    private PhanQuyenDAO phanquyenDAO;
    
    private PhanQuyen chucvuSelected;
    
    private NhanVienDAO nhanvienDAO;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        phanquyenDAO = new PhanQuyenDAO();
        tenNVComBoBox.setStyle("-fx-font: 18px \"Serif\"; -fx-background-color:#ffffff");
    }    
    
    public void setThemChucVuMoi() {
        themHoacChinhSuaLabel.setText("Thêm chức vụ mới");
        newPositionName.setText("");
        describePosition.setText("");
        
    }
    
    public void setChinhSuaChucVu(PhanQuyen selected){
        if(selected != null){
            chucvuSelected = selected;
            themHoacChinhSuaLabel.setText("Chỉnh sửa chức vụ");
            newPositionName.setText(selected.getChucVu());
            describePosition.setText(selected.getQuyen());
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

        if (result.get() == buttonTypeYes) {
            Node DanhSachThucAnPane = (Node) FXMLLoader.load(getClass().getResource("/view/Phan_quyen.fxml"));
            acpChinhSuaChucVu.getChildren().setAll(DanhSachThucAnPane);
        }
    }
    
    public Boolean kiemTraChucVu(){
        if(newPositionName.getText() != null && !newPositionName.getText().equals("")
                && describePosition.getText() != null && !describePosition.getText().equals(""))
            return true;
        return false;
    }

    @FXML
    private void save(ActionEvent event) throws SQLException {
        String str = "Bạn chưa nhập đủ thông tin";
        if (themHoacChinhSuaLabel.getText().equals("Thêm chức vụ mới")){
            if(kiemTraChucVu() == true){
                PhanQuyen phanquyen = new PhanQuyen();
                int maNV = nhanvienDAO.getMaNVBytTenNV(tenNVComBoBox.getValue());
                String tenChucVu = newPositionName.getText();
                String moTa = describePosition.getText();
                
//                phanquyen.setHoTen(maNV);
                
            }
        }
    }


    @FXML
    private void cancel(ActionEvent event) throws IOException {
        //Alert hủy bỏ
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Cảnh báo");
        alert.setHeaderText("Bạn có muốn hủy thay đổi");
        alert.setContentText("Thông tin sẽ không lưu khi bạn hủy!");
        
        //ButtonType để chỉ định nút nào sẽ được hiện thị trong hộp thoại
        ButtonType buttonTypeYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeCancel);
        
        Optional<ButtonType> result = alert.showAndWait();
        
        //Quay về trang Chức vụ
        if(result.get()== buttonTypeYes) {
            Node  DanhSachChucVuPane = (Node) FXMLLoader.load(getClass().getResource("/view/Phan_quyen.fxml"));
            //getChildren là liệt kê các thành phần con 
            //setAll là xóa tất cả phần tử hiện có và thay thế bằng các phần tử trong phương thức setAll
            acpChinhSuaChucVu.getChildren().setAll(DanhSachChucVuPane);
        }
    }
    
    
    
}
