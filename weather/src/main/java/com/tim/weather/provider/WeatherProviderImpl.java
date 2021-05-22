package com.tim.weather.provider;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.v4.util.Pair;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

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

@Route(path = "/weather/weatherdata", name = "weather data provider")
public class WeatherProviderImpl implements IWeatherProvider {
    private final static String TAG = "WeahterProviderImpl";

    @Override
    public void init(Context context){}

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
        ARouter.getInstance().navigation(IFetchWeather.class).queryWeather(cityId);
    }

    @Override
    public void updateWeather(){
        if((ARouter.getInstance().navigation(ICityProvider.class)).hadCurrentCityId()){
            ARouter.getInstance().navigation(IFetchWeather.class).queryWeather(
                    (ARouter.getInstance().navigation(ICityProvider.class)).getCurrentCityId()
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
