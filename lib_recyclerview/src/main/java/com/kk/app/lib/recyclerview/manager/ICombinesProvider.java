package com.kk.app.lib.recyclerview.manager;

import java.util.List;

/**
 * 作者：杨松
 * 日期：2018/1/24 10:42
 */

public interface ICombinesProvider {

    List<IFloorCombine> createCombines(int key);

}
