package com.example.datn_10116101.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.datn_10116101.BaseResponse.ResponseUser1s;
import com.example.datn_10116101.NetWork.ApiUser;
import com.example.datn_10116101.Service.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateThongTinTaiKhoanViewModel extends ViewModel {
    private ApiUser dataClient;


    public MutableLiveData<ResponseUser1s> ResponseUser1s (String acc, String name, String email, String address, String phone) {
        final MutableLiveData<ResponseUser1s> newsData = new MutableLiveData<>();
        dataClient = RetrofitService.cteateService(ApiUser.class);
        dataClient.UpDateTT(acc, name, email, address, phone).enqueue(new Callback<ResponseUser1s>() {
            @Override
            public void onResponse(Call<ResponseUser1s> call, Response<ResponseUser1s> response) {
                newsData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ResponseUser1s> call, Throwable t) {

            }
        });
        return newsData;
    }
}