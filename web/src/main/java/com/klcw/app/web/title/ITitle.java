package com.klcw.app.web.title;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;


import com.bailian.weblib.bljsbridge.BridgeWebView;
import com.bailian.weblib.bljsbridge.IJSCallFunction;


/**
 * 作者：杨松
 * 日期：2017/4/24 16:31
 */

public interface ITitle {

    void registerFunction(BridgeWebView bridgeWebView);

    View createTitle(Context context, ViewGroup parent);

    boolean onGoBack();

    void setInitData(TitleBean titleBean, IJSCallFunction function, String url);


    void setTitle(String title);


}
