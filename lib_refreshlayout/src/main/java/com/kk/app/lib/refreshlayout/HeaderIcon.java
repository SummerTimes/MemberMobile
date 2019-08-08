package com.kk.app.lib.refreshlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.andview.refreshview.callback.IHeaderCallBack;
import com.kk.app.lib.refreshlayout.R;


/**
 * @Auther: yd
 * @datetime: 2018/10/23
 * @desc:
 */
public class HeaderIcon extends RelativeLayout implements IHeaderCallBack {

    ImageView iv_header;
    Animation animation;
    float curDegree = 0.0f;
    boolean isRefreshing;

    public HeaderIcon(Context context) {
        this(context,null);
    }

    public HeaderIcon(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView(){
        LayoutInflater.from(getContext()).inflate(R.layout.header_icon,this);
        iv_header = (ImageView) findViewById(R.id.iv_header);
        iv_header.setScaleType(ImageView.ScaleType.CENTER_CROP);
        LayoutParams params = (LayoutParams)iv_header.getLayoutParams();
        params.addRule(CENTER_HORIZONTAL);
        animation = new RotateAnimation(0.0f,360.0f, Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f);
        animation.setDuration(500);
        animation.setRepeatCount(-1);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        //LinearInterpolator,AccelerateInterpolator,AccelerateDecelerateInterpolator
        //AnticipateInterpolator, AnticipateOvershootInterpolator, OvershootInterpolator
        //BounceInterpolator
    }

    private Animation getPullAnimation(float degree){
        Animation animation = new RotateAnimation(curDegree,degree, Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f);
        animation.setRepeatCount(0);
        animation.setDuration(0);
        curDegree = degree;
        return animation;
    }

    @Override
    public void onStateNormal() {//正常

    }

    @Override
    public void onStateReady() {//准备刷新

    }

    @Override
    public void onStateRefreshing() {//正在刷新
        isRefreshing = true;
        iv_header.startAnimation(animation);
    }

    @Override
    public void onStateFinish(boolean success) {//完成刷新

    }

    @Override
    public void onHeaderMove(double headerMovePercent, int offsetY, int deltaY) {//垂直滑动
        if(!isRefreshing && headerMovePercent>1.0)
            iv_header.startAnimation(getPullAnimation( (float) (headerMovePercent*360) ));
        if(offsetY==0) {
            isRefreshing = false;
            iv_header.clearAnimation();
        }
    }

    @Override
    public void setRefreshTime(long lastRefreshTime) {//刷新时间

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
