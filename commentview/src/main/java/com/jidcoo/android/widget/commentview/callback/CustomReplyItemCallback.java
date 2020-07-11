package com.jidcoo.android.widget.commentview.callback;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jidcoo.android.widget.commentview.model.ReplyEnable;
import com.jidcoo.android.widget.commentview.view.ViewHolder;

/**
 * 自定义回复布局的回调<p></p>
 * 相当于Adapter的getView()方法<p></p>
 * 与Adapter的getView()方法使用一样<p></p>
 * 泛型接口中的泛型对应回复数据模型，即继承在ReplyEnable，查看{@link ReplyEnable}<p></p>
 * 注意：
 * <br></br>
 * 布局：<p>
 * 1、自定义布局xml中最外层布局必须是LinearLayout并且把最外层布局id设置为reply_rootView<p>
 * ViewHolder：<p>
 * 1、在buildReplyItem()中自定义布局初始化时，必须使用ViewHolder机制<p>
 * 2、初始化过程中，自定义Holder必须继承自com.jidcoo.android.widget.commentview.view.ViewHolder,查看{@link ViewHolder}<p>
 *
 * @author Jidcoo
 */
public interface CustomReplyItemCallback<R extends ReplyEnable> {

    /**
     * 相当于adapter中的getView()方法
     *
     * @param groupPosition 所在父级位置
     * @param childPosition 所在位置
     * @param isLastReply   是否是最后一条评论
     * @param convertView   View
     * @param reply         泛型回复数据
     * @param inflater      LayoutInflater实例（非空）
     * @param parent
     * @return
     */
    View buildReplyItem(int groupPosition, int childPosition, boolean isLastReply, R reply, LayoutInflater inflater, View convertView, ViewGroup parent);
}
