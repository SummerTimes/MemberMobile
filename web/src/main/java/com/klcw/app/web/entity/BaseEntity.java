package com.klcw.app.web.entity;

import com.google.gson.Gson;

/**
 * 作者：杨松
 * 日期：2017/3/31 18:20
 */
public class BaseEntity {

    public transient String createCause;

    public String toJson() {
        return new Gson().toJson(this);
    }

}
