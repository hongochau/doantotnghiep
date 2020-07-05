package com.example.datn_10116101.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BannerModel { // maping 1 1 với bảng trong csdl
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("ten")
    @Expose
    private Integer idProduct;
    @SerializedName("hinh")
    @Expose
    private String image;

    public BannerModel(Integer id, Integer idProduct, String image) {
        this.id = id;
        this.idProduct = idProduct;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
