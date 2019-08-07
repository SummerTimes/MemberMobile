package com.klcw.app.product;

import android.text.TextUtils;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.IComponent;
import com.klcw.app.product.constant.ProductConstant;
import com.klcw.app.product.fragment.ProductFragment;
import com.klcw.app.product.util.ProductUtil;

/**
 * @author kk
 * @datetime: 2018/10/24
 * @desc: Mine/Component
 */
public class ProductComponent implements IComponent {

    @Override
    public String getName() {
        return ProductConstant.KRY_PRODUCT_COMPONENT;
    }

    @Override
    public boolean onCall(CC cc) {
        String actionName = cc.getActionName();

        // 体系/Activity
        if (TextUtils.equals(ProductConstant.KRY_PRODUCT_ACTIVITY, actionName)) {
            ProductUtil.openProductActivity(cc);
            CC.sendCCResult(cc.getCallId(), CCResult.success());
        }
        // 体系/Fragment
        else if (TextUtils.equals(ProductConstant.KRY_PRODUCT_FRAGMENT, actionName)) {
            CC.sendCCResult(cc.getCallId(), CCResult.success("fragment", ProductFragment.newInstance("体系")));
            return true;
        } else {
            CC.sendCCResult(cc.getCallId(), CCResult.error("actionName not specified"));
        }
        return false;
    }
}
