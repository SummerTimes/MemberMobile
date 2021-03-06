package com.kk.app.webview.title;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.kk.app.web.bljsbridge.BridgeWebView;
import com.klcw.app.web.R;

/**
 * @author kk
 * @datetime 2018/10/01
 * @desc
 */
public class ShareTitle extends BaseTitle {

    private View title_btn_right;
    private View share_in_title;
    private View title_btn_left;
    private TextView title_content;

    public ShareTitle(Activity activity) {
        super(activity);
    }


    @Override
    public void registerFunction(final BridgeWebView bridgeWebView) {
        super.registerFunction(bridgeWebView);

        title_btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Activity activity = getActivity();
                if (activity != null) {
                    goBack(bridgeWebView);
                }
            }
        });
        share_in_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bridgeWebView.callJs("window.commShare");
            }
        });
        title_btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                GoHomeEvent homeEvent = new GoHomeEvent();
//                homeEvent.view = view;
//                EventBus.getDefault().post(homeEvent);
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
        share_in_title = findView(R.id.share_in_title);
        share_in_title.setVisibility(View.VISIBLE);
        title_btn_left = findView(R.id.title_btn_left);
        title_content = (TextView) findView(R.id.title_content);
    }

    @Override
    protected int getLayout() {
        return R.layout.bl_web_share_title;
    }
}
