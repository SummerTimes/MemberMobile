package com.kk.app.product.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.billy.cc.core.component.CC;
import com.kk.app.lib.widget.BLToast;
import com.kk.app.product.activity.ProductMainActivity;
import com.kk.app.product.constant.ProductConstant;

/**
 * @author kk
 * @datetime: 2018/10/24
 * @desc:
 */
public class ProductUtil {

    /**
     * 跳转Product/Activity
     *
     * @param cc 参数
     */
    public static void openProductActivity(CC cc) {

        String param = cc.getParamItem(ProductConstant.KRY_PARAM);
        if (TextUtils.isEmpty(param)) {
            BLToast.showToast(cc.getContext(), "参数不能为空");
            return;
        }
        Context context = cc.getContext();
        Intent intent = new Intent(context, ProductMainActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        intent.putExtra(ProductConstant.KRY_PARAM, param);
        context.startActivity(intent);
    }

}
