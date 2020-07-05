package com.example.datn_10116101.NetWork;



import com.example.datn_10116101.Model.Billresponse;
import com.example.datn_10116101.Model.ChiTietHoaDon;
import com.example.datn_10116101.Model.HoaDon;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIhoadon {
    @FormUrlEncoded
    // hóa đơn
    @POST("api/HoaDon")
    Call<Integer> GhiHoadon(@Field("idUser") int iduser,
                                @Field("status") String status,
                                @Field("totalprice") int totalprice,
                                @Field("bookingtime") String bookingtime);

    // chi tiết hóa đơn

    @POST("api/ChiTietHoaDon")
    Call<Boolean> GhiChiTietHoaDon (@Body List<ChiTietHoaDon> cthd);


    // get hóa đơn
    @FormUrlEncoded
    @POST("api/getHoaDon")
    Call<List<HoaDon>> getHoaDon(@Field("idUser") int idUser,
                                 @Field("status") String status);


    // get chi tiet hóa đơn
    @FormUrlEncoded
    @POST("api/getChiTietHoaDon")
    Call<List<ChiTietHoaDon>> getChiTietHoaDon(@Field("idhoadon") int idhoadon);

}

