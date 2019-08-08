package com.kk.app.mobile.kit;

import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.kk.app.mobile.R;

import org.json.JSONObject;

import java.lang.ref.WeakReference;

/**
 * @author kk
 * @datetime: 2018/10/29
 * @desc:底部Kit
 */
public class AppPageKit implements View.OnClickListener {

    private final LinearLayout mLlLore;
    private final ImageView mImLore;
    private final TextView mTvLore;

    private final LinearLayout mLlProduct;
    private final ImageView mImProduct;
    private final TextView mTvProduct;

    private final LinearLayout mLlMine;
    private final ImageView mImMine;
    private final TextView mTvMine;

    private String currentTag;
    private TextView[] mTextViews;
    private ImageView[] mImageViews;
    private WeakReference<FragmentActivity> mActivity;
    public static final String[] sPages = {"lore", "product", "mine"};

    /**
     * 初始化View
     *
     * @param activity
     */
    public AppPageKit(FragmentActivity activity) {
        mActivity = new WeakReference<>(activity);

        mLlLore = getView(R.id.ll_lore);
        mImLore = getView(R.id.im_lore);
        mTvLore = getView(R.id.tv_lore);
        mLlLore.setOnClickListener(this);

        mLlProduct = getView(R.id.ll_product);
        mImProduct = getView(R.id.im_product);
        mTvProduct = getView(R.id.tv_product);
        mLlProduct.setOnClickListener(this);

        mLlMine = getView(R.id.ll_mine);
        mImMine = getView(R.id.im_mine);
        mTvMine = getView(R.id.tv_mine);
        mLlMine.setOnClickListener(this);

        mTextViews = new TextView[]{mTvLore, mTvProduct, mTvMine};
        mImageViews = new ImageView[]{mImLore, mImProduct, mImMine};
        switchPage(sPages[0]);
    }

    /**
     * Activity onResume
     */
    public void onResume() {

    }

    /**
     * 页面切换 Fragment
     *
     * @param tag
     */
    public void switchPage(String tag) {
        if (TextUtils.equals(tag, currentTag)) {
            return;
        }
        updateTextByTag(tag);
        Fragment fragment = getTagFragment(tag);
        if (fragment == null) {
            createTagFragment(tag);
        } else {
            showFragment(fragment);
        }
        hideTagElseFragment(tag);
    }

    /**
     * 根据Tag 更新文字的颜色值
     *
     * @param tag
     */
    private void updateTextByTag(String tag) {
        currentTag = tag;
        int tagIndex = getTagIndex(tag);
        if (tagIndex < 0 || null == mTextViews || null == mImageViews) {
            return;
        }
        // 设置选中的颜色
        for (int i = 0, size = mTextViews.length; i < size; i++) {
            if (tagIndex == i) {
                setTextViewColor(mTextViews[i], true);
                mTextViews[tagIndex].setSelected(true);
            } else {
                setTextViewColor(mTextViews[i], false);
                if (mTextViews[i].isSelected()) {
                    mTextViews[i].setSelected(false);
                }
            }
        }
        // 设置选中的图片
        for (int i = 0, size = mImageViews.length; i < size; i++) {
            if (tagIndex == i) {
                setImageView(mImageViews[i], true);
                mImageViews[tagIndex].setSelected(true);
            } else {
                setImageView(mImageViews[i], false);
                if (mImageViews[i].isSelected()) {
                    mImageViews[i].setSelected(false);
                }
            }
        }
    }

    /**
     * 设置颜色值
     *
     * @param textView
     * @param tag
     */
    private void setTextViewColor(TextView textView, boolean tag) {
        if (tag) {
            textView.setTextColor(ContextCompat.getColor(mActivity.get(), R.color.app_000000));
        } else {
            textView.setTextColor(ContextCompat.getColor(mActivity.get(), R.color.app_999999));
        }
    }

    /**
     * 设置图片
     *
     * @param imageView
     * @param tag
     */
    private void setImageView(ImageView imageView, boolean tag) {
        // 博客
        if (imageView.getId() == R.id.im_lore) {
            setImageViewColor(imageView, tag, R.mipmap.app_lore, R.mipmap.app_select_lore);
        }
        // 体系
        else if (imageView.getId() == R.id.im_product) {
            setImageViewColor(imageView, tag, R.mipmap.app_product, R.mipmap.app_select_product);
        }
        // 我的
        else if (imageView.getId() == R.id.im_mine) {
            setImageViewColor(imageView, tag, R.mipmap.app_mine, R.mipmap.app_select_mine);
        }
    }

    /**
     * 设置/图片
     *
     * @param imageView
     * @param tag
     * @param unSelectId
     * @param selectId
     */
    private void setImageViewColor(ImageView imageView, boolean tag, @DrawableRes int unSelectId, @DrawableRes int selectId) {
        if (tag) {
            imageView.setImageDrawable(ContextCompat.getDrawable(mActivity.get(), selectId));
        } else {
            imageView.setImageDrawable(ContextCompat.getDrawable(mActivity.get(), unSelectId));
        }
    }

    /**
     * on Intent
     *
     * @param params
     */
    public void onIntentAction(String params) {
        if (!TextUtils.isEmpty(params) && !"null".equals(params)) {
            try {
                JSONObject jsonObject = new JSONObject(params);
                String action = jsonObject.optString("action");
                //过滤非法action
                if (getTagIndex(action) >= 0) {
                    switchPage(action);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        int index = -1;
        if (R.id.ll_lore == id) {
            index = 0;
        } else if (R.id.ll_product == id) {
            index = 1;
        } else if (R.id.ll_mine == id) {
            index = 2;
        }
        if (index >= 0) {
            switchPage(sPages[index]);
        }
    }


    /**
     * 根据Tag获取 int类型下标
     *
     * @param tag
     * @return
     */
    private int getTagIndex(String tag) {
        int index = -1;
        for (int i = 0, size = sPages.length; i < size; i++) {
            if (TextUtils.equals(sPages[i], tag)) {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * 创建主页fragment
     *
     * @param tag
     * @return
     */
    private Fragment createTagFragment(String tag) {
        Fragment fragment = null;
        if (TextUtils.equals(tag, sPages[0])) {
            fragment = getComponentFragment("loreComponent", "loreFragment", "fragment");
        } else if (TextUtils.equals(tag, sPages[1])) {
            fragment = getComponentFragment("productComponent", "productFragment", "fragment");
        } else if (TextUtils.equals(tag, sPages[2])) {
            fragment = getComponentFragment("mineComponent", "mineFragment", "fragment");
        }
        if (fragment != null) {
            addTagFragment(fragment, tag);
        }
        return fragment;
    }


    /**
     * 获取对应的fragment
     *
     * @param componentName 组件名 componentName
     * @param actionName    组件 actionName
     * @param key           组件 KEY
     * @return
     */
    private Fragment getComponentFragment(String componentName, String actionName, String key) {
        Fragment fragment = null;
        CCResult ccResult = CC.obtainBuilder(componentName)
                .setActionName(actionName)
                .build()
                .call();
        if (ccResult != null && ccResult.isSuccess()) {
            try {
                fragment = ccResult.getDataItem(key);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return fragment;
    }


    /**
     * 根据tag 添加Fragment
     *
     * @param fragment
     * @param tag
     */
    private void addTagFragment(Fragment fragment, String tag) {
        if (fragment != null) {
            mActivity.get().getSupportFragmentManager().beginTransaction().add(R.id.content, fragment, tag).commitAllowingStateLoss();
        }
    }

    /**
     * 根据Tag 获取Fragment
     *
     * @param tag
     * @return
     */
    private Fragment getTagFragment(String tag) {
        return mActivity.get().getSupportFragmentManager().findFragmentByTag(tag);
    }

    /**
     * 显示Fragment
     *
     * @param fragment
     */
    public void showFragment(Fragment fragment) {
        if (fragment != null) {
            mActivity.get().getSupportFragmentManager().beginTransaction().show(fragment).commitAllowingStateLoss();
        }
    }

    /**
     * 隐藏非tag的fragment
     *
     * @param tag
     */
    private void hideTagElseFragment(String tag) {
        for (String item : sPages) {
            if (!TextUtils.equals(item, tag)) {
                hideFragment(getTagFragment(item));
            }
        }
    }

    /**
     * 隐藏Fragment
     *
     * @param fragment
     */
    private void hideFragment(Fragment fragment) {
        if (fragment != null) {
            mActivity.get().getSupportFragmentManager().beginTransaction().hide(fragment).commitAllowingStateLoss();
        }
    }

    /**
     * 清除全部Fragment
     */
    public void release() {
        for (String tag : sPages) {
            removeTagFragment(tag);
        }
        mActivity = null;
    }

    /**
     * 移除所有的fragment
     *
     * @param tag
     */
    private void removeTagFragment(String tag) {
        Fragment fragment = getTagFragment(tag);
        if (fragment != null) {
            mActivity.get().getSupportFragmentManager().beginTransaction().remove(fragment).commitAllowingStateLoss();
        }
    }

    /**
     * 获取View
     *
     * @param id
     * @param <T>
     * @return
     */
    private <T extends View> T getView(int id) {
        return (T) mActivity.get().findViewById(id);
    }
}
