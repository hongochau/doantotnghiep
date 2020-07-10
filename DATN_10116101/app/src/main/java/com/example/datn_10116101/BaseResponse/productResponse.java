package com.example.datn_10116101.BaseResponse;

import com.example.datn_10116101.model.HoaDon;
import com.example.datn_10116101.model.products;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class productResponse extends BaseResponse {
    @SerializedName("data")
    @Expose
    private List<products> data;

    public List<products> getData() {
        return data;
    }

    public void setData(List<products> data) {
        this.data = data;
    }
}
