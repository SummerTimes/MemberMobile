package com.bailian.weblib.bljsbridge;

/**
 * Created by kk on 2017/3/9.
 */

public interface INativeCallBack {


    void onCall(String method, String data, String url, IJSCallFunction ijsCallFunction);

}
