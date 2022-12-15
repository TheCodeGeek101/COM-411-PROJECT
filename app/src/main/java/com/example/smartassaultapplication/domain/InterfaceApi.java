package com.example.smartassaultapplication.domain;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface InterfaceApi {

    @GET("data/2.5/weather")
    Call<Main> getData(
            @Query("lat") double lat,
            @Query("lon") double lon,
            @Query("appid") String APIKEY,
            @Query("units") String units
    );
}
