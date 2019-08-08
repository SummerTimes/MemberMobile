package com.kk.app.lib.recyclerview.util;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by mlp00 on 2017/7/8.
 */

public class SmoothUtil {
    public static void smoothMoveToPosition(final LinearLayoutManager layoutManager, final RecyclerView recyclerView, final int n) {
        int firstItem = layoutManager.findFirstVisibleItemPosition();
        int lastItem = layoutManager.findLastVisibleItemPosition();
        if (n <= firstItem) {
            recyclerView.smoothScrollToPosition(n);
        } else if (n <= lastItem) {
            int top = recyclerView.getChildAt(n - firstItem).getTop();
            recyclerView.smoothScrollBy(0, top);
        } else {
            recyclerView.smoothScrollToPosition(n);
            recyclerView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    smoothMoveToPosition(layoutManager, recyclerView, n);
                }
            }, 0);
        }
    }
}
