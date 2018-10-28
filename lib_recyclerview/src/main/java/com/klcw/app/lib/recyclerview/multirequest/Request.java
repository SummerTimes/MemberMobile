package com.klcw.app.lib.recyclerview.multirequest;

/**
 * 作者：杨松
 * 日期：2018/6/4 10:14
 */

public class Request<In> {

    public Request(In requestParam) {
        this.requestParam = requestParam;
    }

    public Request() {
    }

    public In requestParam;

    public String url;


    public String type;
}
