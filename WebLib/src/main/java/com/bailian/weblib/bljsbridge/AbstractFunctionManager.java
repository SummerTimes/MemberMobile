package com.bailian.weblib.bljsbridge;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

import bl.web.function.register.IFunction;

/**
 * 作者：杨松
 * 日期：2017/9/30 14:55
 * 用来自动注册
 */

public abstract class AbstractFunctionManager implements IFunction{

   protected List<IFunction> mFunctions = new ArrayList<>();


    @Override
    public final void onDestroy(Activity activity) {
        onMyDestroy(activity);
        mFunctions.clear();
    }

    protected void onMyDestroy(Activity activity) {


    }
}
