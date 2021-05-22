package com.silencedut.weather_core.api.coreprovider;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.silencedut.baselib.commonhelper.log.LogHelper;
import com.silencedut.hub_annotation.HubInject;
import com.silencedut.weather_core.corebase.BaseActivity;
import com.silencedut.weather_core.corebase.customview.ShareDialog;

/**
 * Created by SilenceDut on 2018/1/24 .
 */

@Route(path = "/weathercore/service/coreprovider", name = "weather core provider")
public class CoreProviderImpl implements ICoreProvider {
    private static final String TAG = "CoreProviderImpl";
    @Override
    public void init(Context context) {

    }


    @Override
    public void showShareDialog(BaseActivity baseActivity,boolean weather) {
        if(baseActivity.isFinishing()) {
            return;
        }
        try {
            new ShareDialog(baseActivity).show(weather);
        }catch (Exception e) {
            LogHelper.info(TAG,"showShareDialog error %s",e);
        }

    }
}
