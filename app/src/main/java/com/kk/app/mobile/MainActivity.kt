package com.kk.app.mobile;

import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.ViewStub;
import android.widget.Toast;

import com.kk.app.lore.activity.RecommendActivity;
import com.kk.app.mine.activity.MineActivity;
import com.kk.app.mobile.activity.TestActivity;
import com.kk.app.mobile.constant.AppMethod;
import com.kk.app.mobile.kit.AppPageKit;
import com.kk.app.product.activity.ProductMainActivity;

import java.util.ArrayList;
import java.util.List;

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
//        setupShortcuts();
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

    /**
     * 桌面快捷启动1、发微博；2、热门微博；3、扫一扫
     */
    private void setupShortcuts() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            ShortcutManager mShortcutManager = getSystemService(ShortcutManager.class);
            List<ShortcutInfo> infos = new ArrayList<>();
            Intent intent;
            ShortcutInfo info;

            Intent mainIntent = new Intent(Intent.ACTION_MAIN, Uri.EMPTY,
                    this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

            // 发微博
            intent = new Intent(this, RecommendActivity.class);
            intent.setAction(Intent.ACTION_VIEW);
            intent.putExtra("isPublishWeibo", true);
            intent.putExtra("from_shortcuts", true);
            info = new ShortcutInfo.Builder(this, getResources().getString(R.string.shortcuts_publish_weibo_long_disable_msg))
                    .setShortLabel(getResources().getString(R.string.shortcuts_publish_weibo_short_name))
                    .setLongLabel(getResources().getString(R.string.shortcuts_publish_weibo_long_name))
                    .setIcon(Icon.createWithResource(this, R.mipmap.ic_launcher))
                    .setIntents(new Intent[]{mainIntent, intent}).build();
            infos.add(info);

            // 热门微博
            intent = new Intent(this, MineActivity.class);
            intent.setAction(Intent.ACTION_VIEW);
            intent.putExtra("isPublishWeibo", false);
            intent.putExtra("from_shortcuts", true);
            info = new ShortcutInfo.Builder(this, getResources().getString(R.string.shortcuts_popular_weibo_disable_msg))
                    .setShortLabel(getResources().getString(R.string.shortcuts_popular_weibo_short_name))
                    .setLongLabel(getResources().getString(R.string.shortcuts_popular_weibo_long_name))
                    .setIcon(Icon.createWithResource(this, R.mipmap.ic_launcher))
                    .setIntents(new Intent[]{mainIntent, intent}).build();
            infos.add(info);

            // 扫一扫
            intent = new Intent(this, ProductMainActivity.class);
            intent.setAction(Intent.ACTION_VIEW);
            info = new ShortcutInfo.Builder(this, getResources().getString(R.string.shortcuts_qrcord_disable_msg))
                    .setShortLabel(getResources().getString(R.string.shortcuts_qrcord_short_name))
                    .setLongLabel(getResources().getString(R.string.shortcuts_qrcord_long_name))
                    .setIcon(Icon.createWithResource(this, R.mipmap.ic_launcher))
                    .setIntent(intent).setIntents(new Intent[]{mainIntent, intent}).build();

            infos.add(info);
            mShortcutManager.setDynamicShortcuts(infos);
        }
    }

}
