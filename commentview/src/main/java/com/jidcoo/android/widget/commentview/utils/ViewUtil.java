package com.jidcoo.android.widget.commentview.utils;

import android.content.Context;

import com.jidcoo.android.widget.commentview.defaults.DefaultCommentModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * 数据工具类
 *
 * @author Jidcoo
 */
public class ViewUtil {
    private static DateFormat dateFormat = new SimpleDateFormat("MM-dd");
    private static StringBuilder builder = new StringBuilder();

    /**
     * 获取评论/回复的时间差
     *
     * @param time
     * @return
     */
    public static String getTime(long time) {
        String timeStr = null;
        long nowTime = System.currentTimeMillis();
        int day = (int) ((nowTime - time) / 86400000);
        int hour = (int) ((nowTime - time) / 3600000);
        int minute = (int) ((nowTime - time) / 60000);
        int second = (int) ((nowTime - time) / 1000);
        if (day >= 365) {
            int c = day / 365;
            timeStr = c + "年前";
        } else if (day >= 1) {
            timeStr = dateFormat.format(time);
        } else if (hour >= 1) {
            timeStr = hour + "小时前";
        } else if (minute >= 1) {
            timeStr = minute + "分钟前";
        } else if (second > 0) {
            timeStr = second + "秒前";
        } else {
            timeStr = "刚刚";
        }
        return timeStr;
    }

    /**
     * 基于默认模型获取回复的楼层关系
     *
     * @param childPosition
     * @param comment
     * @return
     */
    public static String getRelation(int childPosition, DefaultCommentModel.Comment comment) {
        long pid = comment.getReplies().get(childPosition).getPid();
        if (pid == 0) {
            return "";
        } else {
            for (DefaultCommentModel.Comment.Reply r : comment.getReplies()) {
                if (r.getId() == pid) {
                    builder.delete(0, builder.length());
                    builder.append("回复 @");
                    builder.append(r.getReplierName());
                    builder.append(" ： ");
                    return builder.toString();
                }
            }
        }
        return "";
    }

    public static int dpToPx(int dps, Context context) {
        return Math.round(context.getResources().getDisplayMetrics().density * dps);
    }
}
