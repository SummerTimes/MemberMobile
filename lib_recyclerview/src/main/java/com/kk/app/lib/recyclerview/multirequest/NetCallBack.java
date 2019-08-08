package com.kk.app.lib.recyclerview.multirequest;

import android.support.annotation.NonNull;

import com.billy.cc.core.component.CCResult;
import com.kk.app.lib.network.NetworkCallback;


/**
 * 作者：杨松
 * 日期：2018/6/4 10:06
 */

public abstract class NetCallBack<Response> extends NetworkCallback<Response> {

    @Override
    public final void onSuccess(@NonNull CCResult ccResult, Response response) {

    }

    public abstract void onSuccess(Response response);

    public abstract String name();

    @Override
    public void onFinally(@NonNull CCResult ccResult) {

    }
}
