package com.jidcoo.android.widget.commentview.model;

/**
 * 分页模型<br>
 * <br>
 * 在Android端只实现为对分页数据的Get/Set操作<br>
 * <br>
 * 具体的分页逻辑应该由后端完成，客户端只负责对数据的处理<br>
 * <br>
 *
 * @author Jidcoo
 */
public class PagerEnable {
    /**
     * 当前页码
     */
    public int currentPage;
    /**
     * 每一页数据的大小
     */
    private int pageSize;
    /**
     * 总页数
     */
    public int totalPages;
    /**
     * 数据总数
     */
    public int totalDataSize;
    /**
     * 下一个页码
     */
    public int nextPage;
    /**
     * 上一个页码
     */
    public int prefPage;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalDataSize() {
        return totalDataSize;
    }

    public void setTotalDataSize(int totalDataSize) {
        this.totalDataSize = totalDataSize;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getPrefPage() {
        return prefPage;
    }

    public void setPrefPage(int prefPage) {
        this.prefPage = prefPage;
    }
}
