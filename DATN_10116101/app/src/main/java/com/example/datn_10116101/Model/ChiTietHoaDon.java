package com.example.datn_10116101.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChiTietHoaDon {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("idhoadon")
    @Expose
    private Integer idhoadon;
    @SerializedName("Masanpham")
    @Expose
    private Integer masanpham;
    @SerializedName("Soluong")
    @Expose
    private Integer soluong;
    @SerializedName("Gia")
    @Expose
    private Integer gia;
    @SerializedName("Tensanpham")
    @Expose
    private String tensanpham;
    @SerializedName("Hinhanh")
    @Expose
    private String hinhanh;
    @SerializedName("Mota")
    @Expose
    private String mota;


    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon( Integer idhoadon, Integer masanpham, Integer soluong, Integer gia, String tensanpham, String hinhanh, String mota) {

        this.idhoadon = idhoadon;
        this.masanpham = masanpham;
        this.soluong = soluong;
        this.gia = gia;
        this.tensanpham = tensanpham;
        this.hinhanh = hinhanh;
        this.mota = mota;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdhoadon() {
        return idhoadon;
    }

    public void setIdhoadon(Integer idhoadon) {
        this.idhoadon = idhoadon;
    }

    public Integer getMasanpham() {
        return masanpham;
    }

    public void setMasanpham(Integer masanpham) {
        this.masanpham = masanpham;
    }

    public Integer getSoluong() {
        return soluong;
    }

    public void setSoluong(Integer soluong) {
        this.soluong = soluong;
    }

    public Integer getGia() {
        return gia;
    }

    public void setGia(Integer gia) {
        this.gia = gia;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

}
