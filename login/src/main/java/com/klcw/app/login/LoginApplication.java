package com.klcw.app.login;

import android.app.Application;

import com.billy.cc.core.component.CC;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.klcw.app.lib.network.NetworkConfig;

public class LoginApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CC.enableDebug(true);
        CC.enableVerboseLog(true);
//        CC.enableRemoteCC(true);
        Fresco.initialize(this);
        NetworkConfig.setup(this, "prd");
    }
}
