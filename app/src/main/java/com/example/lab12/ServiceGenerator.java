package com.example.lab12;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private final static String BASE_API_URL = "http://api.nbp.pl/api/exchangerates/rates/a/";
    private static Retrofit retrofit = null;
    private static Gson gson = new GsonBuilder().create();
    private static HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    private static OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor);
    private static OkHttpClient okHttpClient = okHttpClientBuilder.build();
    public static <T> T eur(Class<T> serviceClass){
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_API_URL + "eur/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(serviceClass);
    }
    public static <T> T usd(Class<T> serviceClass){
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_API_URL + "usd/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(serviceClass);
    }
    public static <T> T chf(Class<T> serviceClass){
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_API_URL + "chf/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(serviceClass);
    }
}
