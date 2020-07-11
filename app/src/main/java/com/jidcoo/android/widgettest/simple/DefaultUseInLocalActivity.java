package com.jidcoo.android.widgettest.simple;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.jidcoo.android.widget.commentview.CommentView;
import com.jidcoo.android.widget.commentview.callback.OnCommentLoadMoreCallback;
import com.jidcoo.android.widget.commentview.callback.OnItemClickCallback;
import com.jidcoo.android.widget.commentview.callback.OnPullRefreshCallback;
import com.jidcoo.android.widget.commentview.callback.OnReplyLoadMoreCallback;
import com.jidcoo.android.widget.commentview.callback.OnScrollCallback;
import com.jidcoo.android.widget.commentview.defaults.DefaultCommentModel;
import com.jidcoo.android.widget.commentview.defaults.DefaultViewStyleConfigurator;
import com.jidcoo.android.widgettest.R;

/**
 * 对于CommentView的默认数据模型的简单使用（使用本地测试数据）
 * 使用默认样式配置器，默认数据模型，默认布局
 *
 * @author Jidcoo
 */
public class DefaultUseInLocalActivity extends AppCompatActivity {
    private CommentView commentView;
    private Gson gson;
    private EditText user, editor;
    private Button button;
    private boolean isReply = false;
    private boolean isChildReply = false;
    private int cp, rp;
    private long fid, pid;
    //本地测试数据
    private LocalServer localServer;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 1:
                    //commentView.loadFailed(true);//实际网络请求中如果加载失败调用此方法
                    commentView.loadComplete(gson.fromJson((String) msg.obj, DefaultCommentModel.class));
                    break;
                case 2:
                    //commentView.refreshFailed();//实际网络请求中如果加载失败调用此方法
                    commentView.refreshComplete(gson.fromJson((String) msg.obj, DefaultCommentModel.class));
                    break;
                case 3:
                    //commentView.loadFailed();//实际网络请求中如果加载失败调用此方法
                    commentView.loadMoreComplete(gson.fromJson((String) msg.obj, DefaultCommentModel.class));
                    break;
                case 4:
                    //commentView.loadMoreReplyFailed();//实际网络请求中如果加载失败调用此方法
                    commentView.loadMoreReplyComplete(gson.fromJson((String) msg.obj, DefaultCommentModel.class));
                    break;
            }

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_use);
        setTitle("WidgetTest(默认样式)");
        gson = new Gson();
        localServer = new LocalServer(this, "api1");
        user = findViewById(R.id.user);
        editor = findViewById(R.id.editor);
        button = findViewById(R.id.button);
        commentView = findViewById(R.id.myCommentView);//初始化控件
        commentView.setViewStyleConfigurator(new DefaultViewStyleConfigurator(this));
        //设置空视图
        //commentView.setEmptyView(view);
        //设置错误视图
        //commentView.setErrorView(view);
        //获取callbackBuilder添加事件回调
        commentView.callbackBuilder()
                //下拉刷新回调
                .setOnPullRefreshCallback(new MyOnPullRefreshCallback())
                //上拉加载更多回调（加载更多评论数据）
                .setOnCommentLoadMoreCallback(new MyOnCommentLoadMoreCallback())
                //回复数据加载更多回调（加载更多回复）
                .setOnReplyLoadMoreCallback(new MyOnReplyLoadMoreCallback())
                //评论、回复Item的点击回调（点击事件回调）
                .setOnItemClickCallback(new MyOnItemClickCallback())
                //滚动事件回调
                .setOnScrollCallback(new MyOnScrollCallback())
                //设置完成后必须调用CallbackBuilder的buildCallback()方法，否则设置的回调无效
                .buildCallback();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userStr = user.getText().toString();
                String data = editor.getText().toString();
                if (!userStr.isEmpty() && !data.isEmpty()) {
                    if (isReply && isChildReply) {
                        //现在需要构建一个回复数据实体类
                        DefaultCommentModel.Comment.Reply reply = new DefaultCommentModel.Comment.Reply();
                        reply.setKid(fid);
                        reply.setReplierName(userStr);
                        reply.setReply(data);
                        reply.setDate(System.currentTimeMillis());
                        reply.setPid(pid);
                        commentView.addReply(reply, cp);
                    } else if (isReply && !isChildReply) {
                        //现在需要构建一个回复数据实体类
                        DefaultCommentModel.Comment.Reply reply = new DefaultCommentModel.Comment.Reply();
                        reply.setKid(fid);
                        reply.setReplierName(userStr);
                        reply.setReply(data);
                        reply.setDate(System.currentTimeMillis());
                        reply.setPid(0);
                        commentView.addReply(reply, cp);
                    } else {
                        DefaultCommentModel.Comment comment = new DefaultCommentModel.Comment();
                        comment.setFid(System.currentTimeMillis());
                        comment.setId(comment.getFid() + 1);
                        comment.setDate(comment.getFid());
                        comment.setPid(0);
                        comment.setPosterName(userStr);
                        comment.setComment(data);
                        commentView.addComment(comment);
                    }
                } else {
                    Toast.makeText(DefaultUseInLocalActivity.this, "用户名和内容都不能为空", Toast.LENGTH_LONG).show();
                }
            }
        });
        load(1, 1);
    }


    private void load(int code, int handlerId) {
        localServer.get(code, handler, handlerId);
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
            if (msg != null)
                Toast.makeText(DefaultUseInLocalActivity.this, msg, Toast.LENGTH_LONG).show();
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
            if (msg != null)
                Toast.makeText(DefaultUseInLocalActivity.this, msg, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 回复加载更多回调类
     */
    class MyOnReplyLoadMoreCallback implements OnReplyLoadMoreCallback<DefaultCommentModel.Comment.Reply> {

        @Override
        public void loading(DefaultCommentModel.Comment.Reply reply, int willLoadPage) {
            //因为测试数据写死了，所以这里的逻辑也是写死的
            //在默认回复数据模型中，kid作为父级索引
            //为了扩展性，把对应的具体模型传了出来，可根据具体需求具体使用
            if (reply.getKid() == 1593699394031L) {
                load(4, 4);
            } else {
                if (willLoadPage == 2) {
                    load(5, 4);
                } else if (willLoadPage == 3) {
                    load(6, 4);
                }
            }
        }

        @Override
        public void complete() {
            //加载完成后的操作
        }

        @Override
        public void failure(String msg) {
            if (msg != null)
                Toast.makeText(DefaultUseInLocalActivity.this, msg, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 点击事件回调
     */
    class MyOnItemClickCallback implements OnItemClickCallback<DefaultCommentModel.Comment, DefaultCommentModel.Comment.Reply> {

        @Override
        public void commentItemOnClick(int position, DefaultCommentModel.Comment comment, View view) {
            isReply = true;
            cp = position;
            isChildReply = false;
            fid = comment.getFid();
            editor.setHint("回复@" + comment.getPosterName() + ":");
        }

        @Override
        public void replyItemOnClick(int c_position, int r_position, DefaultCommentModel.Comment.Reply reply, View view) {
            isReply = true;
            cp = c_position;
            rp = r_position;
            isChildReply = true;
            fid = reply.getKid();
            pid = reply.getId();
            editor.setHint("回复@" + reply.getReplierName() + ":");
        }
    }

    class MyOnScrollCallback implements OnScrollCallback {

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            isReply = false;
//            if (editor.getText().toString().length()>0){
//                editor.setText("");
//            }
//            editor.setHint("发表你的评论吧~");
        }

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {

        }

        @Override
        public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {


        }
    }

}
