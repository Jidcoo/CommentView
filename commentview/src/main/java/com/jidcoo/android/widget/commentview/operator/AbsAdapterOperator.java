package com.jidcoo.android.widget.commentview.operator;

import com.jidcoo.android.widget.commentview.model.CommentEnable;
import com.jidcoo.android.widget.commentview.model.ReplyEnable;
import com.jidcoo.android.widget.commentview.view.CommentListView;

import java.util.List;

/**
 * Adapter业务抽象类,抽取出来方便维护<p></p>
 * 具体实现类AdapterOperator,查看{@link AdapterOperator}
 * 泛型C是评论数据模型
 *
 * @author Jidcoo
 */
public abstract class AbsAdapterOperator<C extends CommentEnable> {
    /**
     * 绑定数据与控件
     *
     * @param view
     */
    public abstract void bindView(CommentListView view);

    /**
     * 返回adapter所持有的数据大小
     *
     * @return
     */
    public abstract int getCommentCount();

    /**
     * 手动通知adapter更新数据
     */
    public abstract void notifyChangedManually(int code);

    /**
     * 刷新数据
     *
     * @param list
     */
    public abstract void refreshDataToAdapter(List<C> list);

    /**
     * 加载更多评论
     *
     * @param list
     */
    public abstract void loadMoreCommentsToAdapter(List<C> list);

    /**
     * 加载更多回复
     *
     * @param list
     */
    public abstract void loadMoreRepliesToAdapter(List<C> list);

    /**
     * 加载更多回复失败后重置view的状态
     */
    public abstract void resetViewStateToAdapter();


    /**
     * 获取评论数据
     *
     * @return
     */
    public abstract List<C> getComment();

    /**
     * @param position 父级评论位置
     * @param <R>
     * @return
     */
    public abstract <R extends ReplyEnable> List<R> getReplies(int position);


}
