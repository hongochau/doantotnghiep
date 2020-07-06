package com.example.datn_10116101.ViewModel;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.datn_10116101.model.ChiTietHoaDon;
import com.example.datn_10116101.model.products;
import com.example.datn_10116101.NetWork.APIhoadon;
import com.example.datn_10116101.Service.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiTietHoaDonViewModel extends ViewModel {
    private APIhoadon dataClient; // khai báo giao diện call api
    private MutableLiveData<products> mutableLiveData;

    public MutableLiveData<List<ChiTietHoaDon>> getChiTietHoaDon(int idHoadon){
        final MutableLiveData<List<ChiTietHoaDon>> newsData = new MutableLiveData<>(); // khai báo biến để hứng dữ liệu
        dataClient = RetrofitService.cteateService(APIhoadon.class); // tiến hành khởi tạo
        dataClient.getChiTietHoaDon(idHoadon).enqueue(new Callback<List<ChiTietHoaDon>>() { // call api
            @Override
            public void onResponse(Call<List<ChiTietHoaDon>> call, Response<List<ChiTietHoaDon>> response) {
                newsData.setValue(response.body()); // đưa dữ liệu  vào màn hình khởi tạo
            }

            @Override
            public void onFailure(Call<List<ChiTietHoaDon>> call, Throwable t) {

            }
        });
        return newsData;
    }
}
