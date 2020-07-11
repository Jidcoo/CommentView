package com.jidcoo.android.widget.commentview.view;


import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.InsetDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ExpandableListView;

/**
 * 可上拉刷新ExpandableListView
 *
 * @author Jidcoo
 */
public class CommentListView extends ExpandableListView implements OnScrollListener, ExpandableListView.OnGroupClickListener {

    /**
     * 加载更多监听
     */
    public interface LoadMoreListener {
        void onLoadMore();
    }

    private LoadMoreListener loadMoreListener;
    // 是否可加载更多
    private boolean canLoadMore = true;
    // 加载更多布局
    private LoadingMoreFootView loadingMoreFootView;
    // 正在加载数据中
    private boolean isLoadingData = false;
    private String footdes;
    private boolean isDraw;
    private boolean isAddFooterView = false;

    public interface ViewOnScrollListener {
        void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int
                totalItemCount);

        void onScrollStateChanged(AbsListView view, int scrollState);

        void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY);

    }

    public ViewOnScrollListener listener;

    public void setListViewOnScrollListener(ViewOnScrollListener listener) {
        this.listener = listener;
    }


    public CommentListView(Context context) {
        super(context);

        init(context);
        // TODO Auto-generated constructor stub
    }

    public CommentListView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
        // TODO Auto-generated constructor stub
    }

    public CommentListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        // TODO Auto-generated constructor stub
    }

    public CommentListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
        // TODO Auto-generated constructor stub
    }

    private void init(Context context) {
        loadingMoreFootView = new LoadingMoreFootView(context);
        loadingMoreFootView.setGone();
        setGroupIndicator(null);
        setOnScrollListener(this);
        setNestedScrollingEnabled(true);
        setOnGroupClickListener(this);
    }

    /**
     * 设置父级分割线
     *
     * @param color
     * @param height
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    public void setViewDivider(String color, int height, int left, int top, int right, int bottom) {
        if (!isDraw) {
            setDivider(new InsetDrawable(new ColorDrawable(Color.parseColor(color)), left, top, right, bottom));
            setDividerHeight(height);
            isDraw = true;
        }
    }

    /**
     * 设置父级分割线
     *
     * @param color
     * @param height
     */
    public void setViewDivider(String color, int height) {
        if (!isDraw) {
            setDivider(new ColorDrawable(Color.parseColor(color)));
            setDividerHeight(height);
            isDraw = true;
        }
    }


    /**
     * 设置控件底部样式
     *
     * @param pgb_size
     * @param pgb_color
     * @param pgb_isAdjust
     * @param pgb_left
     * @param pgb_top
     * @param pgb_right
     * @param pgb_bottom
     * @param footText
     * @param footTextColor
     * @param footTextSize
     * @param footTextStyle
     * @param footText_isAdjust
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    public void setFooterStyle(
            int pgb_size, String pgb_color, boolean pgb_isAdjust,
            int pgb_left, int pgb_top, int pgb_right, int pgb_bottom,
            String footText, String footTextColor, int footTextSize,
            Typeface footTextStyle, boolean footText_isAdjust, int left,
            int top, int right, int bottom) {
        if (loadingMoreFootView != null) {
            loadingMoreFootView.setStyle(pgb_size, pgb_color, pgb_isAdjust, pgb_left, pgb_top, pgb_right, pgb_bottom, footText, footTextColor, footTextSize, footTextStyle, footText_isAdjust, left, top, right, bottom);
        }
    }

    //加载失败
    public void LoadDataError() {
        if (loadingMoreFootView != null) {
            loadingMoreFootView.setGone();
        }
        this.smoothScrollBy(-(loadingMoreFootView.getMeasuredHeight()), 0);
        isLoadingData = false;
    }


    //移除加载更多布局
    public void removeFooter() {
        canLoadMore = false;
        isAddFooterView = false;
        this.removeFooterView(loadingMoreFootView);
    }

    //添加加载更多布局 在removeFooter()后调用
    public void addFooter() {
        canLoadMore = true;
        isAddFooterView = true;
        this.addFooterView(loadingMoreFootView, null, false);
    }

    //下拉刷新后初始化底部状态
    public void refreshComplete() {
        if (loadingMoreFootView != null) {
            loadingMoreFootView.setGone();
        }
        isLoadingData = false;
    }

    //上拉加载后初始化底部状态
    public void loadMoreComplete() {
        if (loadingMoreFootView != null) {
            loadingMoreFootView.setGone();
        }
        isLoadingData = false;
    }

    //重置状态
    public void resetState() {
        isLoadingData = false;
        canLoadMore = true;
    }

    //到底了
    public void loadMoreEnd() {
        if (loadingMoreFootView != null) {
            loadingMoreFootView.setEnd();
        }
        canLoadMore = false;
    }


    //设置底部加载中效果
    private void setFootLoadingView(View view) {
        if (loadingMoreFootView != null) {
            loadingMoreFootView.addFootLoadingView(view);
        }

    }

    /**
     * 设置尾部文字
     *
     * @param text
     */
    public void setFootText(String text) {
        loadingMoreFootView.setViewText(text);
    }

    //设置加载更多监听
    public void setLoadMoreListener(LoadMoreListener listener) {
        loadMoreListener = listener;
        loadingMoreFootView.setGone();
        addFooter();
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (listener != null) {
            listener.onScrollChange(this, l, t, oldl, oldt);
        }
    }


    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
        return true;
    }


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (listener != null) {
            listener.onScrollStateChanged(view, scrollState);
        }

        //判断 isLoadingData:不在加载中 ,scrollState:整个滚动事件结束，firstVisibleItem:条目至少上滑一条  canLoadMore:可以加载更多
    }


    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int
            totalItemCount) {
        if (getExpandableListAdapter() == null || (getExpandableListAdapter() != null && getExpandableListAdapter().getGroupCount() == 0)) {
            setNestedScrollingEnabled(false);
        } else {
            setNestedScrollingEnabled(true);
        }
        if (listener != null) {
            listener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
        }
        if ((firstVisibleItem + visibleItemCount) == totalItemCount) {
            View lastVisibleItemView = getChildAt(getChildCount() - 1);
            if (lastVisibleItemView != null && lastVisibleItemView.getBottom() == getHeight()) {
                if (!isLoadingData && firstVisibleItem != 0 && canLoadMore) {
                    if (loadMoreListener != null) {
                        if (loadingMoreFootView != null) {
                            loadingMoreFootView.setVisible();
                        }
                        isLoadingData = true;
                        loadMoreListener.onLoadMore();
                    }
                }
            }
        }


    }
}
