package com.klcw.app.web.entity;

import android.text.TextUtils;


import java.util.List;


/**
 * 作者：杨松
 * 日期：2017/9/5 09:50
 */

public class DefaultTitleBean extends BaseEntity {


    public List<RightButton> rightButtonList;

    public List<LeftButton> leftButtonList;


    public class RightButton extends BaseEntity {

        public String buttonType;

        public String buttonTitle;

        public String fontColor;

        public String fontSize;

        public String action;

        public String isSubmenu;

        public String imageName;
        public String target;

        public List<SubmenuChild> submenuInfo;


        public boolean isImage() {
            return TextUtils.equals(buttonType, TitleConstant.Type.IMAGE);
        }

        public boolean isText() {
            return TextUtils.equals(buttonType, TitleConstant.Type.TEXT);
        }


    }


    public static class LeftButton extends BaseEntity {

        public boolean isBack;

        public String imageName;

        public String buttonType;

    }


    public interface TitleConstant {
        interface ImageName {
            String BACK = "loginback";
            String MORE = "new_more";
            String share = "new_navShare";
        }

        interface Type {
            String TEXT = "text";
            String IMAGE = "image";
        }
    }


    public static class SubmenuChild {
        public String buttonTitle;
    }


}
