package com.jidcoo.android.widget.commentview.operator;

import android.content.Context;
import android.view.View;

import com.jidcoo.android.widget.commentview.CommentView;
import com.jidcoo.android.widget.commentview.model.AbstractCommentModel;
import com.jidcoo.android.widget.commentview.model.CommentEnable;
import com.jidcoo.android.widget.commentview.model.ReplyEnable;
import com.jidcoo.android.widget.commentview.view.ViewStyleConfigurator;

import java.util.List;

/**
 * CommentView 业务抽象类<p></p>
 * 把CommentView的主要业务抽象出来，方便维护<p></p>
 * 具体实现类CommentViewOperator，查看{@link CommentViewOperator}
 *
 * @author Jidcoo
 */
public abstract class AbsOperator {
    /**
     * 初始化控件
     *
     * @param context
     * @param view
     */
    public abstract void init(Context context, CommentView view);


    /**
     * 获取CallbackBuilder
     *
     * @return
     */
    public abstract CommentView.CallbackBuilder getCallbackBuilder();


    /**
     * 构建回调
     *
     * @param callbackBuilder
     */
    public abstract void buildCallback(CommentView.CallbackBuilder callbackBuilder);

    /**
     * 首次加载数据
     *
     * @param model
     */
    public abstract void load(AbstractCommentModel model);

    /**
     * 刷新完成后载入数据
     *
     * @param model
     */
    public abstract void refreshComplete(AbstractCommentModel model);

    /**
     * 加载更多完成后载入数据
     *
     * @param model
     */
    public abstract void loadMoreComplete(AbstractCommentModel model);

    /**
     * 加载更多回复完成后载入数据
     */
    public abstract void loadMoreReplyComplete(AbstractCommentModel model);

    /**
     * 加载数据出错
     *
     * @param code            错误标志
     * @param msg             错误信息，不需要时设置为空
     * @param isShowErrorView 是否显示错误视图
     */
    public abstract void error(int code, String msg, boolean isShowErrorView);


    /**
     * 当数据为空时的视图
     *
     * @param view
     */
    public abstract void setEmptyView(View view);


    /**
     * 当出现错误时的视图
     *
     * @param view
     */
    public abstract void setErrorView(View view);

    /**
     * <p>设置样式配置器</p>
     * <p>该样式配置器针对非自定义控件的样式的具体配置</p>
     * <p>可根据具体需求配置这部分的控件的样式</p>
     * <p>自定义样式配置器必须继承自ViewStyleConfigurator，查看{@link ViewStyleConfigurator}</p>
     *
     * @param styleConfigurator
     */
    public abstract void setViewStyleConfigurator(ViewStyleConfigurator styleConfigurator);


    /**
     * 获取评论数据
     *
     * @return
     */
    public abstract <C extends CommentEnable> List<C> getComment();

    /**
     * @param position 父级评论位置
     * @param <R>
     * @return
     */
    public abstract <R extends ReplyEnable> List<R> getReplies(int position);

    /**
     * 手动添加评论数据
     *
     * @param comment 泛型评论数据
     * @param <C>     泛型回复数据
     */
    public abstract <C extends CommentEnable> void addComment(C comment);


    /**
     * 手动添加回复数据
     *
     * @param reply    泛型回复数据
     * @param position 父级评论数据位置
     * @param <R>      泛型回复数据
     */
    public abstract <R extends ReplyEnable> void addReply(R reply, int position);


    //更多功能，没实现，有想法的可以自己直接扩展

    /**
     * 添加头部布局
     *
     * @param view
     * @param canClickable 布局是否可点击
     */
    public abstract void addHeaderView(View view, boolean canClickable);

    /**
     * 移除头部布局
     *
     * @param view
     */
    public abstract void removeHeaderView(View view);
}
