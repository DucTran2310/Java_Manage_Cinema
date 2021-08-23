/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import dao.ConnectDB;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class DoanhThuController implements Initializable {

    @FXML
    private Label typeReportLabel;
    private Label thangLabel;
    private JFXComboBox<String> thangComboBox;
    private Label namLabel;
    private JFXComboBox<Integer> namComboBox;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;
    private JFXComboBox<String> TypeReport;
    
    ObservableList<String> typeList = FXCollections.observableArrayList("Ngày", "Tháng", "Năm");
    
    ObservableList<String> thangList = FXCollections.observableArrayList();
    
    ObservableList<Integer> namList = FXCollections.observableArrayList();
    
    private Integer namSelected;
    private Integer thangSelected;
    private Connection connection;
    
    private XYChart.Series<String, Integer> selt = new XYChart.Series<>();
    @FXML
    private BarChart<String, Integer> MoneyChart;
    @FXML
    private JFXButton btnLoad;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        MoneyChart.setAnimated(false);
	}

    @FXML
    private void chartLoad(ActionEvent event) {
        String query = "select date(ngayban) day,sum(`giave`) count_for_day from ve group by date(ngayban)";
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        try {
            connection=connectDB();
            
            ResultSet rs = connection.createStatement().executeQuery(query);
            while(rs.next()){
                series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getInt(2)));
            }
            MoneyChart.getData().add(series);
        } catch (Exception e) {
        }
    }
    
    private Connection connectDB(){
        try {
            String dbString = "jdbc:mysql://localhost:3306/cinema";
            String user = "root";
            String password = "";
            
            Connection conn = DriverManager.getConnection(dbString, user, password);
            System.out.println("Connection Success");
            return conn;
        } catch (SQLException ex) {
            Logger.getLogger(DoanhThuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
}
