/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import dao.ChucVuDAO;
import dao.NhanVienChinhSua;
import dao.NhanVienDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.NhanVien;
import model.NhanVien_demo;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ChinhSuaNhanVienController implements Initializable {

    @FXML
    private AnchorPane acpChinhSuaNhanVien;
    @FXML
    private TextField newUserFullName;
    @FXML
    private TextField newUserEmail;
    @FXML
    private TextField newUserPhone;
    @FXML
    private TextField newUserAdress;
    @FXML
    private RadioButton newUserMale;
    private ToggleGroup gender;
    @FXML
    private RadioButton newUserFemale;
    @FXML
    private JFXComboBox<String> newUserRole;
    private PasswordField newUserPassword;
    private PasswordField confirmPassword;
    @FXML
    private JFXButton saveBtn;
    @FXML
    private JFXButton cancelBtn;
    @FXML
    private Label themHoacChinhSuaLabel;
    
    private ChucVuDAO chucVuDAO;
    private NhanVienDAO nhanVienDAO;
    private NhanVien_demo nhanVien;

    /**
     * Initializes the controller class.
     */
    ObservableList<String> list = FXCollections.observableArrayList("Quản lý","Nhân viên bán vé","Nhân viên bán thức ăn","Nhân viên quản lý phòng chiếu");
    @FXML
    private ToggleGroup group;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        chucVuDAO = new ChucVuDAO();
        nhanVienDAO = new NhanVienDAO();
        newUserRole.setItems(list);
        newUserRole.setStyle("-fx-font: 18px \"Serif\"; -fx-background-color:#ffffff");
    }

    public boolean setValidOfValue() {
        if (newUserFullName.getText() != null && !newUserFullName.getText().equals("")
                && newUserEmail.getText() != null && !newUserEmail.getText().equals("")
                && newUserPhone.getText() != null && !newUserPhone.getText().equals("")
                && newUserAdress.getText() != null && !newUserAdress.getText().equals("")) {
            return true;
        }
        return false;
    }
    
    public void setNhanVienThemMoi(){
        themHoacChinhSuaLabel.setText("Thêm nhân viên mới");
    	newUserFullName.setText("");
    	newUserEmail.setText("");
    	newUserPhone.setText("");
    	newUserAdress.setText("");
    	newUserFemale.setSelected(false);
    	newUserMale.setSelected(false);
    }

    public void setNhanVienChinhSua(NhanVien_demo nhanvien) {
        if (nhanvien != null) {
            nhanVien = nhanvien;
            themHoacChinhSuaLabel.setText("Chỉnh sửa nhân viên");
            newUserFullName.setText(nhanvien.getHoTen());
            newUserEmail.setText(nhanvien.getEmail());
            newUserPhone.setText(nhanvien.getSoDienThoai());
            newUserAdress.setText(nhanvien.getDiaChi());
//            newUserRole.getSelectionModel().select(getChucVuByMaChucVu(nhanvien.getMaChucVu()).getTenChucVu());
//            if (nhanvien.getGioiTinh().equals("Nam")) {
//                newUserMale.setSelected(true);
//                newUserFemale.setSelected(false);
//            } else {
//                newUserMale.setSelected(false);
//                newUserFemale.setSelected(true);
//            }
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
            Node DanhSachNhanVienPane = (Node) FXMLLoader.load(getClass().getResource("/view/Danh_sach_nhan_vien.fxml"));
            acpChinhSuaNhanVien.getChildren().setAll(DanhSachNhanVienPane);
        }
    }
    
    @FXML
    private void save(ActionEvent event) throws SQLException, IOException {
        String str = "Bạn chưa nhập đủ thông tin";
        if(themHoacChinhSuaLabel.getText().equals("Thêm nhân viên mới")){
            if(setValidOfValue() == true){
                NhanVien_demo nv = new NhanVien_demo();
                String hoTen = newUserFullName.getText();
                String email = newUserEmail.getText();
                String soDienThoai = newUserPhone.getText();
                String diaChi = newUserAdress.getText();
                
                
                
                
                newUserMale.setToggleGroup(gender);
                newUserMale.setSelected(true);
                
                
                newUserFemale.setToggleGroup(gender);
                
                int maCV  = nhanVienDAO.getMaCVBytTenCV(newUserRole.getValue());
                
                nv.setHoTen(hoTen);
                nv.setEmail(email);
                nv.setSoDienThoai(soDienThoai);
                nv.setDiaChi(diaChi);
                nv.setMaChucVu(maCV);
                
                nhanVienDAO.insertNhanVien(nv);
                showAlertAndChangeScene("Thêm phim thành công");
            }else {
                showAlert(str);
            }
        }else{
            if (setValidOfValue() == true){
                int maNV = NhanVienChinhSua.maNV;
                String hoTen = newUserFullName.getText();
                String email = newUserEmail.getText();
                String soDienThoai = newUserPhone.getText();
                String diaChi = newUserAdress.getText();
                int maChucVu = nhanVienDAO.getMaCVBytTenCV(newUserRole.getValue());
                
                nhanVienDAO.updatePhim(hoTen, email, soDienThoai, diaChi, maChucVu, maNV);
                
                showAlertAndChangeScene("Cập nhật phim thành công!");
            }else {
                showAlert(str);
            }
        }
    }
    
    

    @FXML
    private void cancel(ActionEvent event) throws IOException {
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
        if (result.get()== buttonTypeYes)
        {	
        	Node DanhSachNhanVienPane= (Node) FXMLLoader.load(getClass().getResource("/view/Danh_sach_nhan_vien.fxml"));
                //getChildren là liệt kê các thành phần con 
                //setAll là xóa tất cả phần tử hiện có và thay thế bằng các phần tử trong phương thức setAll
        	acpChinhSuaNhanVien.getChildren().setAll(DanhSachNhanVienPane);
        }
    }

    
}
