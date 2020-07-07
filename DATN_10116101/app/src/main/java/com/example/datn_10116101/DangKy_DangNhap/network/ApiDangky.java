package com.example.datn_10116101.DangKy_DangNhap.network;

import com.example.datn_10116101.BaseResponse.ResponseUser1s;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiDangky {
    @FormUrlEncoded
    @POST("api/checkPhone")
    Call<ResponseUser1s> checkPhone(@Field("phone") String phone);

    @FormUrlEncoded
    @POST("api/checkAccount")
    Call<ResponseUser1s> checkAccount(@Field("acc") String acc);

//haauj
}
