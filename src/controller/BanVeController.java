/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import dao.LichPhimDAO;
import dao.PhimDAO;
import dao.PhimDangChon;
import dao.PhongChieuDAO;
import dao.SeatDAO;
import dao.VeDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.DanhSachGhe;
import model.LichChieuPhim_demo;
import model.Seat;
import model.Ve;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class BanVeController implements Initializable {

    @FXML
    private AnchorPane acpBanVe;
    @FXML
    private AnchorPane acpChinhSua;
    @FXML
    private JFXButton btnDatVe;
    @FXML
    private JFXComboBox<String> comBoBoxNgayChieu;
    @FXML
    private JFXComboBox<String> comBoBoxTenPhong;
    @FXML
    private AnchorPane gpnGhe;

    private List<DanhSachGhe> positions;
    @FXML
    private ImageView backIcon;
    Button btn;

    private PhimDAO phimDAO;
    private PhongChieuDAO phongChieuDAO;
    List<Integer> ghe = new ArrayList();

    private VeDAO veDAO;
    private Ve selectedVe;
    @FXML
    private Button A1;
    @FXML
    private Button A2;
    @FXML
    private Button A3;
    @FXML
    private Button A4;
    @FXML
    private Button A5;
    @FXML
    private Button A6;
    @FXML
    private Button A7;
    @FXML
    private Button A8;
    @FXML
    private Button A9;
    @FXML
    private Button A10;
    @FXML
    private Button A11;
    @FXML
    private Button A12;
    @FXML
    private Button A13;
    @FXML
    private Button A14;
    @FXML
    private Button A15;
    @FXML
    private Button A16;
    @FXML
    private Button A17;
    @FXML
    private Button A18;
    @FXML
    private Button A19;
    @FXML
    private Button A20;
    @FXML
    private Button C1;
    @FXML
    private Button C2;
    @FXML
    private Button C3;
    @FXML
    private Button C4;
    @FXML
    private Button C5;
    @FXML
    private Button C6;
    @FXML
    private Button C7;
    @FXML
    private Button C8;
    @FXML
    private Button C9;
    @FXML
    private Button C10;
    @FXML
    private Button C11;
    @FXML
    private Button C12;
    @FXML
    private Button C13;
    @FXML
    private Button C14;
    @FXML
    private Button C15;
    @FXML
    private Button C16;
    @FXML
    private Button C17;
    @FXML
    private Button C18;
    @FXML
    private Button C19;
    @FXML
    private Button C20;
    @FXML
    private Button C21;
    @FXML
    private Button C22;
    @FXML
    private Button C23;
    @FXML
    private Button C24;
    @FXML
    private Button C25;
    @FXML
    private Button C26;
    @FXML
    private Button C27;
    @FXML
    private Button C28;
    @FXML
    private Button C29;
    @FXML
    private Button C30;
    @FXML
    private Button F1;
    @FXML
    private Button F3;
    @FXML
    private Button F5;
    @FXML
    private Button F7;
    @FXML
    private Button F9;

    ArrayList<String> button_ids = new ArrayList<>();
    ObservableList<String> rooms = FXCollections.observableArrayList();
    ObservableList<String> ngaychieu = FXCollections.observableArrayList();
    ArrayList<Ve> list_ve = new ArrayList<>();
    public static ObservableList tickets = FXCollections.observableArrayList();
    public static String thoiluong;
    List<DanhSachGhe> DanhSachGhe;
    PhimDangChon phimDangChon;
    static int maPhong;
    static int maLichChieu;
    static int maPhim;
    public static Parent root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // clear cache
        button_ids.clear();
        tickets.clear();

        DanhSachGhe = initPositions();
        phimDangChon = ChiTietPhimcontroller.phimDangChon;
        maPhim = phimDangChon.maPhim;
        phongChieuDAO = new PhongChieuDAO();
        veDAO = new VeDAO();
        comBoBoxNgayChieu.setStyle("-fx-font: 18px \"Serif\"; -fx-background-color:#ffffff");
        comBoBoxTenPhong.setStyle("-fx-font: 18px \"Serif\"; -fx-background-color:#ffffff");

//        comBoBoxTenPhong.setItems(list);
        LichPhimDAO lichPhimDAO = new LichPhimDAO();
        ObservableList<LichChieuPhim_demo> lcphim;
        try {
            lcphim = lichPhimDAO.selectAll();
            for (int i = 0; i < lcphim.size(); i++) {
                LichChieuPhim_demo lcp = (LichChieuPhim_demo) lcphim.get(i);
                if (lcp.getTenPhim().equalsIgnoreCase(phimDangChon.tenPhim)) {
                    if (!rooms.contains(lcp.getTenPhong())) {
                        rooms.add(lcp.getTenPhong());
                    }
                }
            }
            comBoBoxTenPhong.setItems(rooms);
            //set default value
            if (!rooms.isEmpty()) {
                comBoBoxTenPhong.setValue(rooms.get(0));
                ngaychieu.clear();
                for (int i = 0; i < lcphim.size(); i++) {
                    LichChieuPhim_demo lcp = (LichChieuPhim_demo) lcphim.get(i);
                    if (lcp.getTenPhim().equalsIgnoreCase(phimDangChon.tenPhim)) {
                        if (rooms.get(0).equalsIgnoreCase(lcp.getTenPhong())) {
                            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                            String datetime = dateFormat.format(lcp.getNgayChieu());
                            datetime += " " + lcp.getThoiGianBatDau();
                            ngaychieu.add(datetime);
                        }
                    }
                }
                comBoBoxNgayChieu.setItems(ngaychieu);
                if (!ngaychieu.isEmpty()) {
                    comBoBoxNgayChieu.setValue(ngaychieu.get(0));
                    try {
                        // get allvalue of ve relate to room and showing time
                        maPhong = phongChieuDAO.getMaPhongbyTenPhong(comBoBoxTenPhong.getValue());
                        String[] parts = comBoBoxNgayChieu.getValue().split(" ");
                        maLichChieu = lichPhimDAO.getLichPhimbyMaPhongAndThoiGianBatDau(maPhong, parts[1]);
                        list_ve = veDAO.getVebyMaPhongAndMaLichPhim(maPhong, maLichChieu);
                        SeatDAO seatDAO = new SeatDAO();
                        ObservableList<Seat> seats = seatDAO.selectAll();
                        if (!list_ve.isEmpty()) {
                            for (Ve ve : list_ve) {

                                ChangeStatusOfSeat(getSeatName(ve.getSeat_id()));
                            }
                        } else {
                            ChangeStatusOfSeat("");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(BanVeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            comBoBoxNgayChieu.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                String room = comBoBoxTenPhong.getValue();

                try {
                    list_ve.clear();
                    // get allvalue of ve relate to room and showing time
                    maPhong = phongChieuDAO.getMaPhongbyTenPhong(comBoBoxTenPhong.getValue());
                    if (comBoBoxNgayChieu.getValue() != null) {
                        String[] parts = comBoBoxNgayChieu.getValue().split(" ");
                        maLichChieu = lichPhimDAO.getLichPhimbyMaPhongAndThoiGianBatDau(maPhong, parts[1]);

                        list_ve = veDAO.getVebyMaPhongAndMaLichPhim(maPhong, maLichChieu);
                    }
                    SeatDAO seatDAO = new SeatDAO();
                    ObservableList<Seat> seats = seatDAO.selectAll();

                    if (!list_ve.isEmpty()) {
                        for (Ve ve : list_ve) {
                            ChangeStatusOfSeat(getSeatName(ve.getSeat_id()));
                        }
                    } else {
                        ChangeStatusOfSeat("");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(BanVeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            );

            comBoBoxTenPhong.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                String room = comBoBoxTenPhong.getValue();
                ngaychieu.clear();
                for (int i = 0; i < lcphim.size(); i++) {
                    LichChieuPhim_demo lcp = (LichChieuPhim_demo) lcphim.get(i);
                    if (lcp.getTenPhim().equalsIgnoreCase(phimDangChon.tenPhim)) {
                        if (room.equalsIgnoreCase(lcp.getTenPhong())) {
                            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                            String datetime = dateFormat.format(lcp.getNgayChieu());
                            datetime += " " + lcp.getThoiGianBatDau();
                            ngaychieu.add(datetime);
                        }
                    }
                }
                comBoBoxNgayChieu.setItems(ngaychieu);
                if (!ngaychieu.isEmpty()) {
                    comBoBoxNgayChieu.setValue(ngaychieu.get(0));
                    System.out.println(ngaychieu.get(0));
                }
            });
        } catch (SQLException ex) {
            Logger.getLogger(BanVeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (Node node : gpnGhe.getChildren()) {
            if (node instanceof Button) {
                Button b = (Button) node;
                b.setOnMouseClicked(event -> {
                    int i = gpnGhe.getChildren().indexOf(b);
                    if (positions.get(i).isIsBook() == false) {
                        positions.get(i).setIsBook(true);
                        b.setStyle("-fx-background-color:  #ad1510");
                        ghe.add(i);
                        //add id book to from array id-book
                        String id = b.getId();
                        button_ids.add(id);

                    } else {
                        positions.get(i).setIsBook(false);
                        switch (positions.get(i).getLoaiGhe()) {
                            case "A":
                                b.setStyle("-fx-border-color:   #4cd137;-fx-border-width:5");
                                break;
                            case "C":
                                b.setStyle("-fx-border-color:   #b05a53;-fx-border-width:5");
                                break;
                            case "F":
                                b.setStyle("-fx-background-color:    #f961af");
                                break;

                            default: {

                            }
                            break;
                        }
                        //unselect_remove from array id-book
                        String rmv_id = b.getId();
                        button_ids.remove(rmv_id);
                    }
                });
            }
        }
    }

    public String getSeatName(int seat_id) throws SQLException {
        SeatDAO seatDAO = new SeatDAO();
        ObservableList<Seat> seats = seatDAO.selectAll();
        for (Seat seat : seats) {
            if (seat.getSeatID() == seat_id) {
                return seat.getSeatName();
            }
        }
        return "";
    }

    public void ChangeStatusOfSeat(String seatName) {
        initPositions();
        for (Node node : gpnGhe.getChildren()) {
            if (node instanceof Button) {
                Button b = (Button) node;
                int i = gpnGhe.getChildren().indexOf(b);
//                System.out.println("index: " + i + "name of seat: " + b.getId());
                if (seatName.equalsIgnoreCase("")) {
                    positions.get(i).setIsBook(false);
                }
                if (b.getId().equalsIgnoreCase(seatName) || (positions.get(i).isIsBook() == true)) {
                    positions.get(i).setIsBook(true);
                } else {
                    positions.get(i).setIsBook(false);
                }
                if (positions.get(i).isIsBook() == true) {
                    b.setDisable(true);
                    b.setStyle("-fx-background-color:  #ad1510");

                } else {
                    b.setDisable(false);
                    switch (positions.get(i).getLoaiGhe()) {
                        case "A":
                            b.setStyle("-fx-border-color:   #4cd137;-fx-border-width:5");
                            break;
                        case "C":
                            b.setStyle("-fx-border-color:   #b05a53;-fx-border-width:5");
                            break;
                        case "F":
                            b.setStyle("-fx-background-color:    #f961af");
                            break;

                        default: {

                        }
                        break;
                    }
                }

            }
        }
    }

    @FXML
    private void clickDatVe(ActionEvent event) throws IOException, SQLException {
        //clear cache
        tickets.clear();

        SeatDAO seatDAO = new SeatDAO();
        ObservableList<Seat> seats = seatDAO.selectAll();
        java.util.Date date = new java.util.Date();
        java.sql.Date day = new java.sql.Date(date.getTime());
        int mv = 1;
        String seatType;
        thoiluong = phimDangChon.thoiluong;

        for (String btn_id : button_ids) {
            //  public Ticket(int MaVe, Date NgayBan, String TenPhim, String Phong, String LoaiGhe, Date NgayChieu, int Gia, String ViTri) {
//            System.out.println(btn_id);
            String type = btn_id.substring(0, 1);
            String index = btn_id.substring(1);

            String loaiGhe = "";
            int price = 0;
            switch (type) {
                case "A":
                    loaiGhe = "Ghế Thường";
                    price = 50000;
                    break;
                case "C":
                    loaiGhe = "Ghế VIP";
                    price = 90000;
                    break;
                case "F":
                    loaiGhe = "Ghế Sweetbox";
                    price = 200000;
                    break;
            }
            Ticket ticket = new Ticket(mv, day, phimDangChon.tenPhim, comBoBoxTenPhong.getValue(), loaiGhe, comBoBoxNgayChieu.getValue(), price, btn_id);
            tickets.add(ticket);
            mv++;
        }

        // nếu như Object tương tác trực tiếp với database người ta gọi là DAO ( Data access object)
        // còn việc tạo thêm Class như thao tác với view hoặc vận chuyển data qua lại giữa các Frame (Pane...) thì gọi là DTO (Data Transfer Object) 
        // Data loại này khác với Entity / Models(Tầng MOdels trong MVC) là DTO có thể có những attribute khác so với entity hoặc với DAO 
        // Cái attribute khác là những gì view nó cần hoặc function cần
        if (button_ids.isEmpty()) {
            alert.AlertBox.show(Alert.AlertType.INFORMATION, "Thông báo", "Chưa chọn ghế nào");
        } else {
            Parent root3 = FXMLLoader.load(getClass().getResource("/view/Dat_Ve.fxml"));

            Scene scene = new Scene(root3);
            Stage stage = new Stage();
            if (true) {
                stage.initModality(Modality.APPLICATION_MODAL);
            }
            stage.setScene(scene);

            stage.show();
            stage.setOnHiding(e -> {
                if (DatVeController.flag) {
                    try {
                        DoneGotoVe();
                    } catch (IOException ex) {
                        Logger.getLogger(BanVeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        };
    }

    public List<DanhSachGhe> getPositions() {
        return positions;
    }

    public void setPositions(List<DanhSachGhe> positions) {
        this.positions = positions;
    }

    public AnchorPane getGpnGhe() {
        return gpnGhe;
    }

    public List<DanhSachGhe> initPositions() {
        if (positions == null) {
            positions = new ArrayList<>();
            List<Node> list = gpnGhe.getChildren();
            int n = list.size();
            for (int i = 0; i < n; i++) {
                Button b = (Button) list.get(i);
                //lay ra text cua cai button vidu: "A1" hoac "A2" ...
                String s = b.getText();
                String type = s.substring(0, 1);
                int index = Integer.parseInt(s.substring(1));
                DanhSachGhe dsg;
                switch (type) {
                    case "A":
                        dsg = new DanhSachGhe(index, type, 50000, false);
                        break;
                    case "C":
                        dsg = new DanhSachGhe(index, type, 90000, false);
                        break;
                    case "F":
                        dsg = new DanhSachGhe(index, type, 200000, false);
                        break;
                    default: {
                        dsg = null;
                    }
                }
                if (dsg != null) {
                    positions.add(dsg);
                }
            }

        }
        if (positions == null) {
            System.out.println("NULL");
        } else {
            System.out.println("Not null");
        }

        return positions;
    }

    @FXML
    private void back(MouseEvent event) throws IOException {
        Node ThongTinPhimPane = (Node) FXMLLoader.load(getClass().getResource("/view/Chi_Tiet_Phim.fxml"));
        acpBanVe.getChildren().setAll(ThongTinPhimPane);
    }

    public void DoneGotoVe() throws IOException {
        Node ThongTinPhimPane = (Node) FXMLLoader.load(getClass().getResource("/view/Ve.fxml"));
        acpBanVe.getChildren().setAll(ThongTinPhimPane);
    }

    public class Ticket {

        private int MaVe;
        private Date NgayBan;
        private String TenPhim;
        private String Phong;
        private String LoaiGhe;
        private String NgayChieu;
        private int Gia;
        private String ViTri;

        public Ticket(int MaVe, Date NgayBan, String TenPhim, String Phong, String LoaiGhe, String NgayChieu, int Gia, String ViTri) {
            this.MaVe = MaVe;
            this.NgayBan = NgayBan;
            this.TenPhim = TenPhim;
            this.Phong = Phong;
            this.LoaiGhe = LoaiGhe;
            this.NgayChieu = NgayChieu;
            this.Gia = Gia;
            this.ViTri = ViTri;
        }

        public int getMaVe() {
            return MaVe;
        }

        public void setMaVe(int MaVe) {
            this.MaVe = MaVe;
        }

        public Date getNgayBan() {
            return NgayBan;
        }

        public void setNgayBan(Date NgayBan) {
            this.NgayBan = NgayBan;
        }

        public String getTenPhim() {
            return TenPhim;
        }

        public void setTenPhim(String TenPhim) {
            this.TenPhim = TenPhim;
        }

        public String getPhong() {
            return Phong;
        }

        public void setPhong(String Phong) {
            this.Phong = Phong;
        }

        public String getLoaiGhe() {
            return LoaiGhe;
        }

        public void setLoaiGhe(String LoaiGhe) {
            this.LoaiGhe = LoaiGhe;
        }

        public String getNgayChieu() {
            return NgayChieu;
        }

        public void setNgayChieu(String NgayChieu) {
            this.NgayChieu = NgayChieu;
        }

        public int getGia() {
            return Gia;
        }

        public void setGia(int Gia) {
            this.Gia = Gia;
        }

        public String getViTri() {
            return ViTri;
        }

        public void setViTri(String ViTri) {
            this.ViTri = ViTri;
        }

    }

}
