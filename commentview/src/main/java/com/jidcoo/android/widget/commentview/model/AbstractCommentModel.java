package com.jidcoo.android.widget.commentview.model;

import java.util.List;

/**
 * 数据抽象模型<p></p>
 * 自定义模型必须继承此抽象模型，并实现其中的方法<p></p>
 * <u>注意：使用自定义数据类型就必须自定义布局实现，否则会抛出数据模型的java.lang.ClassCastException异常</u><br></br>
 * AbstractCommentModel中传入的泛型<T extends CommentEnable>必须继承自CommentEnable,查看{@link CommentEnable}
 *
 * @param <T> 自定义的评论数据模型
 * @author Jidcoo
 */
public abstract class AbstractCommentModel<T extends CommentEnable> extends PagerEnable {
    /**
     * 必须实现该方法并确保返回值非NULL
     *
     * @return
     */
    public abstract List<T> getComments();

}
