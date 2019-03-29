package com.klcw.app.member;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.IComponentCallback;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author kk
 * @datetime: 2018/10/24
 * @desc:程序入口
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 登录
     *
     * @param view
     */
    public void onLoginClick(View view) {
        CC.obtainBuilder("loginComponent")
                .setContext(this)
                .setActionName("LoginActivity")
                .addParam("param", "登陆/模块")
                .build()
                .callAsync(new IComponentCallback() {
                    @Override
                    public void onResult(CC cc, CCResult result) {
                        Log.e("xp", "登陆" + result.toString());
                    }
                });
    }

    /**
     * 个人中心
     *
     * @param view
     */
    public void onMineClick(View view) {
        CC.obtainBuilder("mineComponent")
                .setContext(this)
                .setActionName("MineActivity")
                .addParam("param", "个人中心/模块")
                .build()
                .callAsync(new IComponentCallback() {
                    @Override
                    public void onResult(CC cc, CCResult result) {
                        Log.e("xp", "个人中心" + result.toString());
                    }
                });
    }

    /**
     * 跳转推荐模块
     *
     * @param view
     */
    public void onRecommendClick(View view) {
        CC.obtainBuilder("recommendComponent")
                .setContext(this)
                .build()
                .callAsync(new IComponentCallback() {
                    @Override
                    public void onResult(CC cc, CCResult result) {
                        Log.e("xp", "推荐模块" + result.toString());
                    }
                });
    }

    /**
     * 打开WebView
     */
    public void onWebViewClick(View view) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("title", "商家客服");
            jsonObject.put("noTitle", "noTitle");
            jsonObject.put("url", "http://summertimes.top/2013/06/13/%E6%90%AD%E5%BB%BA:%E5%8D%9A%E5%AE%A2/");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CC.obtainBuilder("WebComponent")
                .setActionName("startWeb")
                .addParam("param", jsonObject)
                .setContext(this)
                .build()
                .callAsync(new IComponentCallback() {
                    @Override
                    public void onResult(CC cc, CCResult result) {
                        Log.e("xp", "---------WebView--------" + result.toString());
                    }
                });
    }

}
