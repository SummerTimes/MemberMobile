package com.kk.app.lib.refreshlayout;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andview.refreshview.callback.IHeaderCallBack;
import com.kk.app.lib.refreshlayout.R;

import java.lang.ref.WeakReference;

/**
 * @Auther: yd
 * @datetime: 2018/10/23
 * @desc:
 */

public class HeaderPullAnim extends RelativeLayout implements IHeaderCallBack {
    private static final int START = 0x7887;
    private static final long SMillis = 750;
    private int sWidth = 25;
    private int sHeight = 38;
    private int sTop = 10;
    private int sHeaderHeight = 75;

    private static float sDensity;
    private HeaderContent mBinding;
    private HeaderState mState;

    private AnimationDrawable mDrawable;
    private AnimationDrawable nDrawable;
    private boolean canRefresh = false;//播放Wi-Fi
    private boolean needRefresh = false;//播放轮廓
    private boolean isRefreshing = false;//正在刷新
    private boolean isReady = false;//readyIcon

    private int[] aUrl;
    private String[] aTxt = {"释放开始刷新","正在刷新"};
    private Handler mHandler;

    private class HeaderContent{
        ImageView ivIcon;
        TextView tvContent;
    }

    public HeaderPullAnim(Context context) {
        this(context,null);
    }

    public HeaderPullAnim(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HeaderPullAnim(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private int dp2px(int dp){
        return  (int)(sDensity*dp+0.5);
    }

    private void init() {
        sDensity = getResources().getDisplayMetrics().density;
        sWidth = dp2px(sWidth);
        sHeight = dp2px(sHeight);
        sTop = dp2px(sTop);
        sHeaderHeight = dp2px(sHeaderHeight);
        inflate(getContext(), R.layout.header_pull_anim,this);
        mBinding = new HeaderContent();
        mBinding.ivIcon = getView(R.id.iv_icon);
        mBinding.tvContent = getView(R.id.tv_content);
        aUrl = new int[]{R.mipmap.pull_icon_start, R.drawable.anim_pull_icon,R.drawable.anim_pull_icon_wifi};
        mHandler = new HeaderPullHandler(this);
        initIcon();
    }

    private <T extends View> T getView(@IdRes int id){
        return (T)findViewById(id);
    }

    @Override
    public void onStateNormal() {
        isReady = false;
        mBinding.ivIcon.setImageResource(aUrl[0]);
    }

    @Override
    public void onStateReady() {
        readyIcon();
    }

    @Override
    public void onStateRefreshing() {
        if(!isReady){
            readyIcon();
        }
        isRefreshing = true;
        mBinding.tvContent.setText(aTxt[1]);
        if(!canRefresh){
            needRefresh = true;
        }else {
            start(true);
        }
    }

    @Override
    public void onStateFinish(boolean success) {
        isRefreshing = false;
        isReady = false;
        stop(false);
        stop(true);
    }

    @Override
    public void onHeaderMove(double headerMovePercent, int offsetY, int deltaY) {
        if(isRefreshing)
            return;
        int dy = Math.abs(offsetY);
        if(0 == dy){
            initIcon();
        }else if(dy < sHeaderHeight){
            changeIcon(dy);
        }
    }

    private void stop(boolean isWifi){//释放资源
        if(isWifi){
            if(nDrawable!=null && nDrawable.isRunning()){
                nDrawable.stop();
                nDrawable = null;
            }
        }else {
            if (mDrawable != null && mDrawable.isRunning()) {
                mDrawable.stop();
                mDrawable = null;
            }
        }
    }

    private void start(boolean isWifi){
        mBinding.ivIcon.setImageResource(aUrl[isWifi?2:1]);
        if(isWifi){
            nDrawable = (AnimationDrawable) mBinding.ivIcon.getDrawable();
            nDrawable.start();
            stop(false);
        }else {
            mDrawable = (AnimationDrawable) mBinding.ivIcon.getDrawable();
            mDrawable.start();
            stop(true);
        }
    }

    private void readyIcon(){
        isReady = true;
        int topMargin = sTop;
        setLayoutParam(sWidth,sHeight,topMargin);
        mBinding.tvContent.setText(aTxt[0]);
        mBinding.tvContent.setVisibility(VISIBLE);
        start(false);
        canRefresh = false;
        sendMsg();
    }

    private void initIcon(){
        setLayoutParam(0,0,sHeaderHeight-sTop);
    }

    private void changeIcon(int dy){
        if(mBinding.tvContent.getVisibility() == VISIBLE){
            mBinding.tvContent.setVisibility(GONE);
        }
        int height = dy-dp2px(2);
        height = height > sHeight ? sHeight : height;
        int width = height*sWidth/sHeight;
        int topMargin = sHeaderHeight-(dy+height)/2 ;
        setLayoutParam(width,height,topMargin);
    }

    private void setLayoutParam(int width,int height,int topMargin){
        LayoutParams params = (LayoutParams)mBinding.ivIcon.getLayoutParams();
        params.height = height;
        params.width = width;
        params.topMargin = topMargin;
        mBinding.ivIcon.setLayoutParams(params);
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
        return sHeaderHeight;
    }

    private void sendMsg(){
        mHandler.sendEmptyMessageDelayed(START,SMillis);
    }

    public void addHeaderState(HeaderState state){
        if(canRefresh) {
            state.onFinish();
        }else{
            mState = state;
        }
    }

    private static class HeaderPullHandler extends Handler{

        private WeakReference<HeaderPullAnim> mRef;

        private HeaderPullHandler(HeaderPullAnim header) {
            mRef = new WeakReference<>(header);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case START:
                    HeaderPullAnim header = mRef!=null ? mRef.get() : null;
                    if(header!=null){
                        if(header.needRefresh){
                            header.needRefresh = false;
                            header.start(true);
                        }
                        header.canRefresh = true;
                        if(header.mState!=null){
                            header.mState.onFinish();
                            header.mState = null;
                        }
                    }
                    break;
            }
        }
    }
}