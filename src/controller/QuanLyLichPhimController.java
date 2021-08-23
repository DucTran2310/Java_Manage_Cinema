/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import alert.AlertBox;
import com.jfoenix.controls.JFXButton;
import dao.LichPhimChinhSua;
import dao.LichPhimDAO;
import dao.PhimDAO;
import dao.PhongChieuDAO;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.LichChieuPhim;
import model.LichChieuPhim_demo;
import model.Phim;
import model.PhongChieu;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class QuanLyLichPhimController implements Initializable {

    @FXML
    private AnchorPane acpQuanLyLichPhim;
    @FXML
    private Label themHoacChinhSuaLabel;
    @FXML
    private ImageView addIcon;
    @FXML
    private TableView<LichChieuPhim_demo> lichPhimTable;
    @FXML
    private JFXButton editBtn;
    @FXML
    private ImageView repairIcon;
    @FXML
    private JFXButton deleteBtn;
    @FXML
    private ImageView deleteIcon;
    @FXML
    private JFXButton addBtn;
    @FXML
    private TableColumn<LichChieuPhim_demo, Integer> clMaLich;
    @FXML
    private TableColumn<LichChieuPhim_demo, String> clTenPhim;
    @FXML
    private TableColumn<LichChieuPhim_demo, String> clTenPhong;
    @FXML
    private TableColumn<LichChieuPhim_demo, String> clThoiGianBatDau;
    
    private LichPhimDAO lpDAO;
    @FXML
    private TableColumn<LichChieuPhim_demo, String> clNgayChieu;
    @FXML
    private TableColumn<LichChieuPhim_demo, String> clThoiGianKetThuc;
    
    private LichPhimChinhSua lpcs;
    
    private PhimDAO phimDAO;
    private PhongChieuDAO phongChieuDAO;
    
    private ObservableList<Phim> phimList;
    private ObservableList<PhongChieu> phongChieuList;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lpDAO = new LichPhimDAO();// khai bao doi tuong DAO truy xuat du lieu tu database
        lpcs = new LichPhimChinhSua();
        try {
            showTableViewLichPhim();
        } catch (SQLException ex) {
            Logger.getLogger(DanhSachPhongChieuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void showTableViewLichPhim() throws SQLException {
        clMaLich.setCellValueFactory(new PropertyValueFactory<>("MaLichPhim"));
        clTenPhim.setCellValueFactory(new PropertyValueFactory<>("TenPhim"));
        clTenPhong.setCellValueFactory(new PropertyValueFactory<>("TenPhong"));
        clNgayChieu.setCellValueFactory(new PropertyValueFactory<>("NgayChieu"));
        clThoiGianBatDau.setCellValueFactory(new PropertyValueFactory<>("ThoiGianBatDau"));
        clThoiGianKetThuc.setCellValueFactory(new PropertyValueFactory<>("ThoiGianKetThuc"));
        
//        private StringProperty tenPhim = new StringProperty();
        
        ObservableList<LichChieuPhim_demo> listLichPhim = FXCollections.observableArrayList();
        listLichPhim = lpDAO.selectAll();
        lichPhimTable.setItems(listLichPhim);
    }

    @FXML
    private void create(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Chinh_Sua_Lich_Phim.fxml"));
        Parent root = (Parent) loader.load();
	ChinhSuaLichPhimController controller = loader.getController();
	controller.setThemMoiLichPhim();
	acpQuanLyLichPhim.getChildren().setAll(root);
    }

    @FXML
    private void edit(ActionEvent event) throws IOException, SQLException {
        LichChieuPhim_demo selected = lichPhimTable.getSelectionModel().getSelectedItem();
        
        if (selected == null) {
            AlertBox.show(Alert.AlertType.INFORMATION, "Thông báo", "Chưa chọn lịch phim để chỉnh sửa");
        } else {
            try {
                
                LichPhimChinhSua.maLichPhim = selected.getMaLichPhim();
                LichPhimChinhSua.maPhim = selected.getMaPhim();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Chinh_Sua_Lich_Phim.fxml"));
                Parent root = (Parent) loader.load();
                ChinhSuaLichPhimController controller = loader.getController();
                
                controller.setChinhSuaLichPhim(selected);
                acpQuanLyLichPhim.getChildren().setAll(root);
            } catch (IOException e) {
                System.out.println("unable to load tab");
            }
        }
    }

    @FXML
    private void delete(ActionEvent event) throws SQLException {
        LichChieuPhim_demo selected = lichPhimTable.getSelectionModel().getSelectedItem();
        if (selected !=  null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Xác nhận");
            alert.setHeaderText("Bạn muốn xóa lịch phim này?");
            
            ButtonType buttonTypeYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
	        
	        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeCancel);

	        Optional<ButtonType> result = alert.showAndWait();

	        if (result.get()== buttonTypeYes)
                {
                                LichChieuPhim_demo lichphim = new LichChieuPhim_demo();
                                lpDAO.deleteLichPhim(selected);
				Alert alert1 = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Thông báo");
                                alert.setHeaderText("Xóa thành công lịch phim ");
                                alert.showAndWait();
	        }
		}
		else {
			Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Thông báo");
                        alert.setHeaderText("Chưa chọn lịch phim nào");
                        alert.showAndWait();
		}
        showTableViewLichPhim();
        }
    }
    
    

