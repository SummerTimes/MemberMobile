package com.bailian.weblib.bljsbridge;

/**
 * Created by kk on 2017/3/9.
 */

public class JSEntity {

    public String status;

    public String data;

    public JSEntity success() {
        this.status = BridgeConfig.SUCCESS;
        return this;
    }


    public JSEntity data(String data) {
        this.data = data;
        return this;
    }

    public JSEntity fail() {
        this.data = BridgeConfig.FAIL;
        return this;
    }


    public JSEntity progress() {
        this.status = BridgeConfig.PROGRESS;
        return this;

    }


    public final String jsNAME = "Callback";
}