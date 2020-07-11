package com.jidcoo.android.widget.commentview.operator;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.jidcoo.android.widget.commentview.CommentView;
import com.jidcoo.android.widget.commentview.R;
import com.jidcoo.android.widget.commentview.defaults.DefaultViewStyleConfigurator;
import com.jidcoo.android.widget.commentview.model.AbstractCommentModel;
import com.jidcoo.android.widget.commentview.model.CommentEnable;
import com.jidcoo.android.widget.commentview.model.ReplyEnable;
import com.jidcoo.android.widget.commentview.view.CommentListView;
import com.jidcoo.android.widget.commentview.view.ViewStyleConfigurator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CommentView 业务实现类<p></p>
 * 把CommentView的主要业务的实现都在这里<p></p>
 *
 * @author Jidcoo
 */
public final class CommentViewOperator extends AbsOperator {
    private static final String TAG = "CommentView";
    /**
     * 布局转换器
     */
    private LayoutInflater inflater;
    /**
     * 评论控件
     */
    private CommentView view;
    /**
     * 下拉刷新控件
     */
    private SwipeRefreshLayout content;
    /**
     * 数据显示器
     */
    private CommentListView dataView;

    /**
     * 错误占位视图
     */
    private View errorView;

    /**
     * 空数据占位视图
     */
    private View emptyView;

    /**
     * 上下文Context
     */
    private Context context;

    /**
     * 数据操作者
     */
    private AbsAdapterOperator adapterOperator;

    /**
     * 内部维护的一个分页管理器
     */
    private Map<String, Integer> pagerManager;

    /**
     * 样式配置器
     */
    private ViewStyleConfigurator styleConfigurator;

    /**
     * 回调集合
     */
    private CommentView.CallbackBuilder callbackBuilder;

    /**
     * callback初始化状态
     */
    private boolean initialized = false;


    /**
     * 初始化
     *
     * @param context
     * @param view
     */
    @Override
    public void init(Context context, CommentView view) {
        this.view = view;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.commentview, view, true);
        content = view.findViewById(R.id.content);
        content.setEnabled(false);
        dataView = view.findViewById(R.id.commentListView);
        pagerManager = new HashMap<>(3);
    }


    /**
     * 对外返回CallbackBuilder
     *
     * @return
     */
    @Override
    public CommentView.CallbackBuilder getCallbackBuilder() {
        return initialized ? this.callbackBuilder : null;
    }

    /**
     * 构建callback
     * 这个方法可以优化
     * 在后面当CallbackBuilder设置为可被修改后：
     * 第一步：如果this.callbackBuilder为空，则为第一次初始化，直接this.callbackBuilder=callbackBuilder;
     * 第二步：当this.callbackBuilder不为空时，将参数callbackBuilder与原this.callbackBuilder一一比较
     * 如果参数callbackBuilder某个回调为空，相应地把this.callbackBuilder修改为空，同时把控件的回调也置空
     * 如果参数callbackBuilder某个回调不为空，比较原来的this.callbackBuilder和参数callbackBuilder对应的回调，如果一致就不变，如果不一致就更新回调
     *
     * @param callbackBuilder
     */
    @Override
    public void buildCallback(CommentView.CallbackBuilder callbackBuilder) {
        this.callbackBuilder = callbackBuilder;
        if (this.callbackBuilder != null) {
            //callback已经初始化
            initialized = true;
            //构建下拉刷新回调
            if (this.callbackBuilder.onPullRefreshCallback != null) {
                content.setEnabled(true);
                content.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        //二次判断
                        if (CommentViewOperator.this.callbackBuilder.onPullRefreshCallback != null) {
                            CommentViewOperator.this.callbackBuilder.onPullRefreshCallback.refreshing();
                        }
                    }
                });
            } else {
                content.setOnRefreshListener(null);
            }
            //构建下拉刷新回调
            //构建加载更多评论回调
            if (this.callbackBuilder.onCommentLoadMoreCallback != null) {
                dataView.setLoadMoreListener(new CommentListView.LoadMoreListener() {
                    @Override
                    public void onLoadMore() {
                        //二次判断
                        if (CommentViewOperator.this.callbackBuilder.onCommentLoadMoreCallback != null) {
                            int totalPages = (Integer) pagerManager.get("totalPages");
                            int currentPage = (Integer) pagerManager.get("currentPage");
                            int nextPage = (Integer) pagerManager.get("nextPage");
                            if (totalPages > currentPage) {
                                //可加载更多
                                CommentViewOperator.this.callbackBuilder.onCommentLoadMoreCallback.loading(currentPage, nextPage, false);
                            } else {
                                //已加载完全数据
                                dataView.loadMoreEnd();
                                CommentViewOperator.this.callbackBuilder.onCommentLoadMoreCallback.loading(currentPage, totalPages, true);
                            }
                        }
                    }
                });
            } else {
                dataView.setLoadMoreListener(null);
            }
            //构建加载更多评论回调
            //构建滚动回调
            if (this.callbackBuilder.onScrollCallback != null) {
                dataView.setListViewOnScrollListener(new CommentListView.ViewOnScrollListener() {
                    @Override
                    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                        //二次判断
                        if (CommentViewOperator.this.callbackBuilder.onScrollCallback != null) {
                            CommentViewOperator.this.callbackBuilder.onScrollCallback.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
                        }
                    }

                    @Override
                    public void onScrollStateChanged(AbsListView view, int scrollState) {
                        if (CommentViewOperator.this.callbackBuilder.onScrollCallback != null) {
                            CommentViewOperator.this.callbackBuilder.onScrollCallback.onScrollStateChanged(view, scrollState);
                        }
                    }

                    @Override
                    public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                        if (CommentViewOperator.this.callbackBuilder.onScrollCallback != null) {
                            CommentViewOperator.this.callbackBuilder.onScrollCallback.onScrollChange(v, scrollX, scrollY, oldScrollX, oldScrollY);
                        }
                    }
                });

            } else {
                dataView.setListViewOnScrollListener(null);
            }
            //构建滚动回调
        }
    }


    /**
     * 加载数据
     *
     * @param model
     */
    @Override
    public void load(AbstractCommentModel model) {
        //初始化判断
        if (initialized) {
            //已初始化
            showErrorView(false);
            if (adapterOperator == null) {
                if (styleConfigurator == null) {
                    setViewStyleConfigurator(new DefaultViewStyleConfigurator(context));
                }
                adapterOperator = createAdapterOperator(model);
                adapterOperator.bindView(dataView);
                dataView.setEmptyView(emptyView);
            } else {
                adapterOperator.refreshDataToAdapter(model.getComments());
            }
            expandAll();
            cacheToManager(model.getCurrentPage(), model.getTotalPages(), model.getNextPage());
        } else {
            //未初始化
            Log.d(TAG, "No CallbackBuilder instance available to complete initialization");
        }
    }

    /**
     * 创建泛型AdapterOperator
     *
     * @param model
     * @return
     */
    private AdapterOperator createAdapterOperator(AbstractCommentModel model) {
        return new AdapterOperator<CommentEnable>(model.getComments(), context, callbackBuilder, styleConfigurator);
    }

    /**
     * 刷新数据
     *
     * @param model
     */
    @Override
    public void refreshComplete(AbstractCommentModel model) {
        if (initialized) {
            load(model);
            content.setRefreshing(false);
            dataView.resetState();
            if (callbackBuilder.onPullRefreshCallback != null) {
                callbackBuilder.onPullRefreshCallback.complete();
            } else {
            }
        } else {
            //未初始化
            Log.d(TAG, "No CallbackBuilder instance available to complete initialization");
        }
    }

    /**
     * 加载更多评论
     *
     * @param model
     */
    @Override
    public void loadMoreComplete(AbstractCommentModel model) {
        if (initialized) {
            showErrorView(false);
            adapterOperator.loadMoreCommentsToAdapter(model.getComments());
            dataView.loadMoreComplete();
            expandAll();
            cacheToManager(model.getCurrentPage(), model.getTotalPages(), model.getNextPage());
            if (callbackBuilder.onCommentLoadMoreCallback != null) {
                callbackBuilder.onCommentLoadMoreCallback.complete();
            } else {
            }
        } else {
            //未初始化
            Log.d(TAG, "No CallbackBuilder instance available to complete initialization");
        }

    }

    /**
     * 加载更多回复
     *
     * @param model
     */
    @Override
    public void loadMoreReplyComplete(AbstractCommentModel model) {
        if (initialized) {
            showErrorView(false);
            adapterOperator.loadMoreRepliesToAdapter(model.getComments());
            expandAll();
            if (callbackBuilder.onReplyLoadMoreCallback != null) {
                callbackBuilder.onReplyLoadMoreCallback.complete();
            } else {
            }
        } else {
            //未初始化
            Log.d(TAG, "No CallbackBuilder instance available to complete initialization");
        }
    }

    /**
     * 加载失败时，回调的错误信息
     *
     * @param code 错误标志
     * @param msg  错误信息，不需要时设置为空，使用时需要判空
     */
    @Override
    public void error(int code, String msg, boolean isShowErrorView) {
        if (initialized) {
            switch (code) {
                case 1:
                    //刷新失败
                    content.setRefreshing(false);
                    if (callbackBuilder.onPullRefreshCallback != null) {
                        callbackBuilder.onPullRefreshCallback.failure(msg);
                    }
                    break;
                case 2:
                    //加载更多评论失败
                    dataView.LoadDataError();
                    if (callbackBuilder.onCommentLoadMoreCallback != null) {
                        callbackBuilder.onCommentLoadMoreCallback.failure(msg);
                    }
                    break;
                case 3:
                    //加载更多回复失败
                    adapterOperator.resetViewStateToAdapter();
                    if (callbackBuilder.onReplyLoadMoreCallback != null) {
                        callbackBuilder.onReplyLoadMoreCallback.failure(msg);
                    }
                    break;
                case 4:
                    //just do showErrorView
                    break;
                default:
                    break;
            }

            showErrorView(isShowErrorView);
        } else {
            //未初始化
            Log.d(TAG, "No CallbackBuilder instance available to complete initialization");
        }

    }

    /**
     * 更新每次的页面缓存
     *
     * @param currentPage
     * @param totalPages
     * @param nextPage
     */
    private void cacheToManager(int currentPage, int totalPages, int nextPage) {
        pagerManager.put("currentPage", currentPage);
        pagerManager.put("totalPages", totalPages);
        pagerManager.put("nextPage", nextPage);
    }

    /**
     * 展开所有数据
     */
    private void expandAll() {
        for (int i = 0; i < adapterOperator.getCommentCount(); i++) {
            dataView.expandGroup(i);
        }
    }

    /**
     * 当数据为空时的视图
     *
     * @param view
     */
    @Override
    public void setEmptyView(View view) {
        if (view != null) {
            view.setVisibility(View.GONE);
            ((ViewGroup) dataView.getParent()).addView(view);
            this.emptyView = view;
        }
    }


    /**
     * 当出现错误时的视图
     *
     * @param view
     */
    @Override
    public void setErrorView(View view) {
        if (view != null) {
            view.setVisibility(View.GONE);
            ((ViewGroup) dataView.getParent()).addView(view);
            this.errorView = view;
        }
    }

    /**
     * 错误视图的显示/隐藏控制
     *
     * @param isShow
     */
    private void showErrorView(boolean isShow) {
        if (dataView != null && errorView != null) {
            if (isShow && errorView.getVisibility() == View.GONE) {
                dataView.setVisibility(View.GONE);
                errorView.setVisibility(View.VISIBLE);
                if (emptyView != null) {
                    emptyView.setVisibility(View.GONE);
                } else {
                }
            } else if (!isShow && dataView.getVisibility() == View.GONE) {
                errorView.setVisibility(View.GONE);
                dataView.setVisibility(View.VISIBLE);
            } else {
            }
        } else {
        }
    }

    /**
     * <p>设置样式配置器</p>
     * <p>该样式配置器针对非自定义控件的样式的具体配置</p>
     * <p>可根据具体需求配置这部分的控件的样式</p>
     * <p>自定义样式配置器必须继承自ViewStyleConfigurator，查看{@link ViewStyleConfigurator}</p>
     *
     * @param styleConfigurator
     */
    @Override
    public void setViewStyleConfigurator(ViewStyleConfigurator styleConfigurator) {
        if (styleConfigurator != null && this.styleConfigurator == null) {
            //样式变量非空校验
            //解析各种样式变量
            if (styleConfigurator.refreshViewColor == null) {
                content.setColorSchemeColors(Color.parseColor("#000000"));
            } else {
                content.setColorSchemeColors(Color.parseColor(styleConfigurator.refreshViewColor));
            }
            if (styleConfigurator.lmv_showText == null) {
                styleConfigurator.lmv_showText = "加载更多回复";
            }
            if (styleConfigurator.lmv_textColor == null) {
                styleConfigurator.lmv_textColor = "#000000";
            } else {
                Color.parseColor(styleConfigurator.lmv_textColor);
            }
            if (styleConfigurator.lmv_textStyle == null) {
                styleConfigurator.lmv_textStyle = Typeface.defaultFromStyle(Typeface.NORMAL);
            }
            if (styleConfigurator.lmv_loading_showText == null) {
                styleConfigurator.lmv_loading_showText = "正在加载中";
            }
            if (styleConfigurator.lmv_loading_textColor == null) {
                styleConfigurator.lmv_loading_textColor = "#000000";
            } else {
                Color.parseColor(styleConfigurator.lmv_loading_textColor);
            }
            if (styleConfigurator.lmv_loading_textStyle == null) {
                styleConfigurator.lmv_loading_textStyle = Typeface.defaultFromStyle(Typeface.NORMAL);
            }
            if (styleConfigurator.lmv_loading_progressBarColor == null) {
                styleConfigurator.lmv_loading_progressBarColor = "#000000";
            } else {
                Color.parseColor(styleConfigurator.lmv_loading_progressBarColor);
            }
            if (styleConfigurator.dividerColor == null) {
                styleConfigurator.dividerColor = "#000000";
            } else {

                Color.parseColor(styleConfigurator.dividerColor);
            }
            if (styleConfigurator.f_divider_adjustMargins) {
                dataView.setViewDivider(styleConfigurator.dividerColor,
                        styleConfigurator.dividerHeight,
                        styleConfigurator.f_divider_adjustMarginsLeft,
                        styleConfigurator.f_divider_adjustMarginsTop,
                        styleConfigurator.f_divider_adjustMarginsRight,
                        styleConfigurator.f_divider_adjustMarginsBottom);
            } else {
                dataView.setViewDivider(styleConfigurator.dividerColor, styleConfigurator.dividerHeight);
            }
            if (styleConfigurator.lm_footerProgressBarColor == null) {
                styleConfigurator.lm_footerProgressBarColor = "#000000";
            } else {
                Color.parseColor(styleConfigurator.lm_footerProgressBarColor);
            }
            if (styleConfigurator.lm_footer_text == null) {
                styleConfigurator.lm_footer_text = "全部数据加载完成";
            }
            if (styleConfigurator.lm_footer_textColor == null) {
                styleConfigurator.lm_footer_textColor = "#000000";
            } else {
                Color.parseColor(styleConfigurator.lm_footer_textColor);
            }
            if (styleConfigurator.lm_footer_textStyle == null) {
                styleConfigurator.lm_footer_textStyle = Typeface.defaultFromStyle(Typeface.NORMAL);
            }
            dataView.setFooterStyle(styleConfigurator.lm_footerProgressBarSize,
                    styleConfigurator.lm_footerProgressBarColor,
                    styleConfigurator.lm_footerProgressBar_adjustMargins,
                    styleConfigurator.lm_footerProgressBar_adjustMarginsLeft,
                    styleConfigurator.lm_footerProgressBar_adjustMarginsTop,
                    styleConfigurator.lm_footerProgressBar_adjustMarginsRight,
                    styleConfigurator.lm_footerProgressBar_adjustMarginsBottom,
                    styleConfigurator.lm_footer_text, styleConfigurator.lm_footer_textColor,
                    styleConfigurator.lm_footer_textSize, styleConfigurator.lm_footer_textStyle,
                    styleConfigurator.lm_footer_text_adjustMargins,
                    styleConfigurator.lm_footer_text_adjustMarginsLeft,
                    styleConfigurator.lm_footer_text_adjustMarginsTop,
                    styleConfigurator.lm_footer_text_adjustMarginsRight,
                    styleConfigurator.lm_footer_text_adjustMarginsBottom);
            //解析各种样式变量
            //样式变量非空校验
            this.styleConfigurator = styleConfigurator;
        }
    }

    /**
     * 获取评论数据
     *
     * @return
     */
    @Override
    public <C extends CommentEnable> List<C> getComment() {
        return adapterOperator.getComment();
    }

    /**
     * @param position 父级评论位置
     * @param <R>
     * @return
     */
    @Override
    public <R extends ReplyEnable> List<R> getReplies(int position) {
        return adapterOperator.getReplies(position);
    }

    /**
     * 手动添加评论数据
     *
     * @param comment 泛型评论数据
     * @param <C>     泛型回复数据
     */
    @Override
    public <C extends CommentEnable> void addComment(C comment) {
        if (initialized) {
            getComment().add(0, comment);
            adapterOperator.notifyChangedManually(2);
            expandAll();
        } else {
            //未初始化
            Log.d(TAG, "No CallbackBuilder instance available to complete initialization");
        }
    }

    /**
     * 手动添加回复数据
     *
     * @param reply    泛型回复数据
     * @param position 父级评论数据位置
     * @param <R>      泛型回复数据
     */
    @Override
    public <R extends ReplyEnable> void addReply(R reply, int position) {
        if (initialized) {
            getReplies(position).add(reply);
            adapterOperator.notifyChangedManually(1);
            expandAll();
        } else {
            //未初始化
            Log.d(TAG, "No CallbackBuilder instance available to complete initialization");
        }
    }


    /**
     * 添加头部布局
     *
     * @param view
     * @param canClickable 布局是否可点击
     */
    @Override
    public void addHeaderView(View view, boolean canClickable) {
        //在安卓4.4之后setAdapter和addHeaderView调用顺序可任意
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (view != null) {
                dataView.addHeaderView(view, null, canClickable);
            } else {
            }
        } else {
            //在安卓4.4之前addHeaderView()必须在setAdapter()之前调用
            if (view != null && adapterOperator == null) {
                dataView.addHeaderView(view, null, canClickable);
            } else {
                Log.e(TAG, "Cannot add header view to list --setAdapter has already been called.");
            }
        }
    }

    /**
     * 移除头部布局
     *
     * @param view
     */
    @Override
    public void removeHeaderView(View view) {
        if (view != null) {
            dataView.removeHeaderView(view);
        }
    }
}
