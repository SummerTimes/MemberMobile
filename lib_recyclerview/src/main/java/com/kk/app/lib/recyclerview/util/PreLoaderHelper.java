package com.kk.app.lib.recyclerview.util;

import com.billy.android.preloader.PreLoader;
import com.billy.android.preloader.interfaces.GroupedDataListener;
import com.billy.android.preloader.interfaces.GroupedDataLoader;
import com.kk.app.lib.recyclerview.loader.CacheLoaderAgent;


/**
 * 预加载简单封装，防止多次监听
 *
 * @author menglp
 * @since 2018/2/8 15:18
 */

public class PreLoaderHelper {
    /**
     * 单次listen
     *
     * @param key      key
     * @param listener listener
     */
    public static <T> void listenData(final int key, final GroupedDataListener<T> listener) {
        GroupedDataListener<T> groupedDataListener = new GroupedDataListener<T>() {
            @Override
            public String keyInGroup() {
                return listener.keyInGroup();
            }

            @Override
            public void onDataArrived(T resourceEntity) {
                PreLoader.removeListener(key, this);
                listener.onDataArrived(resourceEntity);
            }
        };

        PreLoader.listenData(key, groupedDataListener);
    }




    public static int preLoad(GroupedDataLoader... groupedDataLoaders){
        int preLoad = PreLoader.preLoad(groupedDataLoaders);
        for (GroupedDataLoader groupedDataLoader : groupedDataLoaders) {
            if(groupedDataLoader instanceof CacheLoaderAgent){
                ((CacheLoaderAgent) groupedDataLoader).bind(preLoad);
            }
        }
        return preLoad;

    }






}
