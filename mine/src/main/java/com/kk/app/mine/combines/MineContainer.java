package com.kk.app.mine.combines;


import com.kk.app.lib.recyclerview.floormanager.ICombinesProvider;
import com.kk.app.lib.recyclerview.floormanager.IFloorCombine;
import com.kk.app.lib.recyclerview.floormanager.TestCombine;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kk
 * @datetime: 2018/10/24
 * @desc:
 */
public class MineContainer implements ICombinesProvider {

    @Override
    public List<IFloorCombine> createCombines(int key) {
        List<IFloorCombine> iFloorCombines = new ArrayList<>();
        iFloorCombines.add(new TestCombine(key));
        return iFloorCombines;
    }
}
