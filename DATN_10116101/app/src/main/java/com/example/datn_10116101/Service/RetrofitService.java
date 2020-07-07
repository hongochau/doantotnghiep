package com.example.datn_10116101.Service;

import java.util.Collections;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    public static String basePath="http://192.168.2.32/huhu/public/"; // phòng trchiu hciu

    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS) //ngắt kết nối sau 10s nếu sever không trả dữ liệu về
            .writeTimeout(10, TimeUnit.SECONDS) // ngắt kết nối sau 10s nếu ko ghi được dữ liệu lên serverr
            .connectTimeout(10, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true) //cố gắng kết nối lại nếu bị trục trặc về mạng
            .protocols(Collections.singletonList(Protocol.HTTP_1_1)) //tránh bị lỗi khi kết nối không được với sever
            .build();
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(basePath)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public static <S> S cteateService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
