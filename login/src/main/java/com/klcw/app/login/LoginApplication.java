package com.klcw.app.login;

import android.app.Application;

import com.billy.cc.core.component.CC;
import com.facebook.drawee.backends.pipeline.Fresco;
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
        CC.enableDebug(true);
        CC.enableVerboseLog(true);
        Fresco.initialize(this);
        NetworkConfig.setup(this, "prd");
    }
}
