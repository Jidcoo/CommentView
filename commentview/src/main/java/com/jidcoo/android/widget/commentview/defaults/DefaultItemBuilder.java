package com.jidcoo.android.widget.commentview.defaults;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jidcoo.android.widget.commentview.R;
import com.jidcoo.android.widget.commentview.callback.CustomCommentItemCallback;
import com.jidcoo.android.widget.commentview.callback.CustomReplyItemCallback;
import com.jidcoo.android.widget.commentview.model.CommentEnable;
import com.jidcoo.android.widget.commentview.utils.ViewUtil;

import java.util.List;

/**
 * 默认布局的实例构造器
 * <p>相当于getView的逻辑</p>
 * <p>如果使用自定义布局，需要实现CustomCommentItemCallback、CustomReplyItemCallback接口</p>
 * <p>自定义评论布局，查看{@link CustomCommentItemCallback}</p>
 * <p>自定义回复布局，查看{@link CustomReplyItemCallback}</p>
 *
 * @author Jidcoo
 */
public class DefaultItemBuilder {

    /**
     * 默认评论布局初始化
     *
     * @param convertView
     * @param parent
     * @param comment
     */
    public <C extends CommentEnable> View useDefaultCommentItem(LayoutInflater inflater, View convertView, ViewGroup parent, C comment, int groupPosition) {
        final DefaultCommentHolder defaultCommentHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_comment, parent, false);
            defaultCommentHolder = new DefaultCommentHolder(convertView);
            convertView.setTag(defaultCommentHolder);
        } else {
            defaultCommentHolder = (DefaultCommentHolder) convertView.getTag();
        }
        final Context context = convertView.getContext();
        //强制类型转换
        DefaultCommentModel.Comment tmp = (DefaultCommentModel.Comment) comment;
        defaultCommentHolder.posterName.setText(tmp.getPosterName());
        defaultCommentHolder.content.setText(tmp.getComment());
        defaultCommentHolder.time.setText(ViewUtil.getTime(tmp.getDate()));
        if (defaultCommentHolder.prizes.getTag() == null) {
            defaultCommentHolder.prizes.setTag(String.valueOf((int) (Math.random() * (1000 - 100 + 1) + 100)));
        }
        defaultCommentHolder.prizes.setText((String) defaultCommentHolder.prizes.getTag());
        return convertView;
    }


    /**
     * 默认回复布局初始化
     *
     * @param groupPosition
     * @param childPosition
     * @param convertView
     * @param parent
     */

    public <C extends CommentEnable> View useDefaultReplyItem(LayoutInflater inflater, int groupPosition, int childPosition, View convertView, ViewGroup parent, List<C> comments) {
        final DefaultReplyHolder defaultReplyHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_reply, parent, false);
            defaultReplyHolder = new DefaultReplyHolder(convertView);
            convertView.setTag(defaultReplyHolder);
        } else {
            defaultReplyHolder = (DefaultReplyHolder) convertView.getTag();
        }
        //强制类型转换
        DefaultCommentModel.Comment.Reply tmp = (DefaultCommentModel.Comment.Reply) comments.get(groupPosition).getReplies().get(childPosition);
        defaultReplyHolder.replierName.setText(tmp.getReplierName());
        String tmpStr = ViewUtil.getRelation(childPosition, (DefaultCommentModel.Comment) comments.get(groupPosition));
        defaultReplyHolder.content.setText(tmpStr + tmp.getReply());
        if (!tmpStr.isEmpty()) {
            int end_index = tmpStr.length() - 2;
            SpannableString span = new SpannableString(defaultReplyHolder.content.getText().toString());
            span.setSpan(new ForegroundColorSpan(Color.parseColor("#666666")), 3, end_index, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            defaultReplyHolder.content.setText(span);
        }
        if (defaultReplyHolder.prizes.getTag() == null) {
            defaultReplyHolder.prizes.setTag(String.valueOf((int) (Math.random() * (1000 - 100 + 1) + 100)));
        }
        defaultReplyHolder.time.setText(ViewUtil.getTime(tmp.getDate()));

        defaultReplyHolder.prizes.setText((String) defaultReplyHolder.prizes.getTag());
        return convertView;
    }
}
