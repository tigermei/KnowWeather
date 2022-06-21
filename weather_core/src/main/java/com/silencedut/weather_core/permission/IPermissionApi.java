package com.silencedut.weather_core.permission;

import androidx.annotation.NonNull;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.silencedut.weather_core.corebase.BaseActivity;

/**
 * Created by SilenceDut on 2018/1/17 .
 */

public interface IPermissionApi extends IProvider {
    void initUrgentPermission(BaseActivity activity);
    void onRequestPermissionsResult(BaseActivity activity,int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults);
}
