package com.tim.weather.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

//import com.silencedut.city.ui.search.SearchActivity;
//import com.silencedut.knowweather.WeatherApplication;

import com.alibaba.android.arouter.launcher.ARouter;
import com.silencedut.weather_core.api.IActivityRouter;
import com.tim.weather.api.IFetchWeather;
import com.tim.weather.repository.WeatherRepository;

import com.silencedut.baselib.commonhelper.log.LogHelper;
import com.tim.weather.R;
import com.silencedut.weather_core.CoreManager;
import com.silencedut.weather_core.api.cityprovider.ICityProvider;
import com.silencedut.weather_core.api.weatherprovider.WeatherData;
import com.silencedut.weather_core.corebase.StatusDataResource;
import com.silencedut.weather_core.location.ILocationApi;
import com.silencedut.weather_core.location.LocationNotification;
import com.silencedut.weather_core.viewmodel.BaseViewModel;
import com.tim.weather.ui.adapter.AqiData;
import com.tim.weather.ui.adapter.DailyWeatherData;
import com.tim.weather.ui.adapter.HoursForecastData;
import com.tim.weather.ui.adapter.LifeIndexData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SilenceDut on 2018/1/5 .
 */

public class WeatherModel extends BaseViewModel implements LocationNotification  {

    private static final String TAG = "WeatherModel";

    private MutableLiveData<StatusDataResource.Status> mGetWeatherStatus;

    private WeatherData.BasicEntity mWeatherBaseData;
    private List<HoursForecastData> mHoursDatas;
    private AqiData mAqiData;
    private List<DailyWeatherData> mDailyData;
    private LifeIndexData mLifeIndexData;

    @Override
    protected void onCreate() {

        mGetWeatherStatus = new MutableLiveData<>();

        WeatherRepository.getInstance().getWeatherObserver().observe(this, new Observer<StatusDataResource<WeatherData>>() {
            @Override
            public void onChanged(@Nullable StatusDataResource<WeatherData> statusDataResource) {

                try {
                    parseWeather(statusDataResource.data);
                }catch (Exception e) {
                    LogHelper.error(TAG,"parse weather date error %s",e);
                }

                mGetWeatherStatus.setValue(statusDataResource.status);
            }

        });
    }

    public LiveData<StatusDataResource.Status> getGetWeatherStatus() {
        return mGetWeatherStatus;
    }


    public void updateWeather() {
        if((ARouter.getInstance().navigation(ICityProvider.class)).hadCurrentCityId()) {
            ARouter.getInstance().navigation(IFetchWeather.class).queryWeather((ARouter.getInstance().navigation(ICityProvider.class)).getCurrentCityId());
        }
    }

    public boolean locationIsCurrent() {
        return (ARouter.getInstance().navigation(ICityProvider.class)).getCurrentCityId()
                .equals(ARouter.getInstance().navigation(ILocationApi.class).getLocatedCityId());
    }



    private void parseWeather(WeatherData weatherData) {

        mWeatherBaseData= weatherData.getBasic();

        List<HoursForecastData> hoursForecastDatas = new ArrayList<>();
        for (WeatherData.HoursForecastEntity hoursForecastEntity : weatherData.getHoursForecast()) {
            hoursForecastDatas.add(new HoursForecastData(hoursForecastEntity));
        }
        mHoursDatas = hoursForecastDatas ;

        mAqiData = new AqiData(weatherData.getAqi());

        List<DailyWeatherData> dailyWeatherDatas = new ArrayList<>();
        List<WeatherData.DailyForecastEntity> dailyForecastEntities = weatherData.getDailyForecast();
        if(null != dailyForecastEntities){
            for (int count = 0; count < dailyForecastEntities.size() - 2; count++) {
                // only take 5 days weather
                dailyWeatherDatas.add(new DailyWeatherData(dailyForecastEntities.get(count)));
            }
        } else {
            //
            //TODO tigermei

            Log.e(TAG, "parseWeather Error!");
        }
        mDailyData = dailyWeatherDatas;

        mLifeIndexData = new LifeIndexData(weatherData.getLifeIndex());
    }

    public WeatherData.BasicEntity getWeatherBaseData() {
        return mWeatherBaseData;
    }

    public List<HoursForecastData> getHoursDatas() {
        return mHoursDatas;
    }

    AqiData getAqiData() {
        return mAqiData;
    }

    List<DailyWeatherData> getDailyData() {
        return mDailyData;
    }

    LifeIndexData getLifeIndexData() {
        return mLifeIndexData;
    }

    @Override
    public void onLocation(boolean success, String cityId) {

        if (!success) {
            Toast.makeText(CoreManager.getContext(), R.string.weather_add_city_hand_mode, Toast.LENGTH_LONG).show();
            ARouter.getInstance().build("/city/activity/search").navigation();
        } else {
            if(!(ARouter.getInstance().navigation(ICityProvider.class)).hadCurrentCityId()) {
                (ARouter.getInstance().navigation(ICityProvider.class)).saveCurrentCityId(cityId);
                updateWeather();
            }
        }

    }
}
