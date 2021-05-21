package com.silencedut.knowweather.ui;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.arch.lifecycle.Observer;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.silencedut.baselib.commonhelper.adapter.BaseRecyclerAdapter;
import com.silencedut.baselib.commonhelper.utils.TimeUtil;
import com.silencedut.baselib.commonhelper.utils.UIUtil;
import com.silencedut.knowweather.R;
import com.silencedut.knowweather.ui.adapter.HourlyWeatherHolder;
import com.silencedut.knowweather.ui.adapter.HourlyWeatherHolder;
import com.silencedut.knowweather.ui.adapter.MainPageAdapter;
import com.silencedut.taskscheduler.TaskScheduler;
import com.silencedut.weather_core.CoreManager;
import com.silencedut.weather_core.api.IActivityRouter;
import com.silencedut.weather_core.api.cityprovider.ICityProvider;
import com.silencedut.weather_core.api.coreprovider.ICoreProvider;
import com.silencedut.weather_core.api.settingprovider.ISettingProvider;
import com.silencedut.weather_core.api.weatherprovider.IWeatherProvider;
import com.silencedut.weather_core.api.weatherprovider.WeatherData;
import com.silencedut.weather_core.corebase.BaseActivity;
import com.silencedut.weather_core.corebase.BaseFragment;
import com.silencedut.weather_core.corebase.ResourceProvider;
import com.silencedut.weather_core.corebase.StatusDataResource;
import com.silencedut.weather_core.location.ILocationApi;
import com.silencedut.weather_core.permission.IPermissionApi;

//
//TODO
import com.tim.weather.ui.adapter.HoursForecastData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.animation.ObjectAnimator.ofFloat;

/**
 * Created by SilenceDut on 16/10/15.
 */

public class MainActivity extends BaseActivity implements AppBarLayout.OnOffsetChangedListener {

    private static final int ROTATION_DURATION = 1000;
    private static final int POSTTIME_DURATION = 500;
    private static final float DEFAULT_PERCENTAGE = 0.8f;

    private float percentageOfShowTitle = DEFAULT_PERCENTAGE;
    private float mWeatherInfoContainerLeft;
    private BaseRecyclerAdapter mHourlyWeatherAdapter;
    private String mTemperature;
    private String mWeatherStatus;
    protected float mTitlePercentage;
    private ObjectAnimator mActionRotate;
    private Drawable mDrawableLocation;
    private ValueAnimator mSucceedAnimator;

    @BindView(R.id.main_layout)
    CoordinatorLayout mMainLayout;
    @BindView(R.id.title_icon)
    ImageView mTitleIcon;
    @BindView(R.id.title_temp)
    TextView mTitleTemp;
    @BindView(R.id.float_action)
    FloatingActionButton mFloatAction;
    @BindView(R.id.refresh_status)
    ImageView mRefreshStatus;
    @BindView(R.id.app_bar_layout)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.main_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.main_temp)
    TextView mMainTemp;
    @BindView(R.id.container_layout)
    View mTitleContainer;
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.main_hours_weather_recyclerView)
    RecyclerView mHourlyWeatherRecyclerView;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.main_bg)
    View mMainBgIv;
    @BindView(R.id.main_info)
    TextView mMainInfoTv;
    @BindView(R.id.main_location)
    TextView mLocationTv;
    @BindView(R.id.main_post_time)
    TextView mPostTimeTv;

    private long mStartRefresh;
    private String mUpdateTime ="";

    private static final int MIN_REFRESH_MILLS = 2000;


    @Override
    public void initBeforeView() {
        super.initBeforeView();
        CoreManager.getImpl(IPermissionApi.class).initUrgentPermission(this);
    }

    @Override
    public int getContentViewId() {
        return R.layout.weather_activity_main;
    }

    @Override
    public void initViews() {

        if (TimeUtil.isNight()) {
            mMainBgIv.setBackgroundResource(R.mipmap.weather_bg_night);
        }else {
            mMainBgIv.setBackgroundResource(R.mipmap.weather_bg_day);
        }

        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(null);
        getSupportActionBar().setTitle("");
        mAppBarLayout.addOnOffsetChangedListener(this);
        setupViewPager();
        setupHoursForecast();
        mTitleContainer.post(new Runnable() {
            @Override
            public void run() {
                mWeatherInfoContainerLeft = mTitleContainer.getX();
                percentageOfShowTitle = (mTitleContainer.getBottom()) * 1.0f / mAppBarLayout.getTotalScrollRange();
                if (percentageOfShowTitle == 0) {
                    mWeatherInfoContainerLeft = DEFAULT_PERCENTAGE;
                }
            }
        });

        mActionRotate = ObjectAnimator.ofFloat(mRefreshStatus, "rotation", 0, 360);
        mActionRotate.setDuration(ROTATION_DURATION);
        mActionRotate.setRepeatCount(-1);

        mDrawableLocation = UIUtil.getDrawable(this, R.mipmap.core_location);
        mDrawableLocation.setBounds(0, 0, UIUtil.dipToPx(this, R.dimen.common_location_size), UIUtil.dipToPx(this, R.dimen.common_location_size));

        mSucceedAnimator = ofFloat(mPostTimeTv, "scaleX", 1, 0, 1).setDuration(POSTTIME_DURATION);
        mSucceedAnimator.setStartDelay(ROTATION_DURATION);

    }

    @Override
    protected void initDataObserver() {
        CoreManager.getImpl(IWeatherProvider.class).getWeatherData().observe(this, new Observer<StatusDataResource<WeatherData>>() {
            @Override
            public void onChanged(@Nullable final StatusDataResource<WeatherData> status) {
                if(StatusDataResource.Status.LOADING.equals(status.status)) {
                    startRefresh();

                    updateBaseWeatherInfo(status.data.getBasic());
                    List<HoursForecastData> hourlyWeatherDatas = new ArrayList<>();
                    for (WeatherData.HoursForecastEntity hoursForecastEntity : status.data.getHoursForecast()) {
                        hourlyWeatherDatas.add(new HoursForecastData(hoursForecastEntity));
                    }
                    mHourlyWeatherAdapter.setData(hourlyWeatherDatas);
                }else {
                    if(SystemClock.currentThreadTimeMillis() - mStartRefresh > MIN_REFRESH_MILLS) {
                        onWeatherUpdate(StatusDataResource.Status.SUCCESS.equals(status.status));
                    }else {
                        TaskScheduler.runOnUIThread(new Runnable() {
                            @Override
                            public void run() {
                                onWeatherUpdate(StatusDataResource.Status.SUCCESS.equals(status.status));
                            }
                        },MIN_REFRESH_MILLS+SystemClock.currentThreadTimeMillis() - mStartRefresh);
                    }
                }
            }

        });

        initialWeatherData();

    }

    private void initialWeatherData(){
        boolean bHadCurrentCityId = CoreManager.getImpl(ICityProvider.class).hadCurrentCityId();
        if(!bHadCurrentCityId){
            //
            //如果当前没有默认城市，默认城市给到"深圳"
            CoreManager.getImpl(ICityProvider.class).saveCurrentCityId("CN101280601");
        }

        CoreManager.getImpl(IWeatherProvider.class).updateWeather();
    }

    private void setupViewPager() {
        MainPageAdapter adapter = new MainPageAdapter(this, getSupportFragmentManager());

        Pair<BaseFragment, Integer> cityFragmentPair = CoreManager.getImpl(ICityProvider.class).provideCityFragment();
        adapter.addFragment(cityFragmentPair);

        //
        //这里依然可以按照provider的方式来提供UI的供给
        Pair<BaseFragment, Integer> weatherFregment = CoreManager.getImpl(IWeatherProvider.class).provideWeatherFragment();
        adapter.addFragment(weatherFregment);

        Pair<BaseFragment,Integer> settingFragment = CoreManager.getImpl(ISettingProvider.class).provideSettingFragment();
        adapter.addFragment(settingFragment);

        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);

        for(int index =0 ;index < adapter.getCount();index++) {
            mTabLayout.getTabAt(index).setCustomView(adapter.getTabView(index, mTabLayout));
        }

        mViewPager.setOffscreenPageLimit(adapter.getCount());
        mViewPager.setCurrentItem(0);

    }

    void setupHoursForecast() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mHourlyWeatherRecyclerView.setLayoutManager(linearLayoutManager);
        mHourlyWeatherAdapter = new BaseRecyclerAdapter(this);
        mHourlyWeatherAdapter.registerHolder(HourlyWeatherHolder.class, R.layout.weather_item_day);
        mHourlyWeatherRecyclerView.setAdapter(mHourlyWeatherAdapter);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        mTitlePercentage = (float) Math.abs(verticalOffset) / (float) maxScroll;
        handleInfoAnimate(mTitlePercentage);

    }

    private void handleInfoAnimate(float percentage) {
        mToolbar.getBackground().mutate().setAlpha((int) (255 * percentage));
        mTitleContainer.setAlpha(1 - percentage);
        mTitleContainer.setScaleX(1 - percentage);
        mTitleContainer.setScaleY(1 - percentage);
        mHourlyWeatherRecyclerView.setAlpha(1 - percentage);

        if (mWeatherInfoContainerLeft > 0) {
            mTitleContainer.setX(mWeatherInfoContainerLeft * (1 - percentage));
        }

        if (!(percentage < percentageOfShowTitle)) {
            mTitleIcon.setImageResource(ResourceProvider.getIconId(mWeatherStatus));
            mTitleTemp.setText(mTemperature);
            if (mFloatAction.isShown()) {
                mFloatAction.hide();
            }
        } else {
            if (!mFloatAction.isShown() && !mActionRotate.isRunning()) {
                mFloatAction.show();
            }
            mTitleIcon.setImageDrawable(null);
            mTitleTemp.setText("");
        }
    }


    @OnClick(R.id.float_action)
    public void onClick() {
        CoreManager.getImpl(IWeatherProvider.class).updateWeather();
    }

    private void onWeatherUpdate(boolean succeed) {

        if (succeed) {
            updateBaseWeatherInfo(
                    CoreManager.getImpl(IWeatherProvider.class).getWeatherData().getValue().data.getBasic()
            );

            List<HoursForecastData> hourlyWeatherDatas = new ArrayList<>();
            for (WeatherData.HoursForecastEntity hoursForecastEntity : CoreManager.getImpl(IWeatherProvider.class).getWeatherData().getValue().data.getHoursForecast()) {
                hourlyWeatherDatas.add(new HoursForecastData(hoursForecastEntity));
            }
            mHourlyWeatherAdapter.setData(hourlyWeatherDatas);
            updateSucceed(mUpdateTime);
        } else {
            mPostTimeTv.setText(R.string.weather_refresh_fail);
        }
        stopRefreshing();

    }

    private boolean locationIsCurrent() {
        return CoreManager.getImpl(ICityProvider.class).getCurrentCityId()
                .equals(CoreManager.getImpl(ILocationApi.class).getLocatedCityId());
    }

    private void updateBaseWeatherInfo(WeatherData.BasicEntity basicData) {
        if(basicData == null) {
            return;
        }

        mLocationTv.setCompoundDrawables(locationIsCurrent()? mDrawableLocation : null, null, null, null);
        mLocationTv.setText(basicData.getCity());
        mUpdateTime = String.format(getString(R.string.weather_post), TimeUtil.getTimeTips(basicData.getTime()));

        mTemperature = basicData.getTemp();
        mWeatherStatus = basicData.getWeather();

        mMainTemp.setText(mTemperature);
        mMainInfoTv.setText(mWeatherStatus);

        if (TimeUtil.isNight()) {
            if (ResourceProvider.sunny(mWeatherStatus)) {
                mMainBgIv.setBackgroundResource(R.mipmap.weather_bg_night);
            } else {
                mMainBgIv.setBackgroundResource(R.mipmap.weather_bg_night_dark);
            }
        } else {
            mMainBgIv.setBackgroundResource(R.mipmap.weather_bg_day);
        }

    }


    private void updateSucceed(final String postTime) {

        mPostTimeTv.setText(R.string.weather_refresh_succeed);

        mSucceedAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                if (fraction >= 0.5f) {
                    mPostTimeTv.setText(postTime);
                }
            }
        });
        mSucceedAnimator.start();
    }

    private void startRefresh() {
        mPostTimeTv.setText(R.string.weather_refreshing);
        mRefreshStatus.setVisibility(View.VISIBLE);
        mActionRotate.start();
        mFloatAction.hide();
        mStartRefresh = SystemClock.currentThreadTimeMillis();
    }

    private void stopRefreshing() {
        mActionRotate.end();
        mRefreshStatus.setVisibility(View.GONE);
        mFloatAction.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.weather_menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.share_weather:
                CoreManager.getImpl(ICoreProvider.class).showShareDialog(this,true);
                break;
            case R.id.share_app:
                CoreManager.getImpl(ICoreProvider.class).showShareDialog(this,false);
                break;
            case R.id.about:
                CoreManager.getActivityRouter(IActivityRouter.class).toAboutActivity();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSucceedAnimator.removeAllListeners();
        mActionRotate.removeAllListeners();
        mSucceedAnimator.removeAllUpdateListeners();
        mActionRotate.removeAllUpdateListeners();
    }

}
