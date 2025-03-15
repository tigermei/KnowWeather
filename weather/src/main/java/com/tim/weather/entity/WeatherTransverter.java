package com.tim.weather.entity;

import com.alibaba.android.arouter.launcher.ARouter;
import com.silencedut.baselib.commonhelper.log.LogHelper;
import com.silencedut.weather_core.api.cityprovider.City;
import com.silencedut.weather_core.api.cityprovider.ICityProvider;
import com.silencedut.weather_core.entity.AqiEntityV7;
import com.silencedut.weather_core.entity.HeWeatherV7;
import com.silencedut.weather_core.api.weatherprovider.WeatherData;

/**
 * Created by SilenceDut on 2018/1/9 .
 */

public class WeatherTransverter {
    private static final String TAG = "WeatherTransverter";

    public static WeatherData convertFromHeWeather(City city, HeWeatherV7 heWeather, AqiEntityV7 heWeatherAqi) {
        WeatherData weatherData = new WeatherData();
        try {
            HeWeatherV7.HeWeather7Bean.NowBean nowBean = heWeather.getHeWeather7().getNow();
            weatherData.setCityId(city.cityId);

            WeatherData.BasicEntity basicEntity = new WeatherData.BasicEntity();
            weatherData.setBasic(basicEntity);
            basicEntity.setCity(city.cityName);
            basicEntity.setTemp(nowBean.getTemp());
            basicEntity.setWeather(nowBean.getText());
            basicEntity.setTime(nowBean.getObsTime());
            basicEntity.setImg(nowBean.getIcon());
            basicEntity.setProvince(city.province);

            if(heWeatherAqi != null && heWeatherAqi.getAqiV7()!=null  ) {
                AqiEntityV7.AqiV7Bean.NowBean airNowCityBean = heWeatherAqi.getAqiV7().getNow();
                WeatherData.AqiEntity aqiEntity = new WeatherData.AqiEntity();
                aqiEntity.setAqi(airNowCityBean.getAqi());
                aqiEntity.setPm25(airNowCityBean.getPm2p5());
                aqiEntity.setPm10(airNowCityBean.getPm10());
                aqiEntity.setCategory(airNowCityBean.getCategory());
                weatherData.setAqi(aqiEntity);
            }


        }catch (Exception e) {
            LogHelper.error(TAG,"convertFromHeWeather error %s",e);
        }

        return weatherData;
    }



}
