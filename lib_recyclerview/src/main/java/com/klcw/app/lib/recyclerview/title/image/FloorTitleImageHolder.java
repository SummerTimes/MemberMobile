package com.klcw.app.lib.recyclerview.title.image;

import android.view.View;
import android.widget.ImageView;

import com.klcw.app.lib.recyclerview.BaseFloorHolder;
import com.klcw.app.lib.recyclerview.Floor;
import com.klcw.app.lib.recyclerview.title.text.TitleTextUnit;


/**
 * 标题的ViewHolder
 * @author billy.qi
 * @since 18/1/18 20:40
 */
public class FloorTitleImageHolder extends BaseFloorHolder<Floor<TitleTextUnit>> {

    private ImageView imageView;

    public FloorTitleImageHolder(View itemView) {
        super(itemView);
        this.imageView = (ImageView)itemView;
    }

    @Override
    public void bind(Floor<TitleTextUnit> data) {
        if (data != null) {
            //显示图片
        }
    }
}
