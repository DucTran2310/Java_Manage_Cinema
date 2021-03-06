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
        themHoacChinhSuaLabel.setText("Th??m m???i th???c ??n");
    }

    public void setChinhSuaThucAn(Quan_Ly_Thuc_An selected) {
        if (selected != null) {
            thucAnSelected = selected;
            themHoacChinhSuaLabel.setText("Ch???nh s???a th???c ??n");
            tfTenThucAn.setText(selected.getTenThucAn());
            tfGiaThucAn.setText(selected.getGiaTA() + "");
        }
    }

    public void showAlert(String str) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Th??ng b??o");
        alert.setHeaderText(str);
        alert.setContentText("X??c nh???n");

        ButtonType buttonTypeCancel = new ButtonType("OK", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeCancel);
        Optional<ButtonType> result = alert.showAndWait();
    }

    public void showAlertAndChangeScene(String str) throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Th??ng b??o");
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
        //Alert h???y b???
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("C???nh B??o");
        alert.setHeaderText("B???n c?? ch???c mu???n h???y thay ?????i");
        alert.setContentText("Th??ng tin s??? kh??ng ???????c l??u khi b???n h???y.");

        //T???o c??c button s??? hi???n th???
        ButtonType buttonTypeYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();

        //Quay v??? trang danh s??ch nh??n vi??n
        if (result.get() == buttonTypeYes) {
            Node DanhSachThucAnPane = (Node) FXMLLoader.load(getClass().getResource("/view/Quan_Ly_Thuc_An.fxml"));
            //getChildren l?? li???t k?? c??c th??nh ph???n con 
            //setAll l?? x??a t???t c??? ph???n t??? hi???n c?? v?? thay th??? b???ng c??c ph???n t??? trong ph????ng th???c setAll
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

        String str = "B???n ch??a nh???p ????? th??ng tin";
        if (themHoacChinhSuaLabel.getText().equals("Ch???nh s???a th???c ??n")) {
            if (tfTenThucAn.getText() != null && !tfTenThucAn.getText().equals("")
                    && tfGiaThucAn.getText() != null && !tfGiaThucAn.getText().equals("")) {
                String tenThucAn = tfTenThucAn.getText();
                int giaThucAn = Integer.parseInt(tfGiaThucAn.getText());
                int maThucAn = ThucAnChinhSua.maThucAn;

                thucAnDAO.updateThucAn(tenThucAn, giaThucAn, maThucAn);
                showAlertAndChangeScene("C???p nh???t th???c ??n th??nh c??ng!");
                
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
                showAlertAndChangeScene("Th??m th??nh c??ng th???c ??n m???i");
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
