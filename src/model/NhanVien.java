/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author ASUS
 */
public class NhanVien {
    private int MaNV;
    
    private String HoTen;
    
    private String Email;
    
    private String SoDienThoai;
    
    private int MaChucVu;
    
    private String DiaChi;
    
    private boolean DangHoatDong;
    

    public NhanVien() {
    }

    public NhanVien(int MaNV, String HoTen, String Email, String SoDienThoai, int MaChucVu, String DiaChi, boolean DangHoatDong) {
        this.MaNV = MaNV;
        this.HoTen = HoTen;
        this.Email = Email;
        this.SoDienThoai = SoDienThoai;
        this.MaChucVu = MaChucVu;
        this.DiaChi = DiaChi;
        this.DangHoatDong = DangHoatDong;
    }

    public int getMaNV() {
        return MaNV;
    }

    public void setMaNV(int MaNV) {
        this.MaNV = MaNV;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String SoDienThoai) {
        this.SoDienThoai = SoDienThoai;
    }

    public int getMaChucVu() {
        return MaChucVu;
    }

    public void setMaChucVu(int MaChucVu) {
        this.MaChucVu = MaChucVu;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public boolean isDangHoatDong() {
        return DangHoatDong;
    }

    public void setDangHoatDong(boolean DangHoatDong) {
        this.DangHoatDong = DangHoatDong;
    }

    
    
}
