package com.smkapps.weatherforecast.api;

import com.smkapps.weatherforecast.models.threehourforecast.ThreeHourForecast;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface OpenWeatherMapService {


    @GET("/data/2.5/forecast")
    Call<ThreeHourForecast> getThreeHourForecastByCityName(@QueryMap Map<String, String> options);


}