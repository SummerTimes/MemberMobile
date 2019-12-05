package com.kk.app.mobile;


import android.app.Application;
import android.support.multidex.MultiDex;

import com.kk.app.lib.network.NetworkConfig;

/**
 * @author kk
 * @datetime 2019-08-06
 * @desc 程序入口
 */
public class MemberApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        NetworkConfig.setup(this, "prd");
    }
}