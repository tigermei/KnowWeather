package com.tim.weather.api;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.tim.weather.entity.AqiEntity;
import com.tim.weather.entity.HeWeather;
import com.tim.weather.entity.WeatherTransverter;
import com.tim.weather.repository.WeatherRepository;

import com.silencedut.baselib.commonhelper.log.LogHelper;
import com.silencedut.hub_annotation.HubInject;
import com.silencedut.weather_core.AppHttpClient;
import com.silencedut.weather_core.CoreManager;
import com.silencedut.weather_core.api.cityprovider.City;
import com.silencedut.weather_core.api.cityprovider.ICityProvider;
import com.silencedut.weather_core.api.weatherprovider.WeatherData;
import com.silencedut.weather_core.corebase.StatusDataResource;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by SilenceDut on 2018/1/21 .
 */
@Route(path = "/weather/weatherfetch", name = "weather weatherfetch service")
public class WeatherFetchImpl implements IFetchWeather {
    private static final String TAG = "WeatherFetchImpl";
    private static final String $ = "$";
    private NetWeatherApi mNetWeatherApi;
    private AtomicReference<String> mStringAtomicReference = new AtomicReference<>($);

    @Override
    public void init(Context context) {
        mNetWeatherApi = AppHttpClient.getInstance().getService(NetWeatherApi.class);
    }

    private void queryWeatherInternal(final String cityId){
        try {
            WeatherRepository.getInstance().updateWeather(cityId, StatusDataResource.<WeatherData> loading());

            //
            //设置当前的cityid
            (ARouter.getInstance().navigation(ICityProvider.class)).saveCurrentCityId(cityId);

            Call<HeWeather> weatherEntityCall  = mNetWeatherApi.getWeather(NetWeatherApi.sHeyWeatherKey,cityId);

            /**
             *和风天气不支持县级空气质量
             **/
            City currentCity = (ARouter.getInstance().navigation(ICityProvider.class)).searchCity(cityId);
            String cityName = cityId;
            if(currentCity !=null) {
                cityName = currentCity.cityName;
            }

            Call<AqiEntity> aqiEntityCall  = mNetWeatherApi.getAqi(NetWeatherApi.sHeyWeatherKey, cityName);

            Response<HeWeather> heWeatherResponse = weatherEntityCall.execute();
            Response<AqiEntity> aqiEntityResponse = aqiEntityCall.execute();
            if(heWeatherResponse.isSuccessful()) {
                WeatherData weatherData = WeatherTransverter.convertFromHeWeather(heWeatherResponse.body(),aqiEntityResponse.body());
                WeatherRepository.getInstance().updateWeather(cityId,StatusDataResource.success(weatherData));
            }else {
                LogHelper.error(TAG, "fetchWeather fail,response is %s",heWeatherResponse.errorBody());
                WeatherRepository.getInstance().updateWeather(cityId,StatusDataResource.<WeatherData>error(heWeatherResponse.errorBody().string()));
            }
        } catch (Exception e) {
            LogHelper.error(TAG, "fetchWeather fail , error " +e);
            WeatherRepository.getInstance().updateWeather(cityId,StatusDataResource.<WeatherData>error("更新失败"));
        }

    }

    @Override
    public void queryWeather(final String cityId) {
        if(cityId == null || cityId.equals(mStringAtomicReference.get())) {
            return;
        }

        mStringAtomicReference.set(cityId);

        WeatherRepository.getInstance().getWeatherWorkHandler().post(new Runnable() {
            @Override
            public void run() {
                queryWeatherInternal(cityId);

                mStringAtomicReference.set($);

            }

        });
    }

    @Override
    public void queryWeather(List<String> citys) {

    }
}
