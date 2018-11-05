package com.klcw.app.login.bean;

import java.util.List;

/**
 * @Auther: yd
 * @datetime: 2018/11/5
 * @desc:
 */
public class CommonList {

    public List<CommonBean> data;
    public int errorCode;
    public String errorMsg;

    @Override
    public String toString() {
        return "CommonList{" +
                "data=" + data +
                ", errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
