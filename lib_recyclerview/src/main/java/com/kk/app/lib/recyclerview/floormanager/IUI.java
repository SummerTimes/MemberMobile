package com.kk.app.lib.recyclerview.floormanager;

/**
 * 作者：杨松
 * 日期：2018/1/22 15:11
 */

public interface IUI {

    void onCombineRequestInflateUI(IFloorCombine combine);


    void runOnUiThread(Runnable runnable);
}
