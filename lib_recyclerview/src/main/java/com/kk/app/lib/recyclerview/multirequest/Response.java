package com.kk.app.lib.recyclerview.multirequest;


import com.billy.cc.core.component.CCResult;

/**
 * 作者：杨松
 * 日期：2018/6/4 10:12
 */

public class Response<Out> {

    public Out out;

    public CCResult ccResult;

    public boolean isValidity = false;

    public boolean isSuccess = false;

}
