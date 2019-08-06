package com.klcw.app.member;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.IComponentCallback;
import com.klcw.app.image.GlideImageView;
import com.klcw.app.member.utils.AppJumpUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author kk
 * @datetime: 2018/10/24
 * @desc:程序入口
 */
public class MainActivity extends AppCompatActivity {

    private GlideImageView mSivPic;
    String url = "http://img1.imgtn.bdimg.com/it/u=4027212837,1228313366&fm=23&gp=0.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mSivPic = findViewById(R.id.siv_pic);
        mSivPic.load(url);
    }

    /**
     * 登录
     *
     * @param view
     */
    public void onLoginClick(View view) {
        AppJumpUtil.onStartLogin(this);
    }

    /**
     * 个人中心
     *
     * @param view
     */
    public void onMineClick(View view) {
        AppJumpUtil.onStartMine(this);
    }

    /**
     * 跳转推荐模块
     *
     * @param view
     */
    public void onRecommendClick(View view) {
        AppJumpUtil.onStartRecommend(this);
    }

    /**
     * 打开WebView
     */
    public void onWebViewClick(View view) {
        AppJumpUtil.onStartWebView(this);
    }

}
