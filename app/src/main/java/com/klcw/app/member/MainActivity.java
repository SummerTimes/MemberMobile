package com.klcw.app.member;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.aliyun.common.global.AliyunTag;
import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.IComponentCallback;
import com.klcw.app.util.StringUtil;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    /**
     * 初始化View
     */
    private void initView() {
        Log.d("xp", "----打印数据--3--" + AliyunTag.TAG);
    }

    /**
     * 跳转登录模块
     *
     * @param view
     */
    public void onLoginClick(View view) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("title", "商家客服");
//            jsonObject.put("noTitle", "noTitle");
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
                        StringUtil.print("登录模块--------" + result.toString());
                    }
                });
    }

    /**
     * 跳转我的模块
     *
     * @param view
     */
    public void onMineClick(View view) {
        CC.obtainBuilder("mineComponent")
                .setContext(this)
                .build()
                .callAsync(new IComponentCallback() {
                    @Override
                    public void onResult(CC cc, CCResult result) {
                        StringUtil.print("我的模块" + result.toString());
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
                        StringUtil.print("推荐模块" + result.toString());
                    }
                });
    }
}
