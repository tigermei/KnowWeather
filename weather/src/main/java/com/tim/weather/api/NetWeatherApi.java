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

    public final static String sHeyWeatherKey = "7e0c26e74f384de59efb7a86565a1c0f";

    @GET("weather")
    Call<HeWeather> getWeather(@Query("key") String key, @Query("location") String location);

    @GET("air/now")
    Call<AqiEntity> getAqi(@Query("key") String key, @Query("location") String location);
}
