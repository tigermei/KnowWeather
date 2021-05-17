package com.tim.weather.ui.adapter;

import android.view.View;
import android.widget.TextView;

import com.tim.weather.R;
import com.tim.weather.R2;
import com.tim.weather.callbacks.WeatherCallBack;

import com.silencedut.baselib.commonhelper.adapter.BaseRecyclerAdapter;
import com.silencedut.baselib.commonhelper.adapter.BaseViewHolder;
import com.silencedut.router.Router;
import butterknife.BindView;

/**
 * Created by SilenceDut on 16/10/17 .
 */

public class LifeGuideHolder extends BaseViewHolder<LifeIndexGuideData> implements WeatherCallBack.LifeAdvice {
    @BindView(R2.id.guide_title)
    TextView mGuideTitle;

    public LifeGuideHolder(View itemView, BaseRecyclerAdapter baseRecyclerAdapter) {
        super(itemView, baseRecyclerAdapter);
        Router.instance().register(this);
    }

    @Override
    public void updateItem(LifeIndexGuideData data, int position) {
        mGuideTitle.setText(data.guide);
    }


    @Override
    public void lifeAdvice(String index, String advice) {
        mGuideTitle.setText(index);
    }

    @Override
    public int getContentViewId() {
        return R.layout.weather_item_index_guide;
    }
}
