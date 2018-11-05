package com.bailian.weblib.bljsbridge;

import android.util.ArrayMap;

/**
 * 作者：杨松
 * 日期：2018/1/9 14:50
 */

public interface IJSBridgeMediator {


    void onDispatch(String data);


    ArrayMap<String, INativeCallBack> produceFunctionContainer();

    void registerFunction(String key, INativeCallBack callFunction);
}
