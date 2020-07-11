package com.jidcoo.android.widgettest.custom;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.jidcoo.android.widget.commentview.CommentView;
import com.jidcoo.android.widget.commentview.callback.CustomCommentItemCallback;
import com.jidcoo.android.widget.commentview.callback.CustomReplyItemCallback;
import com.jidcoo.android.widget.commentview.callback.OnCommentLoadMoreCallback;
import com.jidcoo.android.widget.commentview.callback.OnItemClickCallback;
import com.jidcoo.android.widget.commentview.callback.OnPullRefreshCallback;
import com.jidcoo.android.widget.commentview.callback.OnReplyLoadMoreCallback;
import com.jidcoo.android.widgettest.R;
import com.jidcoo.android.widgettest.simple.LocalServer;

import java.lang.ref.WeakReference;

/**
 * 对于CommentView的自定义数据类型和布局的使用实例（使用本地测试数据）
 * 使用自定义样式配置器，自定义数据模型，自定义布局
 * <u>注意：使用自定义数据类型就必须自定义布局实现，否则会抛出数据模型的java.lang.ClassCastException异常</u><br></br>
 *
 * @author Jidcoo
 */
public class CustomUseInLocalActivity extends AppCompatActivity {
    private CommentView commentView;
    private Gson gson;
    private LocalServer localServer;
    private LinearLayout mContainer;

    //
    private static class ActivityHandler extends Handler {
        private final WeakReference<CustomUseInLocalActivity> mActivity;

        public ActivityHandler(CustomUseInLocalActivity activity) {
            mActivity = new WeakReference<CustomUseInLocalActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            CustomUseInLocalActivity activity = mActivity.get();
            if (activity != null) {
                switch (msg.what) {
                    case 1:
                        //commentView.loadFailed(true);//实际网络请求中如果加载失败调用此方法
                        activity.commentView.loadComplete(activity.gson.fromJson((String) msg.obj, CustomCommentModel.class));
                        break;
                    case 2:
                        //commentView.refreshFailed();//实际网络请求中如果加载失败调用此方法
                        activity.commentView.refreshComplete(activity.gson.fromJson((String) msg.obj, CustomCommentModel.class));
                        break;
                    case 3:
                        //commentView.loadFailed();//实际网络请求中如果加载失败调用此方法
                        activity.commentView.loadMoreComplete(activity.gson.fromJson((String) msg.obj, CustomCommentModel.class));
                        break;
                    case 4:
                        //commentView.loadMoreReplyFailed();//实际网络请求中如果加载失败调用此方法
                        activity.commentView.loadMoreReplyComplete(activity.gson.fromJson((String) msg.obj, CustomCommentModel.class));
                        break;
                }
            }
        }
    }

    private final ActivityHandler activityHandler = new ActivityHandler(this);
    //

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_use);
        gson = new Gson();
        setTitle("WidgetTest(仿微信)");
        localServer = new LocalServer(this, "api2");
        mContainer = findViewById(R.id.container);
        commentView = new CommentView(this, mContainer);
        //设置空视图
        //commentView.setEmptyView(view);
        //设置错误视图
        //commentView.setErrorView(view);
        //添加控件头布局
        // commentView.addHeaderView();
        commentView.setViewStyleConfigurator(new CustomViewStyleConfigurator(this));

        commentView.callbackBuilder()
                //自定义评论布局(必须使用ViewHolder机制)--CustomCommentItemCallback<C> 泛型C为自定义评论数据类
                .customCommentItem(new CustomCommentItemCallback<CustomCommentModel.CustomComment>() {
                    @Override
                    public View buildCommentItem(int groupPosition, CustomCommentModel.CustomComment comment, LayoutInflater inflater, View convertView, ViewGroup parent) {
                        //使用方法就像adapter里面的getView()方法一样
                        final CustomCommentViewHolder holder;
                        if (convertView == null) {
                            //使用自定义布局
                            convertView = inflater.inflate(R.layout.custom_item_comment, parent, false);
                            holder = new CustomCommentViewHolder(convertView);
                            //必须使用ViewHolder机制
                            convertView.setTag(holder);
                        } else {
                            holder = (CustomCommentViewHolder) convertView.getTag();
                        }
                        holder.prizes.setText("100");
                        holder.userName.setText(comment.getPosterName());
                        holder.comment.setText(comment.getData());
                        return convertView;
                    }
                })
                //自定义评论布局(必须使用ViewHolder机制）
                // 并且自定义ViewHolder类必须继承自com.jidcoo.android.widget.commentview.view.ViewHolder
                // --CustomReplyItemCallback<R> 泛型R为自定义回复数据类
                .customReplyItem(new CustomReplyItemCallback<CustomCommentModel.CustomComment.CustomReply>() {
                    @Override
                    public View buildReplyItem(int groupPosition, int childPosition, boolean isLastReply, CustomCommentModel.CustomComment.CustomReply reply, LayoutInflater inflater, View convertView, ViewGroup parent) {
                        //使用方法就像adapter里面的getView()方法一样
                        //此类必须继承自com.jidcoo.android.widget.commentview.view.ViewHolder，否则报错
                        CustomReplyViewHolder holder = null;
                        //此类必须继承自com.jidcoo.android.widget.commentview.view.ViewHolder，否则报错
                        if (convertView == null) {
                            //使用自定义布局
                            convertView = inflater.inflate(R.layout.custom_item_reply, parent, false);
                            holder = new CustomReplyViewHolder(convertView);
                            //必须使用ViewHolder机制
                            convertView.setTag(holder);
                        } else {
                            holder = (CustomReplyViewHolder) convertView.getTag();
                        }
                        holder.userName.setText(reply.getReplierName());
                        holder.reply.setText(reply.getData());
                        holder.prizes.setText("100");
                        return convertView;
                    }
                })
                //下拉刷新回调
                .setOnPullRefreshCallback(new MyOnPullRefreshCallback())
                //评论、回复Item的点击回调（点击事件回调）
                .setOnItemClickCallback(new MyOnItemClickCallback())
                //回复数据加载更多回调（加载更多回复）
                .setOnReplyLoadMoreCallback(new MyOnReplyLoadMoreCallback())
                //上拉加载更多回调（加载更多评论数据）
                .setOnCommentLoadMoreCallback(new MyOnCommentLoadMoreCallback())
                //设置完成后必须调用CallbackBuilder的buildCallback()方法，否则设置的回调无效
                .buildCallback();
        load(1, 1);
    }


    private void load(int code, int handlerId) {
        localServer.get(code, activityHandler, handlerId);
    }


    /**
     * 下拉刷新回调类
     */
    class MyOnPullRefreshCallback implements OnPullRefreshCallback {

        @Override
        public void refreshing() {
            load(1, 2);


        }

        @Override
        public void complete() {
            //加载完成后的操作
        }

        @Override
        public void failure(String msg) {

        }
    }


    /**
     * 上拉加载更多回调类
     */
    class MyOnCommentLoadMoreCallback implements OnCommentLoadMoreCallback {

        @Override
        public void loading(int currentPage, int willLoadPage, boolean isLoadedAllPages) {
            //因为测试数据写死了，所以这里的逻辑也是写死的
            if (!isLoadedAllPages) {
                if (willLoadPage == 2) {
                    load(2, 3);
                } else if (willLoadPage == 3) {
                    load(3, 3);
                }
            }
        }

        @Override
        public void complete() {
            //加载完成后的操作
        }

        @Override
        public void failure(String msg) {
        }
    }

    /**
     * 回复加载更多回调类
     */
    class MyOnReplyLoadMoreCallback implements OnReplyLoadMoreCallback<CustomCommentModel.CustomComment.CustomReply> {


        @Override
        public void loading(CustomCommentModel.CustomComment.CustomReply reply, int willLoadPage) {
            if (willLoadPage == 2) {
                load(5, 4);
            } else if (willLoadPage == 3) {
                load(6, 4);
            }
        }

        @Override
        public void complete() {

        }

        @Override
        public void failure(String msg) {

        }
    }

    /**
     * 点击事件回调
     */
    class MyOnItemClickCallback implements OnItemClickCallback<CustomCommentModel.CustomComment, CustomCommentModel.CustomComment.CustomReply> {


        @Override
        public void commentItemOnClick(int position, CustomCommentModel.CustomComment comment, View view) {
            Toast.makeText(CustomUseInLocalActivity.this, "你点击的评论：" + comment.getData(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void replyItemOnClick(int c_position, int r_position, CustomCommentModel.CustomComment.CustomReply reply, View view) {
            Toast.makeText(CustomUseInLocalActivity.this, "你点击的回复：" + reply.getData(), Toast.LENGTH_SHORT).show();
        }
    }

}
