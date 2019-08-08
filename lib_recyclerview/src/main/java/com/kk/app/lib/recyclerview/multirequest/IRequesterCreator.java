package com.kk.app.lib.recyclerview.multirequest;

import java.util.Map;

/**
 * 作者：杨松
 * 日期：2018/6/4 22:47
 */

public interface IRequesterCreator<Requester, Response> {

    NetRequester<Requester, Response> createRequest(Map in);


}
