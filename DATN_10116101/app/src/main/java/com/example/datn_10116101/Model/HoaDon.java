package com.example.datn_10116101.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HoaDon {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("IdUser")
    @Expose
    private Integer idUser;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("Totalprice")
    @Expose
    private Integer totalprice;
    @SerializedName("Bookingtime")
    @Expose
    private String bookingtime;

    @SerializedName("name")
    @Expose
    private String nameUser;
    @SerializedName("imagefb")
    @Expose
    private String imgfb;

    public HoaDon() {
    }

    public HoaDon(Integer id, Integer idUser, String status, Integer totalprice, String bookingtime) {
        this.id = id;
        this.idUser = idUser;
        this.status = status;
        this.totalprice = totalprice;
        this.bookingtime = bookingtime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Integer totalprice) {
        this.totalprice = totalprice;
    }

    public String getBookingtime() {
        return bookingtime;
    }

    public void setBookingtime(String bookingtime) {
        this.bookingtime = bookingtime;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getImgfb() {
        return imgfb;
    }

    public void setImgfb(String imgfb) {
        this.imgfb = imgfb;
    }
}
