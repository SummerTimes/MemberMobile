package com.klcw.app.cnetwork;

import android.text.TextUtils;
import android.util.Log;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.IComponent;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 使用okHttp实现的网络请求组件
 * https://github.com/square/okhttp/wiki/Recipes
 *
 * @author billy.qi
 * @since 17/7/13 09:43
 */
public class Network implements IComponent {

    /**
     * 最大重试次数
     */
    public static final int MAX_RETRY_COUNT = 5;

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient client;
    private static final String KEY_URL = "url";
    private static final String KEY_HEADER = "headers";
    private static final String KEY_DATA = "data";
    /**
     * 失败重试次数
     */
    private static final String KEY_RETRY = "retry";

    private static final String ACTION_GET = "GET";
    private static final String ACTION_POST = "POST";
    private static final String ACTION_SETTING = "SETTING";

    private static final String KEY_CONNECT_TIMEOUT = "connectTimeout";
    private static final String KEY_WRITE_TIMEOUT = "writeTimeout";
    private static final String KEY_READ_TIMEOUT = "readTimeout";
    private static final String KEY_HTTP_CODE = "httpCode";

    /**
     * 以下key为内部使用的key
     */
    private static final String PRIVATE_KEY_CONTENT_TYPE = "Content-type";
    /**
     * 已重试的次数计数
     */
    private static final String PRIVATE_KEY_RETRY_COUNT = "retry_count";

    private static final String KEY_RESULT = "result";

    public Network() {
        createOkHttpClient(10, 10, 30);
    }

    @Override
    public String getName() {
        return "network";
    }

    @Override
    public boolean onCall(CC cc) {
        CCResult result;
        try {
            String action = cc.getActionName();
            JSONObject params = new JSONObject(cc.getParams());
            String url = params.optString(KEY_URL);
            Log.e("xp", "-----url-----" + url);
            if (ACTION_GET.equalsIgnoreCase(action)) {
                boolean networkConnected = NetworkUtil.isNetworkConnected(cc.getContext());
                if (networkConnected) {
                    //有网络才发起请求
                    result = get(url, params);
                } else {
                    result = CCResult.error(cc.getContext().getString(R.string.network_unconnected));
                }
                Log.e("xp", "-----GET---result--" + result);
            } else if (ACTION_POST.equalsIgnoreCase(action)) {
                boolean networkConnected = NetworkUtil.isNetworkConnected(cc.getContext());
                if (networkConnected) {
                    //有网络才发起请求
                    result = post(url, params);
                } else {
                    result = CCResult.error(cc.getContext().getString(R.string.network_unconnected));
                }
                Log.e("xp", "-----POST---result--" + result);
            } else if (ACTION_SETTING.equalsIgnoreCase(action)) {
                initOkHttpClient(params);
                result = CCResult.success();
            } else {
                result = CCResult.error("action not support for:" + action);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = CCResult.error(cc.getContext().getString(R.string.network_connect_failed));
        }
        CC.sendCCResult(cc.getCallId(), result);
        return false;
    }

    /**
     * 初始化OkHttp
     *
     * @param params
     */
    private void initOkHttpClient(JSONObject params) {
        int connectTimeout = params.optInt(KEY_CONNECT_TIMEOUT, 10);
        int writeTimeout = params.optInt(KEY_WRITE_TIMEOUT, 10);
        int readTimeout = params.optInt(KEY_READ_TIMEOUT, 30);
        createOkHttpClient(connectTimeout, writeTimeout, readTimeout);
    }

    /**
     * 创建OkHttpClient
     *
     * @param connectTimeout
     * @param writeTimeout
     * @param readTimeout
     */
    private void createOkHttpClient(int connectTimeout, int writeTimeout, int readTimeout) {
        client = new OkHttpClient.Builder()
                .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                .writeTimeout(writeTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                .hostnameVerifier(HttpsUtil.getHostnameVerifier())
                .sslSocketFactory(HttpsUtil.getSSLSocketFactory(), HttpsUtil.getX509TrustManager())
                .build();
    }

    /**
     * get 请求
     *
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    private CCResult get(String url, JSONObject params) throws IOException {
        Object data = params.opt(KEY_DATA);
        if (data != null) {
            if (data instanceof JSONObject) {
                //在url后面追加参数列表
                url = buildGetUrl(url, (JSONObject) data);
            } else {
                if (!url.contains("?")) {
                    url += "?";
                }
                url += data;
            }
        }
        Request.Builder builder = new Request.Builder().url(url).get();
        addHeaders(builder, params);
        Request request = builder.build();
        Response response = client.newCall(request).execute();
        return processResponse(url, params, response);
    }

    /**
     * 拼接参数：url后面追加参数列表
     *
     * @param url
     * @param data
     * @return
     */
    private String buildGetUrl(String url, JSONObject data) {
        StringBuilder sb = new StringBuilder(url);
        boolean first = !url.contains("?");
        Iterator<String> keys = data.keys();
        while (keys.hasNext()) {
            if (first) {
                first = false;
                sb.append("?");
            } else {
                sb.append("&");
            }
            String key = keys.next();
            sb.append(key).append('=').append(data.optString(key));
        }
        return sb.toString();
    }

    /**
     * 过程响应
     *
     * @param url
     * @param params
     * @param response
     * @return
     * @throws IOException
     */
    private CCResult processResponse(String url, JSONObject params, Response response) throws IOException {
        CCResult result = getResult(response);
        //请求失败，处理重试逻辑
        if (!result.isSuccess()) {
            int retry = params.optInt(KEY_RETRY, 0);
            retry = Math.min(Math.max(retry, 0), MAX_RETRY_COUNT);
            int retryCount = params.optInt(PRIVATE_KEY_RETRY_COUNT, 0);
            retryCount = Math.max(retryCount, 0);
            if (retry > retryCount) {
                try {
                    retryCount++;
                    params.put(PRIVATE_KEY_RETRY_COUNT, retryCount);
                } catch (Exception e) {
                    e.printStackTrace();
                    //设置重试次数失败，为避免死循环，不进行重试
                    return result;
                }
                //开始重试
                return post(url, params);
            }
        }
        return result;
    }

    /**
     * post 请求
     *
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    private CCResult post(String url, JSONObject params) throws IOException {
        Request.Builder builder = new Request.Builder();
        addHeaders(builder, params);
        RequestBody body = buildRequestBody(params);
        Request request = builder
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return processResponse(url, params, response);
    }


    /**
     * 构建Body
     *
     * @param params
     * @return
     */
    private RequestBody buildRequestBody(JSONObject params) {
        MediaType mediaType;
        String contentType = params.optString(PRIVATE_KEY_CONTENT_TYPE);
        if (!TextUtils.isEmpty(contentType)) {
            mediaType = MediaType.parse(contentType);
        } else {
            mediaType = JSON;
        }
        Object data = params.opt(KEY_DATA);
        String content;
        if (data == null || data.toString() == null) {
            if (JSON.equals(mediaType)) {
                content = "{}";
            } else {
                content = "";
            }
        } else {
            content = data.toString();
        }
        return RequestBody.create(mediaType, content);
    }


    /**
     * 给网络请求添加head
     */
    private void addHeaders(Request.Builder builder, JSONObject params) {
        JSONObject heads = params.optJSONObject(KEY_HEADER);
        if (heads != null) {
            Iterator<String> keys = heads.keys();
            String key, value;
            while (keys.hasNext()) {
                key = keys.next();
                value = heads.optString(key);
                builder.addHeader(key, value);
                if (PRIVATE_KEY_CONTENT_TYPE.equalsIgnoreCase(key)) {
                    try {
                        params.put(PRIVATE_KEY_CONTENT_TYPE, value);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 构建CCResult对象
     *
     * @param response 网络请求的response
     * @return CCResult对象
     */
    private CCResult getResult(Response response) {
        int code = response.code();
        if (response.isSuccessful()) {
            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                try {
                    String content = responseBody.string();
                    return CCResult.success(KEY_RESULT, content);
                } catch (Exception e) {
                    e.printStackTrace();
                    return CCResult.error(KEY_HTTP_CODE, code);
                }
            }
            return CCResult.success();
        }
        CCResult result = CCResult.error(KEY_HTTP_CODE, code);
        result.setErrorMessage("http response:" + code);
        return result;
    }
}
