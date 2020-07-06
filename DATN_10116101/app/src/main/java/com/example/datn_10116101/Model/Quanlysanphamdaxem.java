package com.example.datn_10116101.model;

public class Quanlysanphamdaxem {
    int id;
    String tenproduct;
    String hinhanhproduct;
    String motaproduct;
    int idloaisanpham;
    public Quanlysanphamdaxem(int id, String tenproduct, String hinhanhproduct, String motaproduct, int idloaisanpham) {
        this.id = id;
        this.tenproduct = tenproduct;
        this.hinhanhproduct = hinhanhproduct;
        this.motaproduct = motaproduct;
        this.idloaisanpham = idloaisanpham;
    }

    public String getTenproduct() {
        return tenproduct;
    }

    public void setTenproduct(String tenproduct) {
        this.tenproduct = tenproduct;
    }

    public String getHinhanhproduct() {
        return hinhanhproduct;
    }

    public void setHinhanhproduct(String hinhanhproduct) {
        this.hinhanhproduct = hinhanhproduct;
    }

    public String getMotaproduct() {
        return motaproduct;
    }

    public void setMotaproduct(String motaproduct) {
        this.motaproduct = motaproduct;
    }

    public int getIdloaisanpham() {
        return idloaisanpham;
    }

    public void setIdloaisanpham(int idloaisanpham) {
        this.idloaisanpham = idloaisanpham;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
