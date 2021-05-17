package com.tim.weather.ui.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tim.weather.R;
import com.tim.weather.R2;

import com.silencedut.baselib.commonhelper.adapter.BaseRecyclerAdapter;
import com.silencedut.baselib.commonhelper.adapter.BaseViewHolder;

import butterknife.BindView;

/**
 * Created by SilenceDut on 16/10/22.
 */

public class GuideHolder extends BaseViewHolder<GuideData> {
    @BindView(R2.id.guide_title)
    TextView mGuideTitle;
    @BindView(R2.id.guide_icon)
    ImageView mGuideIcon;

    public GuideHolder(View itemView, BaseRecyclerAdapter baseRecyclerAdapter) {
        super(itemView, baseRecyclerAdapter);
    }

    @Override
    public void updateItem(GuideData data, int position) {
        mGuideTitle.setText(data.guide);
        if (data.guideIconId != 0) {
            mGuideIcon.setImageResource(data.guideIconId);
        }
    }

    @Override
    public int getContentViewId() {
        return R.layout.weather_item_guide;
    }

}
