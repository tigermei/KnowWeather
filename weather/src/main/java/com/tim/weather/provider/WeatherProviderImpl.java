package com.tim.weather.provider;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.v4.util.Pair;

import com.silencedut.baselib.commonhelper.log.LogHelper;
import com.silencedut.hub_annotation.HubInject;
import com.silencedut.weather_core.CoreManager;
import com.silencedut.weather_core.api.cityprovider.ICityProvider;
import com.silencedut.weather_core.api.weatherprovider.IWeatherProvider;
import com.silencedut.weather_core.api.weatherprovider.WeatherData;
import com.silencedut.weather_core.corebase.BaseFragment;
import com.silencedut.weather_core.corebase.StatusDataResource;

import com.tim.weather.api.IFetchWeather;
import com.tim.weather.repository.WeatherRepository;
import com.tim.weather.R;
import com.tim.weather.ui.WeatherFragment;

import java.util.List;

/**
 * Created by SilenceDut on 2018/1/19 .
 */

@HubInject(api = IWeatherProvider.class)
public class WeatherProviderImpl implements IWeatherProvider {
    private final static String TAG = "WeahterProviderImpl";

    @Override
    public void onCreate() {

    }

    @Override
    public LiveData<StatusDataResource<WeatherData>> getWeatherData() {

        return WeatherRepository.getInstance().getWeatherObserver();
    }

    @Override
    public List<WeatherData> fetchFollowedWeather() {
        return WeatherRepository.getInstance().getFollowedWeather();
    }

    @Override
    public void updateWeather(String cityId) {
        CoreManager.getImpl(IFetchWeather.class).queryWeather(cityId);
    }

    @Override
    public void updateWeather(){
        if(CoreManager.getImpl(ICityProvider.class).hadCurrentCityId()){
            CoreManager.getImpl(IFetchWeather.class).queryWeather(
                    CoreManager.getImpl(ICityProvider.class).getCurrentCityId()
            );
        } else {
            LogHelper.error(TAG, "No current city id!!");
        }

    }

    @Override
    public void deleteWeather(String cityId) {
        WeatherRepository.getInstance().deleteWeather(cityId);
    }

    @Override
    public Pair<BaseFragment, Integer> provideWeatherFragment() {
        return new Pair<>(WeatherFragment.newInstance(), R.drawable.weather_tab_drawable);
    }
}
