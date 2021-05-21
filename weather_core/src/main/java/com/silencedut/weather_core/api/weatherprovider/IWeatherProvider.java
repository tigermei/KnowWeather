package com.silencedut.weather_core.api.weatherprovider;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.v4.util.Pair;

import com.silencedut.weather_core.api.ICoreApi;
import com.silencedut.weather_core.corebase.BaseFragment;
import com.silencedut.weather_core.corebase.StatusDataResource;

import java.util.List;

/**
 * Created by SilenceDut on 2018/1/17 .
 */

public interface IWeatherProvider extends ICoreApi {
    LiveData<StatusDataResource<WeatherData>> getWeatherData();
    List<WeatherData> fetchFollowedWeather();
    void updateWeather();
    void updateWeather(String cityId);
    void deleteWeather(String cityId);
    Pair<BaseFragment,Integer> provideWeatherFragment();
}
