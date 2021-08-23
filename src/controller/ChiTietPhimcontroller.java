/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import dao.PhimChinhSua;
import dao.PhimDAO;
import dao.PhimDangChon;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import model.Phim;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ChiTietPhimcontroller implements Initializable {

    @FXML
    private AnchorPane acpChiTietPhim;
    @FXML
    private ImageView imageFilm;
    @FXML
    private Label nameFilmLabel;
    @FXML
    private Label inforFilmLabel;
    @FXML
    private Label timeStartLabel;
    @FXML
    private Label timeFilmLabel;
    @FXML
    private ImageView backIcon;
    
    private PhimDAO phimDAO;
    @FXML
    private JFXButton btnDatVe;
    Phim phim;
    public static PhimDangChon phimDangChon;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        phimDAO = new PhimDAO();
        try {
            showChiTietPhim();
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietPhimcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    @FXML
	public void back() throws IOException
	{
		Node ThongTinPhimPane= (Node) FXMLLoader.load(getClass().getResource("/view/Thong_Tin_Phim.fxml"));
		acpChiTietPhim.getChildren().setAll(ThongTinPhimPane);
	}
    
    public void showChiTietPhim() throws SQLException {
        phim = new Phim();
        phim = phimDAO.getPhimByMaPhim(PhimChinhSua.maPhim);
        
        nameFilmLabel.setText(phim.getTenPhim());
        inforFilmLabel.setText(phim.getThongTinPhim());
        timeStartLabel.setText(""+phim.getThoiGianKhoiTao());
        timeFilmLabel.setText(""+phim.getThoiLuong());
        
//        Image image = new Image(getClass().getResourceAsStream(phim.getImage()));
//        imageFilm.setImage(image);
        File f = new File(phim.getImage());
        Image img = new Image(f.toURI().toString());
        ImageView iv = new ImageView(img) ;
        imageFilm.setImage(img);
        phimDangChon = new PhimDangChon();
        phimDangChon.setMaPhim(phim.getMaPhim());
        phimDangChon.setTenPhim( phim.getTenPhim());
        phimDangChon.setThoiluong(Integer.toString(phim.getThoiLuong()));
    }

    @FXML
    private void clickDatVe(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Ban_Ve.fxml"));
        Parent root = (Parent) loader.load();
	BanVeController controller = loader.getController();
	acpChiTietPhim.getChildren().setAll(root);
    }
    
}
