package com.klcw.app.lib.recyclerview.loader;

import android.text.TextUtils;

import com.billy.android.preloader.PreLoader;
import com.billy.android.preloader.interfaces.GroupedDataListener;
import com.billy.android.preloader.interfaces.GroupedDataLoader;
import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.google.gson.Gson;
import com.klcw.app.lib.network.NetworkConfig;
import com.klcw.app.lib.recyclerview.util.PreLoaderHelper;
import com.klcw.app.util.log.Logger;

import org.json.JSONObject;

import java.lang.reflect.Type;


/**
 * 作者：杨松
 * 日期：2018/3/13 14:27
 */

public class CacheLoaderAgent<T> implements GroupedDataLoader<T> {

    private final GroupedDataLoader<T> loader;
    private int groupKey = -1;
    private Type type;
    private String key;

    private T data;

    public CacheLoaderAgent(GroupedDataLoader<T> loader, String key) {
        this(loader);
        this.key = key;
    }


    public CacheLoaderAgent bind(int groupKey) {
        this.groupKey = groupKey;
        return this;
    }

    public CacheLoaderAgent(GroupedDataLoader<T> loader) {
        this.loader = loader;
        this.type = TypeUtil.getSuperclassTypeParameter(loader.getClass(), 0);
    }

    @Override
    public String keyInGroup() {
        return loader.keyInGroup();
    }


    @Override
    public T loadData() {
        if (loader == null) {
            this.data = null;
            return null;
        }
        //说明是手动刷新
        if (data != null) {
            T result = getLocalCache("getCache");
            return visitNet(result);

        } else {
            T result = getLocalCache("getNormalCache");
            if (result != null && groupKey != -1) {
                PreLoaderHelper.listenData(groupKey, new GroupedDataListener<Object>() {
                    @Override
                    public String keyInGroup() {
                        return loader.keyInGroup();
                    }

                    @Override
                    public void onDataArrived(Object o) {
                        PreLoader.refresh(groupKey, loader.keyInGroup());
                    }
                });

            }
            return visitNet(result);
        }

    }

    private T visitNet(T result) {
        if (result == null) {
            result = visitNetAndSave();
        }
        this.data = result;
        return data;
    }

    private T visitNetAndSave() {
        T loadData = this.loader.loadData();
        if (loadData != null) {
            saveData(loadData);
        }
        return loadData;
    }


    private void saveData(Object data) {
        String saveKey = getSaveKey();

        CC.obtainBuilder("HomepageComponent").setActionName("setCache")
                .addParam("key", saveKey)
                .addParam("value", data)
                .build().callAsync();
    }

    private String getSaveKey() {
        String saveKey;
        if (TextUtils.isEmpty(key)) {
            saveKey = loader.getClass().getName();
        } else {
            saveKey = key + loader.getClass().getName();
        }
        return saveKey + NetworkConfig.getAppMwUrl();
    }

    private T getLocalCache(String type) {
        String saveKey = getSaveKey();
        CCResult ccResult = CC.obtainBuilder("HomepageComponent").setActionName(type).addParam("key", saveKey).build().call();
        JSONObject ccResultData = ccResult.getData();
        if (ccResultData == null) {
            return null;
        }
        String value = ccResultData.optString("value");
        if (!TextUtils.isEmpty(value)) {
            T result  = new Gson().fromJson(value, this.type);
            if (result != null) {
                Logger.i("缓存", "读取缓存数据 = " + value);
                return result;
            }
        }
        return null;
    }


}
