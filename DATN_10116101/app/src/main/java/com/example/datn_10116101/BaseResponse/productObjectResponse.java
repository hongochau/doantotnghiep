package com.example.datn_10116101.BaseResponse;

import com.example.datn_10116101.model.products;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class productObjectResponse extends BaseResponse {
    @SerializedName("data")
    @Expose
    private products data;

    public products getData() {
        return data;
    }

    public void setData(products data) {
        this.data = data;
    }
}
