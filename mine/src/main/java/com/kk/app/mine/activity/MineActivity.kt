package com.kk.app.mine.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import com.billy.android.preloader.PreLoader
import com.kk.app.lib.rv.manager.IFloorCombine
import com.kk.app.lib.rv.manager.IUI
import com.kk.app.mine.R
import com.kk.app.mine.constant.MineConstant
import com.kk.app.mine.presenter.MinePresenter

/**
 * @author kk
 * @datetime: 2018/10/24
 * @desc:
 */
class MineActivity : AppCompatActivity(), IUI {

    private var mKey = 0
    private lateinit var mMinePresenter: MinePresenter
    private lateinit var mAdapter: RecyclerView.Adapter<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mKey = MinePresenter.preLoad(params)
        mMinePresenter = MinePresenter(mKey)
        setContentView(R.layout.mine_main_activity)
        initView()
    }

    private fun initView() {
        val mRvView = findViewById<RecyclerView>(R.id.rv_view)
        mAdapter = mMinePresenter!!.adapter
        mRvView.layoutManager = LinearLayoutManager(this)
        mRvView.adapter = mMinePresenter.adapter
        mMinePresenter!!.onUIReady(this)
    }

    /**
     * 获取入参
     *
     * @return
     */
    private val params: String
        private get() {
            val mParams = intent.getStringExtra(MineConstant.KRY_PARAM)
            return if (TextUtils.isEmpty(mParams)) {
                ""
            } else mParams
        }

    public override fun onDestroy() {
        super.onDestroy()
        PreLoader.destroy(mKey)
    }

    override fun onCombineRequestInflateUI(combine: IFloorCombine) {
        mMinePresenter!!.notifyDataChanged(combine)
    }
}