package com.example.datn_10116101.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class products {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("idProductType")
    @Expose
    private Integer idProductType;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("describe")
    @Expose
    private String describe;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("daban")
    @Expose
    private Integer daban;
    @SerializedName("created_at")
    @Expose

    private Object createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;

    public products() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdProductType() {
        return idProductType;
    }

    public void setIdProductType(Integer idProductType) {
        this.idProductType = idProductType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Object getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Object createdAt) {
        this.createdAt = createdAt;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getDaban() {
        return daban;
    }

    public void setDaban(Integer daban) {
        this.daban = daban;
    }
}
