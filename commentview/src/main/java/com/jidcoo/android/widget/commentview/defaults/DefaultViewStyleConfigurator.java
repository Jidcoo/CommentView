package com.jidcoo.android.widget.commentview.defaults;

import android.content.Context;
import android.graphics.Typeface;

import com.jidcoo.android.widget.commentview.utils.ViewUtil;
import com.jidcoo.android.widget.commentview.view.ViewStyleConfigurator;

/**
 * 默认样式配置器
 * <p>此配置器为标准模板</p>
 * <p>自定义样式配置器必须继承自ViewStyleConfigurator，查看{@link ViewStyleConfigurator}</p>
 * <p>样式变量说明，请查看{@link ViewStyleConfigurator}</p>
 * <p>实际上，自定义样式配置器可以直接复制本类，然后再根据具体把样式修改就好了</p>
 *
 * @author Jidcoo
 */
public class DefaultViewStyleConfigurator extends ViewStyleConfigurator {
    /*样式变量说明：
            1、refreshViewColor
    类型：String
    说明：下拉刷新后出现的圆形滚动条的颜色
    赋值样例：#000000
            2、lmv_showText(正常显示状态)
    类型：String
    说明：当回复数据Item可以加载更多（分页加载）时，在最后一条回复Item的View下面显示的文字
    赋值样例：展开更多回复
3、lmv_textSize
    类型：int
    说明：第二条说明中的文字大小
    赋值样例：14
            4、lmv_textColor
    类型：String
    说明：第二条说明中的文字颜色
    赋值样例：#000000
            5、lmv_textStyle
    类型：Typeface，查看Typeface
    说明：第二条说明中的文字样式(正常、斜体、加粗)
    赋值样例：Typeface.defaultFromStyle(Typeface.NORMAL)
            6、lmv_loading_showText(加载中状态)
    类型：String
    说明：当回复数据Item可以加载更多（分页加载）时，在最后一条回复Item的加载更多View被点击后显示的文字
    赋值样例：加载中
7、lmv_loading_textSize
    类型：int
    说明：第六条说明中的文字大小
    赋值样例：14
            8、lmv_loading_textColor
    类型：String
    说明：第六条说明中的文字颜色
    赋值样例：#000000
            9、lmv_loading_textStyle
    类型：Typeface，查看Typeface
    说明：第六条说明中的文字样式(正常、斜体、加粗)
    赋值样例：Typeface.defaultFromStyle(Typeface.NORMAL)
            10、lmv_loading_progressBarColor
    类型：String
    说明：当最后一条回复ItemView的可加载更多View状态为正在加载状态时， 第六条说明中的文字右边会显示一个圆形progressBar以增强UI加载效果， 该属性是这个progressBar的颜色值
    赋值样例：#000000
            11、lmv_loading_progressBarSize
    类型：int（要求单位：px，因为需要把(dp/dip值转换为px值)）
    说明：第十条说明中的progressBar控件的大小尺寸
    赋值样例：ViewUtil.dpToPx(14)
            12、lmv_adjustMargins
    类型：Boolean(默认为false)
    说明：是否调整最后一条回复Item的加载更多View的边距，如果自定义回复布局，可能需要 调整这个View的边距来使布局更美观，一般情况下都需要调整边距，所以这个属性一般都为true
    赋值样例：true
            13、lmv_adjustMarginsLeft
    类型：int（要求单位：px，因为需要把(dp/dip值转换为px值)）
    说明：第十二条说明中的view的左边距，如果lmv_adjustMargins为false,则此属性无效
    赋值样例：ViewUtil.dpToPx(14)
            14、lmv_adjustMarginsTop
    类型：int（要求单位：px，因为需要把(dp/dip值转换为px值)）
    说明：第十二条说明中的view的上边距，如果lmv_adjustMargins为false,则此属性无效
    赋值样例：ViewUtil.dpToPx(14)
            15、lmv_adjustMarginsRight
    类型：int（要求单位：px，因为需要把(dp/dip值转换为px值)）
    说明：第十二条说明中的view的右边距，如果lmv_adjustMargins为false,则此属性无效
    赋值样例：ViewUtil.dpToPx(14)
            16、lmv_adjustMarginsBottom
    类型：int（要求单位：px，因为需要把(dp/dip值转换为px值)）
    说明：第十二条说明中的view的下边距，如果lmv_adjustMargins为false,则此属性无效
    赋值样例：ViewUtil.dpToPx(14)
            17、dividerColor
    类型：String
    说明：控件Item的分割线颜色（包含一级分割线和二级分割线）
    赋值样例：#000000
            18、dividerHeight
    类型：int（要求单位：px，因为需要把(dp/dip值转换为px值)）
    说明：控件Item的分割线高度（包含一级分割线和二级分割线）
    赋值样例：ViewUtil.dpToPx(1)，高度一般为1dp
19、isDrawChildDivider
    类型：Boolean(默认为false)
    说明：是否绘制某一条评论下的最后一条回复Item与下一条评论的Item的分割线，默认不绘制， 可根据具体需要选择是否绘制
    赋值样例：true
            20、c_divider_adjustMargins
    类型：Boolean(默认为false)
    说明：是否调整第十七条说明中的分割线的边距，如果isDrawDivider为false,则此属性无效。 因为第十九条说明中的分割线宽度固定为match_parent(即铺满全屏)，但是可以通过调整分割线的边距 来间接调整分割线的宽度和位置，可根据具体需要选择是否调整分割线的边距
    赋值样例：true
            21、c_divider_adjustMarginsLeft
    类型：int（要求单位：px，因为需要把(dp/dip值转换为px值)）
    说明：第十九条说明中的分割线的左边距，如果divider_adjustMargins为false,则此属性无效
    赋值样例：ViewUtil.dpToPx(1)
            22、c_divider_adjustMarginsTop
    类型：int（要求单位：px，因为需要把(dp/dip值转换为px值)）
    说明：第十九条说明中的分割线的上边距，如果divider_adjustMargins为false,则此属性无效
    赋值样例：ViewUtil.dpToPx(1)
            23、c_divider_adjustMarginsRight
    类型：int（要求单位：px，因为需要把(dp/dip值转换为px值)）
    说明：第十九条说明中的分割线的右边距，如果divider_adjustMargins为false,则此属性无效
    赋值样例：ViewUtil.dpToPx(1)
            24、c_divider_adjustMarginsBottom
    类型：int（要求单位：px，因为需要把(dp/dip值转换为px值)）
    说明：第十九条说明中的分割线的下边距，如果divider_adjustMargins为false,则此属性无效
    赋值样例：ViewUtil.dpToPx(1)
            25、f_divider_adjustMargins
    类型：Boolean(默认为false)
    说明：是否调整评论item的分割线的边距
    赋值样例：true
            26、f_divider_adjustMarginsLeft
    类型：int（要求单位：px，因为需要把(dp/dip值转换为px值)）
    说明：评论item的分割线的左边距
    赋值样例：ViewUtil.dpToPx(1)
            27、f_divider_adjustMarginsTop
    类型：int（要求单位：px，因为需要把(dp/dip值转换为px值)）
    说明：评论item的分割线的上边距
    赋值样例：ViewUtil.dpToPx(1)
            28、f_divider_adjustMarginsRight
    类型：int（要求单位：px，因为需要把(dp/dip值转换为px值)）
    说明：评论item的分割线的右边距
    赋值样例：ViewUtil.dpToPx(1)
            29、f_divider_adjustMarginsBottom
    类型：int（要求单位：px，因为需要把(dp/dip值转换为px值)）
    说明：评论item的分割线的下边距
    赋值样例：ViewUtil.dpToPx(1)
            30、lm_footerProgressBarSize
    类型：int（要求单位：px，因为需要把(dp/dip值转换为px值)）
    说明：上拉刷新时，控件底部会有一个圆形ProgressBar来增加加载中的UI效果，此属性为ProgressBar的大小
    赋值样例：ViewUtil.dpToPx(1)
            31、lm_footerProgressBarColor
    类型：String
    说明：第三十条说明中的ProgressBar的颜色
    赋值样例：#000000
            32、lm_footerProgressBar_adjustMargins
    类型：Boolean(默认为false)
    说明：是否调整第三十条说明中的ProgressBar的边距
    赋值样例：true
            33、lm_footerProgressBar_adjustMarginsLeft
    类型：int（要求单位：px，因为需要把(dp/dip值转换为px值)）
    说明：第三十条说明中的ProgressBar的左边距，如果lm_footerProgressBar_adjustMargins为false,则此属性无效
    赋值样例：ViewUtil.dpToPx(14)
            34、lm_footerProgressBar_adjustMarginsTop
    类型：int（要求单位：px，因为需要把(dp/dip值转换为px值)）
    说明：第三十条说明中的ProgressBar的上边距，如果lm_footerProgressBar_adjustMargins为false,则此属性无效
    赋值样例：ViewUtil.dpToPx(14)
            35、lm_footerProgressBar_adjustMarginsRight
    类型：int（要求单位：px，因为需要把(dp/dip值转换为px值)）
    说明：第三十条说明中的ProgressBar的右边距，如果lm_footerProgressBar_adjustMargins为false,则此属性无效
    赋值样例：ViewUtil.dpToPx(14)
            36、lm_footerProgressBar_adjustMarginsBottom
    类型：int（要求单位：px，因为需要把(dp/dip值转换为px值)）
    说明：第三十条说明中的ProgressBar的下边距，如果lm_footerProgressBar_adjustMargins为false,则此属性无效
    赋值样例：ViewUtil.dpToPx(14)
            37、lm_footer_text
    类型：String
    说明：当所有数据加载完成后控件底部textView显示的文字
    赋值样例：哈哈，所有评论都在这了
38、lm_footer_textColor
    类型：String
    说明：第三十七条说明中的textView文字的颜色
    赋值样例：#666666
            39、lm_footer_textSize
    类型：int
    说明：第三十七条说明中的textView文字的大小
    赋值样例：14
            40、lm_footer_textStyle
    类型：Typeface
    说明：第三十七条说明中的textView文字的样式（正常，斜体、加粗）
    赋值样例：Typeface.defaultFromStyle(Typeface.NORMAL)
            41、lm_footer_text_adjustMargins
    类型：Boolean(默认为false)
    说明：是否调整第三十七条说明中的textView的边距
    赋值样例：true
            42、lm_footer_text_adjustMarginsLeft
    类型：int（要求单位：px，因为需要把(dp/dip值转换为px值)）
    说明：第三十七条说明中的textView的左边距，如果lm_footer_text_adjustMargins为false,则此属性无效
    赋值样例：ViewUtil.dpToPx(14)
            43、lm_footer_text_adjustMarginsTop
    类型：int（要求单位：px，因为需要把(dp/dip值转换为px值)）
    说明：第三十七条说明中的textView的上边距，如果lm_footer_text_adjustMargins为false,则此属性无效
    赋值样例：ViewUtil.dpToPx(14)
            44、lm_footer_text_adjustMarginsRight
    类型：int（要求单位：px，因为需要把(dp/dip值转换为px值)）
    说明：第三十七条说明中的textView的右边距，如果lm_footer_text_adjustMargins为false,则此属性无效
    赋值样例：ViewUtil.dpToPx(14)
            45、lm_footer_text_adjustMarginsBottom
    类型：int（要求单位：px，因为需要把(dp/dip值转换为px值)）
    说明：第三十七条说明中的textView的下边距，如果lm_footer_text_adjustMargins为false,则此属性无效
    赋值样例：ViewUtil.dpToPx(14)
*/
    public DefaultViewStyleConfigurator(Context context) {
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
        lmv_adjustMarginsLeft = ViewUtil.dpToPx(88, context);//dp
        lmv_adjustMarginsTop = ViewUtil.dpToPx(5, context);//dp
        lmv_adjustMarginsRight = 0;//dp
        lmv_adjustMarginsBottom = ViewUtil.dpToPx(5, context);//dp
        //*********//回复加载更多控件样式//*************//
        //*********//分割线总样式//*************//
        dividerColor = "#f0f0f0";
        dividerHeight = ViewUtil.dpToPx(1, context);//dp
        //*********//分割线总样式//*************//
        //*********//回复Item最后一项的下划线//*************//
        isDrawChildDivider = true;
        c_divider_adjustMargins = true;
        c_divider_adjustMarginsLeft = ViewUtil.dpToPx(88, context);//dp
        c_divider_adjustMarginsTop = ViewUtil.dpToPx(5, context);//dp
        c_divider_adjustMarginsRight = 0;//dp
        c_divider_adjustMarginsBottom = ViewUtil.dpToPx(5, context);//dp
        //*********//回复Item最后一项的下划线//*************//
        //*********//评论Item的分割线//*************//
        f_divider_adjustMargins = false;
        f_divider_adjustMarginsLeft = 0;//dp
        f_divider_adjustMarginsTop = 0;//dp
        f_divider_adjustMarginsRight = 0;//dp
        f_divider_adjustMarginsBottom = 0;//dp
        //*********//评论Item的分割线//*************//
        //*********//底部上拉加载更多圆形ProgressBar的大小和颜色//*************//
        lm_footerProgressBarSize = ViewUtil.dpToPx(20, context);//dp
        lm_footerProgressBarColor = "#c3c8cb";
        lm_footerProgressBar_adjustMargins = false;
        lm_footerProgressBar_adjustMarginsLeft = 0;//dp
        lm_footerProgressBar_adjustMarginsTop = 0;//dp
        lm_footerProgressBar_adjustMarginsRight = 0;//dp
        lm_footerProgressBar_adjustMarginsBottom = 0;//dp
        //*********//底部上拉加载更多圆形ProgressBar的大小和颜色//*************//


        //*********//底部上拉加载更多完成后的textView//*************//
        //文本,颜色、大小、样式、边距
        lm_footer_text = "评论都给你看完了哦~";
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
