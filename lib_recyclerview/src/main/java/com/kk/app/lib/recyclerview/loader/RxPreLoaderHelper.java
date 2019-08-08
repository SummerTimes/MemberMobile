package com.kk.app.lib.recyclerview.loader;


import com.billy.android.preloader.PreLoader;
import com.billy.android.preloader.interfaces.GroupedDataListener;
import com.kk.app.lib.recyclerview.floormanager.AbstractFloorCombine;
import com.kk.app.lib.recyclerview.util.PreLoaderHelper;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * 作者：杨松
 * 日期：2018/6/28 17:17
 */

public class RxPreLoaderHelper {

    public static <T> Observable<T> mainFloorListen(final int key, final String keyInGroup, final AbstractFloorCombine combine) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(final ObservableEmitter<T> e) throws Exception {
                PreLoader.listenData(key, new GroupedDataListener<T>() {
                    @Override
                    public void onDataArrived(T data) {
                        if (data == null) {
                            combine.getFloors().clear();
                            combine.infoDataSetChanged();
                        } else {
                            e.onNext(data);
                        }
                    }

                    @Override
                    public String keyInGroup() {
                        return keyInGroup;
                    }
                });

            }
        });
    }


    public static <T> Observable<T> listenOnce(final int key, final String keyInGroup) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(final ObservableEmitter<T> e) throws Exception {

                PreLoaderHelper.listenData(key, new GroupedDataListener<T>() {
                    @Override
                    public void onDataArrived(T data) {
                        if (data == null) {
                            e.onComplete();
                        } else {
                            e.onNext(data);
                            e.onComplete();
                        }
                    }

                    @Override
                    public String keyInGroup() {
                        return keyInGroup;
                    }
                });

            }
        });
    }

}
