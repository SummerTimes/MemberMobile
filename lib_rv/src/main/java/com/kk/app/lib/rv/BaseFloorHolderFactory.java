package com.kk.app.lib.rv;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 楼层ViewHolder工厂
 *
 * @author billy.qi
 * @since 18/1/18 20:05
 */
public abstract class BaseFloorHolderFactory implements IFloorHolderFactory {

    protected View getItemView(ViewGroup parent) {
        try {
            int layoutId = getLayoutId();
            if (layoutId != 0) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                return inflater.inflate(layoutId, parent, false);
            }
        } catch (Exception ignored) {
        }
        return null;
    }
}
