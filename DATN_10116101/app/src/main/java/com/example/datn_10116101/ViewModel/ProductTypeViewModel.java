package com.example.datn_10116101.ViewModel;

import com.example.datn_10116101.BaseResponse.ProductBaseResponse;
import com.example.datn_10116101.model.product_types;
import com.example.datn_10116101.NetWork.APIProduct;
import com.example.datn_10116101.NetWork.APIProducttype;
import com.example.datn_10116101.Service.RetrofitService;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductTypeViewModel extends ViewModel {

    private APIProducttype dataClient;
    private APIProduct apiProduct;

    public MutableLiveData<List<product_types>> getProducttype(){
        final MutableLiveData<List<product_types>>  newsData = new MutableLiveData<>();
        dataClient = RetrofitService.cteateService(APIProducttype.class);
        dataClient.getallloaisp().enqueue(new Callback<List<product_types>>() {
            @Override
            public void onResponse(Call<List<product_types>> call, Response<List<product_types>> response) {
                newsData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<product_types>> call, Throwable t) {

            }
        });
        return newsData;
    }

    public MutableLiveData<ProductBaseResponse> getnewpro(){
        final MutableLiveData<ProductBaseResponse>  newsData = new MutableLiveData<>();
        apiProduct = RetrofitService.cteateService(APIProduct.class);
        apiProduct.getnewpro().enqueue(new Callback<ProductBaseResponse>() {
            @Override
            public void onResponse(Call<ProductBaseResponse> call, Response<ProductBaseResponse> response) {
               if(response.isSuccessful()){
                   newsData.setValue(response.body());
               }
            }

            @Override
            public void onFailure(Call<ProductBaseResponse> call, Throwable t) {

            }
        });
        return newsData;
    }

    public MutableLiveData<ProductBaseResponse> LoadListProductType(int idproducttype,int page){
        final MutableLiveData<ProductBaseResponse>  newsData = new MutableLiveData<>();
        apiProduct = RetrofitService.cteateService(APIProduct.class);
        apiProduct.getSPLQ(idproducttype,page).enqueue(new Callback<ProductBaseResponse>() {
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
