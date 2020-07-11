package com.jidcoo.android.widget.commentview.callback;

import android.view.View;
import android.widget.AbsListView;

/**
 * CommentView 滚动事件回调
 *
 * @author Jidcoo
 */
public interface OnScrollCallback {
    /**
     * 滚动回调
     *
     * @param view
     * @param firstVisibleItem
     * @param visibleItemCount
     * @param totalItemCount
     */
    void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int
            totalItemCount);

    /**
     * 滚动状态回调
     *
     * @param view
     * @param scrollState
     */
    void onScrollStateChanged(AbsListView view, int scrollState);

    /**
     * 滚动回调
     *
     * @param v
     * @param scrollX
     * @param scrollY
     * @param oldScrollX
     * @param oldScrollY
     */
    void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY);

}
