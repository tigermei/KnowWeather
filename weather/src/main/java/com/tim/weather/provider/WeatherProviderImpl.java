package com.tim.weather.provider;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.v4.util.Pair;

import com.silencedut.hub_annotation.HubInject;
import com.silencedut.weather_core.CoreManager;
import com.silencedut.weather_core.api.weatherprovider.IWeatherProvider;
import com.silencedut.weather_core.api.weatherprovider.WeatherData;
import com.silencedut.weather_core.corebase.BaseFragment;
import com.silencedut.weather_core.corebase.StatusDataResource;

import com.tim.weather.api.IFetchWeather;
import com.tim.weather.repository.WeatherRepository;
import com.tim.weather.R;

//
//TODO
//import com.silencedut.knowweather.scheduleJob.PollingUtils;
//import com.silencedut.setting.ui.SettingFragment;
import com.tim.weather.ui.WeatherFragment;

import java.util.List;

/**
 * Created by SilenceDut on 2018/1/19 .
 */

@HubInject(api = IWeatherProvider.class)
public class WeatherProviderImpl implements IWeatherProvider {

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
    public void deleteWeather(String cityId) {
        WeatherRepository.getInstance().deleteWeather(cityId);
    }

    @Override
    public void startService(Context context, boolean allowPoll) {

        //
        //TODO
        //PollingUtils.startService(context,allowPoll);
    }

    @Override
    public Pair<BaseFragment, Integer> provideWeatherFragment() {
        return new Pair<>(WeatherFragment.newInstance(), R.drawable.weather_tab_drawable);
    }
}
