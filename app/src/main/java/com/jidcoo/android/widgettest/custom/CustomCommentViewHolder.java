package com.jidcoo.android.widgettest.custom;

import android.view.View;
import android.widget.TextView;

import com.jidcoo.android.widgettest.R;

public class CustomCommentViewHolder {
    public TextView userName, prizes, comment;
    public SampleCircleImageView ico;

    public CustomCommentViewHolder(View view) {
        userName = view.findViewById(R.id.user);
        prizes = view.findViewById(R.id.prizes);
        comment = view.findViewById(R.id.data);
        ico = view.findViewById(R.id.ico);
    }
}
