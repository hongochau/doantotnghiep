package com.example.datn_10116101.ViewModel;

import com.example.datn_10116101.NetWork.DataClient;
import com.example.datn_10116101.Service.RetrofitService;
import com.example.datn_10116101.Model.BannerModel;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BannerViewModel extends ViewModel {
    private DataClient dataClient;

    public MutableLiveData<List<BannerModel>> getBanners(){
        final MutableLiveData<List<BannerModel>>  newsData = new MutableLiveData<>();
        dataClient = RetrofitService.cteateService(DataClient.class);
        dataClient.getBanner().enqueue(new Callback<List<BannerModel>>() {
            @Override
            public void onResponse(Call<List<BannerModel>> call, Response<List<BannerModel>> response) {
              if(response.isSuccessful()){ // check dữ liệu call về xem có đúng ko
                  newsData.setValue(response.body()); // set dữ liệu vào trong newdata trả về bên activity
              }
            }

            @Override
            public void onFailure(Call<List<BannerModel>> call, Throwable t) {

            }
        });
        return newsData;
    }
}
