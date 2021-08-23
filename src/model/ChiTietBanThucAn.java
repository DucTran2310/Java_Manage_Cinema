/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Aduc
 */
public class ChiTietBanThucAn {
    private int MaChiTiet;
    
    private int SoLuong;
    
    private int GiaBan;
    
    private int ThanhTien;
    
    private int MaThucAn;
    
    private int MaNV;

    public ChiTietBanThucAn(int MaChiTiet, int SoLuong, int GiaBan, int ThanhTien, int MaThucAn, int MaNV) {
        this.MaChiTiet = MaChiTiet;
        this.SoLuong = SoLuong;
        this.GiaBan = GiaBan;
        this.ThanhTien = ThanhTien;
        this.MaThucAn = MaThucAn;
        this.MaNV = MaNV;
    }

    public int getMaChiTiet() {
        return MaChiTiet;
    }

    public void setMaChiTiet(int MaChiTiet) {
        this.MaChiTiet = MaChiTiet;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public int getGiaBan() {
        return GiaBan;
    }

    public void setGiaBan(int GiaBan) {
        this.GiaBan = GiaBan;
    }

    public int getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(int ThanhTien) {
        this.ThanhTien = ThanhTien;
    }

    public int getMaThucAn() {
        return MaThucAn;
    }

    public void setMaThucAn(int MaThucAn) {
        this.MaThucAn = MaThucAn;
    }

    public int getMaNV() {
        return MaNV;
    }

    public void setMaNV(int MaNV) {
        this.MaNV = MaNV;
    }
    
    
    
}
