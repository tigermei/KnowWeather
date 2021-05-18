package com.silencedut.weather_core.api.notificationprovider;

import android.content.Context;
import com.silencedut.weather_core.api.ICoreApi;

public interface INotificationService extends ICoreApi {
    void startService(Context context, boolean allowPoll);
    void stopService(Context context);
}
