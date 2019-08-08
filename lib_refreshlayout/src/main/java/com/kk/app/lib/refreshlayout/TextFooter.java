package com.kk.app.lib.refreshlayout;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.callback.IFooterCallBack;


/**
 * @Auther: yd
 * @datetime: 2018/10/23
 * @desc:
 */
public class TextFooter extends RelativeLayout implements IFooterCallBack {

    private String[] Txt = {"加载更多","释放刷新","正在载入..."};
    private String[] TxtResult = {"载入成功","载入失败"};
    private TextView textView;

    public TextFooter(Context context) {
        this(context,null);
    }

    public TextFooter(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TextFooter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    protected void init(){
        setGravity(Gravity.CENTER_HORIZONTAL);
        int padding = dp2px(15f);
        textView = new TextView(getContext());
        textView.setPadding(padding,padding,padding,padding);
        textView.setTextColor(Color.parseColor("#333333"));
        textView.setTextSize(12f);
        textView.setGravity(Gravity.CENTER);
        textView.setSingleLine(true);
        LayoutParams params = new LayoutParams(-2,-2);
        textView.setLayoutParams(params);
        addView(textView);
    }

    static int dp2px(float dp){
        return (int)(dp*Resources.getSystem().getDisplayMetrics().density+0.5f);
    }

    public void setTxt(String normalTxt,String readyTxt,String refreshTxt){
        Txt = new String[]{normalTxt,readyTxt,refreshTxt};
    }

    public void setTxtResult(String success,String fail){
        TxtResult = new String[]{success,fail};
    }

    @Override
    public void callWhenNotAutoLoadMore(XRefreshView xRefreshView) {

    }

    @Override
    public void onStateReady() {

    }

    @Override
    public void onStateRefreshing() {//下拉
        textView.setText(Txt[2]);
    }

    @Override
    public void onReleaseToLoadMore() {
        textView.setText(Txt[1]);
    }

    @Override
    public void onStateFinish(boolean hidefooter) {//加载结束，true成功
        textView.setText(TxtResult[hidefooter ? 0 :1]);
    }

    @Override
    public void onStateComplete() {

    }

    @Override
    public void show(boolean show) {
        setVisibility(show?VISIBLE:GONE);
    }

    @Override
    public boolean isShowing() {
        return getVisibility()==VISIBLE;
    }

    @Override
    public int getFooterHeight() {
        return getMeasuredHeight();
    }
}
