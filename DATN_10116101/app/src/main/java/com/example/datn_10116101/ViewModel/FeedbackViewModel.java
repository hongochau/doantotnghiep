package com.example.datn_10116101.ViewModel;


import android.widget.ListView;

import com.example.datn_10116101.BaseResponse.BaseResponseFeedback;
import com.example.datn_10116101.Model.FeedbackUser;
import com.example.datn_10116101.Model.feedback_products;
import com.example.datn_10116101.NetWork.APIfeedback;
import com.example.datn_10116101.Service.RetrofitService;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedbackViewModel extends ViewModel {
    private APIfeedback dataClient;

    public MutableLiveData<BaseResponseFeedback> getfeedback(int idproduct,int page){
        final MutableLiveData<BaseResponseFeedback> newsData = new MutableLiveData<>();
        dataClient = RetrofitService.cteateService(APIfeedback.class);
        dataClient.getfeedback(idproduct,page).enqueue(new Callback<BaseResponseFeedback>() {
            @Override
            public void onResponse(Call<BaseResponseFeedback> call, Response<BaseResponseFeedback> response) {
                newsData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<BaseResponseFeedback> call, Throwable t) {

            }
        });
        return newsData;
    }

    public MutableLiveData<Integer> pushFeedback (feedback_products feedbackProducts){
        final MutableLiveData<Integer> newsData = new MutableLiveData<>();
        dataClient = RetrofitService.cteateService(APIfeedback.class);
        dataClient.pushFeedback(feedbackProducts).enqueue(new Callback<Integer>() {
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

    public MutableLiveData<List<FeedbackUser>> getfeedbackwithUser (int idUser){
        final MutableLiveData<List<FeedbackUser>> newsData = new MutableLiveData<>();
        dataClient = RetrofitService.cteateService(APIfeedback.class);
        dataClient.getfeedbackwithUser(idUser).enqueue(new Callback<List<FeedbackUser>>() {
            @Override
            public void onResponse(Call<List<FeedbackUser>> call, Response<List<FeedbackUser>> response) {
                newsData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<FeedbackUser>> call, Throwable t) {

            }
        });
        return newsData;
    }

}
