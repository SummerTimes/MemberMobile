package com.klcw.app.lib.recyclerview.title.text;

import android.view.View;
import android.widget.TextView;

import com.klcw.app.lib.recyclerview.BaseFloorHolder;
import com.klcw.app.lib.recyclerview.Floor;


/**
 * 纯文字的title标题的ViewHolder
 *
 * @author billy.qi
 * @since 18/1/18 20:40
 */
public class FloorTitleTextHolder extends BaseFloorHolder<Floor<TitleTextUnit>> {

    private TextView editText;

    public FloorTitleTextHolder(View itemView) {
        super(itemView);
        this.editText = (TextView) itemView;
    }

    @Override
    public void bind(Floor<TitleTextUnit> data) {
        if (data != null && data.getData() != null) {
            editText.setText(data.getData().title);
        }
    }
}
