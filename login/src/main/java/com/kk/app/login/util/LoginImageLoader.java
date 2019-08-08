package com.kk.app.login.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.kk.app.banner.loader.ImageLoader;
import com.kk.app.login.R;

/**
 * @author kk
 * @datetime: 2018/11/14
 * @desc:设置加载的ImageView
 */
public class LoginImageLoader extends ImageLoader {

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        if (!(path instanceof String)) {
            return;
        }
        Glide.with(context).load((String) path)
                .placeholder(R.color.lg_F7F7F7)
                .error(R.color.lg_F7F7F7)
                .into(imageView);
    }
}