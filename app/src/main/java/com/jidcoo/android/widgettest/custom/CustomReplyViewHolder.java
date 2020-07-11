package com.jidcoo.android.widgettest.custom;

import android.view.View;
import android.widget.TextView;

import com.jidcoo.android.widget.commentview.view.ViewHolder;
import com.jidcoo.android.widgettest.R;

public class CustomReplyViewHolder extends ViewHolder {
    public TextView userName, prizes, reply;

    public CustomReplyViewHolder(View view) {
        super(view);
        userName = view.findViewById(R.id.user);
        prizes = view.findViewById(R.id.prizes);
        reply = view.findViewById(R.id.data);
    }
}
