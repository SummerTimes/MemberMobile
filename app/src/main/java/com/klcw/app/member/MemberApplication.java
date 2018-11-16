package com.klcw.app.member;


import android.app.Application;
import android.support.multidex.MultiDex;

import com.billy.cc.core.component.CC;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.klcw.app.lib.network.NetworkConfig;

public class MemberApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        // 开启/关闭CC调试日志
        CC.enableDebug(true);
        // 开启/关闭CC调用执行过程的跟踪日志
        CC.enableVerboseLog(true);
        // 正式上线时禁用跨app调用组件 跨app调用支持：debug时启用，release时禁用
        CC.enableRemoteCC(true);
        Fresco.initialize(this);
        NetworkConfig.setup(this, "prd");
    }
}
