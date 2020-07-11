package com.jidcoo.android.widget.commentview.callback;

/**
 * CommentView下拉刷新回调
 *
 * @author Jidcoo
 */
public interface OnPullRefreshCallback {
    /**
     * 刷新回调
     */
    void refreshing();

    /**
     * 刷新完成回调
     */
    void complete();

    /**
     * 刷新失败
     *
     * @param msg 错误信息
     */
    void failure(String msg);
}
