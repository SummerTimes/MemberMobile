package com.bailian.weblib.bljsbridge;

/**
 * Created by uis on 2017/6/19.
 */

public interface PageCall {

    void onPageStarted(String url);

    void onPageFinished(String url);

}
