package com.klcw.app.lib.recyclerview.title.image;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.klcw.app.lib.recyclerview.BaseFloorHolder;
import com.klcw.app.lib.recyclerview.BaseFloorHolderFactory;
import com.klcw.app.lib.recyclerview.FloorViewType;


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
