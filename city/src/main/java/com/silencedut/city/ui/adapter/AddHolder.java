package com.silencedut.city.ui.adapter;

import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.silencedut.baselib.commonhelper.adapter.BaseRecyclerAdapter;
import com.silencedut.baselib.commonhelper.adapter.BaseViewHolder;
import com.silencedut.city.R;
import com.silencedut.city.R2;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by SilenceDut on 16/10/24 .
 */

public class AddHolder extends BaseViewHolder<AddData> {

    @BindView(R2.id.image)
    ImageView mImage;


    public AddHolder(View itemView, BaseRecyclerAdapter baseRecyclerAdapter) {
        super(itemView, baseRecyclerAdapter);
    }

    @Override
    public void updateItem(AddData data, int position) {

    }

    @Override
    public int getContentViewId() {
        return R.layout.city_item_add_city;
    }

    @OnClick(R2.id.image)
    public void onClick() {
        ARouter.getInstance().build("/city/activity/search").navigation();
    }
}
