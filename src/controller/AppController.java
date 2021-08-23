/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AppController implements Initializable {

    @FXML
    private AnchorPane acpApp;
    @FXML
    private AnchorPane acpSlideBar;
    @FXML
    private ImageView iconThongTinCaNhan;
    @FXML
    private Label lbTen;
    @FXML
    private Label lbChucVu;
    @FXML
    private JFXButton btnDachSachPhim;
    @FXML
    private ImageView iconDanhSachPhim;
    @FXML
    private JFXButton btnThucAn;
    @FXML
    private ImageView iconThucAn;
    @FXML
    private JFXButton btnNhanVien;
    @FXML
    private ImageView iconNhanVIen;
    @FXML
    private JFXButton btnPhanQuyen;
    @FXML
    private ImageView iconPhanQuyen;
    @FXML
    private JFXButton btnQuanLyRapPhim;
    @FXML
    private ImageView iconQuanLyRapPhim;
    @FXML
    private JFXButton btnQuanLyDoAn;
    @FXML
    private ImageView iconQuanLyDoAn;
    @FXML
    private JFXButton btnQuanLyLich;
    @FXML
    private ImageView iconQuanLyLich;
    @FXML
    private JFXButton btnQuanLyPhim;
    @FXML
    private ImageView iconQuanLyPhim;
    @FXML
    private JFXButton btnQuanLyPhong;
    @FXML
    private ImageView iconQuanLyPhong;
    @FXML
    private JFXButton btnQuanLyGhe;
    @FXML
    private ImageView iconQuanLyGhe;
    @FXML
    private JFXButton btnDoanhThu;
    @FXML
    private ImageView iconDoanhThu;
    @FXML
    private JFXButton btnQuanLyNhanVien;
    @FXML
    private ImageView iconQuanLyNhanVien;
    private AnchorPane acpContainer;
    @FXML
    private JFXButton btnClose;
    @FXML
    public  Pane paneContainer;
    @FXML
    private JFXButton btnVe;

    Node acpThongTinPhim;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            Pane newPane = FXMLLoader.load(getClass().getResource("/view/Thong_Tin_Phim.fxml"));
            paneContainer.getChildren().clear();
            paneContainer.getChildren().add(newPane);
        } catch (IOException ex) {
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void clickDanhSachPhim(ActionEvent event) throws IOException {
        Pane newPane = FXMLLoader.load(getClass().getResource("/view/Thong_Tin_Phim.fxml"));
        paneContainer.getChildren().clear();
        paneContainer.getChildren().add(newPane);
    }

    @FXML
    private void clickThucAn(ActionEvent event) throws IOException {
        Pane newPane = FXMLLoader.load(getClass().getResource("/view/Ban_Thuc_An.fxml"));
        paneContainer.getChildren().clear();
        paneContainer.getChildren().add(newPane);
    }

    @FXML
    private void clickNhanVien(ActionEvent event) throws IOException {
        Pane newPane = FXMLLoader.load(getClass().getResource("/view/Danh_sach_nhan_vien.fxml"));
        paneContainer.getChildren().clear();
        paneContainer.getChildren().add(newPane);
    }

    @FXML
    private void clickPhanQuyen(ActionEvent event) throws IOException {
        Pane newPane = FXMLLoader.load(getClass().getResource("/view/Phan_quyen.fxml"));
        paneContainer.getChildren().clear();
        paneContainer.getChildren().add(newPane);
    }

    @FXML
    private void clickQuanLyRapPhim(ActionEvent event) {
    }

    @FXML
    private void clickQuanLyDoAn(ActionEvent event) throws IOException {
        Pane newPane = FXMLLoader.load(getClass().getResource("/view/Quan_Ly_Thuc_An.fxml"));
        paneContainer.getChildren().clear();
        paneContainer.getChildren().add(newPane);
    }

    @FXML
    private void clickQuanLyLich(ActionEvent event) throws IOException {
        Pane newPane = FXMLLoader.load(getClass().getResource("/view/Quan_Ly_Lich_Phim.fxml"));
        paneContainer.getChildren().clear();
        paneContainer.getChildren().add(newPane);
    }

    @FXML
    private void clickQuanLyPhim(ActionEvent event) throws IOException {
        Pane newPane = FXMLLoader.load(getClass().getResource("/view/Danh_sach_phim.fxml"));
        paneContainer.getChildren().clear();
        paneContainer.getChildren().add(newPane);
    }

    @FXML
    private void clickQuanLyPhong(ActionEvent event) throws IOException {
        Pane newPane = FXMLLoader.load(getClass().getResource("/view/Danh_sach_phong_chieu.fxml"));
        paneContainer.getChildren().clear();
        paneContainer.getChildren().add(newPane);
    }

    @FXML
    private void clickQuanLyGhe(ActionEvent event) throws IOException {
        Pane newPane = FXMLLoader.load(getClass().getResource("/view/Danh_sach_loai_ghe.fxml"));
        paneContainer.getChildren().clear();
        paneContainer.getChildren().add(newPane);
    }

    @FXML
    private void clickDoanhThu(ActionEvent event) throws IOException {
        Pane newPane = FXMLLoader.load(getClass().getResource("/view/Doanh_Thu.fxml"));
        paneContainer.getChildren().clear();
        paneContainer.getChildren().add(newPane);
    }

    @FXML
    private void clickQuanLyNhanVien(ActionEvent event) {
    }

    @FXML
    private void clickVe(ActionEvent event) throws IOException {
        Pane newPane = FXMLLoader.load(getClass().getResource("/view/Ve.fxml"));
        paneContainer.getChildren().clear();
        paneContainer.getChildren().add(newPane);
    }

    @FXML
    private void exit(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận");
        alert.setHeaderText("Bạn có muốn thoát?");
        alert.setContentText("Lựa chọn của bạn:");

        ButtonType buttonTypeYes = new ButtonType("Có", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeCancel = new ButtonType("Không", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == buttonTypeYes) {
            System.exit(0);
        }
    }

    public  Pane getPaneContainer() {
        return paneContainer;
    }
    

}
