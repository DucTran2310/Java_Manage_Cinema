/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import alert.AlertBox;
import alert.DBConnectionFactory;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class LoginController implements Initializable {

    @FXML
    private TextField userName;
    @FXML
    private PasswordField pass;
    @FXML
    private JFXButton Login;
    @FXML
    private JFXButton exit;
    
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    @FXML
    private Label lbThongBao;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clickLogin(ActionEvent event) throws SQLException, IOException {
        String username = userName.getText();
        String password = pass.getText();
        
        if(username.equals("") || password.equals("")){
            AlertBox.show(AlertType.INFORMATION, "Thông báo", "Vui lòng nhập đủ username và password");
        }else{
            try {
                con = DBConnectionFactory.getConnection();
                pst = con.prepareStatement("select * from taikhoan where Tentaikhoan = ? and Matkhau = ?");
                pst.setString(1, username);
                pst.setString(2, password);
                
                rs = pst.executeQuery();
                
                if(rs.next()){
                    JOptionPane.showMessageDialog(null, "Đăng nhập thành công");
//                    lbThongBao.setText("Đăng nhập thành công");
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/App.fxml"));
                    Scene secondScene = new Scene(root, 1200, 881);
                    secondScene.getStylesheets().add(getClass().getResource("/alert/application.css").toExternalForm());
                    stage.setScene(secondScene);
                }else{
                    JOptionPane.showMessageDialog(null, "Đăng nhập thất bại");
//                    lbThongBao.setText("Đăng nhập thất bại");
                    userName.setText("");
                    pass.setText("");
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }

    @FXML
    private void clickExit(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận");
        alert.setHeaderText("Bạn có muốn thoát?");
        alert.setContentText("Lựa chọn của bạn:");
        
        ButtonType buttonTypeYes = new ButtonType("Có", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeCancel = new ButtonType("Không", ButtonBar.ButtonData.CANCEL_CLOSE);
        
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get()== buttonTypeYes)
        {
        	System.exit(0);
        }
    }
}
    

