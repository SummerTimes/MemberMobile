package com.klcw.app.lib.recyclerview.multirequest;

/**
 * 作者：杨松
 * 日期：2018/6/4 10:02
 */

public interface IRequest<In, Out> {


    IRequest doRequest();

    IRequest doRequest(In in);

    String name();

    IRequest doRequest(In in, NetCallBack<Out> callBack);


}
