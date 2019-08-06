package com.klcw.app.member;


import android.app.Application;
import android.support.multidex.MultiDex;

import com.klcw.app.lib.network.NetworkConfig;

/**
 * @author kk
 * @datetime 2019-08-06
 * @desc
 */
public class MemberApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        NetworkConfig.setup(this, "prd");
    }
}
