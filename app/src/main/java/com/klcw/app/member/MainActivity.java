package com.klcw.app.member;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.IComponentCallback;
import com.klcw.app.util.StringUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    /**
     * 初始化View
     */
    private void initView() {
        StringUtils.print("初始化App");
    }

    /**
     * 跳转登录模块
     *
     * @param view
     */
    public void onLoginClick(View view) {
        CC.obtainBuilder("loginComponent")
                .setContext(this)
                .build()
                .callAsync(new IComponentCallback() {
                    @Override
                    public void onResult(CC cc, CCResult result) {
                        StringUtils.print("-----onResult-----" + result.toString());
                    }
                });
    }

    /**
     * 跳转我的模块
     *
     * @param view
     */
    public void onMineClick(View view) {
        CC.obtainBuilder("mineComponent")
                .setContext(this)
                .build()
                .callAsync(new IComponentCallback() {
                    @Override
                    public void onResult(CC cc, CCResult result) {
                        StringUtils.print("-----onResult-----" + result.toString());
                    }
                });
    }

    /**
     * 跳转推荐模块
     *
     * @param view
     */
    public void onRecommendClick(View view) {
        CC.obtainBuilder("recommendComponent")
                .setContext(this)
                .build()
                .callAsync(new IComponentCallback() {
                    @Override
                    public void onResult(CC cc, CCResult result) {
                        StringUtils.print("-----onResult-----" + result.toString());
                    }
                });
    }
}
