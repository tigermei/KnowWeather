package com.tim.weather.api;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.silencedut.baselib.commonhelper.utils.JsonHelper;
import com.silencedut.weather_core.api.cityprovider.City;
import com.silencedut.weather_core.entity.AqiEntityV7;
import com.silencedut.weather_core.entity.HeWeatherV7;
import com.tim.weather.entity.WeatherTransverter;
import com.tim.weather.repository.WeatherRepository;

import com.silencedut.baselib.commonhelper.log.LogHelper;
import com.silencedut.weather_core.AppHttpClient;
import com.silencedut.weather_core.api.cityprovider.ICityProvider;
import com.silencedut.weather_core.api.weatherprovider.WeatherData;
import com.silencedut.weather_core.corebase.StatusDataResource;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import retrofit2.Call;
import retrofit2.Callback;
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

    private void showError(String message) {
        LogHelper.error(TAG, message);
    }


    private static City _city = null;
    private static HeWeatherV7 _weatherBean = null;
    private static AqiEntityV7 _aqiBean = null;

    /**
     * 显示天气信息
     * @param weatherBean 当前天气数据
     */
    private void callbackWeatherInfo (HeWeatherV7 weatherBean) {
        if (weatherBean != null) {
            _weatherBean = weatherBean;
            if(_aqiBean != null) {
                WeatherData weatherData = WeatherTransverter.convertFromHeWeather(
                        _city,
                        _weatherBean,
                        _aqiBean);
                WeatherRepository.getInstance().updateWeather(
                        _city.cityId,
                        StatusDataResource.success(weatherData));
                _weatherBean = null;
                _city = null;
                _aqiBean = null;
            }
        }
    }


    /**
     * 显示空气质量信息
     * @param aqiBean 当前空气质量数据
     */
    private void callbackAqiInfo (AqiEntityV7 aqiBean) {
        if (aqiBean != null) {
            _aqiBean = aqiBean;
            if(_weatherBean != null) {
                WeatherData weatherData = WeatherTransverter.convertFromHeWeather(
                        _city,
                        _weatherBean,
                        _aqiBean);
                WeatherRepository.getInstance().updateWeather(
                        _city.cityId,
                        StatusDataResource.success(weatherData));
                _weatherBean = null;
                _city = null;
                _aqiBean = null;
            }
        }
    }

    private void queryWeatherInternal(final String cityId){
        try {
            WeatherRepository.getInstance().updateWeather(cityId, StatusDataResource.<WeatherData> loading());

            //
            //设置当前的cityid
            (ARouter.getInstance().navigation(ICityProvider.class)).saveCurrentCityId(cityId);
            City currentCity = (ARouter.getInstance().navigation(ICityProvider.class)).searchCity(cityId);
            _city = currentCity;

            Call<HeWeatherV7> weatherEntityCall  = mNetWeatherApi.getWeather(NetWeatherApi.sHeyWeatherKey, cityId);
            weatherEntityCall.enqueue(new Callback<HeWeatherV7>() {
                @Override
                public void onResponse(Call<HeWeatherV7> call, Response<HeWeatherV7> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        String strJson = JsonHelper.toJson(response.body());
                        HeWeatherV7 weatherData = response.body();
                        HeWeatherV7.HeWeather7Bean weatherBean = weatherData.getHeWeather7();

                        if (weatherBean != null && "200".equals(weatherBean.getCode())) {
                            HeWeatherV7.HeWeather7Bean.NowBean nowBean = weatherBean.getNow();
                            callbackWeatherInfo(weatherData);
                        } else {
                            showError("获取天气数据失败: " + (weatherBean != null ? weatherBean.getCode() : "未知错误"));
                        }
                    } else {
                        showError("获取天气数据失败: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<HeWeatherV7> call, Throwable t) {
                    showError("网络请求失败: " + t.getMessage());
                    LogHelper.error(TAG, "获取天气数据失败", t);
                }
            });


            Call<AqiEntityV7> aqiEntityCall  = mNetWeatherApi.getAqi(NetWeatherApi.sHeyWeatherKey, cityId);
            aqiEntityCall.enqueue(new Callback<AqiEntityV7>() {
                @Override
                public void onResponse(Call<AqiEntityV7> call, Response<AqiEntityV7> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        String strJson = JsonHelper.toJson(response.body());
                        AqiEntityV7 aqiData = response.body();
                        AqiEntityV7.AqiV7Bean aqiBean = aqiData.getAqiV7();

                        if (aqiBean != null && "200".equals(aqiBean.getCode())) {
                            AqiEntityV7.AqiV7Bean.NowBean nowBean = aqiBean.getNow();
                            callbackAqiInfo(aqiData);
                        } else {
                            showError("获取空气质量数据失败: " + (aqiBean != null ? aqiBean.getCode() : "未知错误"));
                        }
                    } else {
                        showError("获取空气质量数据失败: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<AqiEntityV7> call, Throwable t) {
                    showError("网络请求失败: " + t.getMessage());
                    LogHelper.error(TAG, "获取空气质量数据失败", t);
                }
            });

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
