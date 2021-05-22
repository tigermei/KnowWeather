package com.silencedut.knowweather;

import android.app.Application;
import com.alibaba.android.arouter.launcher.ARouter;

import com.silencedut.weather_core.CoreManager;;

/**
 * Created by SilenceDut on 16/10/15.
 */

public class WeatherApplication extends Application {

    private static Application sApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
        CoreManager.init(this);
        initRouter(sApplication);

    }


    @Override
    public void onTerminate() {
        super.onTerminate();

    }

    public static Application getContext() {
        return sApplication;
    }

    public static void initRouter(Application application) {
        if (BuildConfig.DEBUG) {
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(application);
    }

}
