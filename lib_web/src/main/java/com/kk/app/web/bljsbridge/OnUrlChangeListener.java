package com.kk.app.web.bljsbridge;

/**
 * 作者：杨松
 * 日期：2017/6/13 09:42
 * 普通的URL改变会引起这个
 */

public interface OnUrlChangeListener {

    boolean onChangeUrl(String url);

    boolean onPageStarted(String url);
}
