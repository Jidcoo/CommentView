package com.jidcoo.android.widget.commentview.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jidcoo.android.widget.commentview.R;


/**
 * 底部加载更多的布局
 *
 * @author Jidcoo
 */
public class LoadingMoreFootView extends LinearLayout {
    private TextView tipView;
    private ProgressBar bar;
    private Context context;
    private LinearLayout loading_view_layout;
    private LinearLayout end_layout;
    private boolean isSetStyle;

    public LoadingMoreFootView(Context context) {
        super(context);
        this.context = context;
        initView(context);
    }

    public void initView(Context context) {
        setGravity(Gravity.CENTER);
        setLayoutParams(new AbsListView.LayoutParams(
                AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.WRAP_CONTENT));
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.footer_layout,
                this, false);
        loading_view_layout = (LinearLayout) view.findViewById(R.id.loading_view_layout);
        end_layout = (LinearLayout) view.findViewById(R.id.end_layout);
        bar = new ProgressBar(context, null, android.R.attr.progressBarStyleSmall);

        addFootLoadingView(bar);
        tipView = new TextView(context);
        tipView.setGravity(Gravity.CENTER);
        addFootEndView(tipView);
        addView(view);
    }


    public void setStyle(
            int pgb_size, String pgb_color, boolean pgb_isAdjust,
            int pgb_left, int pgb_top, int pgb_right, int pgb_bottom,
            String footText, String footTextColor, int footTextSize,
            Typeface footTextStyle, boolean footText_isAdjust, int left,
            int top, int right, int bottom) {
        if (!isSetStyle) {
            if (bar != null) {
                if (pgb_isAdjust) {
                    ((LinearLayout.LayoutParams) bar.getLayoutParams()).setMargins(pgb_left, pgb_top, pgb_right, pgb_bottom);
                }
                bar.getLayoutParams().width = pgb_size;
                bar.getLayoutParams().height = pgb_size;
                bar.setIndeterminateTintMode(PorterDuff.Mode.SRC_ATOP);
                bar.setIndeterminateTintList(ColorStateList.valueOf(Color.parseColor(pgb_color)));

                if (tipView != null) {
                    if (footText_isAdjust) {
                        ((LinearLayout.LayoutParams) tipView.getLayoutParams()).setMargins(left, top, right, bottom);
                    }
                    tipView.setText(footText);
                    tipView.setTextColor(Color.parseColor(footTextColor));
                    tipView.setTextSize(footTextSize);
                    tipView.setTypeface(footTextStyle);

                }
                isSetStyle = true;
            }
        }
    }


    //设置底部加载中效果
    public void addFootLoadingView(View view) {
        loading_view_layout.removeAllViews();
        loading_view_layout.addView(view);
    }

    //设置底部到底了布局
    public void addFootEndView(View view) {
        end_layout.removeAllViews();
        end_layout.addView(view);
    }


    //设置已经没有更多数据
    public void setEnd() {
        setVisibility(VISIBLE);
        loading_view_layout.setVisibility(GONE);
        end_layout.setVisibility(VISIBLE);
    }


    public void setVisible() {
        setVisibility(VISIBLE);
        loading_view_layout.setVisibility(VISIBLE);
        end_layout.setVisibility(GONE);
    }


    public void setGone() {
        setVisibility(GONE);
    }

    public void setViewText(String text) {
        if (tipView != null && text != null)
            tipView.setText(text);
    }

}
