package com.jidcoo.android.widget.commentview.defaults;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jidcoo.android.widget.commentview.R;
import com.jidcoo.android.widget.commentview.view.RoundAngleImageView;
import com.jidcoo.android.widget.commentview.view.ViewHolder;

/**
 * 默认回复布局
 * <p>此类可作为自定义回复布局ViewHolder的模板</p>
 * <p><u>注意:</u></p>
 * <p>自定义回复布局ViewHolder必须继承自ViewHolder，查看{@link ViewHolder}</p>
 *
 * @author Jidcoo
 */
public class DefaultReplyHolder extends ViewHolder {
    public TextView replierName, time, content, prizes;
    public ImageView prize;
    public RoundAngleImageView ico;

    public DefaultReplyHolder(View view) {
        super(view);
        replierName = (TextView) view.findViewById(R.id.reply_item_userName);
        time = (TextView) view.findViewById(R.id.reply_item_time);
        content = (TextView) view.findViewById(R.id.reply_item_content);
        prize = (ImageView) view.findViewById(R.id.reply_item_like);
        prizes = (TextView) view.findViewById(R.id.prizes);
        ico = (RoundAngleImageView) view.findViewById(R.id.ico);
    }
}


