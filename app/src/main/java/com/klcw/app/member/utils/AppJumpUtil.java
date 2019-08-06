package com.klcw.app.member.utils;

import android.content.Context;
import android.util.Log;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.IComponentCallback;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author kk
 * @datetime 2019-08-06
 * @desc
 */
public class AppJumpUtil {

    /**
     * 登录
     *
     * @param context
     */
    public static void onStartLogin(Context context) {
        CC.obtainBuilder("loginComponent")
                .setContext(context)
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
     * @param context
     */
    public static void onStartMine(Context context) {
        CC.obtainBuilder("mineComponent")
                .setContext(context)
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
     * @param context
     */
    public static void onStartRecommend(Context context) {
        CC.obtainBuilder("recommendComponent")
                .setActionName("recommendActivity")
                .setContext(context)
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
    public static void onStartWebView(Context context) {
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
                .setContext(context)
                .build()
                .callAsync(new IComponentCallback() {
                    @Override
                    public void onResult(CC cc, CCResult result) {
                        Log.e("xp", "---------WebView--------" + result.toString());
                    }
                });
    }
}
