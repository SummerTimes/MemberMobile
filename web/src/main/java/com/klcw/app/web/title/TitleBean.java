package com.klcw.app.web.title;


import com.klcw.app.web.entity.BaseEntity;

import java.util.List;

/**
 * 作者：杨松
 * 日期：2017/7/18 10:19
 */

public class TitleBean extends BaseEntity {

    public String type;

    private List<WidgetsBean> widgets;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<WidgetsBean> getWidgets() {
        return widgets;
    }

    public void setWidgets(List<WidgetsBean> widgets) {
        this.widgets = widgets;
    }

    public static class WidgetsBean {
        /**
         * clickAble : true
         * text : 清空
         * widgetIndex : 1
         */

        private boolean clickAble = false;
        private String text;
        private String widgetIndex;
        private String title;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isClickAble() {
            return clickAble;
        }

        public void setClickAble(boolean clickAble) {
            this.clickAble = clickAble;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getWidgetIndex() {
            return widgetIndex;
        }

        public void setWidgetIndex(String widgetIndex) {
            this.widgetIndex = widgetIndex;
        }
    }
}
