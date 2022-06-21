package com.silencedut.weather_core.corebase;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;

import com.alibaba.android.arouter.launcher.ARouter;
import com.silencedut.router.Router;
import com.silencedut.weather_core.CoreManager;
import com.silencedut.weather_core.permission.IPermissionApi;

import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by SilenceDut on 16/10/15.
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseViewInit {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Router.instance().register(this);
        initBeforeView();
        setContentView(getContentViewId());
        ButterKnife.bind(this);
        initViews();
        initDataObserver();
    }

    protected void initDataObserver() {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void initBeforeView() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Router.instance().unregister(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ARouter.getInstance().navigation(IPermissionApi.class).onRequestPermissionsResult(this,requestCode,permissions,grantResults);
    }
}
