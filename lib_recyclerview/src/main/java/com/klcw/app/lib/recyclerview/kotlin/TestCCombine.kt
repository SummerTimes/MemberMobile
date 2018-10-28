package cn.com.bailian.bailianmobile.libs.recyclerview.ui.kotlin

import com.klcw.app.lib.recyclerview.floormanager.AbstractFloorCombine
import com.klcw.app.lib.recyclerview.floormanager.IUI

/**
 * 作者：杨松
 * 日期：2018/7/23 19:07
 */
class TestCCombine(key: Int) : AbstractFloorCombine(key) {

    override fun onUIReady(ui: IUI?, alreadyInsert: Boolean) {
        for (i: Int in 1..1000) {
            val kotlinFloor = TestFloor()
            kotlinFloor.data = "哈哈哈$i"
            add(kotlinFloor)
        }
        infoDataSetChanged()
    }
}