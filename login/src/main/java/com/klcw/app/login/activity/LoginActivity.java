package com.klcw.app.login.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.klcw.app.banner.Banner;
import com.klcw.app.banner.listener.OnBannerListener;
import com.klcw.app.lib.network.NetworkConfig;
import com.klcw.app.lib.network.NetworkHelper;
import com.klcw.app.lib.widget.BLToast;
import com.klcw.app.login.R;
import com.klcw.app.login.util.CustomFrescoImageLoader;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private SimpleDraweeView mSivPic;
    private Banner mBanner;

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
        mSivPic.setImageURI("http://seopic.699pic.com/photo/50055/5642.jpg_wh1200.jpg");

        List<String> urls = new ArrayList<>();
        urls.add("http://seopic.699pic.com/photo/50055/5642.jpg_wh1200.jpg");
        urls.add("http://seopic.699pic.com/photo/50055/5642.jpg_wh1200.jpg");
        urls.add("http://seopic.699pic.com/photo/50055/5642.jpg_wh1200.jpg");

        mBanner.setImages(urls)
                .setImageLoader(new CustomFrescoImageLoader())
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        Toast.makeText(LoginActivity.this, "---position---" + position, Toast.LENGTH_SHORT).show();
                    }
                }).start();
    }

    /**
     * 测试获取网络信息数据接口
     *
     * @param view
     */
    public void onCkeckNet(View view) {
        BLToast.showToast(this,"登陆");
        Intent  intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }


    /**
     * 同步请求资源位
     *
     * @param id 资源位id
     * @return 资源位实体
     */
    public void getResourceEntity(String id) {
        JsonObject otherResource = new JsonObject();
        JsonObject resource = new JsonObject();
        resource.addProperty("resourceId", id);
        otherResource.add("activity", new JsonArray());
        otherResource.addProperty("otherresource", new Gson().toJson(resource));
        String reqParams = new Gson().toJson(otherResource);
        String result = NetworkHelper.query(NetworkConfig.getAppMwUrl() + "app/site/queryAdDeploy.htm", reqParams, NetworkHelper.HTTP_POST);
        Log.e("xp", "请求数据列表-----" + result);
    }
}
