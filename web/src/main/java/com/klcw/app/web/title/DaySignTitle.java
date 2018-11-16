package com.klcw.app.web.title;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.bailian.weblib.bljsbridge.BridgeWebView;
import com.klcw.app.util.log.Logger;
import com.klcw.app.web.R;



public final class DaySignTitle extends BaseTitle {

    private BridgeWebView mBridgeWebView;
    private TextView tv_right;
    private TextView tv_txt;

    public DaySignTitle(Activity activity) {
        super(activity);
    }

    @Override
    protected final int getLayout() {
        return R.layout.bl_web_title_day_sign;
    }


    @Override
    public void registerFunction(final BridgeWebView bridgeWebView) {
        super.registerFunction(bridgeWebView);
        this.mBridgeWebView = bridgeWebView;
    }

    @Override
    protected void onSetTitle(String data) {
        tv_txt.setText(data);
    }

    @Override
    public void initOnCreateTitle() {

        findView(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = getActivity();
                if (activity != null) goBack(mBridgeWebView);
            }
        });
        tv_right = (TextView) findView(R.id.tv_right);
        tv_txt = (TextView) findView(R.id.tv_txt);

        tv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.i("签到说明");
                mBridgeWebView.callJs("BLAlertSignConfirm");
            }
        });
    }

    public void setTitle(String title) {
        onSetTitle(title);
    }
}
