package com.kk.app.login

import com.billy.cc.core.component.CC
import com.billy.cc.core.component.CCResult
import com.billy.cc.core.component.IComponent
import com.kk.app.login.constant.LoginConstant
import com.kk.app.login.util.LoginUtils

/**
 * @author kk
 * @datetime: 2018/10/24
 * @desc:组件/我的Component
 */
class LoginComponent : IComponent {
    override fun getName(): String {
        return LoginConstant.Companion.KRY_LOGIN_COMPONENT
    }

    override fun onCall(cc: CC): Boolean {
        val actionName = cc.actionName
        when (actionName) {
            LoginConstant.Companion.KRY_LOGIN_ACTIVITY_ACTION -> {
                LoginUtils.openLoginActivity(cc)
                CC.sendCCResult(cc.callId, CCResult.success())
                return true
            }
            else -> CC.sendCCResult(cc.callId, CCResult.error("actionName not specified"))
        }
        return true
    }
}