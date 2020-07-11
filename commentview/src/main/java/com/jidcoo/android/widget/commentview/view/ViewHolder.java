package com.jidcoo.android.widget.commentview.view;

import android.view.View;
import android.widget.LinearLayout;

import com.jidcoo.android.widget.commentview.R;

/**
 * 自定义回复布局必须继承此抽象类并在构造方法中调用父类构造方法，否则会报错。<p>
 * <br></br>
 * 在自定义回复布局中最外层必须使用LinearLayout并将布局id设置为“reply_rootView”，否则会报错。<p>
 *
 * @author Jidcoo
 */
public abstract class ViewHolder {
    /**
     * 自定义布局这个view必须为非空
     */
    public LinearLayout rootView;

    public ViewHolder(View view) {
        try {
            rootView = view.findViewById(R.id.reply_rootView);
        } catch (Exception e) {
            throw new RuntimeException("If you use a custom layout, the outermost layout must be a LinearLayout, and you need to set its id attribute value to \"reply_rootView\"");
        }
    }
}
