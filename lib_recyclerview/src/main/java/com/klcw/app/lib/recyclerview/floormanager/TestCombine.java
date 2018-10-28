package com.klcw.app.lib.recyclerview.floormanager;

import com.klcw.app.lib.recyclerview.Floor;
import com.klcw.app.lib.recyclerview.FloorViewType;
import com.klcw.app.lib.recyclerview.title.text.TitleTextUnit;


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
