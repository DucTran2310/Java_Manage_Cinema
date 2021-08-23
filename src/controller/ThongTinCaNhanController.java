/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ThongTinCaNhanController implements Initializable {

    @FXML
    private AnchorPane acpThongTinCaNhan;
    @FXML
    private TextField newUserFullName;
    @FXML
    private TextField newUserName;
    @FXML
    private TextField newUserEmail;
    @FXML
    private TextField newUserPhone;
    @FXML
    private TextField newUserAdress;
    @FXML
    private PasswordField newUserPassword;
    @FXML
    private PasswordField confirmPassword;
    @FXML
    private JFXButton saveBtn;
    @FXML
    private Label dangerLabel;
    @FXML
    private ImageView dangerIcon;
    @FXML
    private TextField newUserAdress1;
    @FXML
    private TextField newUserAdress2;
    @FXML
    private ImageView backIcon;
    @FXML
    private JFXButton cancelBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void save(ActionEvent event) {
    }

    @FXML
    private void cancel(ActionEvent event) {
    }
    
}
