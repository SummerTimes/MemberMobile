package com.kk.app.login.activity

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.KeyEvent
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.kk.app.lib.widget.NoDoubleClickListener
import com.kk.app.lib.widget.neterror.NeterrorLayout
import com.kk.app.login.R
import com.kk.app.util.NetUtil
import org.json.JSONException
import org.json.JSONObject

/**
 * @author kk
 * @datetime: 2018/10/24
 * @desc:
 */
class WebActivity : AppCompatActivity() {
    var neterror: NeterrorLayout? = null
    var rltTitle: RelativeLayout? = null
    var tvTitle: TextView? = null
    var ivBack: ImageView? = null
    var webContent: WebView? = null
    var mUrl: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        initView()
        initView()
        initListener()
        initVariables()
        checkNet()
    }

    private fun initView() {
        webContent = findViewById(R.id.web_content)
        rltTitle = findViewById(R.id.rlt_title)
        tvTitle = findViewById(R.id.tv_title)
        ivBack = findViewById(R.id.iv_back)
        neterror = findViewById(R.id.nel_error)
        initialWebSetting()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initialWebSetting() {
        val settings = webContent!!.settings
        settings.javaScriptEnabled = true
        //setCacheMode，有网络就是使用默认缓存策略
        if (!NetUtil.checkNet(this)) {
            settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        } else {
            settings.cacheMode = WebSettings.LOAD_DEFAULT
        }
        if (Build.VERSION.SDK_INT >= 21) {
            webContent!!.settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }
        // 设置可以使用localStorage
        settings.domStorageEnabled = true
        // 应用可以有缓存
        val appCacheDir = this.applicationContext.getDir("cache", Context.MODE_PRIVATE).path
        settings.setAppCachePath(appCacheDir)
        settings.setAppCacheEnabled(true)
        settings.useWideViewPort = true
        settings.loadWithOverviewMode = true
        settings.saveFormData = false
        settings.builtInZoomControls = false
    }

    private fun initListener() {
        ivBack!!.setOnClickListener(object : NoDoubleClickListener() {
            override fun onNoDoubleClick(v: View) {
                onBack()
            }
        })
    }

    protected fun initVariables() {
        val params = intent.getStringExtra(PARAMS)
        try {
            val data = JSONObject(params)
            mUrl = data.optString(URL)
            tvTitle!!.text = data.optString(TITLE)
            val isHideTitle = data.optBoolean(HIDE_TITLE)
            if (isHideTitle) {
                rltTitle!!.visibility = View.GONE
            } else {
                rltTitle!!.visibility = View.VISIBLE
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    private fun checkNet() {
        if (!NetUtil.checkNet(this)) {
            noNet()
        } else {
            hasNet()
        }
    }

    private fun noNet() {
        neterror!!.onTimeoutError()
        webContent!!.visibility = View.GONE
    }

    private fun hasNet() {
        webContent!!.visibility = View.VISIBLE
        neterror!!.onConnected()
        webContent!!.webChromeClient = WebChromeClient()
        webContent!!.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                val parse = Uri.parse(url)
                val scheme = parse.scheme
                return false
            }

            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap) {
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                if (!TextUtils.isEmpty(webContent!!.title) && !webContent!!.title.contains("http")) {
                    tvTitle!!.text = webContent!!.title
                }
            }
        }
        if (!TextUtils.isEmpty(mUrl) && webContent != null) {
            webContent!!.loadUrl(mUrl)
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun onBack() {
        if (webContent != null) {
            if (webContent!!.canGoBack()) {
                webContent!!.goBack()
            } else {
                onBackPressed()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (webContent != null) {
            webContent!!.clearCache(true)
        }
    }

    companion object {
        const val PARAMS = "params"
        const val URL = "url"
        const val TITLE = "title"
        const val HIDE_TITLE = "hide_title"
        const val TAG = "xp"
    }
}