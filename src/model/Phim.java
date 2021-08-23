/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 *
 * @author ASUS
 */
public class Phim {
    private int MaPhim;
    
    private String TenPhim;
    
    private int ThoiLuong;
    
    private String ThongTinPhim;
    
    private Timestamp ThoiGianKhoiTao;
    
    private Timestamp ThoiGianCapNhat;
    
    private String image;
    
    private int maTL;
    
//    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public Phim(){
    };
    


    public Phim(int MaPhim, String TenPhim, int ThoiLuong, String ThongTinPhim, Timestamp ThoiGianKhoiTao, Timestamp ThoiGianCapNhat, String image, int maTL) {
        this.MaPhim = MaPhim;
        this.TenPhim = TenPhim;
        this.ThoiLuong = ThoiLuong;
        this.ThongTinPhim = ThongTinPhim;
        this.ThoiGianKhoiTao = ThoiGianKhoiTao;
        this.ThoiGianCapNhat = ThoiGianCapNhat;
        this.image = image;
//        this.maTL = maTL;
    }

    public int getMaPhim() {
        return MaPhim;
    }

    public void setMaPhim(int MaPhim) {
        this.MaPhim = MaPhim;
    }

    public String getTenPhim() {
        return TenPhim;
    }

    public void setTenPhim(String TenPhim) {
        this.TenPhim = TenPhim;
    }

    public int getThoiLuong() {
        return ThoiLuong;
    }

    public void setThoiLuong(int ThoiLuong) {
        this.ThoiLuong = ThoiLuong;
    }

    public String getThongTinPhim() {
        return ThongTinPhim;
    }

    public void setThongTinPhim(String ThongTinPhim) {
        this.ThongTinPhim = ThongTinPhim;
    }

    public Timestamp getThoiGianKhoiTao() {
        return ThoiGianKhoiTao;
    }

    public void setThoiGianKhoiTao(Timestamp ThoiGianKhoiTao) {
        this.ThoiGianKhoiTao = ThoiGianKhoiTao;
    }

    public Timestamp getThoiGianCapNhat() {
        return ThoiGianCapNhat;
    }

    public void setThoiGianCapNhat(Timestamp ThoiGianCapNhat) {
        this.ThoiGianCapNhat = ThoiGianCapNhat;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getMaTL() {
        return maTL;
    }

    public void setMaTL(int maTL) {
        this.maTL = maTL;
    }
    
    

}
