package com.klcw.app.lib.refreshlayout;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andview.refreshview.callback.IHeaderCallBack;


/**
 * @Auther: yd
 * @datetime: 2018/10/23
 * @desc:
 */
public class TextHeader extends RelativeLayout implements IHeaderCallBack {

    private String[] Txt = {"下拉刷新","释放刷新","正在载入..."};
    private String[] TxtResult = {"载入成功","载入失败"};
    private TextView textView;

    public TextHeader(Context context) {
        this(context,null);
    }

    public TextHeader(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TextHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    protected void init(){
        setGravity(Gravity.CENTER_HORIZONTAL);
        int padding = TextFooter.dp2px(15f);
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

    public void setTxt(String normalTxt,String readyTxt,String refreshTxt){
        Txt = new String[]{normalTxt,readyTxt,refreshTxt};
    }

    public void setTxtResult(String success,String fail){
        TxtResult = new String[]{success,fail};
    }

    @Override
    public void onStateNormal() {
        textView.setText(Txt[0]);
    }

    @Override
    public void onStateReady() {
        textView.setText(Txt[1]);
    }

    @Override
    public void onStateRefreshing() {
        textView.setText(Txt[2]);
    }

    @Override
    public void onStateFinish(boolean success) {
        textView.setText(success?TxtResult[0]:TxtResult[1]);
    }

    @Override
    public void onHeaderMove(double headerMovePercent, int offsetY, int deltaY) {

    }

    @Override
    public void setRefreshTime(long lastRefreshTime) {

    }

    @Override
    public void hide() {
        setVisibility(GONE);
    }

    @Override
    public void show() {
        setVisibility(VISIBLE);
    }

    @Override
    public int getHeaderHeight() {
        return getMeasuredHeight();
    }
}
