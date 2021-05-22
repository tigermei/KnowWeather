package com.silencedut.weather_core.location;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.silencedut.weather_core.api.cityprovider.City;

/**
 * Created by SilenceDut on 2018/1/8 .
 */

public interface ILocationApi extends IProvider {


    void startLocation();
    String getLocatedCityId();
    City getLocatedCity();


}
