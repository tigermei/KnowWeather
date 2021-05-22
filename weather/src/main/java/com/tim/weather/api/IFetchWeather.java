package com.tim.weather.api;

import com.alibaba.android.arouter.facade.template.IProvider;

import java.util.List;

/**
 * Created by SilenceDut on 2018/1/21 .
 */

public interface IFetchWeather extends IProvider {
    void queryWeather(String cityId);
    void queryWeather(List<String> citys);
}
