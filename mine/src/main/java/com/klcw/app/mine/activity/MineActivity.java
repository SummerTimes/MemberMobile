package com.klcw.app.mine.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;

import com.billy.android.preloader.PreLoader;
import com.billy.android.preloader.interfaces.GroupedDataListener;
import com.klcw.app.lib.recyclerview.floormanager.IFloorCombine;
import com.klcw.app.lib.recyclerview.floormanager.IUI;
import com.klcw.app.mine.R;
import com.klcw.app.mine.constant.MineConstant;
import com.klcw.app.mine.dataload.MinDataLoad;
import com.klcw.app.mine.presenter.MinePresenter;

/**
 * @author kk
 * @datetime: 2018/10/24
 * @desc:
 */
public class MineActivity extends AppCompatActivity implements IUI {

    private int mKey;
    private RecyclerView mRvView;
    private MinePresenter mMinePresenter;
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mKey = MinePresenter.preLoad(getParams());
        initPresenter();
        setContentView(R.layout.mine_main_activity);
        initView();
        initListener();
    }

    /**
     * 监听是否有数据
     */
    private void initListener() {
        PreLoader.listenData(mKey, new GroupedDataListener<String>() {
            @Override
            public String keyInGroup() {
                return MinDataLoad.MINE_LIST_KEY;
            }

            @Override
            public void onDataArrived(String str) {
                Log.e("xp", "---网络数据----" + str);
            }
        });

    }

    private void initPresenter() {
        if (null == mMinePresenter) {
            mMinePresenter = new MinePresenter(mKey);
        }
        mMinePresenter.bindActivity(this);
    }

    private void initView() {
        mRvView = findViewById(R.id.rv_view);
        mAdapter = mMinePresenter.getAdapter();
        mRvView.setLayoutManager(new LinearLayoutManager(this));
        mRvView.setAdapter(mAdapter);
        mMinePresenter.onUIReady(this);
    }

    /**
     * 获取入参
     *
     * @return
     */
    private String getParams() {
        String mParams = getIntent().getStringExtra(MineConstant.KRY_PARAM);
        if (TextUtils.isEmpty(mParams)) {
            return "";
        }
        return mParams;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        PreLoader.destroy(mKey);
    }

    @Override
    public void onCombineRequestInflateUI(IFloorCombine combine) {
        mMinePresenter.notifyDataChanged(combine);
    }
}
