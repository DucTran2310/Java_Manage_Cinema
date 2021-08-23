/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import dao.PhimChinhSua;
import dao.PhimDAO;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.layout.AnchorPane;
import model.Phim;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ChinhSuaPhimController implements Initializable {

    @FXML
    private AnchorPane acpChinhSuaPhim;
    @FXML
    private Label themHoacChinhSuaLabel;
    private TextField newFilmName;
    private TextField newFilmTime;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnCancle;
    @FXML
    private JFXComboBox<String> newFilmTitle;
    private TextField newFilmLinkImage;
    private TextArea newFilmInformation;
    
    private Phim phimSelected;
    
    private PhimDAO phimDAO;
    @FXML
    private TextField tfNameFilm;
    @FXML
    private TextField tfTimeFilm;
    @FXML
    private TextField tfLinkImageFilm;
    @FXML
    private TextArea tfInformationFilm;
    
    private FileChooser fileChooser;
    private File file;
    private final Desktop desktop = Desktop.getDesktop();
    private Image image;
    
    /**
     * Initializes the controller class.
     */
    ObservableList<String> list = FXCollections.observableArrayList("Hành động","Khoa học viễn tưởng","Tình cảm","Kinh dị","Hài hước");
    @FXML
    private JFXButton btnBrowse;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        phimDAO = new PhimDAO();
        newFilmTitle.setItems(list);
        newFilmTitle.setStyle("-fx-font: 18px \"Serif\"; -fx-background-color:#ffffff");
    }    
    
    public void setThemMoiPhim(){
        themHoacChinhSuaLabel.setText("Thêm phim mới");
        tfNameFilm.setText("");
        tfTimeFilm.setText("");
        tfInformationFilm.setText("");
    }
    
    public void setChinhSuaPhim(Phim selected ){
        if (selected != null) {
            phimSelected = selected;
            themHoacChinhSuaLabel.setText("Chỉnh sửa phim");
            tfNameFilm.setText(selected.getTenPhim());
            tfTimeFilm.setText(selected.getThoiLuong() + "");
            tfInformationFilm.setText(selected.getThongTinPhim());
            tfLinkImageFilm.setText(selected.getImage());
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
            Node DanhSachThucAnPane = (Node) FXMLLoader.load(getClass().getResource("/view/Danh_sach_phim.fxml"));
            acpChinhSuaPhim.getChildren().setAll(DanhSachThucAnPane);
        }
    }
    
    public Boolean kiemTraPhim(){
        if(tfNameFilm.getText() != null && !tfTimeFilm.getText().equals("")
			&& tfTimeFilm.getText() != null && !tfTimeFilm.getText().equals("")
			&& tfInformationFilm.getText() != null && !tfInformationFilm.getText().equals(""))
            return true;
        return false;
    }
    


    @FXML
    private void save(ActionEvent event) throws IOException, SQLException {
        String str = "Bạn chưa nhập đủ thông tin";
        if (themHoacChinhSuaLabel.getText().equals("Chỉnh sửa phim")){
            if(kiemTraPhim() == true){
                int maPhim = PhimChinhSua.maPhim;
                String tenPhim = tfNameFilm.getText();
                int thoiLuong = Integer.parseInt(tfTimeFilm.getText());
                String thongTinPhim = tfInformationFilm.getText();
                
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                java.util.Date date = new java.util.Date();                
                Timestamp thoiGianCapNhat = new java.sql.Timestamp(date.getTime());
                
                String image = tfLinkImageFilm.getText();
                
                phimDAO.updatePhim(tenPhim, thoiLuong, thongTinPhim, thoiGianCapNhat, image, maPhim);
                
                showAlertAndChangeScene("Cập nhật phim thành công!");
            } else {
                showAlert(str);
            }
        } else {
            if(kiemTraPhim() == true){
                Phim phim = new Phim();
                String tenPhim = tfNameFilm.getText();
                int thoiLuong = Integer.parseInt(tfTimeFilm.getText());
                String thongTinPhim = tfInformationFilm.getText();
                int maTL  = phimDAO.getMaTLBytTenTL(newFilmTitle.getValue());
                
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                java.util.Date date = new java.util.Date(); 
                Timestamp thoiGianKhoiTao = new java.sql.Timestamp(date.getTime());
                Timestamp thoiGianCapNhat = new java.sql.Timestamp(date.getTime());
                
                
                String image = tfLinkImageFilm.getText();
                
                phim.setTenPhim(tenPhim);
                phim.setThoiLuong(thoiLuong);
                phim.setThongTinPhim(thongTinPhim);
                phim.setMaTL(maTL);
                phim.setThoiGianKhoiTao(thoiGianKhoiTao);
                phim.setThoiGianCapNhat(thoiGianCapNhat);
                phim.setImage(image);
                
                phimDAO.insertPhim(phim);
                showAlertAndChangeScene("Thêm phim thành công");
            } else {
                showAlert(str);
            }
        }
    }

    @FXML
    private void cancle(ActionEvent event) throws IOException {
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
        	Node DanhSachPhimPane= (Node) FXMLLoader.load(getClass().getResource("/view/Danh_sach_phim.fxml"));
                //getChildren là liệt kê các thành phần con 
                //setAll là xóa tất cả phần tử hiện có và thay thế bằng các phần tử trong phương thức setAll
        	acpChinhSuaPhim.getChildren().setAll(DanhSachPhimPane);
        }
    }

    @FXML
    private void browse(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        
        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);
        
        //Show open file dialog
        file = fileChooser.showOpenDialog(null);
        
        if(file != null){
            tfLinkImageFilm.setText(file.getAbsolutePath());
            image = new Image(file.toURI().toString(),200,280,true,true);
        }
        
        
    }
    
    
}
