package com.kk.app.mobile.web;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.kk.app.web.bljsbridge.AbstractFunction;
import com.kk.app.web.bljsbridge.BridgeConfig;
import com.kk.app.web.bljsbridge.BridgeWebView;
import com.kk.app.web.bljsbridge.IJSCallFunction;
import com.kk.app.web.bljsbridge.INativeCallBack;
import com.kk.app.web.bljsbridge.JSEntity;

import org.json.JSONObject;

/**
 * @author kk
 * @datetime 2019-08-06
 * @desc
 */
public class NavigationFunction extends AbstractFunction {

    private static final String NAVIGATION_TRANS = "Demo" + "#" + "push";

    @Override
    public void registerFunction(BridgeWebView webView, Context context) {
        super.registerFunction(webView, context);
        openSelfStoreMapPage(webView, (Activity) context);
    }

    /**
     * @param webView
     * @param context
     */
    private void openSelfStoreMapPage(BridgeWebView webView, final Activity context) {
        webView.registerFunction(NAVIGATION_TRANS, new INativeCallBack() {
            @Override
            public void onCall(String method, String data, String url, IJSCallFunction ijsCallFunction) {
                String info = "玩我呢？";
                try {
                    JSEntity entity = new JSEntity();
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("key", info);
                    Log.e("xp", "-----jsonObject.toString()-------" + jsonObject.toString());
                    //  entity.data = TextUtils.isEmpty(info) ? "{}" : info;
                    entity.data = jsonObject.toString();
                    entity.status = BridgeConfig.SUCCESS;
                    ijsCallFunction.onCall(entity, url);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
