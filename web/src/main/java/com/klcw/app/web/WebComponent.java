package com.klcw.app.web;

import android.text.TextUtils;
import android.util.Log;


import com.aliyun.common.global.AliyunTag;
import com.bailian.weblib.vassonic.SonicRuntimeImpl;
import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.IComponent;
import com.tencent.sonic.sdk.SonicConfig;
import com.tencent.sonic.sdk.SonicEngine;
import com.tencent.sonic.sdk.SonicSessionConfig;

import org.json.JSONObject;


/**
 * 作者：杨松
 * 日期：2017/9/20 14:43
 */

public class WebComponent implements IComponent {


    @Override
    public String getName() {
        return "WebComponent";
    }

    @Override
    public boolean onCall(CC cc) {

        switch (cc.getActionName()) {
            case "startWeb":

                JSONObject jsonObject = cc.getParamItem("param");
                Log.e("xp", "---------startWeb--------" + jsonObject.toString());
                if (jsonObject == null || jsonObject.isNull("url")) {
                    CC.invokeCallback(cc.getCallId(), CCResult.error("url地址为空"));
                    return false;
                }
                //VasSonic预先加载
                try {
                    String url = jsonObject.optString("url");
                    if (!TextUtils.isEmpty(url)) {
                        if (!SonicEngine.isGetInstanceAllowed()) {
                            SonicEngine.createInstance(new SonicRuntimeImpl(cc.getApplication()), new SonicConfig.Builder().build());
                            SonicEngine.getInstance().preCreateSession(url, new SonicSessionConfig.Builder().setSupportLocalServer(true).build());
                        }
                    }
                } catch (Exception ex) {
                }
                if (BLWebActivity.startAct(cc.getContext(), jsonObject.toString())) {
                    CC.invokeCallback(cc.getCallId(), CCResult.success(null));
                } else {
                    CC.invokeCallback(cc.getCallId(), CCResult.error("url地址为空"));
                }
                break;
            case "getRegisterFunction":
                CC.sendCCResult(cc.getCallId(), CCResult.success("managers", FunctionRegister.mFunctionManagers));
                break;
            default:
                break;
        }

        return false;
    }
}
