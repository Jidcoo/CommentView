package com.jidcoo.android.widget.commentview.view;

import android.graphics.Typeface;


/**
 * 非可自定义控件样式配置器
 * <p>该样式配置器针对非自定义控件的样式的具体配置</p>
 * <p>可根据具体需求配置这部分的控件的样式</p>
 * <p>自定义样式配置器必须继承自本类</p>
 * <br></br>
 * <h3>样式变量说明：</h3>
 * <p>1、refreshViewColor </p>
 * <p>类型：String</p>
 * <p>说明：下拉刷新后出现的圆形滚动条的颜色</p>
 * <p>赋值样例：#000000</p>
 * <p>2、lmv_showText(正常显示状态) </p>
 * <p>类型：String</p>
 * <p>说明：当回复数据Item可以加载更多（分页加载）时，在最后一条回复Item的View下面显示的文字</p>
 * <p>赋值样例：展开更多回复</p>
 * <p>3、lmv_textSize </p>
 * <p>类型：int</p>
 * <p>说明：第二条说明中的文字大小</p>
 * <p>赋值样例：14</p>
 * <p>4、lmv_textColor </p>
 * <p>类型：String</p>
 * <p>说明：第二条说明中的文字颜色</p>
 * <p>赋值样例：#000000</p>
 * <p>5、lmv_textStyle </p>
 * <p>类型：Typeface，查看{@link Typeface}</p>
 * <p>说明：第二条说明中的文字样式(正常、斜体、加粗)</p>
 * <p>赋值样例：Typeface.defaultFromStyle(Typeface.NORMAL)</p>
 * <p>6、lmv_loading_showText(加载中状态) </p>
 * <p>类型：String</p>
 * <p>说明：当回复数据Item可以加载更多（分页加载）时，在最后一条回复Item的加载更多View被点击后显示的文字</p>
 * <p>赋值样例：加载中</p>
 * <p>7、lmv_loading_textSize </p>
 * <p>类型：int</p>
 * <p>说明：第六条说明中的文字大小</p>
 * <p>赋值样例：14</p>
 * <p>8、lmv_loading_textColor </p>
 * <p>类型：String</p>
 * <p>说明：第六条说明中的文字颜色</p>
 * <p>赋值样例：#000000</p>
 * <p>9、lmv_loading_textStyle </p>
 * <p>类型：Typeface，查看{@link Typeface}</p>
 * <p>说明：第六条说明中的文字样式(正常、斜体、加粗)</p>
 * <p>赋值样例：Typeface.defaultFromStyle(Typeface.NORMAL)</p>
 * <p>10、lmv_loading_progressBarColor </p>
 * <p>类型：String</p>
 * <p>说明：当最后一条回复ItemView的可加载更多View状态为正在加载状态时，
 * 第六条说明中的文字右边会显示一个圆形progressBar以增强UI加载效果，
 * 该属性是这个progressBar的颜色值</p>
 * <p>赋值样例：#000000</p>
 * <p>11、lmv_loading_progressBarSize </p>
 * <p>类型：int（要求单位：px，因为需要把(dp/dip值转换为px值)）</p>
 * <p>说明：第十条说明中的progressBar控件的大小尺寸</p>
 * <p>赋值样例：ViewUtil.dpToPx(14)</p>
 * <p>12、lmv_adjustMargins </p>
 * <p>类型：Boolean(默认为false)</p>
 * <p>说明：是否调整最后一条回复Item的加载更多View的边距，如果自定义回复布局，可能需要
 * 调整这个View的边距来使布局更美观，一般情况下都需要调整边距，所以这个属性一般都为true</p>
 * <p>赋值样例：true</p>
 * <p>13、lmv_adjustMarginsLeft </p>
 * <p>类型：int（要求单位：px，因为需要把(dp/dip值转换为px值)）</p>
 * <p>说明：第十二条说明中的view的左边距，如果lmv_adjustMargins为false,则此属性无效</p>
 * <p>赋值样例：ViewUtil.dpToPx(14)</p>
 * <p>14、lmv_adjustMarginsTop </p>
 * <p>类型：int（要求单位：px，因为需要把(dp/dip值转换为px值)）</p>
 * <p>说明：第十二条说明中的view的上边距，如果lmv_adjustMargins为false,则此属性无效</p>
 * <p>赋值样例：ViewUtil.dpToPx(14)</p>
 * <p>15、lmv_adjustMarginsRight </p>
 * <p>类型：int（要求单位：px，因为需要把(dp/dip值转换为px值)）</p>
 * <p>说明：第十二条说明中的view的右边距，如果lmv_adjustMargins为false,则此属性无效</p>
 * <p>赋值样例：ViewUtil.dpToPx(14)</p>
 * <p>16、lmv_adjustMarginsBottom </p>
 * <p>类型：int（要求单位：px，因为需要把(dp/dip值转换为px值)）</p>
 * <p>说明：第十二条说明中的view的下边距，如果lmv_adjustMargins为false,则此属性无效</p>
 * <p>赋值样例：ViewUtil.dpToPx(14)</p>
 * <p>17、dividerColor </p>
 * <p>类型：String</p>
 * <p>说明：控件Item的分割线颜色（包含一级分割线和二级分割线）</p>
 * <p>赋值样例：#000000</p>
 * <p>18、dividerHeight </p>
 * <p>类型：int（要求单位：px，因为需要把(dp/dip值转换为px值)）</p>
 * <p>说明：控件Item的分割线高度（包含一级分割线和二级分割线）</p>
 * <p>赋值样例：ViewUtil.dpToPx(1)，高度一般为1dp</p>
 * <p>19、isDrawChildDivider </p>
 * <p>类型：Boolean(默认为false)</p>
 * <p>说明：是否绘制某一条评论下的最后一条回复Item与下一条评论的Item的<u>分割线</u>，默认不绘制，
 * 可根据具体需要选择是否绘制</p>
 * <p>赋值样例：true</p>
 * <p>20、c_divider_adjustMargins </p>
 * <p>类型：Boolean(默认为false)</p>
 * <p>说明：是否调整第十七条说明中的分割线的边距，如果isDrawDivider为false,则此属性无效。
 * 因为第十九条说明中的分割线宽度固定为match_parent(即铺满全屏)，但是可以通过调整分割线的边距
 * 来间接调整分割线的宽度和位置，可根据具体需要选择是否调整分割线的边距</p>
 * <p>赋值样例：true</p>
 * <p>21、c_divider_adjustMarginsLeft </p>
 * <p>类型：int（要求单位：px，因为需要把(dp/dip值转换为px值)）</p>
 * <p>说明：第十九条说明中的分割线的左边距，如果divider_adjustMargins为false,则此属性无效</p>
 * <p>赋值样例：ViewUtil.dpToPx(1)</p>
 * <p>22、c_divider_adjustMarginsTop </p>
 * <p>类型：int（要求单位：px，因为需要把(dp/dip值转换为px值)）</p>
 * <p>说明：第十九条说明中的分割线的上边距，如果divider_adjustMargins为false,则此属性无效</p>
 * <p>赋值样例：ViewUtil.dpToPx(1)</p>
 * <p>23、c_divider_adjustMarginsRight </p>
 * <p>类型：int（要求单位：px，因为需要把(dp/dip值转换为px值)）</p>
 * <p>说明：第十九条说明中的分割线的右边距，如果divider_adjustMargins为false,则此属性无效</p>
 * <p>赋值样例：ViewUtil.dpToPx(1)</p>
 * <p>24、c_divider_adjustMarginsBottom </p>
 * <p>类型：int（要求单位：px，因为需要把(dp/dip值转换为px值)）</p>
 * <p>说明：第十九条说明中的分割线的下边距，如果divider_adjustMargins为false,则此属性无效</p>
 * <p>赋值样例：ViewUtil.dpToPx(1)</p>
 * <p>25、f_divider_adjustMargins </p>
 * <p>类型：Boolean(默认为false)</p>
 * <p>说明：是否调整评论item的分割线的边距</p>
 * <p>赋值样例：true</p>
 * <p>26、f_divider_adjustMarginsLeft </p>
 * <p>类型：int（要求单位：px，因为需要把(dp/dip值转换为px值)）</p>
 * <p>说明：评论item的分割线的左边距</p>
 * <p>赋值样例：ViewUtil.dpToPx(1)</p>
 * <p>27、f_divider_adjustMarginsTop </p>
 * <p>类型：int（要求单位：px，因为需要把(dp/dip值转换为px值)）</p>
 * <p>说明：评论item的分割线的上边距</p>
 * <p>赋值样例：ViewUtil.dpToPx(1)</p>
 * <p>28、f_divider_adjustMarginsRight </p>
 * <p>类型：int（要求单位：px，因为需要把(dp/dip值转换为px值)）</p>
 * <p>说明：评论item的分割线的右边距</p>
 * <p>赋值样例：ViewUtil.dpToPx(1)</p>
 * <p>29、f_divider_adjustMarginsBottom </p>
 * <p>类型：int（要求单位：px，因为需要把(dp/dip值转换为px值)）</p>
 * <p>说明：评论item的分割线的下边距</p>
 * <p>赋值样例：ViewUtil.dpToPx(1)</p>
 * <p>30、lm_footerProgressBarSize </p>
 * <p>类型：int（要求单位：px，因为需要把(dp/dip值转换为px值)）</p>
 * <p>说明：上拉刷新时，控件底部会有一个圆形ProgressBar来增加加载中的UI效果，此属性为ProgressBar的大小</p>
 * <p>赋值样例：ViewUtil.dpToPx(1)</p>
 * <p>31、lm_footerProgressBarColor </p>
 * <p>类型：String</p>
 * <p>说明：第三十条说明中的ProgressBar的颜色</p>
 * <p>赋值样例：#000000</p>
 * <p>32、lm_footerProgressBar_adjustMargins </p>
 * <p>类型：Boolean(默认为false)</p>
 * <p>说明：是否调整第三十条说明中的ProgressBar的边距</p>
 * <p>赋值样例：true</p>
 * <p>33、lm_footerProgressBar_adjustMarginsLeft </p>
 * <p>类型：int（要求单位：px，因为需要把(dp/dip值转换为px值)）</p>
 * <p>说明：第三十条说明中的ProgressBar的左边距，如果lm_footerProgressBar_adjustMargins为false,则此属性无效</p>
 * <p>赋值样例：ViewUtil.dpToPx(14)</p>
 * <p>34、lm_footerProgressBar_adjustMarginsTop </p>
 * <p>类型：int（要求单位：px，因为需要把(dp/dip值转换为px值)）</p>
 * <p>说明：第三十条说明中的ProgressBar的上边距，如果lm_footerProgressBar_adjustMargins为false,则此属性无效</p>
 * <p>赋值样例：ViewUtil.dpToPx(14)</p>
 * <p>35、lm_footerProgressBar_adjustMarginsRight </p>
 * <p>类型：int（要求单位：px，因为需要把(dp/dip值转换为px值)）</p>
 * <p>说明：第三十条说明中的ProgressBar的右边距，如果lm_footerProgressBar_adjustMargins为false,则此属性无效</p>
 * <p>赋值样例：ViewUtil.dpToPx(14)</p>
 * <p>36、lm_footerProgressBar_adjustMarginsBottom </p>
 * <p>类型：int（要求单位：px，因为需要把(dp/dip值转换为px值)）</p>
 * <p>说明：第三十条说明中的ProgressBar的下边距，如果lm_footerProgressBar_adjustMargins为false,则此属性无效</p>
 * <p>赋值样例：ViewUtil.dpToPx(14)</p>
 * <p>37、lm_footer_text</p>
 * <p>类型：String</p>
 * <p>说明：当所有数据加载完成后控件底部textView显示的文字</p>
 * <p>赋值样例：哈哈，所有评论都在这了</p>
 * <p>38、lm_footer_textColor</p>
 * <p>类型：String</p>
 * <p>说明：第三十七条说明中的textView文字的颜色</p>
 * <p>赋值样例：#666666</p>
 * <p>39、lm_footer_textSize</p>
 * <p>类型：int</p>
 * <p>说明：第三十七条说明中的textView文字的大小</p>
 * <p>赋值样例：14</p>
 * <p>40、lm_footer_textStyle</p>
 * <p>类型：Typeface</p>
 * <p>说明：第三十七条说明中的textView文字的样式（正常，斜体、加粗）</p>
 * <p>赋值样例：Typeface.defaultFromStyle(Typeface.NORMAL)</p>
 * <p>41、lm_footer_text_adjustMargins </p>
 * <p>类型：Boolean(默认为false)</p>
 * <p>说明：是否调整第三十七条说明中的textView的边距</p>
 * <p>赋值样例：true</p>
 * <p>42、lm_footer_text_adjustMarginsLeft </p>
 * <p>类型：int（要求单位：px，因为需要把(dp/dip值转换为px值)）</p>
 * <p>说明：第三十七条说明中的textView的左边距，如果lm_footer_text_adjustMargins为false,则此属性无效</p>
 * <p>赋值样例：ViewUtil.dpToPx(14)</p>
 * <p>43、lm_footer_text_adjustMarginsTop </p>
 * <p>类型：int（要求单位：px，因为需要把(dp/dip值转换为px值)）</p>
 * <p>说明：第三十七条说明中的textView的上边距，如果lm_footer_text_adjustMargins为false,则此属性无效</p>
 * <p>赋值样例：ViewUtil.dpToPx(14)</p>
 * <p>44、lm_footer_text_adjustMarginsRight </p>
 * <p>类型：int（要求单位：px，因为需要把(dp/dip值转换为px值)）</p>
 * <p>说明：第三十七条说明中的textView的右边距，如果lm_footer_text_adjustMargins为false,则此属性无效</p>
 * <p>赋值样例：ViewUtil.dpToPx(14)</p>
 * <p>45、lm_footer_text_adjustMarginsBottom </p>
 * <p>类型：int（要求单位：px，因为需要把(dp/dip值转换为px值)）</p>
 * <p>说明：第三十七条说明中的textView的下边距，如果lm_footer_text_adjustMargins为false,则此属性无效</p>
 * <p>赋值样例：ViewUtil.dpToPx(14)</p>
 * <br><br>
 *
 * @author Jidcoo
 */
public abstract class ViewStyleConfigurator {
    /**
     * 下拉刷新控件颜色
     */
    public String refreshViewColor;

    //*********//回复加载更多控件样式//*************//
    //Part1//
    public String lmv_showText;
    public int lmv_textSize;
    public String lmv_textColor;
    public Typeface lmv_textStyle;
    //Part2//
    public String lmv_loading_showText;
    public int lmv_loading_textSize;
    public String lmv_loading_textColor;
    public Typeface lmv_loading_textStyle;
    public String lmv_loading_progressBarColor;
    public int lmv_loading_progressBarSize;
    //Part3//
    public boolean lmv_adjustMargins;
    public int lmv_adjustMarginsLeft;//dp
    public int lmv_adjustMarginsTop;//dp
    public int lmv_adjustMarginsRight;//dp
    public int lmv_adjustMarginsBottom;//dp
    //*********//回复加载更多控件样式//*************//

    //*********//分割线总样式//*************//
    public String dividerColor;
    public int dividerHeight;//dp
    //*********//分割线总样式//*************//

    //*********//回复Item最后一项的下划线（分样式）//*************//
    public boolean isDrawChildDivider;
    public boolean c_divider_adjustMargins;
    public int c_divider_adjustMarginsLeft;//dp
    public int c_divider_adjustMarginsTop;//dp
    public int c_divider_adjustMarginsRight;//dp
    public int c_divider_adjustMarginsBottom;//dp
    //*********//回复Item最后一项的下划线（分样式）//*************//


    //*********//评论Item的分割线（分样式）//*************//
    /**
     * 评论Item的分割线颜色、高度与回复Item的分割线一样
     */
    public boolean f_divider_adjustMargins;
    public int f_divider_adjustMarginsLeft;//dp
    public int f_divider_adjustMarginsTop;//dp
    public int f_divider_adjustMarginsRight;//dp
    public int f_divider_adjustMarginsBottom;//dp
    //*********//评论Item的分割线（分样式）//*************//

    //*********//底部上拉加载更多圆形ProgressBar的大小和颜色//*************//
    public int lm_footerProgressBarSize = 12;//dp
    public String lm_footerProgressBarColor;
    public boolean lm_footerProgressBar_adjustMargins;
    public int lm_footerProgressBar_adjustMarginsLeft;//dp
    public int lm_footerProgressBar_adjustMarginsTop;//dp
    public int lm_footerProgressBar_adjustMarginsRight;//dp
    public int lm_footerProgressBar_adjustMarginsBottom;//dp
    //*********//底部上拉加载更多圆形ProgressBar的大小和颜色//*************//


    //*********//底部上拉加载更多完成后的textView//*************//
    //文本,颜色、大小、样式、边距
    public String lm_footer_text;
    public String lm_footer_textColor;
    public int lm_footer_textSize = 12;
    public Typeface lm_footer_textStyle;
    public boolean lm_footer_text_adjustMargins;
    public int lm_footer_text_adjustMarginsLeft;//dp
    public int lm_footer_text_adjustMarginsTop;//dp
    public int lm_footer_text_adjustMarginsRight;//dp
    public int lm_footer_text_adjustMarginsBottom;//dp
    //*********//底部上拉加载更多完成后的textView//*************//

}
