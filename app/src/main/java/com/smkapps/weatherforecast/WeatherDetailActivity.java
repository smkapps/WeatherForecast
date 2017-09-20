package com.smkapps.weatherforecast;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.smkapps.weatherforecast.adapters.ForecastAdapter;
import com.smkapps.weatherforecast.api.OpenWeatherMapClient;
import com.smkapps.weatherforecast.api.OpenWeatherMapService;
import com.smkapps.weatherforecast.models.threehourforecast.ThreeHourForecast;
import com.smkapps.weatherforecast.models.threehourforecast.ThreeHourWeather;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherDetailActivity extends AppCompatActivity {
    public static final String CITY_KEY = "city_key";
    public static final String API_KEY = "d2a6b21c943e38d9e44edcc03c9912ad";
    OpenWeatherMapService mOpenWeatherMapService;
    RecyclerView mRecyclerView;
    Toolbar mToolbar;
    String cityName;
    List<ThreeHourWeather> weatherList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_detail);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        cityName = getIntent().getStringExtra(CITY_KEY);
        mToolbar.setSubtitle(cityName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mOpenWeatherMapService = OpenWeatherMapClient.getClient().create(OpenWeatherMapService.class);
        Map<String, String> map = getQueryMap();
        mOpenWeatherMapService.getThreeHourForecastByCityName(map).enqueue(new Callback<ThreeHourForecast>() {
            @Override
            public void onResponse(Call<ThreeHourForecast> call, Response<ThreeHourForecast> response) {

                ThreeHourForecast threeHourForecast = response.body();
                weatherList = threeHourForecast.getThreeHourWeatherArray();
                ForecastAdapter adapter = new ForecastAdapter(WeatherDetailActivity.this, weatherList);
                mRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ThreeHourForecast> call, Throwable t) {

            }
        });
    }

    @NonNull
    private Map<String, String> getQueryMap() {
        Map<String,String> map  = new HashMap<>();
        map.put("q", cityName);
        map.put("units", "metric");
        map.put("APPID", API_KEY);
        return map;
    }

    public void addCityToSaved(View view) {

        DBHelper dbHelper = new DBHelper(this);
        dbHelper.addCityToSaved(cityName);
    }
}
