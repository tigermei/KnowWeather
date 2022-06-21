package com.silencedut.weather_core.api.cityprovider;

import android.os.Handler;
import androidx.annotation.WorkerThread;
import androidx.core.util.Pair;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.silencedut.weather_core.corebase.BaseFragment;

/**
 * Created by SilenceDut on 2018/1/17 .
 */

public interface ICityProvider extends IProvider {
    @WorkerThread
    City searchCity( String cityId);
    @WorkerThread
    City searchCity( String cityName,final String county);
    Handler getCityWorkHandler();
    void saveCurrentCityId(String cityId);
    String getCurrentCityId();
    boolean hadCurrentCityId();
    Pair<BaseFragment,Integer> provideCityFragment();
    void loadCitys();

}
