package com.klcw.app.login.util;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.klcw.app.banner.loader.ImageLoader;

/**
 * @author kk
 * @datetime: 2018/11/14
 * @desc:设置加载的ImageView
 */
public class LoginImageLoader extends ImageLoader {

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        if (path == null || !(path instanceof String)) {
            return;
        }
        Uri uri = Uri.parse((String) path);
        imageView.setImageURI(uri);
    }

    /**
     * 提供createImageView 方法，方便fresco自定义ImageView
     *
     * @param context
     * @return
     */
    @Override
    public ImageView createImageView(Context context) {
        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(context);
        GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(context.getResources());
        simpleDraweeView.setHierarchy(builder.build());
        return simpleDraweeView;
    }
}