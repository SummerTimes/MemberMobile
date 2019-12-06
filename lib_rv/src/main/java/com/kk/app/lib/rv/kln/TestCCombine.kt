import com.kk.app.lib.rv.manager.AbstractFloorCombine
import com.kk.app.lib.rv.manager.IUI
import com.kk.app.lib.rv.kln.TestFloor

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