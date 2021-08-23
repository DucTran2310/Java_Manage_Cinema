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
public class PhanQuyen {
    
    private String HoTen;
    
    private String ChucVu;

    private String Quyen;
    
    public PhanQuyen(){
    }

    public PhanQuyen(String HoTen, String ChucVu, String Quyen) {
        this.HoTen = HoTen;
        this.ChucVu = ChucVu;
        this.Quyen = Quyen;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public String getChucVu() {
        return ChucVu;
    }

    public void setChucVu(String ChucVu) {
        this.ChucVu = ChucVu;
    }

    public String getQuyen() {
        return Quyen;
    }

    public void setQuyen(String Quyen) {
        this.Quyen = Quyen;
    }

    
    
    
}
