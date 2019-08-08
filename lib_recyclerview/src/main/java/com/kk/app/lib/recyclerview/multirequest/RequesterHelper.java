package com.kk.app.lib.recyclerview.multirequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者：杨松
 * 日期：2018/6/4 23:10
 */

public class RequesterHelper {


    private Map<String, NetRequester> iRequestMap = new HashMap<>();

    public <In, Out> RequesterHelper doRequest(NetRequester<In, Out> request) {
        request.doRequest();
        return this;
    }

    public RequesterHelper wrapper(NetRequester... netRequesters) {
        for (NetRequester netRequester : netRequesters) {
            iRequestMap.put(netRequester.name(), netRequester);
        }
        return this;
    }


    public RequesterHelper listen(RequestListenerHelper.MultiListener listener, String... requestName) {
        List<IRequest> iRequests = new ArrayList<>();
        for (String name : requestName) {
            IRequest iRequest = iRequestMap.get(name);
            iRequests.add(iRequest);
        }
        NetRequester[] requesters = iRequests.toArray(new NetRequester[iRequests.size()]);
        RequestListenerHelper.listenMulti(listener, requesters);
        return this;
    }


    public <Request, Response> RequesterHelper afterDoRequester(final IRequesterCreator<Request, Response> creator, String... requestName) {
        List<IRequest> iRequests = new ArrayList<>();
        for (String name : requestName) {
            IRequest iRequest = iRequestMap.get(name);
            iRequests.add(iRequest);
        }
        NetRequester[] requesters = iRequests.toArray(new NetRequester[iRequests.size()]);
        RequestListenerHelper.listenMulti(new RequestListenerHelper.MultiListener() {
            @Override
            public void onCall(String changeRequestName, Map<String, Object> map) {
                NetRequester<Request, Response> request = creator.createRequest(map);
                if (request == null) {
                    return;
                }
                request.doRequest();
            }
        }, requesters);
        return this;
    }


}
