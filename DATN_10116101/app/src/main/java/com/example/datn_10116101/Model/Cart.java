package com.example.datn_10116101.Model;

public class Cart {
    public Cart(int id, int idproduct, int cout, int price, String voucher, String date, String tenproduct, String hinhanhproduct, String motaproduct) {
        this.id = id;
        this.idproduct = idproduct;
        this.cout = cout;
        this.price = price;
        this.voucher = voucher;
        this.date = date;
        this.tenproduct = tenproduct;
        this.hinhanhproduct = hinhanhproduct;
        this.motaproduct = motaproduct;
    }

    int id;
    int idproduct;
    int cout;
    int price ;
    String voucher;
    String date;
    String tenproduct;
    String hinhanhproduct;
    String motaproduct;



    public Cart() {
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(int idproduct) {
        this.idproduct = idproduct;
    }

    public int getCout() {
        return cout;
    }

    public void setCout(int cout) {
        this.cout = cout;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
}
