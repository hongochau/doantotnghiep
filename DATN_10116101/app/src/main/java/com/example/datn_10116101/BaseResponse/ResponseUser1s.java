package com.example.datn_10116101.BaseResponse;

import com.example.datn_10116101.Model.user1s;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseUser1s  extends BaseResponse {
    @SerializedName("data")
    @Expose
    private user1s data = null;

    public user1s getData() {
        return data;
    }

    public void setData(user1s data) {
        this.data = data;
    }
}
