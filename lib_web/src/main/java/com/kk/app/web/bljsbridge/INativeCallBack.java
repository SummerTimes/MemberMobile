package com.kk.app.web.bljsbridge;

/**
 * @author kk
 * @datetime 2018/10/01
 * @desc
 */
public interface INativeCallBack {

    void onCall(String method, String data, String url, IJSCallFunction ijsCallFunction);

}
