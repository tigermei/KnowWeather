package com.silencedut.knowweather.notificationprovider;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.silencedut.knowweather.notification.PollingUtils;
import com.silencedut.weather_core.api.notificationprovider.INotificationService;
import com.silencedut.weather_core.api.weatherprovider.IWeatherProvider;

@Route(path = "/app/service/notificationservice", name = "weather notification service")
public class NotificationProviderImpl implements INotificationService {
    @Override
    public void init(Context context) {

    }

    @Override
    public void startService(Context context, boolean allowPoll) {
        PollingUtils.startService(context,allowPoll);
    }

    @Override
    public void stopService(Context context) {
        PollingUtils.stopPolling(context);
    }
}
