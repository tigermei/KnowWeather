package com.silencedut.weather_core.api.coreprovider;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.silencedut.weather_core.corebase.BaseActivity;

/**
 * Created by SilenceDut on 2018/1/24 .
 */

public interface ICoreProvider extends IProvider {
    void showShareDialog(BaseActivity baseActivity,boolean weather);
}
