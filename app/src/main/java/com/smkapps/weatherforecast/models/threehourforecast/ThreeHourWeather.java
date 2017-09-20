package com.smkapps.weatherforecast.models.threehourforecast;

import com.google.gson.annotations.SerializedName;
import com.smkapps.weatherforecast.models.common.Clouds;
import com.smkapps.weatherforecast.models.common.Main;
import com.smkapps.weatherforecast.models.common.Sys;
import com.smkapps.weatherforecast.models.common.Weather;
import com.smkapps.weatherforecast.models.common.Wind;

import java.util.List;



public class ThreeHourWeather {

    @SerializedName("dt")
    private Long dt;

    @SerializedName("main")
    private Main mMain;

    @SerializedName("weather")
    private List<Weather> mWeatherArray;

    @SerializedName("clouds")
    private Clouds mClouds;

    @SerializedName("wind")
    private Wind mWind;

    @SerializedName("sys")
    private Sys mSys;

    @SerializedName("dt_txt")
    private String dtTxt;

    public Long getDt() {
        return dt;
    }

    public void setDt(Long dt) {
        this.dt = dt;
    }

    public Main getMain() {
        return mMain;
    }

    public void setMain(Main main) {
        mMain = main;
    }

    public List<Weather> getWeatherArray() {
        return mWeatherArray;
    }

    public void setWeatherArray(List<Weather> weatherArray) {
        mWeatherArray = weatherArray;
    }

    public Clouds getClouds() {
        return mClouds;
    }

    public void setClouds(Clouds clouds) {
        mClouds = clouds;
    }

    public Wind getWind() {
        return mWind;
    }

    public void setWind(Wind wind) {
        mWind = wind;
    }

    public Sys getSys() {
        return mSys;
    }

    public void setSys(Sys sys) {
        mSys = sys;
    }

    public String getDtTxt() {
        return dtTxt;
    }

    public void setDtTxt(String dtTxt) {
        this.dtTxt = dtTxt;
    }
}
