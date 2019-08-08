package com.kk.app.lib.recyclerview.title.image;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kk.app.lib.recyclerview.BaseFloorHolderFactory;
import com.kk.app.lib.recyclerview.FloorViewType;
import com.kk.app.lib.recyclerview.BaseFloorHolder;


/**
 * @author billy.qi
 * @since 18/1/18 20:38
 */
public class FloorTitleImageHolerFactory extends BaseFloorHolderFactory {
    @Override
    public BaseFloorHolder createFloorHolder(ViewGroup parent) {
        return new FloorTitleImageHolder(getItemView(parent));
    }

    @Override
    protected View getItemView(ViewGroup parent) {
        return new ImageView(parent.getContext());
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public int getViewType() {
        return FloorViewType.VIEW_TYPE_TITLE_IMAGE;
    }
}
