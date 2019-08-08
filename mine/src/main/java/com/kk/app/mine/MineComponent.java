package com.kk.app.mine;

import android.text.TextUtils;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.IComponent;
import com.kk.app.mine.constant.MineConstant;
import com.kk.app.mine.fragment.MineFragment;
import com.kk.app.mine.util.MineUtils;

/**
 * @author kk
 * @datetime: 2018/10/24
 * @desc: Mine/Component
 */
public class MineComponent implements IComponent {

    @Override
    public String getName() {
        return MineConstant.KRY_MINE_COMPONENT;
    }

    @Override
    public boolean onCall(CC cc) {
        String actionName = cc.getActionName();
        // 我的/Activity
        if (TextUtils.equals(MineConstant.KRY_MINE_ACTIVITY, actionName)) {
            MineUtils.openMineActivity(cc);
            CC.sendCCResult(cc.getCallId(), CCResult.success());
        }
        // 我的/Fragment
        else if (TextUtils.equals(MineConstant.KRY_MINE_FRAGMENT, actionName)) {
            CC.sendCCResult(cc.getCallId(), CCResult.success("fragment", MineFragment.newInstance("我的")));
            return true;
        } else {
            CC.sendCCResult(cc.getCallId(), CCResult.error("actionName not specified"));
        }
        return false;
    }
}
