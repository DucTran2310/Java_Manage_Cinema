/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author ASUS
 */
public class LichChieuPhim_demo {
    private int MaLichPhim;
    
    private String TenPhim;
    
    private String TenPhong;
    
    private int MaPhim;
    
    private int MaPhong;
    
    private Date NgayChieu;
   
    private String ThoiGianBatDau;
    
    private String ThoiGianKetThuc;
    
    public LichChieuPhim_demo(){
    }; 

    public LichChieuPhim_demo(int MaLichPhim, String TenPhim, String TenPhong, int MaPhim, int MaPhong, Date NgayChieu, String ThoiGianBatDau, String ThoiGianKetThuc) {
        this.MaLichPhim = MaLichPhim;
        this.TenPhim = TenPhim;
        this.TenPhong = TenPhong;
        this.MaPhim = MaPhim;
        this.MaPhong = MaPhong;
        this.NgayChieu = NgayChieu;
        this.ThoiGianBatDau = ThoiGianBatDau;
        this.ThoiGianKetThuc = ThoiGianKetThuc;
    }

    public int getMaLichPhim() {
        return MaLichPhim;
    }

    public void setMaLichPhim(int MaLichPhim) {
        this.MaLichPhim = MaLichPhim;
    }

    public String getTenPhim() {
        return TenPhim;
    }

    public void setTenPhim(String TenPhim) {
        this.TenPhim = TenPhim;
    }

    public String getTenPhong() {
        return TenPhong;
    }

    public void setTenPhong(String TenPhong) {
        this.TenPhong = TenPhong;
    }

    public int getMaPhim() {
        return MaPhim;
    }

    public void setMaPhim(int MaPhim) {
        this.MaPhim = MaPhim;
    }

    public int getMaPhong() {
        return MaPhong;
    }

    public void setMaPhong(int MaPhong) {
        this.MaPhong = MaPhong;
    }

    public Date getNgayChieu() {
        return NgayChieu;
    }

    public void setNgayChieu(Date NgayChieu) {
        this.NgayChieu = NgayChieu;
    }

    public String getThoiGianBatDau() {
        return ThoiGianBatDau;
    }

    public void setThoiGianBatDau(String ThoiGianBatDau) {
        this.ThoiGianBatDau = ThoiGianBatDau;
    }

    public String getThoiGianKetThuc() {
        return ThoiGianKetThuc;
    }

    public void setThoiGianKetThuc(String ThoiGianKetThuc) {
        this.ThoiGianKetThuc = ThoiGianKetThuc;
    }

    
}
