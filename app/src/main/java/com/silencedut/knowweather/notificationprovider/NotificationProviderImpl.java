package com.silencedut.knowweather.notificationprovider;

import android.content.Context;

import com.silencedut.hub_annotation.HubInject;
import com.silencedut.knowweather.notification.PollingUtils;
import com.silencedut.weather_core.api.notificationprovider.INotificationService;
import com.silencedut.weather_core.api.weatherprovider.IWeatherProvider;

@HubInject(api = INotificationService.class)
public class NotificationProviderImpl implements INotificationService {
    @Override
    public void startService(Context context, boolean allowPoll) {
        PollingUtils.startService(context,allowPoll);
    }

    @Override
    public void stopService(Context context) {
        PollingUtils.stopPolling(context);
    }

    @Override
    public void onCreate() {

    }
}
