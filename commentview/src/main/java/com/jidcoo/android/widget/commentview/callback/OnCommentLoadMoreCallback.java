package com.jidcoo.android.widget.commentview.callback;

/**
 * CommentView加载更多评论回调
 *
 * @author Jidcoo
 */
public interface OnCommentLoadMoreCallback {

    /**
     * 上拉加载更多评论回调
     *
     * @param currentPage      当前页码
     * @param willLoadPage     下一个页码
     * @param isLoadedAllPages 是否已经加载完所有数据
     */
    void loading(int currentPage, int willLoadPage, boolean isLoadedAllPages);

    /**
     * 上拉加载更多评论完成
     */
    void complete();

    /**
     * 加载失败
     *
     * @param msg 错误信息
     */
    void failure(String msg);
}
