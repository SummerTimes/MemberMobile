package com.klcw.app.lib.recyclerview;

/**
 * UI楼层的viewType清单，每种楼层UI控件的ViewType固定，并且唯一
 *
 * @author billy.qi
 * @since 18/1/18 18:56
 */
public interface FloorViewType {

    /**
     * 分组标题：文字型
     */
    int VIEW_TYPE_TITLE_TEXT = 1;
    /**
     * 分组标题：图片型
     */
    int VIEW_TYPE_TITLE_IMAGE = 2;

}
