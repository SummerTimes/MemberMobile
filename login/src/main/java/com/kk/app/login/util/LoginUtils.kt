package com.kk.app.login.util

import android.app.Activity
import android.content.Intent
import android.text.TextUtils
import com.billy.cc.core.component.CC
import com.kk.app.lib.widget.BLToast
import com.kk.app.login.activity.LoginActivity
import com.kk.app.login.constant.LoginConstant

/**
 * @author kk
 * @datetime: 2018/11/14
 * @desc:
 */
object LoginUtils {
    /**
     * 跳转LoginActivity
     *
     * @param cc 参数
     */
    fun openLoginActivity(cc: CC) {
        val param = cc.getParamItem<String>(LoginConstant.Companion.KRY_PARAM)
        if (TextUtils.isEmpty(param)) {
            BLToast.showToast(cc.context, "参数不能为空")
            return
        }
        val context = cc.context
        val intent = Intent(context, LoginActivity::class.java)
        if (context !is Activity) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        intent.putExtra(LoginConstant.Companion.KRY_PARAM, param)
        context.startActivity(intent)
    }
}