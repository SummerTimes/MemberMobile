package com.klcw.app.web.title;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.bailian.weblib.bljsbridge.BridgeWebView;
import com.klcw.app.web.R;


/**
 * FlyFish
 * 2017/7/14 16:12
 */

public class AnfbLoginTitle extends BaseTitle {

    private View title_btn_right;
    private View share_in_title;
    private TextView title_content;
    private View title_btn_left;

    AnfbLoginTitle(Activity activity) {
        super(activity);
    }


    @Override
    public void registerFunction(final BridgeWebView bridgeWebView) {
        super.registerFunction(bridgeWebView);
        title_btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack(bridgeWebView);
            }
        });
    }

    @Override
    protected void onSetTitle(String data) {
        title_content.setText(data);
    }


    @Override
    public void initOnCreateTitle() {

        title_btn_right = findView(R.id.title_btn_right);
        title_btn_left = findView(R.id.title_btn_left);
        share_in_title = findView(R.id.share_in_title);
        title_content = (TextView) findView(R.id.title_content);

        title_btn_right.setVisibility(View.INVISIBLE);
        share_in_title.setVisibility(View.INVISIBLE);
        title_content.setText("联合登录");
    }

    @Override
    protected int getLayout() {
        return R.layout.bl_web_common_title;
    }
}
