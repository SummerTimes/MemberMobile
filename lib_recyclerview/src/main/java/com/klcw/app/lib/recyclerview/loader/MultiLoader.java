package com.klcw.app.lib.recyclerview.loader;

import com.billy.android.preloader.MultiEveryInfoDataListener;
import com.billy.android.preloader.MultipleGroupDataListener;
import com.billy.android.preloader.PreLoader;
import com.billy.android.preloader.interfaces.GroupedDataListener;
import com.billy.android.preloader.interfaces.GroupedDataLoader;

import java.util.Map;

/**
 * 作者：杨松
 * 日期：2018/5/29 16:43
 */

public class MultiLoader<IN, OUT> implements GroupedDataLoader<OUT> {




    public void demo(final GroupedDataLoader loader, final GroupedDataLoader loader1){
        int preLoad = PreLoader.preLoad(loader);
        PreLoader.listenData(preLoad, new GroupedDataListener<Object>() {
            @Override
            public String keyInGroup() {
                return loader.keyInGroup();
            }

            @Override
            public void onDataArrived(Object o) {
                PreLoader.preLoad(loader1);
            }
        });

        PreLoader.listenData(preLoad, new MultipleGroupDataListener() {
            @Override
            public void onDataArrived(Map<String, Object> stringObjectMap) {
                super.onDataArrived(stringObjectMap);


            }
        },loader.keyInGroup(),loader1.keyInGroup());


    }


    private final int mKey;
    private final String[] groupKeys;

    private static final String TAG="MultiLoader";

    public MultiLoader(int key, String... groupKeys) {
        this.mKey = key;
        this.groupKeys = groupKeys;
    }


    @Override
    public String keyInGroup() {
        return TAG;
    }


    @Override
    public OUT loadData() {
        PreLoader.listenData(mKey, new MultiEveryInfoDataListener() {
            @Override
            public void onDataArrived(Map<String, Object> stringObjectMap, String key) {
                super.onDataArrived(stringObjectMap, key);





//                PreLoader.refresh(mKey, MultiLoader.this.keyInGroup());
            }
        }, groupKeys);
        return null;
    }
}
