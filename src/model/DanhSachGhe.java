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
public class DanhSachGhe {
    private int MaGhe;
    
    private String LoaiGhe;
    
    private int GiaGhe;
    
    private boolean book;

    public DanhSachGhe(int MaGhe, String LoaiGhe, int GiaGhe, boolean book) {
        this.MaGhe = MaGhe;
        this.LoaiGhe = LoaiGhe;
        this.GiaGhe = GiaGhe;
        this.book = false;
    }

    public DanhSachGhe() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getMaGhe() {
        return MaGhe;
    }

    public void setMaGhe(int MaGhe) {
        this.MaGhe = MaGhe;
    }

    public String getLoaiGhe() {
        return LoaiGhe;
    }

    public void setLoaiGhe(String LoaiGhe) {
        this.LoaiGhe = LoaiGhe;
    }

    public int getGiaGhe() {
        return GiaGhe;
    }

    public void setGiaGhe(int GiaGhe) {
        this.GiaGhe = GiaGhe;
    }

    public void setIsBook(boolean isBook) {
        this.book = isBook;
    }

    public boolean isIsBook() {
        return book;
    }
    
   

    
    
    
}
