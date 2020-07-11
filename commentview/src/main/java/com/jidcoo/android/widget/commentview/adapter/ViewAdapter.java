package com.jidcoo.android.widget.commentview.adapter;


import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jidcoo.android.widget.commentview.CommentView;
import com.jidcoo.android.widget.commentview.R;
import com.jidcoo.android.widget.commentview.callback.OnConvertViewClickCallback;
import com.jidcoo.android.widget.commentview.defaults.DefaultItemBuilder;
import com.jidcoo.android.widget.commentview.model.CommentEnable;
import com.jidcoo.android.widget.commentview.view.ViewHolder;
import com.jidcoo.android.widget.commentview.view.ViewStyleConfigurator;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 控件适配器
 * 核心逻辑：实现View的复用
 *
 * @author Jidcoo
 */
public final class ViewAdapter<C extends CommentEnable> extends BaseExpandableListAdapter {
    private DefaultItemBuilder itemBuilder;
    private List<C> comments;
    private LayoutInflater inflater;
    private ViewPool viewPool;
    public int G_position;
    public int C_position;
    private Map<String, Object> viewCache;
    private CommentView.CallbackBuilder callbackBuilder;
    private int left, top, right, bottom;
    private int left1, top1, right1, bottom1;
    private int lmv_textSize, lmv_loading_textSize, lmv_loading_progressBarSize, dividerHeight;
    //
    /**
     * 下拉刷新控件颜色
     */
    public String refreshViewColor;
    //*********//回复加载更多控件样式//*************//
    //Part1//
    public String lmv_showText;
    public String lmv_textColor;
    public Typeface lmv_textStyle;
    //Part2//
    public String lmv_loading_showText;
    public String lmv_loading_textColor;
    public Typeface lmv_loading_textStyle;
    public String lmv_loading_progressBarColor;
    //Part3//
    //*********//回复加载更多控件样式//*************//
    //*********//回复Item最后一项的下划线//*************//
    public boolean isDraw;
    public String dividerColor;

    //
    public ViewAdapter(List<C> comments, LayoutInflater inflater, CommentView.CallbackBuilder callbackBuilder, ViewStyleConfigurator styleConfigurator) {
        this.comments = comments;
        this.inflater = inflater;
        this.callbackBuilder = callbackBuilder;
        viewPool = new ViewPool();
        viewCache = new HashMap<>(4);//  (2/0.75)+1
        unPackingStyleConfigurator(styleConfigurator);
    }

    /**
     * 获取样式配置器的配置信息
     *
     * @param styleConfigurator
     */
    private void unPackingStyleConfigurator(ViewStyleConfigurator styleConfigurator) {
        this.refreshViewColor = styleConfigurator.refreshViewColor;
        this.lmv_showText = styleConfigurator.lmv_showText;
        this.lmv_textColor = styleConfigurator.lmv_textColor;
        this.lmv_textStyle = styleConfigurator.lmv_textStyle;
        this.lmv_loading_showText = styleConfigurator.lmv_loading_showText;
        this.lmv_loading_textColor = styleConfigurator.lmv_loading_textColor;
        this.lmv_loading_textStyle = styleConfigurator.lmv_loading_textStyle;
        this.lmv_loading_progressBarColor = styleConfigurator.lmv_loading_progressBarColor;
        this.dividerColor = styleConfigurator.dividerColor;
        this.isDraw = styleConfigurator.isDrawChildDivider;
        if (isDraw) {
            if (styleConfigurator.dividerHeight == 0) {
                this.dividerHeight = 1;
            } else {
                this.dividerHeight = styleConfigurator.dividerHeight;
            }
        }
        if (styleConfigurator.lmv_adjustMargins) {
            this.left = styleConfigurator.lmv_adjustMarginsLeft;
            this.right = styleConfigurator.lmv_adjustMarginsRight;
            this.top = styleConfigurator.lmv_adjustMarginsTop;
            this.bottom = styleConfigurator.lmv_adjustMarginsBottom;
        }
        if (styleConfigurator.c_divider_adjustMargins) {
            this.left1 = styleConfigurator.c_divider_adjustMarginsLeft;
            this.right1 = styleConfigurator.c_divider_adjustMarginsRight;
            this.top1 = styleConfigurator.c_divider_adjustMarginsTop;
            this.bottom1 = styleConfigurator.c_divider_adjustMarginsBottom;
        }
        if (styleConfigurator.lmv_textSize == 0) {
            this.lmv_textSize = 10;
        } else {
            this.lmv_textSize = styleConfigurator.lmv_textSize;
        }
        if (styleConfigurator.lmv_loading_textSize == 0) {
            this.lmv_loading_textSize = 14;
        } else {
            this.lmv_loading_textSize = styleConfigurator.lmv_loading_textSize;
        }
        if (styleConfigurator.lmv_loading_progressBarSize == 0) {
            this.lmv_loading_progressBarSize = 14;
        } else {
            this.lmv_loading_progressBarSize = styleConfigurator.lmv_loading_progressBarSize;
        }
    }

    private static int k = 1;

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        //判断是否使用了自定义布局
        //如果没有就使用默认布局
        if (callbackBuilder.customCommentItemCallback == null) {
            if (itemBuilder == null) {
                itemBuilder = new DefaultItemBuilder();
            }
            convertView = itemBuilder.useDefaultCommentItem(inflater, convertView, parent, comments.get(groupPosition), groupPosition);
        } else {
            convertView = callbackBuilder.customCommentItemCallback.buildCommentItem(groupPosition, comments.get(groupPosition), inflater, convertView, parent);
        }
        //此处不判断convertView是否为空，自定义使用时要注意
        if (convertView.getTag(R.id.clickCallback) == null) {
            OnConvertViewClickCallback callback = new OnConvertViewClickCallback(convertView, R.id.clickGPosition) {
                @Override
                public void onClickCallback(View view, int... positionIds) {
                    if (callbackBuilder.onItemClickCallback != null) {
                        callbackBuilder.onItemClickCallback.commentItemOnClick(positionIds[0], comments.get(positionIds[0]), view);
                    }
                }
            };
            convertView.setOnClickListener(callback);
            convertView.setTag(R.id.clickCallback, callback);
        }
        convertView.setTag(R.id.clickGPosition, groupPosition);
        //此处不判断convertView是否为空，自定义使用时要注意
        return convertView;
    }


    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        //判断是否使用了自定义布局
        //如果没有就使用默认布局
        if (callbackBuilder.customReplyItemCallback == null) {
            if (itemBuilder == null) {
                itemBuilder = new DefaultItemBuilder();
            }
            convertView = itemBuilder.useDefaultReplyItem(inflater, groupPosition, childPosition, convertView, parent, comments);
        } else {
            convertView = callbackBuilder.customReplyItemCallback.buildReplyItem(groupPosition, childPosition, isLastChild, comments.get(groupPosition).getReplies().get(childPosition), inflater, convertView, parent);
        }
        //此处不判断convertView是否为空，自定义使用时要注意
        if (convertView.getTag() == null) {
            throw new RuntimeException("You should call convertView.getTag() method to use Holder instance as the tag of convertView");
        } else {
            Object object = convertView.getTag();
            if (object instanceof ViewHolder) {
                linkView(groupPosition, childPosition, (ViewHolder) object, isLastChild, parent);
            } else {
                throw new RuntimeException("The ReplyHolder must extent from ViewHolder");
            }
        }
        if (isDraw) {
            //绘制子分割线
            drawChildDivider(convertView, isLastChild);
        }
        //此处不判断convertView是否为空，自定义使用时要注意
        if (convertView.getTag(R.id.clickCallback) == null) {
            OnConvertViewClickCallback callback = new OnConvertViewClickCallback(convertView, R.id.clickGPosition, R.id.clickCPosition) {
                @Override
                public void onClickCallback(View view, int... positionIds) {
                    if (callbackBuilder.onItemClickCallback != null) {
                        callbackBuilder.onItemClickCallback.replyItemOnClick(positionIds[0], positionIds[1], comments.get(positionIds[0]).getReplies().get(positionIds[1]), view);
                    }
                }
            };
            convertView.setOnClickListener(callback);
            convertView.setTag(R.id.clickCallback, callback);
        }
        convertView.setTag(R.id.clickGPosition, groupPosition);
        convertView.setTag(R.id.clickCPosition, childPosition);
        //此处不判断convertView是否为空，自定义使用时要注意
        return convertView;
    }


    /**
     * 重置加载更多View状态
     */
    public void resetView() {
        Object tmp = viewCache.get("view");
        if (tmp != null) {
            ((LinkView) tmp).reset();
            viewCache.clear();
        }
    }

    @Override
    public int getGroupCount() {
        return comments.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (comments.get(groupPosition).getReplies() != null) {
            return comments.get(groupPosition).getReplies().size();
        }
        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return comments.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return comments.get(groupPosition).getReplies().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return groupPosition + childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }


    private ColorStateList colorStateList;

    /**
     * 实现View的复用ViewPool
     * 不直接使用listView原有的复用机制的原因：
     * 1、直接使用listView原有的复用机制在某些情况下会多创建几个view出来，但是是没有必要的，所以
     * 还可以在优化，实现了ViewPool，可以减少在某些情况下创建的view浪费了的情况
     * <p>第一步：首先判断是否满足在尾部添加View（targetView）的条件</p>
     * <p>1、如果targetView==null，则从ViewPool中获取一个View，然后将view缓存到replyHolder.rootView中</p>
     * <p>2、如果targetView!=null,表示已经创建过view。那么就直接在缓存中拿出来，重置状态就可</p>
     */

    private void linkView(final int groupPosition, final int childPosition, ViewHolder replyHolder, boolean isLast, ViewGroup parent) {
        if (replyHolder == null) {
            return;
        }
        boolean hasNextPage = (comments.get(groupPosition).getTotalPages() > 1) && (comments.get(groupPosition).getCurrentPage() < comments.get(groupPosition).getTotalPages());
        //判断是否是最后一条，如果是才进行添加View
        Object targetView = replyHolder.rootView.getTag(R.id.viewTag);
        if (isLast && hasNextPage) {
            //targetView存在，直接返回，不需要从viewPool中拿
            if (targetView != null) {
                LinkView v = ((LinkView) targetView);
                v.reset();
                v.setTag(R.id.clickGPosition, groupPosition);
                v.setTag(R.id.clickCPosition, childPosition);
                return;
            }
            //targetView为空，就从viewPool拿一个
            final LinkView view = viewPool.obtainV(parent.getContext());
            //如果view的点击事件为空，就添加一个点击事件
            if (view.convertViewClickCallback == null) {
                //添加一个点击事件
                OnConvertViewClickCallback callback = new OnConvertViewClickCallback(view, R.id.clickGPosition, R.id.clickCPosition) {
                    @Override
                    public void onClickCallback(View v, int... positionIds) {
                        resetView();
                        view.pro.setVisibility(View.VISIBLE);
                        view.layout.setVisibility(View.GONE);
                        if (callbackBuilder.onReplyLoadMoreCallback != null) {
                            G_position = positionIds[0];
                            C_position = positionIds[1];
                            viewCache.put("view", view);
                            callbackBuilder.onReplyLoadMoreCallback.loading(
                                    comments.get(positionIds[0]).getReplies().get(positionIds[1]),
                                    comments.get(positionIds[0]).getNextPage());
                        }
                    }
                };
                view.layout.setOnClickListener(callback);
                view.convertViewClickCallback = callback;
            }
            //如果view的点击事件为空，就添加一个点击事件
            view.setTag(R.id.clickGPosition, groupPosition);
            view.setTag(R.id.clickCPosition, childPosition);
            replyHolder.rootView.addView(view);
            replyHolder.rootView.setTag(R.id.viewTag, null);
            replyHolder.rootView.setTag(R.id.viewTag, view);
        } else {
            //进入这个else代码块，就表示这个view不应该出现在当前位置
            //那么就判断targetView是否为空，如果不为空，就把它移除并放回ViewPool中，等待下一次复用
            if (targetView != null) {
                //targetView存在，并且不符合存在的条件，那么就移除
                LinkView view = (LinkView) targetView;
                viewCache.remove("view");
                viewPool.recycleV(view);
                replyHolder.rootView.setTag(R.id.viewTag, null);
            } else {
                //targetView不存在，不需要操作
            }
        }
    }

    private LinearLayout.LayoutParams lp;

    /**
     * 实现View的复用_复用池
     * <p>第一步：首先判断是否满足在尾部添加View（object）的条件</p>
     * <p>1、如果object==null，则从复用池获取一个View，然后将view缓存到convertView中</p>
     * <p>2、如果object!=null,表示已经创建过view。那么就直接在缓存中拿</p>
     */
    private void drawChildDivider(View convertView, boolean isLastChild) {
        Object object = convertView.getTag(R.id.dividerTag);
        Object object2 = convertView.getTag();
        if (object2 == null) {
            return;
        }
        if (isLastChild) {
            //分割线不为空，直接返回，不用从复用池中获取
            ViewHolder holder = (ViewHolder) object2;
            if (object != null) {
                View o = (View) object;
                if (o != holder.rootView.getChildAt(holder.rootView.getChildCount() - 1)) {
                    holder.rootView.removeViewInLayout(o);
                    holder.rootView.addView(o);
                }
                return;
            }
            //分割线为空，从复用池中获取一个
            View divider = viewPool.obtainD(convertView.getContext());
            holder.rootView.addView(divider);
            convertView.setTag(R.id.dividerTag, null);
            convertView.setTag(R.id.dividerTag, divider);
        } else {
            //如果object不为空，就回收分割线放回复用池
            if (object != null) {
                //如果object不为空，就回收分割线放回复用池
                ViewHolder holder = (ViewHolder) object2;
                View view = (View) object;
                viewPool.recycleD(view);
                convertView.setTag(R.id.dividerTag, null);
            } else {
                //object不存在，不需要操作
            }
        }
    }


    /**
     * View的复用池：
     * 一个单向队列
     * 当需要一个，从队列里面取
     * 用完以后恢复状态再放回队列
     * 当队列没有的时候就创建一个
     */
    class ViewPool {
        Queue<LinkView> vPool;//加载更多View_Pool
        Queue<View> dPool;//分割线_Pool

        ViewPool() {
            if (vPool == null) {
                vPool = new ArrayDeque<LinkView>();
            }
            if (dPool == null) {
                dPool = new ArrayDeque<View>();
            }
        }

        /**
         * 创建分割线
         *
         * @param context
         */
        void buildOneD(Context context) {
            //创建一个view并加入队列
            View divider = null;
            if (lp == null) {
                lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, dividerHeight);
            }
            divider = new View(context);
            divider.setBackgroundColor(Color.parseColor(dividerColor));
            lp.setMargins(left1, top1, right1, bottom1);
            divider.setLayoutParams(lp);
            dPool.offer(divider);
        }

        /**
         * 创建加载更多View
         *
         * @param context
         */
        void buildOneV(Context context) {
            //创建一个view并加入队列
            vPool.offer(new LinkView(context, inflater));
        }

        //从复用池获取一个分割线
        View obtainD(Context context) {
            if (dPool.isEmpty()) {
                buildOneD(context);
            } else {
            }
            return dPool.poll();
        }


        //从复用池获取一个加载更多View
        LinkView obtainV(Context context) {
            if (vPool.isEmpty()) {
                buildOneV(context);
            }
            return vPool.poll();
        }

        //回收一个加载更多View
        void recycleV(LinkView view) {
            //重置状态并再次放入队列
            if (view != null) {
                //如果当前view还挂靠在某View上，就先移除
                if ((ViewGroup) view.getParent() != null) {
                    ((ViewGroup) view.getParent()).removeView(view);
                }
                view.reset();
                vPool.offer(view);
            }
        }

        //回收一个分割线
        void recycleD(View view) {
            //重置状态并再次放入队列
            if (view != null) {
                //如果当前view还挂靠在某View上，就先移除
                if ((ViewGroup) view.getParent() != null) {
                    ((ViewGroup) view.getParent()).removeView(view);
                }
                dPool.offer(view);
            }
        }


    }


    class LinkView extends LinearLayout {
        public LinearLayout layout, pro;
        public ProgressBar bar;
        public TextView tv, tv2;
        public OnConvertViewClickCallback convertViewClickCallback;

        public LinkView(Context context, LayoutInflater inflater) {
            super(context);
            inflater.inflate(R.layout.item_loadmore, this, true);
            findView();
        }

        private void findView() {
            layout = findViewById(R.id.button_loadmore);
            pro = findViewById(R.id.pro_loadmore);
            bar = findViewById(R.id.bar);
            tv2 = findViewById(R.id.text2);
            tv = findViewById(R.id.text);
            initView();
        }

        private void initView() {
            tv.setText(lmv_showText);
            tv.setTextSize(lmv_textSize);
            tv.setTextColor(Color.parseColor(lmv_textColor));
            tv.setTypeface(lmv_textStyle);
            tv2.setText(lmv_loading_showText);
            tv2.setTextSize(lmv_loading_textSize);
            tv2.setTextColor(Color.parseColor(lmv_loading_textColor));
            tv2.setTypeface(lmv_loading_textStyle);
            bar.getLayoutParams().width = lmv_loading_progressBarSize;
            bar.getLayoutParams().height = lmv_loading_progressBarSize;
            if (colorStateList == null) {
                colorStateList = ColorStateList.valueOf(Color.parseColor(lmv_loading_progressBarColor));
            }
            bar.setIndeterminateTintMode(PorterDuff.Mode.SRC_ATOP);
            bar.setIndeterminateTintList(colorStateList);
            ((LinearLayout.LayoutParams) layout.getLayoutParams()).setMargins(left, top, right, bottom);
            ((LinearLayout.LayoutParams) pro.getLayoutParams()).setMargins(left, top, right, bottom);
        }

        public void reset() {
            layout.setVisibility(VISIBLE);
            pro.setVisibility(GONE);
        }
    }
}
