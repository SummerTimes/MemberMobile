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
import com.kk.app.lib.widget.utils.LxStatusBarUtil;
import com.kk.app.login.R;
import com.kk.app.login.bean.CommonBean;
import com.kk.app.login.bean.CommonList;
import com.kk.app.login.util.LoginUtils;

import org.json.JSONException;
import org.json.JSONObject;

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
        LxStatusBarUtil.setLightMode(this);
    }


    /**
     * 测试获取网络信息数据接口
     *
     * @param view
     */
    public void onCheckNet(View view) {
        String Url = "https://www.wanandroid.com/user/login";
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("username", "lxtime");
            jsonObject.put("password", "123456789");
            NetworkHelper.queryApi(Url, jsonObject.toString(), NetworkHelper.HTTP_POST, new NetworkCallback<String>() {
                @Override
                public void onSuccess(@NonNull CCResult rawResult, String str) {
                    Log.e("xp", "---onSuccess----" + str);
                }

                @Override
                public void onFailed(@NonNull CCResult result) {
                    Log.e("xp", "---onFailed----" + result.getData());
                }

                @Override
                public void onFinally(@NonNull CCResult result) {

                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}