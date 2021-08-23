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
public class PhongChieu {
    private int MaPhong;
    
    private String TenPhong;
    
    private int SoGheNgang;
    
    private int SoGheDoc;
    
    public PhongChieu(){
    }

    public PhongChieu(int MaPhong, String TenPhong, int SoGheNgang, int SoGheDoc) {
        this.MaPhong = MaPhong;
        this.TenPhong = TenPhong;
        this.SoGheNgang = SoGheNgang;
        this.SoGheDoc = SoGheDoc;
    }

    public int getMaPhong() {
        return MaPhong;
    }

    public void setMaPhong(int MaPhong) {
        this.MaPhong = MaPhong;
    }

    public String getTenPhong() {
        return TenPhong;
    }

    public void setTenPhong(String TenPhong) {
        this.TenPhong = TenPhong;
    }

    public int getSoGheNgang() {
        return SoGheNgang;
    }

    public void setSoGheNgang(int SoGheNgang) {
        this.SoGheNgang = SoGheNgang;
    }

    public int getSoGheDoc() {
        return SoGheDoc;
    }

    public void setSoGheDoc(int SoGheDoc) {
        this.SoGheDoc = SoGheDoc;
    }
    
    
}
