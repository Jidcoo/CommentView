package com.jidcoo.android.widget.commentview.defaults;

import com.jidcoo.android.widget.commentview.model.AbstractCommentModel;
import com.jidcoo.android.widget.commentview.model.CommentEnable;
import com.jidcoo.android.widget.commentview.model.PagerEnable;
import com.jidcoo.android.widget.commentview.model.ReplyEnable;

import java.util.List;


/**
 * <h1>默认评论回复的分页数据模型</h1>
 * {@link PagerEnable}分页模型</br>
 * <p>一个Model实例含有n条母数据</p>
 * <p>每1条母数据（评论）携带m条子数据（评论的回复）</p>
 * <p>模型结构：</p>
 * &emsp;&emsp;CommentModel
 * <br>--->Comment(Size=n)【评论】
 * <br>--->Reply(Size=m)【回复】
 * <br>-------------------------------</br>
 * <p>
 * <p>支持Comment、Reply自定义数据类型：</p>
 * <p><u>自定义数据类型必须继承自抽象类AbstractCommentModel，查看{@link AbstractCommentModel}</u></p>
 * <u>自定义数据类型请对照本类写法</u><p></p>
 * <u>注意：使用自定义数据类型就必须自定义布局实现，否则会抛出数据模型的java.lang.ClassCastException异常</u>
 *
 * @author Jidcoo
 */
public class DefaultCommentModel extends AbstractCommentModel<DefaultCommentModel.Comment> {
    /**
     * n条母数据
     */
    public List<Comment> comments;

    @Override
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    /**
     * 母数据（评论）模型
     * <p>可自定义数据类型，但必须实现CommentEnable接口，如下：</p>
     */
    public static class Comment extends CommentEnable {
        /**
         * 数据id
         */
        public long id;
        /**
         * 父级id
         */
        public long fid;
        /**
         * 评论者用户名
         */
        public String posterName;
        /**
         * 评论内容
         */
        public String comment;
        /**
         * 时间
         */
        public long date;
        /**
         * 点赞数
         */
        public int prizes;

        /**
         * 楼层ID，如果是楼主，为0
         * 在Comment类，该属性是0
         */
        public long pid;


        /**
         * m条子数据
         */
        public List<Reply> replies;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getFid() {
            return fid;
        }

        public void setFid(long fid) {
            this.fid = fid;
        }

        public String getPosterName() {
            return posterName;
        }

        public void setPosterName(String posterName) {
            this.posterName = posterName;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public long getDate() {
            return date;
        }

        public void setDate(long date) {
            this.date = date;
        }

        public int getPrizes() {
            return prizes;
        }

        public void setPrizes(int prizes) {
            this.prizes = prizes;
        }


        public long getPid() {
            return pid;
        }

        public void setPid(long pid) {
            this.pid = pid;
        }


        @Override
        public List<Reply> getReplies() {
            return replies;
        }


        public void setReplies(List<Reply> replies) {
            this.replies = replies;
        }


        /**
         * 子数据（回复）模型
         */
        public static class Reply extends ReplyEnable {
            /**
             * 数据id
             */
            public long id;
            /**
             * 子级id
             */
            public long kid;
            /**
             * 回复者用户名
             */
            public String replierName;
            /**
             * 评论内容
             */
            public String reply;
            /**
             * 时间
             */
            public long date;
            /**
             * 点赞数
             */
            public int prizes;


            /**
             * 楼层ID，如果是回复楼主，为0
             * 如果回复非楼主，为被回复者的ID
             */
            public long pid;

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public long getKid() {
                return kid;
            }

            public void setKid(long kid) {
                this.kid = kid;
            }

            public String getReplierName() {
                return replierName;
            }

            public void setReplierName(String replierName) {
                this.replierName = replierName;
            }

            public String getReply() {
                return reply;
            }

            public void setReply(String reply) {
                this.reply = reply;
            }

            public long getDate() {
                return date;
            }

            public void setDate(long date) {
                this.date = date;
            }

            public int getPrizes() {
                return prizes;
            }

            public void setPrizes(int prizes) {
                this.prizes = prizes;
            }

            public long getPid() {
                return pid;
            }

            public void setPid(long pid) {
                this.pid = pid;
            }


        }

    }
}
