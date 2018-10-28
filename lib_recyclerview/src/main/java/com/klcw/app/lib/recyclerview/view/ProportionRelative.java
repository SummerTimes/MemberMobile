package com.klcw.app.lib.recyclerview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.klcw.app.lib.recyclerview.R;


/**
 * 作者：杨松
 * 日期：2018/2/2 17:00
 * 宽高比固定的
 */

public class ProportionRelative extends RelativeLayout {


    private float mProportion;

    public ProportionRelative(Context context, AttributeSet attrs) {
        super(context, attrs);

        if (attrs == null) {
            return;
        }
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ProportionSimpleDrawView);

        float proportion = typedArray.getFloat(R.styleable.ProportionSimpleDrawView_proportion, -1);

        if (proportion != -1) {
            this.mProportion = proportion;
        }
        typedArray.recycle();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, (int) (widthMeasureSpec * mProportion));
    }
}
