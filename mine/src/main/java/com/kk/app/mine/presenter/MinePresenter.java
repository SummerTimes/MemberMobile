package com.kk.app.mine.presenter;

import com.billy.android.preloader.PreLoader;
import com.kk.app.lib.recyclerview.floormanager.AbstractPresenter;
import com.kk.app.lib.recyclerview.floormanager.ICombinesProvider;
import com.kk.app.mine.combines.MineContainer;
import com.kk.app.mine.dataload.MinDataLoad;

/**
 * @author kk
 * @datetime: 2018/10/24
 * @desc:
 */
public class MinePresenter extends AbstractPresenter {


    public MinePresenter(int key) {
        super(key);
    }

    /**
     * 加载数据
     *
     * @param par
     * @return
     */
    public static int preLoad(String par) {
        return PreLoader.preLoad(new MinDataLoad(par));
    }


    @Override
    public void visitNet(boolean isPullDown) {
        PreLoader.refresh(mKey);
    }

    @Override
    protected ICombinesProvider provideCombinesProvider() {
        return new MineContainer();
    }
}
