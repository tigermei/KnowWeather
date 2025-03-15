package com.tim.weather.api;

import com.silencedut.weather_core.entity.AqiEntityV7;
import com.silencedut.weather_core.entity.HeWeatherV7;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by SilenceDut on 16/10/28.
 */

public interface NetWeatherApi {

    public final static String sHeyWeatherKey = "ce1b15a6370640148e149d331d6e5d95";

    @GET("weather/now")
    Call<HeWeatherV7> getWeather(@Query("key") String key, @Query("location") String location);

    @GET("air/now")
    Call<AqiEntityV7> getAqi(@Query("key") String key, @Query("location") String location);
}
