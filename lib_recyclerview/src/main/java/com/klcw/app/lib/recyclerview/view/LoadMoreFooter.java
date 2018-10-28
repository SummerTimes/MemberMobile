package com.klcw.app.lib.recyclerview.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.callback.IFooterCallBack;
import com.klcw.app.lib.recyclerview.R;
import com.klcw.app.util.UnitUtil;


/**
 * 加载更多布局文件
 *
 * @author yangdong
 */

public class LoadMoreFooter extends LinearLayout implements IFooterCallBack {
    private TextView tips;

    public LoadMoreFooter(Context context) {
        super(context);
        init();
    }

    public LoadMoreFooter(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public LoadMoreFooter(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.lib_recy_foot_view, this);
        tips = (TextView) findViewById(R.id.rwg_load_more);
    }

    @Override
    public void callWhenNotAutoLoadMore(XRefreshView xRefreshView) {

    }

    @Override
    public void onStateReady() {
        tips.setText(R.string.lib_recy_load_more_tips2);
    }

    @Override
    public void onStateRefreshing() {
        tips.setText(R.string.lib_recy_load_more_tips2);
    }

    @Override
    public void onReleaseToLoadMore() {
        tips.setText(R.string.lib_recy_load_more_tips);
    }

    @Override
    public void onStateFinish(boolean hidefooter) {
        if (hidefooter) {
            tips.setText(R.string.xrefreshview_footer_hint_normal);
        }
        tips.setVisibility(VISIBLE);
    }

    @Override
    public void onStateComplete() {
        tips.setVisibility(VISIBLE);
    }

    @Override
    public void show(boolean show) {

    }

    @Override
    public boolean isShowing() {
        return false;
    }

    @Override
    public int getFooterHeight() {
        return UnitUtil.dip2px(45);
    }
}
