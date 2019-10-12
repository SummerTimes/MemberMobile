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
import com.kk.app.login.util.LoginUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kk
 * @datetime: 2018/10/24
 * @desc:
 */
public class LoginActivity extends AppCompatActivity {

    private List<String> mUrls;
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
        mSivPic.load("http://seopic.699pic.com/photo/50055/5642.jpg_wh1200.jpg");

        mUrls = new ArrayList<>();
        mUrls.add("http://seopic.699pic.com/photo/50055/5642.jpg_wh1200.jpg");
        mUrls.add("http://seopic.699pic.com/photo/50055/5642.jpg_wh1200.jpg");
        mUrls.add("http://seopic.699pic.com/photo/50055/5642.jpg_wh1200.jpg");
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
                Log.e("xp", "---onSuccess----" + commonList.toString());
                List<CommonBean> data = commonList.data;
                for (int i = 0; i < data.size(); i++) {
                    Log.e("xp", "---单条数据----" + data.get(i).toString());
                }
            }

            @Override
            public void onFailed(@NonNull CCResult result) {
                Log.e("xp", "---onFailed----" + result.getData());
            }

            @Override
            public void onFinally(@NonNull CCResult result) {
                Log.e("xp", "---onFinally----" + result.getData());
            }
        });
    }
}
