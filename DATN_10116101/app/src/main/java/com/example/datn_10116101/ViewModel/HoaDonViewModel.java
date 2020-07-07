package com.example.datn_10116101.ViewModel;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.datn_10116101.model.HoaDon;
import com.example.datn_10116101.model.products;
import com.example.datn_10116101.NetWork.APIhoadon;
import com.example.datn_10116101.Service.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HoaDonViewModel extends ViewModel {
    private APIhoadon dataClient;
    private MutableLiveData<products> mutableLiveData;

    public MutableLiveData<List<HoaDon>> getHoadon(int idUser,String stt){
        final MutableLiveData<List<HoaDon>> newsData = new MutableLiveData<>();
        dataClient = RetrofitService.cteateService(APIhoadon.class);
        dataClient.getHoaDon(idUser,stt).enqueue(new Callback<List<HoaDon>>() {
            @Override
            public void onResponse(Call<List<HoaDon>> call, Response<List<HoaDon>> response) {
               if(response.isSuccessful()){
                   newsData.setValue(response.body());
               }
            }

            @Override
            public void onFailure(Call<List<HoaDon>> call, Throwable t) {

            }
        });
        return newsData;
    }
}
