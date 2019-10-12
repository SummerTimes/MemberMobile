package com.kk.app.lib.recyclerview.multirequest;

import android.support.annotation.NonNull;

import com.billy.cc.core.component.CCResult;
import com.google.gson.Gson;
import com.kk.app.lib.network.NetworkCallback;
import com.kk.app.lib.network.NetworkHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * 作者：杨松
 * 日期：2018/6/4 10:04
 */

public class NetRequester<In, Out> implements IRequest<Request<In>, Out> {


    private final String name;
    private Map<String, NetCallBack<Out>> callBackMap = new HashMap<>();

    Response<Out> response;


    public NetRequester(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }


    protected void visitNet(Request<In> inRequest, String param) {
        try {
            NetworkHelper.query(inRequest.url, new JSONObject(param), new NetworkCallback<Out>() {
                @Override
                public void onSuccess(@NonNull CCResult ccResult, Out out) {
                    setResponse(ccResult, out, true);
                    info();
                }

                @Override
                public void onFailed(@NonNull CCResult ccResult) {
                    setResponse(ccResult, null, false);
                    info();
                }

                @Override
                public void onFinally(@NonNull CCResult ccResult) {

                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    Request<In> inRequest;

    public NetRequester<In, Out> setRequest(Request<In> inRequest) {
        this.inRequest = inRequest;
        return this;
    }

    @Override
    public NetRequester<In, Out> doRequest(Request<In> inRequest) {
        if (response != null) {
            response.isValidity = false;
        }
        setRequest(inRequest);
        String param;
        if (inRequest.requestParam instanceof String) {
            param = (String) inRequest.requestParam;
        } else {
            param = new Gson().toJson(inRequest.requestParam);
        }
        visitNet(inRequest, param);
        return this;
    }


    public void addCallBack(NetCallBack<Out> callBack) {
        callBackMap.put(callBack.name(), callBack);

    }


    protected void setResponse(CCResult ccResult, Out out, boolean isSuccess) {
        if (response == null) {
            response = new Response<>();
        }
        response.out = out;
        response.ccResult = ccResult;
        response.isSuccess = isSuccess;
        response.isValidity = true;
    }

    public void info() {
        if (response != null && response.isValidity) {
            if (response.isSuccess) {
                for (Map.Entry<String, NetCallBack<Out>> iCallBackEntry : callBackMap.entrySet()) {
                    NetCallBack<Out> responseICallBack = callBackMap.get(iCallBackEntry.getKey());
                    responseICallBack.onSuccess(response.out);
                }
            } else {
                for (Map.Entry<String, NetCallBack<Out>> iCallBackEntry : callBackMap.entrySet()) {
                    NetCallBack<Out> responseICallBack = callBackMap.get(iCallBackEntry.getKey());
                    responseICallBack.onFailed(response.ccResult);
                }
            }

        }
    }


    @Override
    public NetRequester<In, Out> doRequest(Request<In> inRequest, NetCallBack<Out> callBack) {
        this.callBackMap.put(callBack.name(), callBack);
        doRequest(inRequest);
        return this;
    }

    @Override
    public NetRequester<In, Out> doRequest() {
        return doRequest(this.inRequest);
    }


}
