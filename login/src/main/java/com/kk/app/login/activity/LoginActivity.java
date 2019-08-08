package com.kk.app.login.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.billy.cc.core.component.CCResult;
import com.kk.app.banner.Banner;
import com.kk.app.banner.listener.OnBannerListener;
import com.kk.app.image.GlideImageView;
import com.kk.app.lib.network.NetworkCallback;
import com.kk.app.lib.network.NetworkHelper;
import com.kk.app.login.R;
import com.kk.app.login.bean.CommonBean;
import com.kk.app.login.bean.CommonList;
import com.kk.app.login.util.LoginImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kk
 * @datetime: 2018/10/24
 * @desc:
 */
public class LoginActivity extends AppCompatActivity {

    private Banner mBanner;
    private GlideImageView mSivPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    /**
     * 初始化View
     */
    private void initView() {
        mSivPic = findViewById(R.id.siv_pic);
        mBanner = findViewById(R.id.banner);
        mSivPic.load("http://seopic.699pic.com/photo/50055/5642.jpg_wh1200.jpg");

        List<String> urls = new ArrayList<>();
        urls.add("http://seopic.699pic.com/photo/50055/5642.jpg_wh1200.jpg");
        urls.add("http://seopic.699pic.com/photo/50055/5642.jpg_wh1200.jpg");
        urls.add("http://seopic.699pic.com/photo/50055/5642.jpg_wh1200.jpg");
        mBanner.setImages(urls)
                .setImageLoader(new LoginImageLoader())
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {


                    }
                }).start();
    }

    /**
     * 测试获取网络信息数据接口
     *
     * @param view
     */
    public void onCheckNet(View view) {
        String Url = "https://wanandroid.com/wxarticle/chapters/json";
        NetworkHelper.queryApi(Url, null, NetworkHelper.HTTP_GET, new NetworkCallback<CommonList>() {
            @Override
            public void onSuccess(@NonNull CCResult rawResult, CommonList commonList) {
                List<CommonBean> data = commonList.data;
                for (int i = 0; i < data.size(); i++) {
                    Log.e("xp", "---单条数据----" + data.get(i).toString());
                }
            }

            @Override
            public void onFailed(@NonNull CCResult result) {
            }

            @Override
            public void onFinally(@NonNull CCResult result) {

            }
        });
    }
}
