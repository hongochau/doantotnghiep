package com.example.datn_10116101.BaseResponse;

import com.example.datn_10116101.model.HoaDon;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseBill extends BaseResponse {
    @SerializedName("data")
    @Expose
    private List<HoaDon> data;

    public List<HoaDon> getData() {
        return data;
    }

    public void setData(List<HoaDon> data) {
        this.data = data;
    }
}
