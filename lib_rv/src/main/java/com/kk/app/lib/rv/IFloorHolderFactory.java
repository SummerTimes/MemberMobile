package com.kk.app.lib.rv;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

/**
 * 楼层工厂接口
 *
 * @author billy.qi
 * @since 18/1/18 18:01
 */
public interface IFloorHolderFactory {

    /**
     * 创建楼层ViewHolder
     *
     * @param parent 父容器
     * @return 给RecyclerView用的ViewHolder
     */
    @NonNull
    BaseFloorHolder createFloorHolder(ViewGroup parent);

    @LayoutRes
    int getLayoutId();

    /**
     * view的类型，必须唯一
     *
     * @return
     */
    int getViewType();
}
