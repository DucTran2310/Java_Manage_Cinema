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
public class Seat {
    private int seatID;
    private String seatName;
    private int maghe;
    private boolean isBook;

    public Seat() {
    }

    public Seat(int seatID, String seatName, int maghe, boolean isBook) {
        this.seatID = seatID;
        this.seatName = seatName;
        this.maghe = maghe;
        this.isBook = isBook;
    }

    public int getSeatID() {
        return seatID;
    }

    public void setSeatID(int seatID) {
        this.seatID = seatID;
    }

    public String getSeatName() {
        return seatName;
    }

    public void setSeatName(String seatName) {
        this.seatName = seatName;
    }

    public int getMaghe() {
        return maghe;
    }

    public void setMaghe(int maghe) {
        this.maghe = maghe;
    }


    public boolean isIsBook() {
        return isBook;
    }

    public void setIsBook(boolean isBook) {
        this.isBook = isBook;
    }
    
    
}
