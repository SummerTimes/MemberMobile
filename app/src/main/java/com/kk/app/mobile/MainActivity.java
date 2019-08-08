package com.kk.app.mobile;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.ViewStub;
import android.widget.Toast;

import com.kk.app.mobile.constant.AppMethod;
import com.kk.app.mobile.kit.AppPageKit;

/**
 * @author kk
 * @datetime 2018/10/24
 * @desc 程序入口
 */
public class MainActivity extends AppCompatActivity {

    private boolean mIsExit;
    private AppPageKit mAppPageKit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_main_activity);
        initView();
    }

    private void initView() {
        ((ViewStub) findViewById(R.id.vs_mainPage)).inflate();
        mAppPageKit = new AppPageKit(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAppPageKit.onResume();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        if (getIntent().getExtras() != null) {
            if (null != mAppPageKit) {
                mAppPageKit.onIntentAction(getIntent().getExtras().getString(AppMethod.KRY_PARAM));
            }
        }
    }

    @Override
    protected void onDestroy() {
        if (null != mAppPageKit) {
            mAppPageKit.release();
        }
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mIsExit) {
                finish();
            } else {
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                mIsExit = true;
                new Handler().postDelayed(() -> mIsExit = false, 2000);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
