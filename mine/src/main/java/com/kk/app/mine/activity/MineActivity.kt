package com.kk.app.mine.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.util.Log
import com.billy.android.preloader.PreLoader
import com.billy.android.preloader.interfaces.GroupedDataListener
import com.kk.app.lib.recyclerview.manager.IFloorCombine
import com.kk.app.lib.recyclerview.manager.IUI
import com.kk.app.mine.R
import com.kk.app.mine.constant.MineConstant
import com.kk.app.mine.dataload.MinDataLoad
import com.kk.app.mine.presenter.MinePresenter

/**
 * @author kk
 * @datetime: 2018/10/24
 * @desc:
 */
class MineActivity : AppCompatActivity(), IUI {
    private var mKey = 0
    private var mRvView: RecyclerView? = null
    private var mMinePresenter: MinePresenter? = null
    private var mAdapter: RecyclerView.Adapter<*>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mKey = MinePresenter.Companion.preLoad(params)
        initPresenter()
        setContentView(R.layout.mine_main_activity)
        initView()
        initListener()
    }

    /**
     * 监听是否有数据
     */
    private fun initListener() {
        PreLoader.listenData<String>(mKey, object : GroupedDataListener<String?> {
            override fun keyInGroup(): String {
                return MinDataLoad.Companion.MINE_LIST_KEY
            }

            override fun onDataArrived(str: String) {
                Log.e("xp", "---网络数据----$str")
            }
        })
    }

    private fun initPresenter() {
        if (null == mMinePresenter) {
            mMinePresenter = MinePresenter(mKey)
        }
        mMinePresenter!!.bindActivity(this)
    }

    private fun initView() {
        mRvView = findViewById(R.id.rv_view)
        mAdapter = mMinePresenter!!.adapter
        mRvView.setLayoutManager(LinearLayoutManager(this))
        mRvView.setAdapter(mAdapter)
        mMinePresenter!!.onUIReady(this)
    }

    /**
     * 获取入参
     *
     * @return
     */
    private val params: String
        private get() {
            val mParams = intent.getStringExtra(MineConstant.Companion.KRY_PARAM)
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