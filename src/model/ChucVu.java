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
public class ChucVu {
    private int MaChucVu;
    
    private String TenChucVu;
    
    public ChucVu(){};

    public ChucVu(int MaChucVu, String TenChucVu) {
        this.MaChucVu = MaChucVu;
        this.TenChucVu = TenChucVu;
    }

    public int getMaChucVu() {
        return MaChucVu;
    }

    public void setMaChucVu(int MaChucVu) {
        this.MaChucVu = MaChucVu;
    }

    public String getTenChucVu() {
        return TenChucVu;
    }

    public void setTenChucVu(String TenChucVu) {
        this.TenChucVu = TenChucVu;
    }
    
    
}
