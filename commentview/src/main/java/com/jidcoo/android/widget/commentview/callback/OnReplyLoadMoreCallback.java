package com.jidcoo.android.widget.commentview.callback;


import com.jidcoo.android.widget.commentview.model.ReplyEnable;

/**
 * CommentView加载更多回复回调<p></p>
 * 泛型接口中的泛型R是回复数据模型，必须继承自ReplyEnable，查看{@link ReplyEnable}
 *
 * @author Jidcoo
 */
public interface OnReplyLoadMoreCallback<R extends ReplyEnable> {

    /**
     * 加载更多回复回调
     *
     * @param reply        对应回复的数据
     * @param willLoadPage
     */
    void loading(R reply, int willLoadPage);

    /**
     * 加载更多回复完成回调
     */
    void complete();

    /**
     * 加载失败
     *
     * @param msg 错误信息
     */
    void failure(String msg);
}
