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
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Quan_Ly_Thuc_An;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ChinhSuaThucAnController implements Initializable {

    @FXML
    private AnchorPane acpChinhSuaThucAn;
    @FXML
    private Label themHoacChinhSuaLabel;
    @FXML
    private JFXButton cancelBtn;

    private Quan_Ly_Thuc_An thucAnSelected;
    @FXML
    private TextField tfTenThucAn;
    @FXML
    private TextField tfGiaThucAn;

    private QuanLyThucAn_DAO thucAnDAO;
    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnThemThucAn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        thucAnDAO = new QuanLyThucAn_DAO();
        tfGiaThucAn.setText("" + ThucAnChinhSua.giaThucAn);
        tfTenThucAn.setText(ThucAnChinhSua.tenThucAn);

        //Listener check chi cho phep nhap so khong cho nhap chu cai
        tfGiaThucAn.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                tfGiaThucAn.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    public void setThemMoiThucAn() {
        themHoacChinhSuaLabel.setText("Thêm mới thức ăn");
    }

    public void setChinhSuaThucAn(Quan_Ly_Thuc_An selected) {
        if (selected != null) {
            thucAnSelected = selected;
            themHoacChinhSuaLabel.setText("Chỉnh sửa thức ăn");
            tfTenThucAn.setText(selected.getTenThucAn());
            tfGiaThucAn.setText(selected.getGiaTA() + "");
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
            Node DanhSachThucAnPane = (Node) FXMLLoader.load(getClass().getResource("/view/Quan_Ly_Thuc_An.fxml"));
            acpChinhSuaThucAn.getChildren().setAll(DanhSachThucAnPane);
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
        if (result.get() == buttonTypeYes) {
            Node DanhSachThucAnPane = (Node) FXMLLoader.load(getClass().getResource("/view/Quan_Ly_Thuc_An.fxml"));
            //getChildren là liệt kê các thành phần con 
            //setAll là xóa tất cả phần tử hiện có và thay thế bằng các phần tử trong phương thức setAll
            acpChinhSuaThucAn.getChildren().setAll(DanhSachThucAnPane);
        }
    }

    @FXML
    private void handleThemTHucAn(ActionEvent event) throws SQLException, IOException {
//        Quan_Ly_Thuc_An thucAn = new Quan_Ly_Thuc_An();
//        String tenThucAn = tfTenThucAn.getText();
//        int giaThucAn = Integer.parseInt(tfGiaThucAn.getText());
//        thucAn.setTenThucAn(tenThucAn);
//        thucAn.setGiaTA(giaThucAn);
//        thucAnDAO.insertThucAn(thucAn);
//        Alert al = new Alert(Alert.AlertType.INFORMATION);
//        al.setTitle("Thong tin them thuc an");
//        al.setHeaderText("Thanh cong");
//        al.setContentText("Them thuc an thanh cong");
//        al.showAndWait();

        String str = "Bạn chưa nhập đủ thông tin";
        if (themHoacChinhSuaLabel.getText().equals("Chỉnh sửa thức ăn")) {
            if (tfTenThucAn.getText() != null && !tfTenThucAn.getText().equals("")
                    && tfGiaThucAn.getText() != null && !tfGiaThucAn.getText().equals("")) {
                String tenThucAn = tfTenThucAn.getText();
                int giaThucAn = Integer.parseInt(tfGiaThucAn.getText());
                int maThucAn = ThucAnChinhSua.maThucAn;

                thucAnDAO.updateThucAn(tenThucAn, giaThucAn, maThucAn);
                showAlertAndChangeScene("Cập nhật thức ăn thành công!");
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Quan_Ly_Thuc_An.fxml"));
                Parent root = (Parent) loader.load();
                DanhSachThucAnController controller = loader.getController();
                controller.showTableViewFood();
            } else {
                showAlert(str);
            }
        } else {
            if (tfTenThucAn.getText() != null && !tfTenThucAn.getText().equals("")
                    && tfGiaThucAn.getText() != null && !tfGiaThucAn.getText().equals("")) {
                Quan_Ly_Thuc_An thucAn = new Quan_Ly_Thuc_An();
                String tenThucAn = tfTenThucAn.getText();
                int giaThucAn = Integer.parseInt(tfGiaThucAn.getText());
                thucAn.setTenThucAn(tenThucAn);
                thucAn.setGiaTA(giaThucAn);
                thucAnDAO.insertThucAn(thucAn);
                showAlertAndChangeScene("Thêm thành công thức ăn mới");
            } else {
                showAlert(str);
            }
        }
    }

    @FXML
    private void handleClear(ActionEvent event) {
        tfGiaThucAn.setText("0");
        tfTenThucAn.setText(null);
    }

}
