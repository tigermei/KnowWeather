package com.silencedut.knowweather.ui.adapter;

import com.silencedut.baselib.commonhelper.adapter.BaseAdapterData;
import com.silencedut.weather_core.api.weatherprovider.WeatherData;
import com.silencedut.knowweather.R;

/**
 * Created by SilenceDut on 16/10/29.
 */

public class HourlyWeatherData implements BaseAdapterData {

    public WeatherData.HoursForecastEntity hoursForecastData;

    public HourlyWeatherData(WeatherData.HoursForecastEntity hoursForecastData) {
        this.hoursForecastData = hoursForecastData;
    }

    @Override
    public int getContentViewId() {
        return R.layout.weather_item_day;
    }
}
