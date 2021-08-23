/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.PhimChinhSua;
import dao.PhimDAO;
import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import model.Phim;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ThongTinPhimController implements Initializable {

    @FXML
    private AnchorPane acpThongTinPhim;
    @FXML
    private ScrollPane scrollPane;
    String id;

    private ArrayList<Phim> phimList;
    @FXML
    private FlowPane flowPaneMovies;

    private PhimDAO phimDAO;
    @FXML
    private HBox HBox;
    private Image image;
    private FileChooser fileChooser;
    private File file;
    private final Desktop desktop = Desktop.getDesktop();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        phimDAO = new PhimDAO();
        try {
            showMovies();
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(ThongTinPhimController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    

    public void showMovies() throws SQLException {

        ObservableList<Phim> listMovies = FXCollections.observableArrayList();
        listMovies = phimDAO.selectAll();
        Font x = new Font("",14);

        Label[] lbListMovie = new Label[listMovies.size()];
        
        
        for (int i = 0; i < listMovies.size(); i++) {
            //File file = new File("" + listMovies.get(i).getImage());
//            file = fileChooser.showOpenDialog(null);
//        
//            
//            image = new Image(file.toURI().toString(), 200, 280, true, true);
//            ImageView iv = new ImageView(image.getClass().get(listMovies.get(i).getImage()));
//            Image img= new Image(getClass().getResource("C:\Users\ASUS\OneDrive\Documents\NetBeansProjects\DoAn1\src\image\*.jpg").toString());
            System.out.println(listMovies.get(i).getImage());
          
         
             File f = new File(listMovies.get(i).getImage());
             Image img = new Image(f.toURI().toString());
                     
            // getResourceAsStream hàm này nghiaax là tìm cái file trong thư mục resource á em
            ImageView iv = new ImageView(img) ;
            iv.setFitHeight(280);
            iv.setFitWidth(200);
//            ImageView iv = new ImageView(new Image(getClass().getResourceAsStream(listMovies.get(i).getImage())));
//            
//            iv.setFitHeight(280);
//            iv.setFitWidth(200);
            
            
//            danhSachPhimGrid.setFillWidth(lbListMovie[i], true);
            //iv.setPreserveRatio(true);

            lbListMovie[i] = new Label();
            lbListMovie[i].setText(listMovies.get(i).getTenPhim());
            lbListMovie[i].setAlignment(Pos.CENTER);
            lbListMovie[i].setContentDisplay(ContentDisplay.TOP);
            lbListMovie[i].setMaxWidth(160);
            lbListMovie[i].setMaxHeight(200);
            lbListMovie[i].setWrapText(true);
            lbListMovie[i].setFont(x);
            lbListMovie[i].setGraphic(iv);
            
            final int j = i;
            lbListMovie[i].setOnMouseClicked((mouseEvent) -> {
                
                try {
                    ObservableList<Phim> listMovies1 = FXCollections.observableArrayList();
                    listMovies1 = phimDAO.selectAll();
                    PhimChinhSua.maPhim = listMovies1.get(j).getMaPhim();
                    
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Chi_Tiet_Phim.fxml"));
                    Parent root = (Parent) loader.load();
                    ChiTietPhimcontroller controller = loader.getController();
                    acpThongTinPhim.getChildren().setAll(root);
                    
//                    Parent root = FXMLLoader.load(getClass().getResource("/view/Chi_Tiet_Phim.fxml"));
//                    Scene scene = new Scene(root);
//                    Stage stage = new Stage();
//                    
//                    stage.setScene(scene);
//                    stage.show();
                    
                } catch (IOException ex) {
                    Logger.getLogger(ThongTinPhimController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(ThongTinPhimController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                    
                    
            });
        }
        //flowPaneMovies.setPadding(new Insets(30, 30, 30, 30));
       
        flowPaneMovies.getChildren().clear();
        for (int i = 0; i < listMovies.size(); i++) {
            flowPaneMovies.getChildren().add(lbListMovie[i]);
        }
    }
    @FXML
    public void xemThongTinPhim(MouseEvent event) throws IOException {
        Node acpChiTietPhim = (Node) FXMLLoader.load(getClass().getResource("/view/Chi_Tiet_Phim.fxml"));
        acpThongTinPhim.getChildren().setAll(acpChiTietPhim);
    }
}
