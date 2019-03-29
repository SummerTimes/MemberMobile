package com.klcw.app.mine;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.IComponent;
import com.klcw.app.mine.constant.MineConstant;
import com.klcw.app.mine.util.MineUtils;

/**
 * @author kk
 * @datetime: 2018/10/24
 * @desc: 组件MineComponent
 * <p>
 * 注：
 * false: 组件同步实现（onCall方法执行完之前会将执行结果CCResult发送给CC）
 * true: 组件异步实现（onCall方法执行完之后再将CCResult发送给CC，CC会持续等待组件调用CC.sendCCResult发送的结果，直至超时）
 */
public class MineComponent implements IComponent {

    @Override
    public String getName() {
        return MineConstant.KRY_MINE_COMPONENT;
    }

    @Override
    public boolean onCall(CC cc) {
        String actionName = cc.getActionName();
        switch (actionName) {
            case MineConstant.KRY_MINE_ACTIVITY_ACTION:
                MineUtils.openMineActivity(cc);
                CC.sendCCResult(cc.getCallId(), CCResult.success());
                return true;
            default:
                CC.sendCCResult(cc.getCallId(), CCResult.error("actionName not specified"));
                break;
        }
        return true;
    }
}
