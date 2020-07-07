package com.example.datn_10116101.Admin.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.datn_10116101.Admin.APIadmin;
import com.example.datn_10116101.BaseResponse.ResponseBill;
import com.example.datn_10116101.Service.RetrofitService;
import com.example.datn_10116101.model.HoaDon;
import com.example.datn_10116101.model.products;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminViewModel extends ViewModel {
    private APIadmin apIadmin=RetrofitService.cteateService(APIadmin.class);

    public MutableLiveData<ResponseBill> getallWithtype(String stt){
        final MutableLiveData<ResponseBill>  newsData = new MutableLiveData<>();
        apIadmin.getallbillwithtype(stt).enqueue(new Callback<ResponseBill>() {
            @Override
            public void onResponse(Call<ResponseBill> call, Response<ResponseBill> response) {
                if(response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ResponseBill> call, Throwable t) {

            }
        });
        return newsData;
    }

    public MutableLiveData<ResponseBill> changeStatus(String stt,int idbill){
        final MutableLiveData<ResponseBill>  newsData = new MutableLiveData<>();
        apIadmin.changeStatus(stt,idbill).enqueue(new Callback<ResponseBill>() {
            @Override
            public void onResponse(Call<ResponseBill> call, Response<ResponseBill> response) {
                if(response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ResponseBill> call, Throwable t) {

            }
        });
        return newsData;
    }

    public MutableLiveData<List<products>> loadSanphamBanChay(){
        final MutableLiveData<List<products>>  newsData = new MutableLiveData<>();
        apIadmin.getDescProduct().enqueue(new Callback<List<products>>() {
            @Override
            public void onResponse(Call<List<products>> call, Response<List<products>> response) {
                if(response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<products>> call, Throwable t) {

            }
        });
        return newsData;
    }

    public MutableLiveData<List<HoaDon>> getHoadonbeetween(String from, String to){
        final MutableLiveData<List<HoaDon>>  newsData = new MutableLiveData<>();
        apIadmin.getHoadonbeetween(from,to).enqueue(new Callback<List<HoaDon>>() {
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
