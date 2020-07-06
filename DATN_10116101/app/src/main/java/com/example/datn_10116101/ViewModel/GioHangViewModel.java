package com.example.datn_10116101.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.datn_10116101.model.ChiTietHoaDon;
import com.example.datn_10116101.model.products;
import com.example.datn_10116101.NetWork.APIhoadon;
import com.example.datn_10116101.Service.RetrofitService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GioHangViewModel extends ViewModel {
    private APIhoadon dataClient;
    private MutableLiveData<products> mutableLiveData;

    public MutableLiveData<Integer> GhiHoadon(int iduser, String status, int totalprice, String booktime) {
        final MutableLiveData<Integer> newsData = new MutableLiveData<>();
        dataClient = RetrofitService.cteateService(APIhoadon.class);
        dataClient.GhiHoadon(iduser, status, totalprice, booktime).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                newsData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });
        return newsData;
    }

    public MutableLiveData<Boolean> GhiChiTietHoaDon(ArrayList<ChiTietHoaDon> chiTietHoaDons) {
        final MutableLiveData<Boolean> newsData = new MutableLiveData<>();
        dataClient = RetrofitService.cteateService(APIhoadon.class);
        dataClient.GhiChiTietHoaDon(chiTietHoaDons).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if(response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }
        });
        return newsData;
    }
}