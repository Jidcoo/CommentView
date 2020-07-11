package com.jidcoo.android.widget.commentview.defaults;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jidcoo.android.widget.commentview.R;
import com.jidcoo.android.widget.commentview.view.RoundAngleImageView;

/**
 * 默认评论布局
 * <p>评论布局无特殊要求，可根据需要实现</p>
 *
 * @author Jidcoo
 */
public class DefaultCommentHolder {
    public TextView posterName, time, content, prizes;
    public ImageView prize;
    public RoundAngleImageView ico;

    public DefaultCommentHolder(View view) {
        posterName = (TextView) view.findViewById(R.id.comment_item_userName);
        time = (TextView) view.findViewById(R.id.comment_item_time);
        content = (TextView) view.findViewById(R.id.comment_item_content);
        prize = (ImageView) view.findViewById(R.id.comment_item_like);
        prizes = (TextView) view.findViewById(R.id.prizes);
        ico = (RoundAngleImageView) view.findViewById(R.id.ico);
    }
}
