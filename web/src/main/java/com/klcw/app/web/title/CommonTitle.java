package com.klcw.app.web.title;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bailian.weblib.bljsbridge.BridgeWebView;
import com.klcw.app.lib.widget.BLToast;
import com.klcw.app.web.R;

/**
 * 作者：杨松
 * 日期：2017/7/17 16:06
 *
 * @author kk
 */

public class CommonTitle extends BaseTitle {


    private View ivBack;
    private ImageView right;
    private TextView tv_txt;


    public CommonTitle(Activity activity) {
        super(activity);
    }

    @Override
    public void registerFunction(final BridgeWebView bridgeWebView) {
        super.registerFunction(bridgeWebView);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack(bridgeWebView);
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BLToast.showToast(v.getContext(), "又");
            }
        });
    }


    @Override
    protected void onSetTitle(String data) {
        tv_txt.setText(data);
    }


    @Override
    protected void initOnCreateTitle() {
        ivBack = findView(R.id.iv_back);
        right = (ImageView) findView(R.id.right);
        tv_txt = (TextView) findView(R.id.tv_txt);
    }


    @Override
    protected int getLayout() {
        return R.layout.bl_web_title_common;
    }

}
