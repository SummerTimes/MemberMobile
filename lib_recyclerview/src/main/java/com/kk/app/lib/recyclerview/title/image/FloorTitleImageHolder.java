package com.kk.app.lib.recyclerview.title.image;

import android.view.View;
import android.widget.ImageView;

import com.kk.app.lib.recyclerview.Floor;
import com.kk.app.lib.recyclerview.title.text.TitleTextUnit;
import com.kk.app.lib.recyclerview.BaseFloorHolder;


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
