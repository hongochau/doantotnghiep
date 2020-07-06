package com.example.datn_10116101.NetWork;


import com.example.datn_10116101.BaseResponse.ProductBaseResponse;
import com.example.datn_10116101.model.products;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIProduct {
    @POST("api/getProduct")
    Call<List<products>> getProduct();

    @GET("api/getAll1")
    Call<ProductBaseResponse> getListProduct(@Query("page") int page);

    @FormUrlEncoded
    @POST("api/getproType")
    Call<ProductBaseResponse> getproType(@Field("idProductType") int id);

    @FormUrlEncoded
    @POST("api/getSPLQ")
    Call<ProductBaseResponse> getSPLQ(@Field("idProductype") int id, @Query("page") int page);

    @GET("api/getCount")
    Call<String> getCount();

    @FormUrlEncoded // seảch sản phẩm theo tên hoặc theo description
    @POST("api/searchProduct")
    Call<List<products>> searchProduct(@Field("name") String name);

    @FormUrlEncoded //
    @POST("api/searchByid")
    Call<products> searchByid(@Field("id") String id);


    @POST("api/getnewpro")
    Call<ProductBaseResponse> getnewpro();

    @FormUrlEncoded //
    @POST("api/LoadmoreWithproductType")
    Call<ProductBaseResponse> LoadmoreWithproductType(@Query("page") int page,@Field("idProductType") int id);

}
