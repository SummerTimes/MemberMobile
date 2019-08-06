package com.klcw.app.image.progress;

import android.text.TextUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * @author by sunfusheng on 2017/6/14.
 */
public class ProgressManager {

    private static Map<String, OnProgressListener> listenersMap = Collections.synchronizedMap(new HashMap<>());
    private static OkHttpClient okHttpClient;

    private ProgressManager() {
    }

    public static OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder()
                    .addNetworkInterceptor(chain -> {
                        Request request = chain.request();
                        Response response = chain.proceed(request);
                        return response.newBuilder()
                                .body(new ProgressResponseBody(request.url().toString(), LISTENER, response.body()))
                                .build();
                    })
                    .build();
        }
        return okHttpClient;
    }

    private static final ProgressResponseBody.InternalProgressListener LISTENER = (url, bytesRead, totalBytes) -> {
        OnProgressListener onProgressListener = getProgressListener(url);
        if (onProgressListener != null) {
            int percentage = (int) ((bytesRead * 1f / totalBytes) * 100f);
            boolean isComplete = percentage >= 100;
            onProgressListener.onProgress(isComplete, percentage, bytesRead, totalBytes);
            if (isComplete) {
                removeListener(url);
            }
        }
    };

    public static void addListener(String url, OnProgressListener listener) {
        if (!TextUtils.isEmpty(url) && listener != null) {
            listenersMap.put(url, listener);
            listener.onProgress(false, 1, 0, 0);
        }
    }

    public static void removeListener(String url) {
        if (!TextUtils.isEmpty(url)) {
            listenersMap.remove(url);
        }
    }

    public static OnProgressListener getProgressListener(String url) {
        if (TextUtils.isEmpty(url) || listenersMap == null || listenersMap.size() == 0) {
            return null;
        }

        OnProgressListener listenerWeakReference = listenersMap.get(url);
        if (listenerWeakReference != null) {
            return listenerWeakReference;
        }
        return null;
    }
}
