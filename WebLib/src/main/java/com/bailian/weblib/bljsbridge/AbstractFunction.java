package com.bailian.weblib.bljsbridge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;


import java.lang.ref.WeakReference;

import bl.web.function.register.IFunction;


/**
 * 作者：杨松
 * 日期：2017/9/5 11:55
 */

public abstract class AbstractFunction implements IFunction {


    private @Nullable
    WeakReference<Activity> mWeakReferenceActivity;

    @Override
    public void registerFunction(BridgeWebView webView, Context context) {

    }

    /**
     * 不要再复写这个方法
     *
     * @param activity
     */
    @Deprecated
    @Override
    public void onActivityAttach(Activity activity) {
        if (mWeakReferenceActivity == null || mWeakReferenceActivity.get() == null) {
            this.mWeakReferenceActivity = new WeakReference<>(activity);
        }
    }

    @Override
    public void onResume(Activity activity) {
        if (mWeakReferenceActivity == null || mWeakReferenceActivity.get() == null) {
            this.mWeakReferenceActivity = new WeakReference<>(activity);
        }
    }

    @Override
    public void onDestroy(Activity activity) {

    }

    @Override
    public void onReceive(String msg) {

    }

    @Override
    public void onPause(Activity activity) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }


    protected @Nullable
    Activity getActivity() {
        if (mWeakReferenceActivity == null) {
            return null;
        }
        return mWeakReferenceActivity.get();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

    }
}
