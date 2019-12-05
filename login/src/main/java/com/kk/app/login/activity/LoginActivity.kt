package com.kk.app.login.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.billy.cc.core.component.CCResult
import com.kk.app.image.GlideImageView
import com.kk.app.lib.network.NetworkCallback
import com.kk.app.lib.network.NetworkHelper
import com.kk.app.lib.widget.utils.LxStatusBarUtil
import com.kk.app.login.R
import org.json.JSONException
import org.json.JSONObject

/**
 * @author kk
 * @datetime: 2018/10/24
 * @desc:
 */
class LoginActivity : AppCompatActivity() {
    private val mUrls: List<String>? = null
    private val mSivPic: GlideImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView()
    }

    /**
     * 初始化View
     */
    private fun initView() {
        LxStatusBarUtil.setLightMode(this)
    }

    /**
     * 测试获取网络信息数据接口
     *
     * @param view
     */
    fun onCheckNet(view: View?) {
        val Url = "https://www.wanandroid.com/user/login"
        try {
            val jsonObject = JSONObject()
            jsonObject.put("username", "lxtime")
            jsonObject.put("password", "123456789")
            NetworkHelper.queryApi(Url, jsonObject.toString(), NetworkHelper.HTTP_POST, object : NetworkCallback<String>() {
                override fun onSuccess(rawResult: CCResult, str: String) {
                    Log.e("xp", "---onSuccess----$str")
                }

                override fun onFailed(result: CCResult) {
                    Log.e("xp", "---onFailed----" + result.data)
                }

                override fun onFinally(result: CCResult) {}
            })
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}