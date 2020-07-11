package com.jidcoo.android.widget.commentview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.jidcoo.android.widget.commentview.callback.CustomCommentItemCallback;
import com.jidcoo.android.widget.commentview.callback.CustomReplyItemCallback;
import com.jidcoo.android.widget.commentview.callback.OnCommentLoadMoreCallback;
import com.jidcoo.android.widget.commentview.callback.OnItemClickCallback;
import com.jidcoo.android.widget.commentview.callback.OnPullRefreshCallback;
import com.jidcoo.android.widget.commentview.callback.OnReplyLoadMoreCallback;
import com.jidcoo.android.widget.commentview.callback.OnScrollCallback;
import com.jidcoo.android.widget.commentview.model.AbstractCommentModel;
import com.jidcoo.android.widget.commentview.model.CommentEnable;
import com.jidcoo.android.widget.commentview.model.ReplyEnable;
import com.jidcoo.android.widget.commentview.operator.AbsOperator;
import com.jidcoo.android.widget.commentview.operator.CommentViewOperator;
import com.jidcoo.android.widget.commentview.view.ViewStyleConfigurator;

import java.util.List;

/**
 * 评论控件
 * <p>本类只对外提供控件的方法和接口</p>
 * <p>业务逻辑抽象类AbsOperator,此抽象类提供了CommentView控件所有可访问的方法，请查看{@link AbsOperator}</p>
 * <p>业务逻辑实现类CommentViewOperator,此类实现了CommentView控件所有可访问的方法，请查看{@link CommentViewOperator}</p>
 * <br><br>
 * <h3>主要业务方法说明：</h3>
 * <p>{@link #callbackBuilder()}: 获取控件的回调构造类{@link CallbackBuilder}
 * <p>{@link #loadComplete(AbstractCommentModel model)}: 首次加载完成数据后调用
 * <p>{@link #loadFailed(boolean)}: 首次加载出现错误时调用，可以控制是否显示错误视图
 * <p>{@link #refreshComplete(AbstractCommentModel model)}: 刷新数据完成后调用
 * <p>{@link #refreshFailed(String, boolean)}: 刷新出现错误时调用，可以控制是否显示错误视图
 * <p>{@link #loadMoreComplete(AbstractCommentModel model)}: 上拉加载更多数据完成后调用
 * <p>{@link #loadMoreFailed(String, boolean)}: 上拉加载更多数据出现错误时调用，可以控制是否显示错误视图
 * <p>{@link #loadMoreReplyComplete(AbstractCommentModel model)}: 加载更多回复数据完成后调用
 * <p>{@link #loadMoreReplyFailed(String, boolean)}: 加载更多回复数据出现错误时调用，可以控制是否显示错误视图
 * <p>{@link #setEmptyView(View)}: 设置数据为空时的空数据视图
 * <p>{@link #setErrorView(View)}: 设置加载失败的使用的错误视图
 * <p>{@link #setViewStyleConfigurator(ViewStyleConfigurator)}: 设置控件样式配置器,控件默认使用默认样式配置器
 * {@link com.jidcoo.android.widget.commentview.defaults.DefaultViewStyleConfigurator},
 * 如果自定义样式配置器，请继承自{@link ViewStyleConfigurator}，自定义样式配置器可参考默认样式配置器
 * {@link com.jidcoo.android.widget.commentview.defaults.DefaultViewStyleConfigurator}
 * <p>{@link #getCommentList()}: 获取评论数据集合
 * <p>{@link #getReplyList(int position)}:根据父级评论的位置获取父级评论回复的集合
 * <p>{@link #addComment(CommentEnable)}:添加一条评论
 * <p>{@link #addReply(ReplyEnable, int)}:给所在评论中添加一条回复
 * <p>{@link #addHeaderView(View, boolean)}:添加头部布局
 * <p>{@link #removeHeaderView(View)}:移除头部布局
 * @author Jidcoo
 * @github https://github.com/Jidcoo/
 */
public class CommentView extends LinearLayout {
    //业务逻辑类
    private AbsOperator operator;

    ///////***CallbackBuilder***//////
    public final class CallbackBuilder {
        ///////***Callback***//////
        public OnPullRefreshCallback onPullRefreshCallback;//下拉刷新回调
        public OnCommentLoadMoreCallback onCommentLoadMoreCallback;//加载更多评论回调
        public OnReplyLoadMoreCallback onReplyLoadMoreCallback;//加载更多回复回调
        public OnItemClickCallback onItemClickCallback;//点击事件回调
        public OnScrollCallback onScrollCallback;//滚动事件回调
        public CustomCommentItemCallback customCommentItemCallback;//自定义评论布局回调
        public CustomReplyItemCallback customReplyItemCallback;//自定义回复布局回调

        ///////***Callback***//////
        public CallbackBuilder setOnPullRefreshCallback(OnPullRefreshCallback onPullRefreshCallback) {
            this.onPullRefreshCallback = onPullRefreshCallback;
            return this;
        }

        public CallbackBuilder setOnCommentLoadMoreCallback(OnCommentLoadMoreCallback onCommentLoadMoreCallback) {
            this.onCommentLoadMoreCallback = onCommentLoadMoreCallback;
            return this;
        }

        public CallbackBuilder setOnReplyLoadMoreCallback(OnReplyLoadMoreCallback onReplyLoadMoreCallback) {
            this.onReplyLoadMoreCallback = onReplyLoadMoreCallback;
            return this;
        }

        public CallbackBuilder setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
            this.onItemClickCallback = onItemClickCallback;
            return this;
        }

        public CallbackBuilder setOnScrollCallback(OnScrollCallback onScrollCallback) {
            this.onScrollCallback = onScrollCallback;
            return this;
        }

        public CallbackBuilder customCommentItem(CustomCommentItemCallback customCommentItemCallback) {
            this.customCommentItemCallback = customCommentItemCallback;
            return this;
        }

        public CallbackBuilder customReplyItem(CustomReplyItemCallback customReplyItemCallback) {
            this.customReplyItemCallback = customReplyItemCallback;
            return this;
        }

        public CommentView buildCallback() {
            return initialize(this);
        }
    }

    ///////***CallbackBuilder***//////
    public final CallbackBuilder callbackBuilder() {
        return operator.getCallbackBuilder() != null ? operator.getCallbackBuilder() : new CallbackBuilder();
    }
    ///////***CallbackBuilder***//////
    ///////***public业务方法***//////

    /**
     * <p>{@link #callbackBuilder()}: 获取控件的回调构造类{@link CallbackBuilder}
     * <p>{@link #loadComplete(AbstractCommentModel model)}: 首次加载完成数据后调用
     * <p>{@link #loadFailed(boolean)}: 首次加载出现错误时调用，可以控制是否显示错误视图
     * <p>{@link #refreshComplete(AbstractCommentModel model)}: 刷新数据完成后调用
     * <p>{@link #refreshFailed(String, boolean)}: 刷新出现错误时调用，可以控制是否显示错误视图
     * <p>{@link #loadMoreComplete(AbstractCommentModel model)}: 上拉加载更多数据完成后调用
     * <p>{@link #loadMoreFailed(String, boolean)}: 上拉加载更多数据出现错误时调用，可以控制是否显示错误视图
     * <p>{@link #loadMoreReplyComplete(AbstractCommentModel model)}: 加载更多回复数据完成后调用
     * <p>{@link #loadMoreReplyFailed(String, boolean)}: 加载更多回复数据出现错误时调用，可以控制是否显示错误视图
     * <p>{@link #setEmptyView(View)}: 设置数据为空时的空数据视图
     * <p>{@link #setErrorView(View)}: 设置加载失败的使用的错误视图
     * <p>{@link #setViewStyleConfigurator(ViewStyleConfigurator)}: 设置控件样式配置器,控件默认使用默认样式配置器
     * {@link com.jidcoo.android.widget.commentview.defaults.DefaultViewStyleConfigurator},
     * 如果自定义样式配置器，请继承自{@link ViewStyleConfigurator}，自定义样式配置器可参考默认样式配置器
     * {@link com.jidcoo.android.widget.commentview.defaults.DefaultViewStyleConfigurator}
     * <p>{@link #getCommentList()}: 获取评论数据集合
     * <p>{@link #getReplyList(int position)}:根据父级评论的位置获取父级评论回复的集合
     * <p>{@link #addComment(CommentEnable)}:添加一条评论
     * <p>{@link #addReply(ReplyEnable, int)}:给所在评论中添加一条回复
     * <p>{@link #addHeaderView(View, boolean)}:添加头部布局
     * <p>{@link #removeHeaderView(View)}:移除头部布局
     */
    public void loadComplete(AbstractCommentModel model) {
        operator.load(model);
    }

    public void loadFailed(boolean isShowErrorView) {
        operator.error(4, null, isShowErrorView);
    }

    ;

    public void refreshComplete(AbstractCommentModel model) {
        operator.refreshComplete(model);
    }

    public void refreshFailed(String msg, boolean isShowErrorView) {
        operator.error(1, msg, isShowErrorView);
    }

    ;

    public void loadMoreComplete(AbstractCommentModel model) {
        operator.loadMoreComplete(model);
    }

    public void loadMoreFailed(String msg, boolean isShowErrorView) {
        operator.error(2, msg, isShowErrorView);
    }

    ;

    public void loadMoreReplyComplete(AbstractCommentModel model) {
        operator.loadMoreReplyComplete(model);
    }

    public void loadMoreReplyFailed(String msg, boolean isShowErrorView) {
        operator.error(3, msg, isShowErrorView);
    }

    ;

    public void setEmptyView(View view) {
        operator.setEmptyView(view);
    }

    public void setErrorView(View view) {
        operator.setErrorView(view);
    }

    public void addHeaderView(View view, boolean canClickable) {
        operator.addHeaderView(view, canClickable);
    }

    public void removeHeaderView(View view) {
        operator.removeHeaderView(view);
    }

    public void setViewStyleConfigurator(ViewStyleConfigurator styleConfigurator) {
        operator.setViewStyleConfigurator(styleConfigurator);
    }

    ;

    public List<? extends CommentEnable> getCommentList() {
        return operator.getComment();
    }

    public List<? extends ReplyEnable> getReplyList(int position) {
        return operator.getReplies(position);
    }

    public <C extends CommentEnable> void addComment(C comment) {
        operator.addComment(comment);
    }

    ;

    public <R extends ReplyEnable> void addReply(R reply, int position) {
        operator.addReply(reply, position);
    }

    ;
    ///////***public业务方法***//////

    ///////////////////////////////////////////////////////////////////////

    /**
     * 初始化控件
     *
     * @param context
     */
    private void initView(Context context) {
        operator = new CommentViewOperator();
        operator.init(context, this);
    }

    /**
     * 构建回调的初始化
     *
     * @param builder
     * @return
     */
    private CommentView initialize(CallbackBuilder builder) {
        operator.buildCallback(builder);
        return this;
    }


    public CommentView(Context context, ViewGroup attachTo) {
        super(context);
        //java代码中定义
        if (attachTo != null) {
            attachTo.addView(this);
        }
        initView(context);


    }

    public CommentView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //xml中定义
        initView(context);

    }

    public CommentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public CommentView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }


}

