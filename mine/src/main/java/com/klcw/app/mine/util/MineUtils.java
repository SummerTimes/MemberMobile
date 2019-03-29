package com.klcw.app.mine.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.billy.cc.core.component.CC;
import com.klcw.app.lib.widget.BLToast;
import com.klcw.app.mine.activity.MineActivity;
import com.klcw.app.mine.constant.MineConstant;

/**
 * @author kk
 * @datetime: 2018/10/24
 * @desc:
 */
public class MineUtils {

    /**
     * 跳转MineActivity
     *
     * @param cc 参数
     */
    public static void openMineActivity(CC cc) {
        String param = cc.getParamItem(MineConstant.KRY_PARAM);
        if (TextUtils.isEmpty(param)) {
            BLToast.showToast(cc.getContext(), "参数不能为空");
            return;
        }
        Context context = cc.getContext();
        Intent intent = new Intent(context, MineActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        intent.putExtra(MineConstant.KRY_PARAM, param);
        context.startActivity(intent);
    }

}
