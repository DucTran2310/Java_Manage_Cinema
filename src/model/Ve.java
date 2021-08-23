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
public class Ve {
    private int MaVe;
    
    private Date NgayBan;
    
    private int MaPhim;
    
    private int MaPhong;
    
    private int GiaVe;
    
    private int MaGhe;
    
    private int MaLich;
    
    private int seat_id;
//    private String HoTen;

    public Ve() {
    }

    public Ve(int MaVe, Date NgayBan, int MaPhim, int MaPhong, int GiaVe, int MaGhe, int MaLich, int seat_id) {
        this.MaVe = MaVe;
        this.NgayBan = NgayBan;
        this.MaPhim = MaPhim;
        this.MaPhong = MaPhong;
        this.GiaVe = GiaVe;
        this.MaGhe = MaGhe;
        this.MaLich = MaLich;
        this.seat_id = seat_id;
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

    public int getGiaVe() {
        return GiaVe;
    }

    public void setGiaVe(int GiaVe) {
        this.GiaVe = GiaVe;
    }

    public int getMaGhe() {
        return MaGhe;
    }

    public void setMaGhe(int MaGhe) {
        this.MaGhe = MaGhe;
    }

    public int getMaLich() {
        return MaLich;
    }

    public void setMaLich(int MaLich) {
        this.MaLich = MaLich;
    }

    public int getSeat_id() {
        return seat_id;
    }

    public void setSeat_id(int seat_id) {
        this.seat_id = seat_id;
    }

    @Override
    public String toString() {
        return "Ve{" + "MaVe=" + MaVe + ", NgayBan=" + NgayBan + ", MaPhim=" + MaPhim + ", MaPhong=" + MaPhong + ", GiaVe=" + GiaVe + ", MaGhe=" + MaGhe + ", MaLich=" + MaLich + ", seat_id=" + seat_id + '}';
    }

    

    
    
    
}
