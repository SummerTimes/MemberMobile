package com.kk.app.lore.activity

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.kk.app.lib.recyclerview.manager.IFloorCombine
import com.kk.app.lib.recyclerview.manager.IUI
import com.kk.app.lore.R
import com.kk.app.lore.presenter.TestPresenter

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
        testPresenter = TestPresenter(1)
        setContentView(R.layout.activity_test)
        val recyclerView = findViewById<RecyclerView>(R.id.container)
        recyclerView.adapter = testPresenter.adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        testPresenter.onUIReady(this)
    }
}