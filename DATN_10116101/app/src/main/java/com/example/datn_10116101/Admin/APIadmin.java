package com.example.datn_10116101.Admin;

import com.example.datn_10116101.BaseResponse.ResponseBill;
import com.example.datn_10116101.BaseResponse.productObjectResponse;
import com.example.datn_10116101.BaseResponse.productResponse;
import com.example.datn_10116101.model.HoaDon;
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
    Call<ResponseBill> changeStatus(@Field("stt") String stt, @Field("idbill") int idbill);

    @GET("api/getDescProduct")
    Call<List<products>> getDescProduct();

    @FormUrlEncoded
    @POST("api/getHoadonbeetween")
    Call<List<HoaDon>> getHoadonbeetween(@Field("from") String from, @Field("to") String to);


    @GET("api/getAllProduct")
    Call<productResponse> getAllProduct();

    @FormUrlEncoded
    @POST("api/CreateProduct")
    Call<productObjectResponse> CreateProduct(@Field("idProductType") int idproducttype,
                                              @Field("name") String name,
                                              @Field("describe") String describe,
                                              @Field("price") int price,
                                              @Field("image") String image);

    @FormUrlEncoded
    @POST("api/updateProduct")
    Call<productResponse> updateProduct(
            @Field("id") int id,@Field("idProductType") int idproducttype,
                                       @Field("name") String name,
                                       @Field("describe") String describe,
                                       @Field("price") int price,
                                       @Field("image") String image);

    @FormUrlEncoded
    @POST("api/deleteProduct")
    Call<productResponse> deleteProduct(@Field("id") int idproduct);


}
