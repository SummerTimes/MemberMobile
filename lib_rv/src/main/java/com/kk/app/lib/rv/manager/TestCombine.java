package com.kk.app.lib.rv.manager;

import com.kk.app.lib.rv.Floor;
import com.kk.app.lib.rv.FloorViewType;
import com.kk.app.lib.rv.title.TitleTextUnit;


/**
 * 作者：杨松
 * 日期：2018/1/22 16:40
 */

public class TestCombine extends AbstractFloorCombine {

    public TestCombine(int key) {
        super(key);
    }

    @Override
    protected void onUIReady(IUI ui, boolean alreadyInsert) {
        getFloors().clear();
        TitleTextUnit titleTextUnit = new TitleTextUnit();
        titleTextUnit.title = "测试1111";
        Floor<TitleTextUnit> titleTextUnitFloor = Floor.buildFloor(FloorViewType.VIEW_TYPE_TITLE_TEXT,
                titleTextUnit);
        add(titleTextUnitFloor);
        add(titleTextUnitFloor);
        add(titleTextUnitFloor);
        add(titleTextUnitFloor);
        add(titleTextUnitFloor);
        add(titleTextUnitFloor);
        add(titleTextUnitFloor);
        add(titleTextUnitFloor);
        add(titleTextUnitFloor);
        add(titleTextUnitFloor);
        add(titleTextUnitFloor);
        add(titleTextUnitFloor);
        add(titleTextUnitFloor);
        add(titleTextUnitFloor);
        add(titleTextUnitFloor);
        add(titleTextUnitFloor);
        add(titleTextUnitFloor);
        add(titleTextUnitFloor);
        add(titleTextUnitFloor);
        add(titleTextUnitFloor);
        add(titleTextUnitFloor);
        add(titleTextUnitFloor);
        add(titleTextUnitFloor);
        add(titleTextUnitFloor);
        add(titleTextUnitFloor);
        add(titleTextUnitFloor);
        add(titleTextUnitFloor);
        ui.onCombineRequestInflateUI(this);
    }
}
