package com.example.datn_10116101.Admin;

import com.example.datn_10116101.BaseResponse.ResponseBill;
import com.example.datn_10116101.model.products;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIadmin {
    @FormUrlEncoded
    @POST("api/getAllBillWithType")
    Call<ResponseBill> getallbillwithtype(@Field("stt") String stt);

    @FormUrlEncoded
    @POST("api/changeStatus")
    Call<ResponseBill> changeStatus(@Field("stt") String stt,@Field("idbill") int idbill);

    @GET("api/getDescProduct")
    Call<List<products>> getDescProduct();
}
