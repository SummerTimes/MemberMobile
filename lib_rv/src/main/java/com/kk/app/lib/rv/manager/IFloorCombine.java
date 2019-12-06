package com.kk.app.lib.rv.manager;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * 作者：杨松
 * 日期：2018/1/22 10:38
 */

public interface IFloorCombine {


    void onRequestInflateRecyclerView(IUI ui);


    List<Object> lastFloors();

    @NonNull
    List<Object> getFloors();


    boolean getAlreadyInsert();


    void setAlreadyInsert(boolean b);

    void infoCombineDataChanged();

    int getIndex();

}
