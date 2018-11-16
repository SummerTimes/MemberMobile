package com.klcw.app.web.title;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bailian.weblib.bljsbridge.BridgeConfig;
import com.bailian.weblib.bljsbridge.BridgeWebView;
import com.bailian.weblib.bljsbridge.IJSCallFunction;
import com.bailian.weblib.bljsbridge.INativeCallBack;
import com.bailian.weblib.bljsbridge.JSEntity;
import com.google.gson.Gson;
import com.klcw.app.web.R;
import com.klcw.app.web.entity.DefaultTitleBean;

import java.util.List;
import java.util.Stack;


/**
 * 作者：杨松
 * 日期：2017/7/17 16:06
 *
 * @author kk
 */

public class CommonTitle extends BaseTitle {

    public final String RIGHT_BUTTON = "1";
    public final String TITLE_BUTTON = "0";

    private BridgeWebView mBridgeWebView;

    String CHANGE_TITLE = "BLDefaultWebView" + "#" + "configNavbar";

    final String CHANGE_TITLE_ANDROID = "changeTitle" + "#" + "Android";


    private View ivBack;
    private View right_container;
    private ImageView share_in_title;
    private TextView tv_right;
    private ImageView right;
    private TextView tv_txt;


    public CommonTitle(Activity activity) {
        super(activity);
    }

    @Override
    public void registerFunction(BridgeWebView bridgeWebView) {
        super.registerFunction(bridgeWebView);
        this.mBridgeWebView = bridgeWebView;

        registerTitleFunction(bridgeWebView, bridgeWebView.getContext());

    }


    private Stack<TitleBean> mTitleList;

    /**
     * 普通头
     *
     * @param webView
     * @param context
     */
    private void registerTitleFunction(final BridgeWebView webView, Context context) {

        webView.registerFunction(CHANGE_TITLE, new INativeCallBack() {
            @Override
            public void onCall(String method, String data, String url, IJSCallFunction ijsCallFunction) {
                DefaultTitleBean defaultTitleBean = new Gson().fromJson(data, DefaultTitleBean.class);
                inflateTitle(defaultTitleBean, ijsCallFunction, url);
            }
        });


        webView.registerFunction(CHANGE_TITLE_ANDROID, new INativeCallBack() {
            @Override
            public void onCall(String method, String data, String url, IJSCallFunction ijsCallFunction) {
                if (mTitleList == null) {
                    mTitleList = new Stack<>();
                }
                TitleBean bean = new Gson().fromJson(data, TitleBean.class);
                mTitleList.push(bean);
                setInitData(bean, ijsCallFunction, url);
            }
        });
    }


    private void inflateTitle(DefaultTitleBean defaultTitleBean, final IJSCallFunction ijsCallFunction, final String url) {

        List<DefaultTitleBean.LeftButton> leftButtons = defaultTitleBean.leftButtonList;
        List<DefaultTitleBean.RightButton> rightButtons = defaultTitleBean.rightButtonList;

        if (rightButtons == null || rightButtons.size() == 0) {
            tv_right.setVisibility(View.GONE);
            return;
        }

        right.setTag(null);
        tv_right.setTag(null);
        share_in_title.setTag(null);


        if (rightButtons.size() > 0) {
            for (DefaultTitleBean.RightButton rightButton : rightButtons) {
                if (rightButton.isText()) {
                    tv_right.setTag(rightButton);
                    tv_right.setText(rightButton.buttonTitle);
                    tv_right.setVisibility(TextUtils.isEmpty(rightButton.buttonTitle) ? View.GONE : View.VISIBLE);
                    continue;
                }
                if (rightButton.isImage()) {
                    if (TextUtils.equals(rightButton.imageName, DefaultTitleBean.TitleConstant.ImageName.MORE)) {
                        right.setImageDrawable(getActivity().getResources().getDrawable(R.mipmap.common_icon_more_v4));
                        right.setTag(rightButton);
                        right.setVisibility(View.VISIBLE);
                        continue;
                    }
                }

                if (rightButton.isImage()) {
                    if (TextUtils.equals(rightButton.imageName, DefaultTitleBean.TitleConstant.ImageName.share)) {
                        share_in_title.setTag(rightButton);
                        share_in_title.setVisibility(View.VISIBLE);
                    }
                }
            }
        }

        setVisibility(right);
        setVisibility(tv_right);
        setVisibility(share_in_title);

        share_in_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSEntity jsEntity = new JSEntity();
                jsEntity.data(new Gson().toJson(v.getTag()));
                jsEntity.progress();
                ijsCallFunction.onCall(jsEntity, url);
            }
        });


        tv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSEntity jsEntity = new JSEntity();
                jsEntity.data(new Gson().toJson(v.getTag()));
                jsEntity.progress();
                ijsCallFunction.onCall(jsEntity, url);

            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Object object = right.getTag();
                if (object == null) {
                    return;
                }
                if (object instanceof DefaultTitleBean.RightButton) {
                    DefaultTitleBean.RightButton button = (DefaultTitleBean.RightButton) object;
                    showMorePop(getActivity(), right, button.submenuInfo);
                }
            }
        });

    }

    private void setVisibility(View view) {
        if (view.getTag() == null) {
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.VISIBLE);
        }
    }


    /**
     * more右上角点击事件
     */
    @SuppressLint("InflateParams")
    private void showMorePop(Context context, View more, List<DefaultTitleBean.SubmenuChild> submenuInfo) {
        View popupView = LayoutInflater.from(context).inflate(R.layout.bl_web_pop_more, null);
        RecyclerView recyclerView = (RecyclerView) popupView.findViewById(R.id.recycler);
//        RecyclerViewInflater inflater = RecyclerViewInflater.newInstance(recyclerView).build();
//
//        GoHomeEvent event = new GoHomeEvent();
//        event.view = more;
//        EventBus.getDefault().post(event);

    }


    @Override
    protected void onSetTitle(String data) {
        tv_txt.setText(data);
    }

    @Override
    protected void initOnCreateTitle() {

        ivBack = findView(R.id.iv_back);
        right_container = findView(R.id.right_container);
        share_in_title = (ImageView) findView(R.id.share_in_title);
        tv_right = (TextView) findView(R.id.tv_right);
        right = (ImageView) findView(R.id.right);
        tv_txt = (TextView) findView(R.id.tv_txt);


        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = getActivity();
                if (activity != null) {
                    activity.onBackPressed();
                }
            }
        });

        tv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TitleBean.WidgetsBean widgetsBean = getButtonWidget(tv_right, RIGHT_BUTTON);
                if (widgetsBean == null || !widgetsBean.isClickAble()) {
                    return;
                }
                JSEntity jsEntity = new JSEntity();
                jsEntity.status = BridgeConfig.PROGRESS;
                jsEntity.data = widgetsBean.getWidgetIndex();
                mCallFunction.onCall(jsEntity, mMethodUrl);
            }
        });
    }


    @Override
    public void setInitData(TitleBean titleBean, IJSCallFunction callFunction, String url) {
        super.setInitData(titleBean, callFunction, url);

        TitleBean.WidgetsBean rightButton = getButtonWidget(tv_right, RIGHT_BUTTON);
        if (rightButton != null) {
            tv_right.setVisibility(View.VISIBLE);
            tv_right.setText(rightButton.getText());
        } else {
            tv_right.setVisibility(View.GONE);
        }
        TitleBean.WidgetsBean titleButton = getButtonWidget(tv_txt, TITLE_BUTTON);

        if (titleButton != null) {
            tv_txt.setText(titleButton.getText());
        }


    }


    @Override
    public boolean onGoBack() {
        if (mTitleList != null) {
            if (!mTitleList.empty()) {
                mTitleList.pop();
            }
            if (!mTitleList.empty()) {
                TitleBean titleBean = mTitleList.pop();
                setInitData(titleBean, mCallFunction, mMethodUrl);
                return true;
            }
        }
        tv_right.setTag(null);
        setInitData(null, mCallFunction, mMethodUrl);

        return super.onGoBack();
    }


    @Override
    protected int getLayout() {
        return R.layout.bl_web_title_common;
    }

}
