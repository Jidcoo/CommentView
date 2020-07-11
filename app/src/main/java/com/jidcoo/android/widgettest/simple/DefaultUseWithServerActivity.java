package com.jidcoo.android.widgettest.simple;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

import static com.jidcoo.android.widgettest.simple.HttpServer.IP;


/**
 * 对于CommentView的默认数据模型的简单使用（使用服务端）
 * 使用默认样式配置器，默认数据模型，默认布局
 * 需要用到服务端
 * 该实例需要用到服务端程序，如果需要用来测试可以邮件联系我：
 * jidcoo@163.com
 * <p>或者自行根据Json数据来编写后端的测试API</p>
 * @author Jidcoo
 */
public class DefaultUseWithServerActivity extends AppCompatActivity {
    private CommentView commentView;
    private String data;
    private Gson gson;
    private EditText user, editor;
    private Button button;
    private boolean isReply = false;
    private boolean isChildReply = false;
    private int cp, rp;
    private long fid, pid;
    private DefaultCommentModel.Comment.Reply reply;
    /**
     * 数据处理
     */
    private Handler myHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            data = (String) msg.obj;
            switch (msg.what) {
                case 1:
                    if (data == null) {
                        commentView.loadFailed(true);
                        return;
                    }
                    commentView.loadComplete(gson.fromJson(data, DefaultCommentModel.class));

                    break;
                case 2:
                    if (data == null) {
                        commentView.refreshFailed("请求数据失败，刷新失败", true);
                        return;
                    }
                    commentView.refreshComplete(gson.fromJson(data, DefaultCommentModel.class));
                    break;
                case 3:
                    if (data == null) {
                        commentView.loadMoreFailed("请求数据失败，加载更多失败", false);
                        return;
                    }
                    commentView.loadMoreComplete(gson.fromJson(data, DefaultCommentModel.class));
                    break;
                case 4:
                    if (data == null) {
                        commentView.loadMoreReplyFailed("请求数据失败，加载更多回复失败", false);
                        return;
                    }
                    commentView.loadMoreReplyComplete(gson.fromJson(data, DefaultCommentModel.class));
                    break;
                case 5:
                    //load(IP + "/api?do=gc", 2);
                    commentView.addReply(reply, cp);
                    break;
                case 6:
                    load(IP + "/api?do=gc", 2);
                    break;
            }
        }
    };

    /**
     * 网络请求
     *
     * @param url
     * @param code
     */
    private void load(String url, int code) {
        HttpServer.getData(url, myHandler, code);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_use);
        gson = new Gson();
        user = findViewById(R.id.user);
        editor = findViewById(R.id.editor);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userStr = user.getText().toString();
                String data = editor.getText().toString();
                if (!userStr.isEmpty() && !data.isEmpty()) {
                    if (isReply && isChildReply) {
                        //现在需要构建一个回复数据实体类
                        reply = new DefaultCommentModel.Comment.Reply();
                        reply.setKid(fid);
                        reply.setReplierName(userStr);
                        reply.setReply(data);
                        reply.setDate(System.currentTimeMillis());
                        reply.setPid(pid);
                        load(IP + "/servlet?do=ar&kid=" + fid + "&replierName=" + userStr + "&reply=" + data + "&pid=" + pid, 5);
                    } else if (isReply && !isChildReply) {
                        //现在需要构建一个回复数据实体类
                        reply = new DefaultCommentModel.Comment.Reply();
                        reply.setKid(fid);
                        reply.setReplierName(userStr);
                        reply.setReply(data);
                        reply.setDate(System.currentTimeMillis());
                        reply.setPid(0);
                        load(IP + "/servlet?do=ar&kid=" + fid + "&replierName=" + userStr + "&reply=" + data + "&pid=0", 5);
                    } else {
                        load(IP + "/servlet?do=ac&posterName=" + userStr + "&comment=" + data, 6);
                    }
                } else {
                    Toast.makeText(DefaultUseWithServerActivity.this, "用户名和内容都不能为空", Toast.LENGTH_LONG).show();
                }
            }
        });
        commentView = findViewById(R.id.myCommentView);//初始化控件
        commentView.setViewStyleConfigurator(new DefaultViewStyleConfigurator(this));
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        TextView tv1 = new TextView(this);
        tv1.setText("没有评论哦，快去抢沙发吧~");
        tv1.setGravity(Gravity.CENTER);
        TextView tv2 = new TextView(this);
        tv2.setText("网络出现异常，无法获取数据。\n如果需要服务端程序进行测试，请联系我：jidcoo@163.com");
        tv2.setGravity(Gravity.CENTER);
        tv1.setLayoutParams(lp);
        tv2.setLayoutParams(lp);
        //设置空数据视图
        commentView.setEmptyView(tv1);
        //设置错误视图
        commentView.setErrorView(tv2);
        //获取callbackBuilder添加事件回调
        commentView.callbackBuilder()
                .setOnPullRefreshCallback(new MyOnPullRefreshCallback())
                .setOnCommentLoadMoreCallback(new MyOnCommentLoadMoreCallback())
                .setOnReplyLoadMoreCallback(new MyOnReplyLoadMoreCallback())
                .setOnItemClickCallback(new MyOnItemClickCallback())
                .setOnScrollCallback(new MyOnScrollCallback())
                //设置完成后必须调用CallbackBuilder的buildCallback()方法，否则设置的回调无效
                .buildCallback();
        load(IP + "/api?do=gc", 1);
    }


    /**
     * 下拉刷新回调类
     */
    class MyOnPullRefreshCallback implements OnPullRefreshCallback {

        @Override
        public void refreshing() {
            load(IP + "/api?do=gc", 2);
        }

        @Override
        public void complete() {
            //加载完成后的操作
        }

        @Override
        public void failure(String msg) {
            if (msg != null)
                Toast.makeText(DefaultUseWithServerActivity.this, msg, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 上拉加载更多回调类
     */
    class MyOnCommentLoadMoreCallback implements OnCommentLoadMoreCallback {

        @Override
        public void loading(int currentPage, int willLoadPage, boolean isLoadedAllPages) {
            if (!isLoadedAllPages) {
                load(IP + "/api?do=gc&currentPage=" + willLoadPage, 3);
            }
        }

        @Override
        public void complete() {
            //加载完成后的操作
        }

        @Override
        public void failure(String msg) {
            if (msg != null)
                Toast.makeText(DefaultUseWithServerActivity.this, msg, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 回复加载更多回调类
     */
    class MyOnReplyLoadMoreCallback implements OnReplyLoadMoreCallback<DefaultCommentModel.Comment.Reply> {

        @Override
        public void loading(DefaultCommentModel.Comment.Reply reply, int willLoadPage) {
            //在默认回复数据模型中，kid作为父级索引
            //为了扩展性，把对应的具体模型传了出来，可根据具体需求具体使用
            load(IP + "/api?do=gr&kid=" + reply.getKid() + "&currentPage=" + willLoadPage, 4);
        }

        @Override
        public void complete() {
            //加载完成后的操作
        }

        @Override
        public void failure(String msg) {
            if (msg != null)
                Toast.makeText(DefaultUseWithServerActivity.this, msg, Toast.LENGTH_LONG).show();
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
            if (editor.getText().toString().length() > 0) {
                editor.setText("");
            }
            editor.setHint("发表你的评论吧~");
        }

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {

        }

        @Override
        public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

        }
    }

}
