package com.example.datn_10116101.DangKy_DangNhap.viewModel;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.datn_10116101.BaseResponse.ResponseUser1s;
import com.example.datn_10116101.DangKy_DangNhap.network.ApiDangky;
import com.example.datn_10116101.NetWork.ApiUser;
import com.example.datn_10116101.Service.RetrofitService;
import com.example.datn_10116101.model.products;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangkyViewModel extends ViewModel {
    private ApiDangky apiDangky = RetrofitService.cteateService(ApiDangky.class);

    public MutableLiveData<ResponseUser1s> CheckPhone(String phone) {
        final MutableLiveData<ResponseUser1s> newsData = new MutableLiveData<>();
        apiDangky.checkPhone(phone).enqueue(new Callback<ResponseUser1s>() {
            @Override
            public void onResponse(Call<ResponseUser1s> call, Response<ResponseUser1s> response) {
                if (response.isSuccessful()) {
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ResponseUser1s> call, Throwable t) {

            }
        });
        return newsData;
    }

    public MutableLiveData<ResponseUser1s> checkAccount(String acc) {
        final MutableLiveData<ResponseUser1s> newsData = new MutableLiveData<>();
        apiDangky.checkAccount(acc).enqueue(new Callback<ResponseUser1s>() {
            @Override
            public void onResponse(Call<ResponseUser1s> call, Response<ResponseUser1s> response) {
                if (response.isSuccessful()) {
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ResponseUser1s> call, Throwable t) {

            }
        });
        return newsData;
    }

}
