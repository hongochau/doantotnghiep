package com.example.datn_10116101.NetWork;

import com.example.datn_10116101.model.product_types;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIProducttype {
    @GET("api/getAllLoaiSp")
    Call<List<product_types>> getallloaisp();
}
