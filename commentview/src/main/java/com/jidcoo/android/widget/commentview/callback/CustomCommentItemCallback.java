package com.jidcoo.android.widget.commentview.callback;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jidcoo.android.widget.commentview.model.CommentEnable;

/**
 * 自定义评论布局的回调<p></p>
 * 相当于Adapter的getView()方法<p></p>
 * 与Adapter的getView()方法使用一样<p></p>
 * 泛型接口中的泛型对应评论数据模型，即继承在CommentEnable，查看{@link CommentEnable}
 *
 * @author Jidcoo
 */
public interface CustomCommentItemCallback<C extends CommentEnable> {

    /**
     * 相当于adapter中的getView()方法
     *
     * @param groupPosition Item所在groupPosition
     * @param comment       泛型评论数据
     * @param inflater      LayoutInflater实例（非空）
     * @param convertView   View
     * @param parent
     * @return
     */
    View buildCommentItem(int groupPosition, C comment, LayoutInflater inflater, View convertView, ViewGroup parent);
}
