package com.jidcoo.android.widgettest.custom;


import android.content.Context;
import android.graphics.Typeface;

import com.jidcoo.android.widget.commentview.utils.ViewUtil;
import com.jidcoo.android.widget.commentview.view.ViewStyleConfigurator;


public class CustomViewStyleConfigurator extends ViewStyleConfigurator {

    public CustomViewStyleConfigurator(Context context) {
        /**
         * 下拉刷新控件颜色
         */
        refreshViewColor = "#D81B60";
        //*********//回复加载更多控件样式//*************//
        //Part1//
        lmv_showText = "展开更多回复";
        lmv_textSize = 14;
        lmv_textColor = "#D81B60";
        lmv_textStyle = Typeface.defaultFromStyle(Typeface.NORMAL);
        //Part2//
        lmv_loading_showText = "加载中";
        lmv_loading_textSize = 14;
        lmv_loading_textColor = "#666666";
        lmv_loading_textStyle = Typeface.defaultFromStyle(Typeface.NORMAL);
        lmv_loading_progressBarColor = "#666666";
        lmv_loading_progressBarSize = ViewUtil.dpToPx(14, context);
        //Part3//
        lmv_adjustMargins = true;
        lmv_adjustMarginsLeft = ViewUtil.dpToPx(52, context);//dp
        lmv_adjustMarginsTop = ViewUtil.dpToPx(5, context);//dp
        lmv_adjustMarginsRight = 0;//dp
        lmv_adjustMarginsBottom = ViewUtil.dpToPx(5, context);//dp
        //*********//回复加载更多控件样式//*************//
        //*********//分割线总样式//*************//
        dividerColor = "#00000000";
        dividerHeight = ViewUtil.dpToPx(1, context);//dp
        //*********//分割线总样式//*************//
        //*********//回复Item最后一项的下划线//*************//
//        isDrawChildDivider = false;
//        c_divider_adjustMargins = true;
//        c_divider_adjustMarginsLeft = ViewUtil.dpToPx(88, context);//dp
//        c_divider_adjustMarginsTop = ViewUtil.dpToPx(5, context);//dp
//        c_divider_adjustMarginsRight = 0;//dp
//        c_divider_adjustMarginsBottom = ViewUtil.dpToPx(5, context);//dp
//        //*********//回复Item最后一项的下划线//*************//
//        //*********//评论Item的分割线//*************//
//        f_divider_adjustMargins = false;
//        f_divider_adjustMarginsLeft = 0;//dp
//        f_divider_adjustMarginsTop = 0;//dp
//        f_divider_adjustMarginsRight = 0;//dp
//        f_divider_adjustMarginsBottom = 0;//dp
        //*********//评论Item的分割线//*************//
        //*********//底部上拉加载更多圆形ProgressBar的大小和颜色//*************//
        lm_footerProgressBarSize = ViewUtil.dpToPx(20, context);//dp
        lm_footerProgressBarColor = "#666666";
        lm_footerProgressBar_adjustMargins = false;
        lm_footerProgressBar_adjustMarginsLeft = 0;//dp
        lm_footerProgressBar_adjustMarginsTop = 0;//dp
        lm_footerProgressBar_adjustMarginsRight = 0;//dp
        lm_footerProgressBar_adjustMarginsBottom = 0;//dp
        //*********//底部上拉加载更多圆形ProgressBar的大小和颜色//*************//


        //*********//底部上拉加载更多完成后的textView//*************//
        //文本,颜色、大小、样式、边距
        lm_footer_text = "到底了(⊙o⊙)！";
        lm_footer_textColor = "#c3c8cb";
        lm_footer_textSize = 14;
        lm_footer_textStyle = Typeface.defaultFromStyle(Typeface.NORMAL);
        lm_footer_text_adjustMargins = false;
        lm_footer_text_adjustMarginsLeft = 0;//dp
        lm_footer_text_adjustMarginsTop = 0;//dp
        lm_footer_text_adjustMarginsRight = 0;//dp
        lm_footer_text_adjustMarginsBottom = 0;//dp
        //*********//底部上拉加载更多完成后的textView//*************//
    }
}
