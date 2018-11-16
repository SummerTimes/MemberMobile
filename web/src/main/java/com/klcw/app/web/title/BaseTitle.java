package com.klcw.app.web.title;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;

import com.bailian.weblib.bljsbridge.BridgeWebView;
import com.bailian.weblib.bljsbridge.IJSCallFunction;
import com.bailian.weblib.bljsbridge.INativeCallBack;

import java.lang.ref.WeakReference;
import java.util.Stack;


/**
 * 作者：杨松
 * 日期：2017/4/24 16:33
 */

public abstract class BaseTitle implements ITitle {


    String SET_TITLE = "title" + "#" + "title";

    private WeakReference<Activity> mActivity;


    private Stack<String> mTitleStack = new Stack<>();
    protected TitleBean mTitleBean;
    protected IJSCallFunction mCallFunction;
    protected String mMethodUrl;
    private View mView;


    public BaseTitle(Activity activity) {
        this.mActivity = new WeakReference<>(activity);
    }


    @Override
    public final View createTitle(Context context, ViewGroup parent) {
        if (mView == null) {
            mView = LayoutInflater.from(context).inflate(getLayout(), parent, false);
            initOnCreateTitle();
        }
        parent.removeAllViews();
        parent.addView(mView);
        return mView;
    }

    protected abstract void initOnCreateTitle();


    protected final TitleBean.WidgetsBean getButtonWidget(View view, String tag) {
        if (mTitleBean == null || mTitleBean.getWidgets() == null) {
            return null;
        }

        for (TitleBean.WidgetsBean widget : mTitleBean.getWidgets()) {
            if (TextUtils.equals(widget.getWidgetIndex(), tag)) {
                view.setTag(widget);
                return widget;
            }
        }
        return null;
    }

    protected abstract @LayoutRes
    int getLayout();


    public Activity getActivity() {
        if (mActivity == null) {
            return null;
        }
        return mActivity.get();
    }

    @CallSuper
    @Override
    public void registerFunction(BridgeWebView bridgeWebView) {
        bridgeWebView.registerFunction(SET_TITLE, new INativeCallBack() {
            @Override
            public void onCall(String method, String data, String url, IJSCallFunction ijsCallFunction) {
                Log.e("xp", "----registerFunction---data--" + data);
                Log.e("xp", "----registerFunction---method--" + method);
                Log.e("xp", "----registerFunction---url--" + url);
                if (!TextUtils.isEmpty(data)) {
                    data = data.substring(1, data.length() - 1);
                }
                mTitleStack.push(data);
                onSetTitle(data);
            }
        });
    }

    protected abstract void onSetTitle(String data);


    @Override
    public boolean onGoBack() {
        if (mTitleStack.isEmpty()) {
            return false;
        }
        mTitleStack.pop();
        if (mTitleStack.isEmpty()) {
            return false;
        }
        onSetTitle(mTitleStack.pop());
        return true;
    }

    public void goBack(BridgeWebView webview) {
        if (webview.canGoBack()) {
            if (!mTitleStack.isEmpty()) {//把当前的移除
                mTitleStack.pop();
            }
            if (!mTitleStack.isEmpty()) {
                onSetTitle(mTitleStack.pop());
            }
            // 返回上一页面
            webview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
            webview.goBack();
        } else {
            onBackPressed();
        }

    }


    protected void finish() {
        Activity activity = getActivity();
        if (activity != null) {
            activity.finish();
        }
    }

    protected View findView(int id) {
        return mView.findViewById(id);
    }

    protected void onBackPressed() {
        if (getActivity() != null) {
            getActivity().onBackPressed();
        }
    }

    @Override
    public void setTitle(String title) {
        onSetTitle(title);
    }

    @Override
    public void setInitData(TitleBean titleBean, IJSCallFunction callFunction, String url) {
        this.mTitleBean = titleBean;
        this.mCallFunction = callFunction;
        this.mMethodUrl = url;
    }
}
