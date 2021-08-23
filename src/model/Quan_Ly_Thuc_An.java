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
public class Quan_Ly_Thuc_An {
    
    private int MaThucAn;
    
    private String TenThucAn;
    
    private int GiaTA;
    
    public Quan_Ly_Thuc_An(){
    }

    public Quan_Ly_Thuc_An(int MaThucAn, String TenThucAn, int GiaTA) {
        this.MaThucAn = MaThucAn;
        this.TenThucAn = TenThucAn;
        this.GiaTA = GiaTA;
    }

    public int getMaThucAn() {
        return MaThucAn;
    }

    public void setMaThucAn(int MaThucAn) {
        this.MaThucAn = MaThucAn;
    }

    public String getTenThucAn() {
        return TenThucAn;
    }

    public void setTenThucAn(String TenThucAn) {
        this.TenThucAn = TenThucAn;
    }

    public int getGiaTA() {
        return GiaTA;
    }

    public void setGiaTA(int GiaTA) {
        this.GiaTA = GiaTA;
    }

    
    
}
