package com.klcw.app.cnetwork;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


/***
 * @author kk
 * @datetime: 17/8/11 15:25
 * @desc:网络工具类
 */
public class NetworkUtil {

    /**
     * 是否有网络
     *
     * @param context
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = manager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

}
