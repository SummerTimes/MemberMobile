package com.klcw.app.recommend

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import com.klcw.app.lib.recyclerview.floormanager.IFloorCombine
import com.klcw.app.lib.recyclerview.floormanager.IUI
import com.klcw.app.lib.widget.RobustLinearLayoutManager

/**
 * @Auther: yd
 * @datetime: 2018/10/24
 * @desc:
 */

class  TestActivity:Activity(), IUI {

    private lateinit  var testPresenter: TestPresenter

    override fun onCombineRequestInflateUI(combine: IFloorCombine?) {
        testPresenter.notifyDataChanged(combine)
    }


    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layoutManager = RobustLinearLayoutManager(this)
        testPresenter = TestPresenter(1)
        setContentView(R.layout.activity_test)
        val recyclerView = findViewById<RecyclerView>(R.id.container)
        recyclerView.adapter = testPresenter.adapter
        recyclerView.layoutManager = layoutManager
        testPresenter.onUIReady(this)
    }


}