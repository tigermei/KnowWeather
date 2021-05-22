package com.silencedut.city.provider;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.WorkerThread;
import android.support.v4.util.Pair;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.silencedut.city.R;
import com.silencedut.city.repository.ICityRepositoryApi;
import com.silencedut.city.ui.CityFragment;
import com.silencedut.weather_core.api.cityprovider.City;
import com.silencedut.weather_core.api.cityprovider.ICityProvider;
import com.silencedut.weather_core.corebase.BaseFragment;

/**
 * Created by SilenceDut on 2018/1/17 .
 */

@Route(path = "/city/service/citydata", name = "weather citydata provider")
public class CityProviderImpl implements ICityProvider {

    @Override
    public void init(Context context) {

    }


    @Override
    public City searchCity(String cityId) {
        return ARouter.getInstance().navigation(ICityRepositoryApi.class).searchCity(cityId);
    }

    @WorkerThread
    @Override
    public City searchCity(String cityName, String county) {
        return ARouter.getInstance().navigation(ICityRepositoryApi.class).searchCity(cityName,county);
    }

    @WorkerThread
    @Override
    public Handler getCityWorkHandler() {
        return ARouter.getInstance().navigation(ICityRepositoryApi.class).getCityWorkHandler();
    }


    @Override
    public void saveCurrentCityId(String cityId) {
        ARouter.getInstance().navigation(ICityRepositoryApi.class).saveCurrentCityId(cityId);
    }

    @Override
    public String getCurrentCityId() {
        return ARouter.getInstance().navigation(ICityRepositoryApi.class).getCurrentCityId();
    }

    @Override
    public boolean hadCurrentCityId() {
        return ARouter.getInstance().navigation(ICityRepositoryApi.class).hadCurrentCityId();
    }


    @Override
    public void loadCitys() {
        ARouter.getInstance().navigation(ICityRepositoryApi.class).loadCitys();
    }

    @Override
    public Pair<BaseFragment,Integer> provideCityFragment() {
        return new Pair<>(CityFragment.newInstance(), R.drawable.city_tab_drawable);
    }


}
