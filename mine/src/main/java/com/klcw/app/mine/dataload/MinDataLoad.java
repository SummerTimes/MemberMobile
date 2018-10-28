package com.klcw.app.mine.dataload;

import com.billy.android.preloader.interfaces.GroupedDataLoader;

/**
 * @Auther: yd
 * @datetime: 2018/10/24
 * @desc:
 */
public class MinDataLoad implements GroupedDataLoader<String> {

    public static final String MINE_LIST_KEY = "MinDataLoadKey";

    public MinDataLoad(String par) {

    }

    @Override
    public String keyInGroup() {
        return MINE_LIST_KEY;
    }

    @Override
    public String loadData() {
        return "你好";
    }
}
