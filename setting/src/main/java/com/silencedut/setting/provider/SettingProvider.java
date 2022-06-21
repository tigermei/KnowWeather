package com.silencedut.setting.provider;

import android.content.Context;
import androidx.core.util.Pair;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.silencedut.setting.R;
import com.silencedut.setting.ui.SettingFragment;
import com.silencedut.weather_core.api.settingprovider.ISettingProvider;
import com.silencedut.weather_core.corebase.BaseFragment;

/**
 * Created by SilenceDut on 2018/1/22 .
 */
@Route(path = "/setting/service/settings", name = "weather settings service")
public class SettingProvider implements ISettingProvider {
    @Override
    public void init(Context context) {

    }

    @Override
    public Pair<BaseFragment, Integer> provideSettingFragment() {
        return new Pair<>(SettingFragment.newInstance(), R.drawable.setting_tab_drawable);
    }
}
