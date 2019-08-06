package com.klcw.app.login;

import android.app.Application;

import com.klcw.app.lib.network.NetworkConfig;

/**
 * @author kk
 * @datetime: 2018/10/24
 * @desc:
 */
public class LoginApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        NetworkConfig.setup(this, "prd");
    }
}
