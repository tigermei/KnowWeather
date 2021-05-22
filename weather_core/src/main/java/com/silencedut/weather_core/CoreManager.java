package com.silencedut.weather_core;

import android.app.Application;

import com.silencedut.weather_core.appconfig.AppConfig;

/**
 * Created by SilenceDut on 2018/1/1 .
 */

public class CoreManager {
    private static Application sContext;

    public static void init(Application context) {
        sContext = context;
        AppConfig.initialize(context);

    }

    public static Application getContext() {
        return sContext;
    }

    public static long getUid() {
        return 0;
    }

}
