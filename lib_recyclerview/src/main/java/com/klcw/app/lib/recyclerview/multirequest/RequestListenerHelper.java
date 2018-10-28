package com.klcw.app.lib.recyclerview.multirequest;

import android.support.annotation.NonNull;

import com.billy.cc.core.component.CCResult;

import java.util.HashMap;
import java.util.Map;


/**
 * 作者：杨松
 * 日期：2018/6/4 11:02
 */

public class RequestListenerHelper {


    public interface MultiListener {

        void onCall(String changeRequestName, Map<String, Object> map);

    }


    /**
     * @param listener
     * @param request
     */
    public static void listenMulti(final MultiListener listener, final NetRequester... request) {
        final Map<String, Object> objectMap = new HashMap<>();
        final int size = request.length;
        final long currentTimeMillis = System.currentTimeMillis();

        for (final NetRequester netRequester : request) {

            netRequester.addCallBack(new NetCallBack() {
                @Override
                public void onFailed(@NonNull CCResult ccResult) {
                    objectMap.put(netRequester.name(), null);
                    listener.onCall(netRequester.name(), objectMap);
                }

                @Override
                public void onSuccess(Object o) {
                    objectMap.put(netRequester.name(), o);
                    listener.onCall(netRequester.name(), objectMap);
                }

                @Override
                public String name() {
                    return netRequester.name() + currentTimeMillis;
                }
            });

        }

    }


}
