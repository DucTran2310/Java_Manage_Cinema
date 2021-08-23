/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import dao.QuanLyThucAn_DAO;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Quan_Ly_Thuc_An;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class BanThucAnController implements Initializable {
    //public Food(int stt, int maThucAn, String name, int gia, int soluong, JFXButton them, JFXButton giam) {
    Stage stage;
    @FXML
    public  TableView<Food> tbvThucAn;
    @FXML
    private TableColumn<Food, Integer> sttColumn;
    private TableColumn<Food, Integer> maThucAncolumn;
    @FXML
    private TableColumn<Food, String> nameColumn;
    @FXML
    private TableColumn<Food, Integer> giaColumn;
    @FXML
    private TableColumn<Food, JFXButton> themColumn;
    @FXML
    private TableColumn<Food, Integer> soluongColumn;
    @FXML
    private TableColumn<Food, JFXButton> giamColumn;
    @FXML
    private JFXButton btnDatThucAn;

    ObservableList<Food> foodList;
    public static  ObservableList foods = FXCollections.observableArrayList();
    String[] TenThucAn;
    int[] GiaThucAn;
    int[] MaThucAn;
    int[] SoLuongThucAn;
    JFXButton[] btnThem;
    JFXButton[] btnGiam;


//    private void createButton(ObservableList<Food> list) {
//        TenThucAn = new String[list.size()];
//        MaThucAn = new int[list.size()];
//        GiaThucAn = new int[list.size()];
//        SoLuongThucAn = new int[list.size()];
//        btnThem = new JFXButton[list.size()];
//        btnGiam = new JFXButton[list.size()];
//
//        Color c = Color.rgb(255, 255, 255);
//        Font f = new Font("Arial", 18);
//
//        for (int i = 0; i < list.size(); i++) {
//            Food food = (Food) list.get(i);
//            TenThucAn[i] = food.getName();
////            MaThucAn[i] = food.getMaThucAn();
//            GiaThucAn[i] = food.getGia();
//            SoLuongThucAn[i] = 0;
//            btnThem[i] = new JFXButton("+");
//            btnGiam[i] = new JFXButton("-");
//            btnThem[i].setTextFill(c);
//            btnThem[i].setFont(f);
//            btnGiam[i].setTextFill(c);
//            btnGiam[i].setFont(f);
//            btnThem[i].setStyle("-fx-background-color: #B53471");
//            btnGiam[i].setStyle("-fx-background-color: #B53471");
//            btnThem[i] = new JFXButton();
//            btnThem[i].setOnAction(new EventHandler<ActionEvent>() {
//                public void handle(ActionEvent event) {
//                    System.out.println("do action add");
//                    for (int i = 0; i < btnThem.length; i++) {
//                        if (event.getSource() == btnThem[i]) {
//                            SoLuongThucAn[i]++;
//                            foodList.get(i).Soluong++;
//                            soluongColumn.setCellValueFactory(new PropertyValueFactory<>("Soluong"));
//                            tbvThucAn.setItems(foodList);
//                            tbvThucAn.refresh();
//                        }
//                    }
//
//                }
//            });
//            btnGiam[i] = new JFXButton();
//            btnGiam[i].setOnAction(new EventHandler<ActionEvent>() {
//                public void handle(ActionEvent event) {
//                    System.out.println("do action degree");
//                    for (int i = 0; i < btnGiam.length; i++) {
//                        if (event.getSource() == btnGiam[i]) {
//                            if (foodList.get(i).Soluong > 0) {
//                                SoLuongThucAn[i]--;
//                                foodList.get(i).Soluong--;
//                                soluongColumn.setCellValueFactory(new PropertyValueFactory<>("Soluong"));
//                                tbvThucAn.setItems(foodList);
//                                tbvThucAn.refresh();
//                            }
//                        }
//                    }
//
//                }
//            });
//        }
//    }

    public void resetvalue() {
        for (int i = 0; i < foodList.size(); i++) {
            SoLuongThucAn[i] = 0;
            foodList.get(i).Soluong = 0;
            soluongColumn.setCellValueFactory(new PropertyValueFactory<>("Soluong"));
            tbvThucAn.setItems(foodList);
        }
        tbvThucAn.refresh();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        foods.clear();
        QuanLyThucAn_DAO ThucAn_DAO = new QuanLyThucAn_DAO();
        try {
            ObservableList<Quan_Ly_Thuc_An> list_thuc_an = ThucAn_DAO.selectAll();
            ObservableList<Food> foods = setUpDataForFoodtable(list_thuc_an);
            showTableViewFood(foods);
            System.out.println("here");
        } catch (SQLException ex) {
            Logger.getLogger(BanThucAnController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObservableList<Food> setUpDataForFoodtable(ObservableList<Quan_Ly_Thuc_An> list_thuc_an) {
        int stt = 1;
        btnThem = new JFXButton[list_thuc_an.size()];
        btnGiam = new JFXButton[list_thuc_an.size()];
        for (Quan_Ly_Thuc_An thuc_an : list_thuc_an) {
            Color c = Color.rgb(255, 255, 255);
            Font f = new Font("Arial", 18);
            JFXButton btn_add = new JFXButton();
            btn_add.setText("+");
            btn_add.setId(String.valueOf(stt - 1));
            btn_add.setTextFill(c);
            btn_add.setFont(f);
            btn_add.setStyle("-fx-background-color: #B53471");
            btn_add.setOnAction(this::btnThemOnclick);
            JFXButton btn_giam = new JFXButton();
            btn_giam.setText("-");
            btn_giam.setId(String.valueOf(stt - 1));
            btn_giam.setTextFill(c);
            btn_giam.setFont(f);
            btn_giam.setStyle("-fx-background-color: #B53471");
            btn_giam.setOnAction(this::btnGiamOnclick);
            Food food = new Food(stt, thuc_an.getTenThucAn(), thuc_an.getGiaTA(), btn_add, 0,btn_giam);
            foods.add(food);
            stt++;
        }

        return foods;
    }

    public void btnThemOnclick(ActionEvent e) {
        Food food = (Food) foods.get(Integer.valueOf(((Control) e.getSource()).getId()));
//        System.out.println(food.getName());
        food.setSoluong(food.getSoluong() + 1);
        // setUpDataForFoodtable(foods);
        tbvThucAn.refresh();
    }

    public void btnGiamOnclick(ActionEvent e) {
        Food food = (Food) foods.get(Integer.valueOf(((Control) e.getSource()).getId()));
//        System.out.println(food.getName());
        if (food.getSoluong() > 0) {
            food.setSoluong(food.getSoluong() - 1);
        }
        // setUpDataForFoodtable(foods);
        tbvThucAn.refresh();
    }
    public  void doRefresh(){
          tbvThucAn.refresh();
    }

    public void showTableViewFood(ObservableList<Food> foods) {
        sttColumn.setCellValueFactory(new PropertyValueFactory<>("stt"));
//        maThucAncolumn.setCellValueFactory(new PropertyValueFactory<>("MaThucAn"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        giaColumn.setCellValueFactory(new PropertyValueFactory<>("gia"));
        themColumn.setCellValueFactory(new PropertyValueFactory<>("them"));
        soluongColumn.setCellValueFactory(new PropertyValueFactory<>("soluong"));
        giamColumn.setCellValueFactory(new PropertyValueFactory<>("giam"));
        tbvThucAn.setItems(foods);
    }

    @FXML
    private void clickDatThucAn(ActionEvent event) throws IOException {
        Parent root3 = FXMLLoader.load(getClass().getResource("/view/Dat_Thuc_An.fxml"));
        Scene scene = new Scene(root3);
        stage = new Stage();
        if (true) {
            stage.initModality(Modality.APPLICATION_MODAL);
        } 
        stage.setScene(scene);
        stage.show();
        stage.setOnHiding( e -> {doRefresh();} );
    }

    public class Food {

        public Food(int stt,  String name, int gia, JFXButton them, int soluong, JFXButton giam) {
            super();
            Stt = stt;
//            MaThucAn = maThucAn;
            Name = name;
            Gia = gia;
            Them = them;
            Soluong = soluong;
            Giam = giam;
        }

        private int Stt;
//        private int MaThucAn;
        private String Name = null;
        private int Gia;
        private int Soluong;
        protected JFXButton Them;
        protected JFXButton Giam;

        private Food() {
        }

        public int getStt() {
            return Stt;
        }

        public void setStt(int Stt) {
            this.Stt = Stt;
        }

//        public int getMaThucAn() {
//            return MaThucAn;
//        }
//
//        public void setMaThucAn(int MaThucAn) {
//            this.MaThucAn = MaThucAn;
//        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public int getGia() {
            return Gia;
        }

        public void setGia(int Gia) {
            this.Gia = Gia;
        }

        public int getSoluong() {
            return Soluong;
        }

        public void setSoluong(int Soluong) {
            this.Soluong = Soluong;
        }

        public JFXButton getThem() {
            return Them;
        }

        public void setThem(JFXButton Them) {
            this.Them = Them;
        }

        public JFXButton getGiam() {
            return Giam;
        }

        public void setGiam(JFXButton Giam) {
            this.Giam = Giam;
        }

    }

}
