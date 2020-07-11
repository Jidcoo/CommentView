package com.jidcoo.android.widget.commentview.callback;

import android.view.View;

import com.jidcoo.android.widget.commentview.model.CommentEnable;
import com.jidcoo.android.widget.commentview.model.ReplyEnable;

/**
 * CommentView中评论Item和回复Item点击事件回调
 * 泛型接口中的C、R泛型：<p></p>
 * 1、C是评论数据模型，必须继承自CommentEnable,查看{@link CommentEnable}<p></p>
 * 2、R是回复数据模型，必须继承自ReplyEnable,查看{@link ReplyEnable}<p></p>
 *
 * @author Jidcoo
 */
public interface OnItemClickCallback<C extends CommentEnable, R extends ReplyEnable> {
    /**
     * 评论Item点击回调事件
     *
     * @param position 被点击Item的位置
     * @param comment  被点击Item所对应的数据
     * @param view     被点击Item的View
     */
    void commentItemOnClick(int position, C comment, View view);

    /**
     * 回复Item点击回调事件
     *
     * @param c_position 被点击Item所在的父级位置
     * @param r_position 被点击Item所在位置
     * @param reply      被点击Item所对应的数据
     * @param view       被点击Item的View
     */
    void replyItemOnClick(int c_position, int r_position, R reply, View view);
}
