package com.example.datn_10116101.ViewModel;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.datn_10116101.BaseResponse.ProductBaseResponse;
import com.example.datn_10116101.BaseResponse.ResponseUser1s;
import com.example.datn_10116101.Model.products;
import com.example.datn_10116101.NetWork.APIProduct;
import com.example.datn_10116101.NetWork.ApiUser;
import com.example.datn_10116101.Service.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserViewModel extends ViewModel {
    private ApiUser dataClient;
    private MutableLiveData<products> mutableLiveData;

    public MutableLiveData<ResponseUser1s> Register(String tk ,String acc, String mk){
         final MutableLiveData<ResponseUser1s> newsData = new MutableLiveData<>();
        dataClient = RetrofitService.cteateService(ApiUser.class);
        dataClient.Register(tk,acc,mk).enqueue(new Callback<ResponseUser1s>() {
            @Override
            public void onResponse(Call<ResponseUser1s> call, Response<ResponseUser1s> response) {
                if(response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ResponseUser1s> call, Throwable t) {

            }
        });
        return newsData;
    }

    public MutableLiveData<ResponseUser1s> Login(String tk , String mk ){
        final MutableLiveData<ResponseUser1s> newsData = new MutableLiveData<>();
        dataClient = RetrofitService.cteateService(ApiUser.class);
        dataClient.login(tk,mk).enqueue(new Callback<ResponseUser1s>() {
            @Override
            public void onResponse(Call<ResponseUser1s> call, Response<ResponseUser1s> response) {
                if(response.code()==200){ // check code trả về
                    newsData.setValue(response.body()); //đưa dữ liệu vào
                }
            }

            @Override
            public void onFailure(Call<ResponseUser1s> call, Throwable t) {

            }
        });
        return newsData;
    }

}
