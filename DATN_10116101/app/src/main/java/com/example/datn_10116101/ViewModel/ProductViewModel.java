package com.example.datn_10116101.ViewModel;



import com.example.datn_10116101.BaseResponse.ProductBaseResponse;
import com.example.datn_10116101.model.products;
import com.example.datn_10116101.NetWork.APIProduct;
import com.example.datn_10116101.Service.RetrofitService;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductViewModel extends ViewModel {
    private APIProduct dataClient;
    private MutableLiveData<products> mutableLiveData;

    public MutableLiveData<ProductBaseResponse> LoadProduct(int page){
        final MutableLiveData<ProductBaseResponse> newsData = new MutableLiveData<>();
        dataClient = RetrofitService.cteateService(APIProduct.class);
        dataClient.getListProduct(page).enqueue(new Callback<ProductBaseResponse>() {
            @Override
            public void onResponse(Call<ProductBaseResponse> call, Response<ProductBaseResponse> response) {
                newsData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ProductBaseResponse> call, Throwable t) {

            }
        });
        return newsData;
    }

    public MutableLiveData<String> getCount(){
        final MutableLiveData<String> newsData = new MutableLiveData<>();
        dataClient = RetrofitService.cteateService(APIProduct.class);
        dataClient.getCount().enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                newsData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
        return newsData;
    }

    public MutableLiveData<ProductBaseResponse> LoadProductwithType(int id){
        final MutableLiveData<ProductBaseResponse> newsData = new MutableLiveData<>();
        dataClient = RetrofitService.cteateService(APIProduct.class);
        dataClient.getproType(id).enqueue(new Callback<ProductBaseResponse>() {
            @Override
            public void onResponse(Call<ProductBaseResponse> call, Response<ProductBaseResponse> response) {
                newsData.setValue(response.body());
            }
            @Override
            public void onFailure(Call<ProductBaseResponse> call, Throwable t) {

            }
        });
        return newsData;
    }


    public MutableLiveData<List<products>> searchProduct(String name){
        final MutableLiveData<List<products>> newsData = new MutableLiveData<>();
        dataClient = RetrofitService.cteateService(APIProduct.class);
        dataClient.searchProduct(name).enqueue(new Callback<List<products>>() {
            @Override
            public void onResponse(Call<List<products>> call, Response<List<products>> response) {
                newsData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<products>> call, Throwable t) {
            }
        });
        return newsData;
    }

    public MutableLiveData<products> searchByid(String id){
        final MutableLiveData<products> newsData = new MutableLiveData<>();
        dataClient = RetrofitService.cteateService(APIProduct.class);
        dataClient.searchByid(id).enqueue(new Callback<products>() {
            @Override
            public void onResponse(Call<products> call, Response<products> response) {
                newsData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<products> call, Throwable t) {

            }
        });
        return newsData;
    }

    public MutableLiveData<ProductBaseResponse> LoadProductwithType(int page,int idproduct){
        final MutableLiveData<ProductBaseResponse> newsData = new MutableLiveData<>();
        dataClient = RetrofitService.cteateService(APIProduct.class);
        dataClient.LoadmoreWithproductType(page,idproduct).enqueue(new Callback<ProductBaseResponse>() {
            @Override
            public void onResponse(Call<ProductBaseResponse> call, Response<ProductBaseResponse> response) {
                newsData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ProductBaseResponse> call, Throwable t) {

            }
        });
        return newsData;
    }



}
