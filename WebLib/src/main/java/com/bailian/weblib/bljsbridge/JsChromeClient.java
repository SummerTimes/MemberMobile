package com.bailian.weblib.bljsbridge;

import android.util.Log;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;


/**
 * 作者：杨松
 * 日期：2017/10/24 13:59
 */

class JsChromeClient extends WebChromeClient implements IWebClientCallBack {


    private boolean mAlreadyPutIn = false;


    @Override
    public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
        return super.onJsConfirm(view, url, message, result);
    }

    @Override
    public boolean onJsBeforeUnload(WebView view, String url, String message, JsResult result) {
        return super.onJsBeforeUnload(view, url, message, result);
    }

    @Override
    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
        return super.onJsPrompt(view, url, message, defaultValue, result);
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
        if (!mAlreadyPutIn && newProgress > 25) {
            mAlreadyPutIn = true;
            BridgeUtil.webViewLoadLocalJs(view, BridgeWebView.toLoadJs);
        }
        if (newProgress < 25) {
            mAlreadyPutIn = false;
        }

    }


    @Override
    public void onReload(WebView webView) {
        BridgeUtil.webViewLoadLocalJs(webView, BridgeWebView.toLoadJs);
    }
}
