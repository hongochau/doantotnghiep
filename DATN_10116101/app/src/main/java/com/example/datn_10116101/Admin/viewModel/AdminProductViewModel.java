package com.example.datn_10116101.Admin.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.datn_10116101.Admin.APIadmin;
import com.example.datn_10116101.BaseResponse.ResponseBill;
import com.example.datn_10116101.BaseResponse.productObjectResponse;
import com.example.datn_10116101.BaseResponse.productResponse;
import com.example.datn_10116101.Service.RetrofitService;
import com.example.datn_10116101.model.HoaDon;
import com.example.datn_10116101.model.products;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminProductViewModel extends ViewModel {
    private APIadmin apIadmin = RetrofitService.cteateService(APIadmin.class);

    public MutableLiveData<productResponse> getAllProduct() {
        final MutableLiveData<productResponse> newsData = new MutableLiveData<>();
        apIadmin.getAllProduct().enqueue(new Callback<productResponse>() {
            @Override
            public void onResponse(Call<productResponse> call, Response<productResponse> response) {
                if (response.isSuccessful()) {
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<productResponse> call, Throwable t) {

            }
        });
        return newsData;
    }


    public MutableLiveData<productObjectResponse> CreateProduct(int idProductType, String name, String describe, int price, String image) {
        final MutableLiveData<productObjectResponse> newsData = new MutableLiveData<>();
        apIadmin.CreateProduct(idProductType, name, describe, price, image).enqueue(new Callback<productObjectResponse>() {
            @Override
            public void onResponse(Call<productObjectResponse> call, Response<productObjectResponse> response) {
                if (response.isSuccessful()) {
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<productObjectResponse> call, Throwable t) {

            }
        });
        return newsData;
    }

    public MutableLiveData<productResponse> updateProduct(int id,int idProductType, String name, String describe, int price, String image) {
        final MutableLiveData<productResponse> newsData = new MutableLiveData<>();
        apIadmin.updateProduct(id,idProductType, name, describe, price, image).enqueue(new Callback<productResponse>() {
            @Override
            public void onResponse(Call<productResponse> call, Response<productResponse> response) {
                if (response.isSuccessful()) {
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<productResponse> call, Throwable t) {

            }
        });
        return newsData;
    }

    public MutableLiveData<productResponse> deleteProduct(int id) {
        final MutableLiveData<productResponse> newsData = new MutableLiveData<>();
        apIadmin.deleteProduct(id).enqueue(new Callback<productResponse>() {
            @Override
            public void onResponse(Call<productResponse> call, Response<productResponse> response) {
                if (response.isSuccessful()) {
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<productResponse> call, Throwable t) {

            }
        });
        return newsData;
    }

}
