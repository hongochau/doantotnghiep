package com.example.datn_10116101.NetWork;

import com.example.datn_10116101.BaseResponse.ResponseUser1s;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiUser {
    @FormUrlEncoded
    @POST("api/CheckUser")
    Call<ResponseUser1s> login(@Field("email") String tk, @Field("password") String mk);

    @FormUrlEncoded
    @POST("api/Register")
    Call<ResponseUser1s> Register(@Field("email") String tk,
                                  @Field("account") String acc,
                                       @Field("password") String mk);

    @FormUrlEncoded
    @POST("api/UpdateThongTinTaiKhoan")
    Call<ResponseUser1s> UpDateTT
                                  (@Field("account") String acc,
                                   @Field("name") String name,
                                  @Field("email") String email,
                                  @Field("address") String address,
                                  @Field("phone") String phone);


}
