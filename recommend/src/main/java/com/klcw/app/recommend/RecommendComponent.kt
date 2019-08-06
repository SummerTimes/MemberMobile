package com.klcw.app.recommend

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.util.Log

import com.billy.cc.core.component.CC
import com.billy.cc.core.component.CCResult
import com.billy.cc.core.component.IComponent

/**
 * 组件调用/我的模块
 */
class RecommendComponent : IComponent {

    override fun getName(): String {
        return "recommendComponent"
    }

    override fun onCall(cc: CC): Boolean {
        if (TextUtils.equals("recommendActivity", cc.actionName)) {
            Log.e("xp", "---推荐---onCall----")
            val context = cc.context
            val intent = Intent(context, RecommendActivity::class.java)
            if (context !is Activity) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
            CC.sendCCResult(cc.callId, CCResult.success())
        } else {
            CC.sendCCResult(cc.callId, CCResult.error(""))
        }
        return false
    }
}
