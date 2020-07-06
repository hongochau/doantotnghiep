package com.example.datn_10116101.NetWork;


import com.example.datn_10116101.model.vouchers;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiVoucher {
    @FormUrlEncoded
    @POST("api/checkVoucher")
    Call<vouchers> checkVoucher(@Field("voucher") String voucher, @Field("idProduct") int idproduct);
}
