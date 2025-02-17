package com.tim.weather.api;

import com.tim.weather.entity.AqiEntity;
import com.tim.weather.entity.HeWeather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by SilenceDut on 16/10/28.
 */

public interface NetWeatherApi {

    public final static String sHeyWeatherKey = "ce1b15a6370640148e149d331d6e5d95";

    @GET("weather/now")
    Call<HeWeather> getWeather(@Query("key") String key, @Query("location") String location);

    @GET("air/now")
    Call<AqiEntity> getAqi(@Query("key") String key, @Query("location") String location);
}
