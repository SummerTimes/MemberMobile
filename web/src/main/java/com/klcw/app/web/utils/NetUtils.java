package com.klcw.app.web.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


/***
 * @功能说明：网络工具类
 */
public class NetUtils {

    /**
     * 检查当前的网络（wifi及移动网络）
     *
     * @param context
     * @return
     */
    public static boolean checkNet(Context context) {
        // 获取到wifi和mobile连接方式
        Context applicationContext = context.getApplicationContext();
        boolean wifiConnection = isWIFIConnection(applicationContext);
        boolean mobileConnection = isMobileConnection(applicationContext);

        if (!wifiConnection && !mobileConnection) {
            // 如果都不能连接
            // 提示用户设置当前网络——跳转到设置界面
            return false;
        }
        return true;
    }

    /**
     * 判断wifi是否连接
     *
     * @param context
     * @return
     */
    public static boolean isWIFIConnection(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (networkInfo == null) {
            return false;
        }
        return networkInfo.isConnected() ? true : false;
    }

    /**
     * 判断是否是手机移动网络
     *
     * @param context
     * @return
     */
    public static boolean isMobileConnection(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (networkInfo != null) {
            return networkInfo.isConnected();
        }
        return false;
    }


}
