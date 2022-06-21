package com.silencedut.weather_core.api.settingprovider;

import androidx.core.util.Pair;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.silencedut.weather_core.corebase.BaseFragment;

/**
 * Created by SilenceDut on 2018/1/22 .
 */

public interface ISettingProvider extends IProvider {
    Pair<BaseFragment,Integer> provideSettingFragment();
}
