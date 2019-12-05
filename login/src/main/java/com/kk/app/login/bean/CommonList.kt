package com.kk.app.login.bean

/**
 * @author kk
 * @datetime: 2018/10/24
 * @desc:
 */
class CommonList {
    var data: List<CommonBean>? = null
    var errorCode = 0
    var errorMsg: String? = null
    override fun toString(): String {
        return "CommonList{" +
                "data=" + data +
                ", errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                '}'
    }
}