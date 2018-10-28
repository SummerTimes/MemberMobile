package com.klcw.app.lib.recyclerview.title.text;

import android.view.ViewGroup;

import com.klcw.app.lib.recyclerview.BaseFloorHolder;
import com.klcw.app.lib.recyclerview.BaseFloorHolderFactory;
import com.klcw.app.lib.recyclerview.FloorViewType;
import com.klcw.app.lib.recyclerview.R;


/**
 * 纯文字的title
 * @author billy.qi
 * @since 18/1/18 20:38
 */
public class FloorTitleTextHolerFactory extends BaseFloorHolderFactory {
    @Override
    public BaseFloorHolder createFloorHolder(ViewGroup parent) {
        return new FloorTitleTextHolder(getItemView(parent));
    }

    @Override
    public int getLayoutId() {
        return R.layout.floor_title_text;
    }

    @Override
    public int getViewType() {
        return FloorViewType.VIEW_TYPE_TITLE_TEXT;
    }
}
