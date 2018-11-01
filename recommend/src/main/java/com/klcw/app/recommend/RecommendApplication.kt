package com.klcw.app.recommend

import android.app.Application

import com.billy.cc.core.component.CC

class RecommendApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        CC.enableDebug(true)
        CC.enableVerboseLog(true)
//        CC.enableRemoteCC(true)
    }
}
