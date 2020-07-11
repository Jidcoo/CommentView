package com.jidcoo.android.widget.commentview.operator;

import android.content.Context;
import android.view.LayoutInflater;

import com.jidcoo.android.widget.commentview.CommentView;
import com.jidcoo.android.widget.commentview.adapter.ViewAdapter;
import com.jidcoo.android.widget.commentview.model.CommentEnable;
import com.jidcoo.android.widget.commentview.model.ReplyEnable;
import com.jidcoo.android.widget.commentview.view.CommentListView;
import com.jidcoo.android.widget.commentview.view.ViewStyleConfigurator;

import java.util.List;

/**
 * Adapter业务逻辑实现类，对Adapter的业务逻辑操作和管理都在这里<p></p>
 * 在AdapterOperator内部维护ViewAdapter，并对外提供服务
 *
 * @author Jidcoo
 */
public final class AdapterOperator<C extends CommentEnable> extends AbsAdapterOperator<C> {
    private ViewAdapter adapter;
    private List<C> comments;
    private LayoutInflater inflater;

    public AdapterOperator(List<C> list, Context context, CommentView.CallbackBuilder builder, ViewStyleConfigurator styleConfigurator) {
        this.comments = list;
        this.inflater = LayoutInflater.from(context);
        adapter = new ViewAdapter(comments, inflater, builder, styleConfigurator);

    }

    /**
     * 绑定控件与数据
     *
     * @param view
     */
    @Override
    public void bindView(CommentListView view) {
        if (view != null) {
            view.setAdapter(adapter);
        }
    }


    /**
     * 返回adapter所持有的数据大小
     *
     * @return
     */
    @Override
    public int getCommentCount() {
        return this.adapter.getGroupCount();
    }

    /**
     * 手动通知adapter更新数据
     */
    @Override
    public void notifyChangedManually(int code) {
        if (code == 1) {
            adapter.notifyDataSetChanged();
        } else {
            adapter.notifyDataSetInvalidated();
        }

    }

    /**
     * 刷新数据
     *
     * @param list
     */
    @Override
    public void refreshDataToAdapter(List<C> list) {
        this.comments.clear();
        this.comments.addAll(list);
        adapter.notifyDataSetInvalidated();
    }

    /**
     * 加载更多评论
     *
     * @param list
     */
    @Override
    public void loadMoreCommentsToAdapter(List<C> list) {
        this.comments.addAll(list);
        //adapter.notifyDataSetChanged();
    }

    /**
     * 加载更多回复
     *
     * @param list
     */
    @Override
    public void loadMoreRepliesToAdapter(List<C> list) {
        if (list.size() > 0) {
            /**
             * 找到原位置之后插入新数据
             */
            C oldComment = comments.get(adapter.G_position);
            oldComment.setTotalPages(list.get(0).getTotalPages());
            oldComment.setTotalDataSize(list.get(0).getTotalDataSize());
            oldComment.setCurrentPage(list.get(0).getCurrentPage());
            oldComment.setNextPage(list.get(0).getNextPage());
            oldComment.getReplies().addAll(list.get(0).getReplies());
            adapter.notifyDataSetChanged();
        }
    }


    /**
     * 当回复加载失败后调用此方法将View的加载中状态重置为未加载状态
     */
    @Override
    public void resetViewStateToAdapter() {
        adapter.resetView();
    }


    /**
     * 获取评论数据
     *
     * @return
     */
    @Override
    public List<C> getComment() {
        return comments;
    }

    /**
     * @param position 父级评论位置
     * @param <R>
     * @return
     */
    @Override
    public <R extends ReplyEnable> List<R> getReplies(int position) {
        return comments.get(position).getReplies();
    }
}
