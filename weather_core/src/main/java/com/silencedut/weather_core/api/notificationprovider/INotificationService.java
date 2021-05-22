package com.silencedut.weather_core.api.notificationprovider;

import android.content.Context;

import com.alibaba.android.arouter.facade.template.IProvider;

public interface INotificationService extends IProvider {
    void startService(Context context, boolean allowPoll);
    void stopService(Context context);
}
