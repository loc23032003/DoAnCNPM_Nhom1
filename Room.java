package com.example.firebaseauth;

import java.io.Serializable;

public class Room implements Serializable {
    int id;
    String maPhong;
    String loaiPhong;
    String image;
    String giaTien;

    public Room(int id, String maPhong, String loaiPhong, String giaTien, String image) {
        this.id = id;
        this.maPhong = maPhong;
        this.loaiPhong = loaiPhong;
        this.giaTien = giaTien;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getLoaiPhong() {
        return loaiPhong;
    }

    public void setLoaiPhong(String loaiPhong) {
        this.loaiPhong = loaiPhong;
    }

    public String getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(String giaTien) {
        this.giaTien = giaTien;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
