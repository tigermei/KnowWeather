package com.silencedut.city.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.annotation.MainThread;
import androidx.annotation.Nullable;

import com.alibaba.android.arouter.launcher.ARouter;
import com.silencedut.baselib.commonhelper.log.LogHelper;
import com.silencedut.city.ui.adapter.FollowedCityData;
import com.silencedut.city.ui.adapter.FollowedCityHolder;
import com.silencedut.taskscheduler.Task;
import com.silencedut.taskscheduler.TaskScheduler;
import com.silencedut.weather_core.CoreManager;
import com.silencedut.weather_core.api.cityprovider.ICityProvider;
import com.silencedut.weather_core.api.weatherprovider.IWeatherProvider;
import com.silencedut.weather_core.api.weatherprovider.WeatherData;
import com.silencedut.weather_core.corebase.StatusDataResource;
import com.silencedut.weather_core.location.ILocationApi;
import com.silencedut.weather_core.location.LocationNotification;
import com.silencedut.weather_core.viewmodel.BaseViewModel;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by SilenceDut on 2018/1/17 .
 */

public class CityModel extends BaseViewModel implements LocationNotification{
    private static final String TAG = "CityModel";

    private MutableLiveData<List<FollowedCityData>> mFollowedWeather = new MutableLiveData<>();


    @Override
    protected void onCreate() {
        mFollowedWeather.setValue(new CopyOnWriteArrayList<FollowedCityData>());
        ARouter.getInstance().navigation(IWeatherProvider.class).getWeatherData().observe(this, new Observer<StatusDataResource<WeatherData>>() {
            @Override
            public void onChanged(@Nullable StatusDataResource<WeatherData> statusDataResource) {
                if(statusDataResource.isSucceed()) {
                    onWeather(statusDataResource.data);
                }
            }

        });
    }

    LiveData<List<FollowedCityData>> getFollowedWeather() {
        return mFollowedWeather;
    }


    public void fetchFollowedWeather() {
        TaskScheduler.execute(new Task<List<WeatherData>>() {
            @Override
            public List<WeatherData> doInBackground() throws InterruptedException {
                return  ARouter.getInstance().navigation(IWeatherProvider.class).fetchFollowedWeather();
            }

            @Override
            public void onSuccess(List<WeatherData> weatherData) {
                parseFollowedWeathers(weatherData);
            }
        });

    }

    public void deleteFollowedWeather(String cityId) {
        ARouter.getInstance().navigation(IWeatherProvider.class).deleteWeather(cityId);

        if((ARouter.getInstance().navigation(ICityProvider.class)).getCurrentCityId().equals(cityId)) {
            String locationId = ARouter.getInstance().navigation(ILocationApi.class).getLocatedCityId();
            (ARouter.getInstance().navigation(ICityProvider.class)).saveCurrentCityId(locationId);
            ARouter.getInstance().navigation(IWeatherProvider.class).updateWeather(locationId);
        }

        List<FollowedCityData> followedCityDatas = mFollowedWeather.getValue();
        for (FollowedCityData followedCityData : followedCityDatas) {
            if (followedCityData.getCityId().equals(cityId)) {
                followedCityDatas.remove(followedCityData);
                break;
            }
        }

        mFollowedWeather.setValue(followedCityDatas);
        LogHelper.info(TAG, "followedCityDatas.size() = :"+followedCityDatas.size());
    }


    @MainThread
    private  void parseFollowedWeathers(List<WeatherData> weatherDatas) {
        List<FollowedCityData> followedCityDatas = mFollowedWeather.getValue();
        followedCityDatas.clear();

        for(int index =0;index<weatherDatas.size();index++) {
            WeatherData weatherData = weatherDatas.get(index);
            if (weatherData != null) {
                if (weatherData.getCityId().equals(ARouter.getInstance().navigation(ILocationApi.class).getLocatedCityId())) {
                    followedCityDatas.add(0, new FollowedCityData(weatherData, FollowedCityHolder.BLUR_IMAGE[index % FollowedCityHolder.BLUR_IMAGE.length]));
                } else {
                    followedCityDatas.add(new FollowedCityData(weatherData, FollowedCityHolder.BLUR_IMAGE[index % FollowedCityHolder.BLUR_IMAGE.length]));
                }
            }
        }
        mFollowedWeather.postValue(followedCityDatas);
        LogHelper.info(TAG, "parseFollowedWeathers followedCityDatas.size() = :"+followedCityDatas.size());
    }

    private void onWeather(WeatherData weatherData) {
        boolean exist = false;
        List<FollowedCityData> followedCityDatas = mFollowedWeather.getValue();
        for (FollowedCityData followedCityData : followedCityDatas) {
            if (followedCityData.getCityId().equals(weatherData.getCityId())) {
                followedCityData.update(weatherData);
                exist = true;
                break;
            }
        }

        if(!exist) {
            if (ARouter.getInstance().navigation(ILocationApi.class).getLocatedCityId().equals(weatherData.getCityId())) {
                followedCityDatas.add(0, new FollowedCityData(weatherData, FollowedCityHolder.BLUR_IMAGE[(followedCityDatas.size()+1) % FollowedCityHolder.BLUR_IMAGE.length]));
            } else {
                followedCityDatas.add(new FollowedCityData(weatherData, FollowedCityHolder.BLUR_IMAGE[(followedCityDatas.size()+1) % FollowedCityHolder.BLUR_IMAGE.length]));
            }
        }

        mFollowedWeather.setValue(followedCityDatas);
        LogHelper.info(TAG, "onWeather followedCityDatas.size() = :"+followedCityDatas.size());
    }


    @Override
    public void onLocation(boolean success, String cityId) {
        fetchFollowedWeather();
    }
}
