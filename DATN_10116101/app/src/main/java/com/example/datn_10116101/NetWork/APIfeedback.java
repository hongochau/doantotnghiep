package com.example.datn_10116101.NetWork;

import com.example.datn_10116101.BaseResponse.BaseResponseFeedback;
import com.example.datn_10116101.model.FeedbackUser;
import com.example.datn_10116101.model.feedback_products;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIfeedback {
    @FormUrlEncoded // cứ dùng post là phải có
    @POST("api/getfeedback") // đường dẫn  BaseResponseFeedback là dạng dữ liệu server trả về
    Call<BaseResponseFeedback> getfeedback(@Field("idProduct") int idproduct, @Query("page") int page);


    @POST("api/pushFeedback")
    Call<Integer> pushFeedback(@Body feedback_products feedbackProducts);

    @FormUrlEncoded
    @POST("api/getfeedbackWithIdUser")
    Call<List<FeedbackUser>> getfeedbackwithUser(@Field("idUser") int idUser);
}
