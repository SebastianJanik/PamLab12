package com.example.lab12;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("?format=json")
    Call<IPInfo> getIPInfo();
}
