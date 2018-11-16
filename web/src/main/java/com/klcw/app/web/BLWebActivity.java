package com.klcw.app.web;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.GeolocationPermissions;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bailian.weblib.bljsbridge.BridgeWebView;
import com.bailian.weblib.bljsbridge.IJSCallFunction;
import com.bailian.weblib.bljsbridge.INativeCallBack;
import com.bailian.weblib.vassonic.SonicRuntimeImpl;
import com.bailian.weblib.vassonic.SonicSessionClientImpl;
import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.IComponentCallback;
import com.klcw.app.util.log.Logger;
import com.klcw.app.web.title.ITitle;
import com.klcw.app.web.title.RechargeTitle;
import com.klcw.app.web.title.WebTitleFactory;
import com.klcw.app.web.utils.NetUtils;
import com.tencent.sonic.sdk.SonicConfig;
import com.tencent.sonic.sdk.SonicEngine;
import com.tencent.sonic.sdk.SonicSession;
import com.tencent.sonic.sdk.SonicSessionConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.Stack;


/**
 * 作者：杨松
 * 日期：2017/9/20 13:22
 *
 * @author kk
 */

public class BLWebActivity extends AppCompatActivity {


    private @Nullable
    ITitle mITitle;
    private CardView cardView;

    private String mTitleStr;

    private String mNoNeedTitle;
    boolean mIsFirstIn = true;

    private String mUrl;
    private String params = null;

    private SonicSession sonicSession;
    private FunctionRegister mRegister;
    private BridgeWebView bridgeWebView;
    private SonicSessionClientImpl sonicSessionClient;

    private static final String PARAM = "params";
    private Stack<ITitle> mTitleStack = new Stack<>();

    /**
     * start BLWebActivity
     *
     * @param context
     * @param params
     * @return
     */
    public static boolean startAct(Context context, String params) {
        if (!TextUtils.isEmpty(params)) {
            Intent intent = new Intent(context, BLWebActivity.class);
            intent.putExtra(PARAM, params);
            context.startActivity(intent);
            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        initVariables();
        if (0 == ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            initSonic();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 701);
        }
        setContentView(R.layout.bl_web_act);
        initView();
        setWeb();
        registerFunction();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mRegister.onResume(this);
        if (!mIsFirstIn) {
            bridgeWebView.callJs("window.currentPageReload");
        }
        mIsFirstIn = false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        mRegister.onPause(this);
    }

    @Override
    protected void onDestroy() {
        if (null != sonicSession) {
            sonicSession.destroy();
            sonicSession = null;
            if (null != sonicSessionClient) {
                sonicSessionClient.destroy();
                sonicSessionClient = null;
            }
        }
        super.onDestroy();
        mRegister.onDestroy(this);
    }

    @Override
    public void onBackPressed() {
        if (mITitle != null) {
            mITitle.onGoBack();
        }
        goBack(bridgeWebView);
    }

    /**
     * 初始化数据
     */
    protected void initVariables() {
        Intent intent = getIntent();
        if (intent != null) {
            params = intent.getStringExtra(PARAM);
            Log.e("xp", "-----params-------" + params);
            try {

                JSONObject jsonObject = new JSONObject(params);
                if (!jsonObject.isNull("url")) {
                    mUrl = jsonObject.getString("url");
                }

                if (!jsonObject.isNull("title")) {
                    setTitle(jsonObject.getString("title"));
                }

                if (!TextUtils.isEmpty(jsonObject.optString("finalTitle"))) {
                    mTitleStr = jsonObject.optString("finalTitle");
                }

                mNoNeedTitle = jsonObject.optString("noTitle");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    void initSonic() {
        if (!SonicEngine.isGetInstanceAllowed()) {
            SonicEngine.createInstance(new SonicRuntimeImpl(getApplication()), new SonicConfig.Builder().build());
        }
        sonicSession = SonicEngine.getInstance().createSession(mUrl, new SonicSessionConfig.Builder().setSupportLocalServer(true).build());
        if (null != sonicSession) {
            sonicSession.bindClient(sonicSessionClient = new SonicSessionClientImpl());
        } else {
            Logger.i("BLWeb", "create sonic session fail!");
        }
    }

    private void initView() {
        cardView = findViewById(R.id.title_container);
        bridgeWebView = findViewById(R.id.web);
        bridgeWebView.setSonicSession(sonicSession);

        if (TextUtils.equals("noTitle", mNoNeedTitle)) {
            cardView.setVisibility(View.GONE);
        } else {
            mITitle = WebTitleFactory.produceWebTitle(mUrl, this, bridgeWebView);
            mITitle.createTitle(this, cardView);
            mITitle.registerFunction(bridgeWebView);
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void setWeb() {
        final WebSettings settings = bridgeWebView.getSettings();
        String userAgent = settings.getUserAgentString();
        settings.setUserAgentString(userAgent + " klcw");
        settings.setJavaScriptEnabled(true);
        if (!NetUtils.checkNet(this)) {
            settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        } else {
            settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        }

        if (Build.VERSION.SDK_INT >= 21) {
            bridgeWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        // 设置可以使用localStorage
        settings.setDomStorageEnabled(true);
        // 应用可以有缓存

        String appCaceDir = this.getApplicationContext().getDir("cache", Context.MODE_PRIVATE).getPath();
        settings.setAppCachePath(appCaceDir);
        settings.setAppCacheEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSaveFormData(false);
        settings.setBuiltInZoomControls(false);

        //启用数据库
        settings.setDatabaseEnabled(true);
        //设置定位的数据库路径
        String dir = this.getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath();
        settings.setGeolocationDatabasePath(dir);
        //启用地理定位
        settings.setGeolocationEnabled(true);

        bridgeWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);

                Log.e("xp", "----onReceivedTitle-----" + title);
                if (mITitle != null) {
                    mITitle.setTitle(mTitleStr);
                    return;
                }

                if (!TextUtils.isEmpty(view.getTitle()) && !view.getTitle().contains("http") && mITitle != null) {
                    mITitle.setTitle(view.getTitle());
                }
            }
        });

        if (!TextUtils.isEmpty(mUrl)) {
            mUrl = mUrl.replaceAll(" ", "");
        }
        if (sonicSessionClient != null) {
            sonicSessionClient.bindWebView(bridgeWebView);
            sonicSessionClient.clientReady();
        } else {
            bridgeWebView.loadUrl(mUrl);
        }
    }

    private void registerFunction() {
        mRegister = new FunctionRegister();
        mRegister.onActivityAttach(this);
        mRegister.registerFunction(bridgeWebView, this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mRegister.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (mRegister != null) {//fixed nullpoint
            mRegister.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    public void goBack(BridgeWebView webview) {
        if (webview.canGoBack()) {
            // 返回上一页面
            webview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
            webview.goBack();

            if (mITitle != null && !mITitle.onGoBack()) {
                if (!mTitleStack.isEmpty()) {
                    mTitleStack.pop();
                }
                if (!mTitleStack.isEmpty()) {
                    mITitle = mTitleStack.pop();
                    mITitle.createTitle(BLWebActivity.this, cardView);
                    mTitleStack.push(mITitle);
                }
            }
        } else {
            finish();
        }
    }
}














