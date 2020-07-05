package com.example.datn_10116101.NetWork;

import com.example.datn_10116101.Model.BannerModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;

public interface DataClient {


    @POST("api/getBanner")
    Call<List<BannerModel>> getBanner();



}
