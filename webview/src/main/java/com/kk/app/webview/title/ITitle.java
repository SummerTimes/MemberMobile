package com.kk.app.webview.title;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.kk.app.web.bljsbridge.BridgeWebView;
import com.kk.app.web.bljsbridge.IJSCallFunction;


/**
 * 作者：杨松
 * 日期：2017/4/24 16:31
 */

public interface ITitle {

    void registerFunction(BridgeWebView bridgeWebView);

    View createTitle(Context context, ViewGroup parent);

    boolean onGoBack();

    void setInitData(IJSCallFunction function, String url);


    void setTitle(String title);

}
