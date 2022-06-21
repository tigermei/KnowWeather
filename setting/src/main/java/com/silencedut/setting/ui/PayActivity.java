package com.silencedut.setting.ui;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.widget.Toolbar;
import android.widget.ImageView;
import android.widget.Toast;

import com.silencedut.setting.R;
import com.silencedut.setting.R2;
import com.silencedut.weather_core.corebase.BaseActivity;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import butterknife.BindView;
import butterknife.OnLongClick;

/**
 * Created by SilenceDut on 2016/12/2 .
 */

public class PayActivity extends BaseActivity {
    @BindView(R2.id.title)
    Toolbar mTitle;
    @BindView(R2.id.pay_qrcode)
    ImageView mPayQrcode;

    public static void navigationActivity(Context from) {
        Intent intent = new Intent(from, PayActivity.class);
        from.startActivity(intent);
    }

    @Override
    public int getContentViewId() {
        return R.layout.setting_activity_pay;
    }

    @Override
    public void initViews() {
        setSupportActionBar(mTitle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.thanks);
    }

    @OnLongClick(R2.id.pay_qrcode)
    boolean onShareToWeChat() {
        ShareAction shareAction = new ShareAction(this).setPlatform(SHARE_MEDIA.WEIXIN).withTitle(getString(R.string.thanks));

        final UMImage umImage = new UMImage(this, R.mipmap.setting_wechat_pay);
        shareAction.withMedia(umImage);


        shareAction.setCallback(new UMShareListener() {
            @Override
            public void onResult(SHARE_MEDIA platform) {
                Toast.makeText(PayActivity.this, R.string.core_share_success, Toast.LENGTH_SHORT).show();
                umImage.asBitmap().recycle();
            }

            @Override
            public void onError(SHARE_MEDIA platform, Throwable t) {
                if (t != null) {
                    Toast.makeText(PayActivity.this, R.string.core_share_fail, Toast.LENGTH_LONG).show();
                }
                umImage.asBitmap().recycle();

            }

            @Override
            public void onCancel(SHARE_MEDIA platform) {
                umImage.asBitmap().recycle();
            }
        });
        shareAction.share();
        return true;
    }

}
