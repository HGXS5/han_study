package com.han.comm;

import java.util.List;

public class PageBean<T> {
    private static final int DEFAUL_INIT_PAGE = 1;
    private static final int DEFAUL_PAGE_SIZE = 10;
    private static final int DEFAUL_PAGE_COUNT = 5;

    private List<T> data; //分页数据
    private PageRange pageRange; //页码范围
    private int totalPage; //总页数
    private int size; //每页显示条数
    private int currentPage; //当前页码
    private int pageCount; //页码数量

    /**
     * 构造器
     * @param size 每页显示的条数
     * @param currentPage 当前页码
     * @param pageCount 页码数量
     */
    public PageBean(int size, int currentPage, int pageCount) {
        this.size = size;
        this.currentPage = currentPage;
        this.pageCount = pageCount;
    }

    /**
     * 构造器
     * @param size 每页显示的条数
     * @param currentPage 当前页码
     */
    public PageBean(int size, int currentPage) {
        this.size = size;
        this.currentPage = currentPage;
    }

    /**
     * 构造器
     * @param currentPage 当前页码
     */
    public PageBean(int currentPage) {
        this.currentPage = currentPage;
    }

    public PageBean() {
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public PageRange getPageRange() {
        return pageRange;
    }

    public void setPageRange(PageRange pageRange) {
        this.pageRange = pageRange;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public void transferQueryResult(QueryResult<T> queryResult){
        long totalRecords = queryResult.getTotalRecords();
        data = queryResult.getResult();
        totalPage = (int) ((totalRecords + size - 1) / size);
        totalPage = totalPage >= 0 ? totalPage : Integer.MAX_VALUE;
        this.pageRange  = new PageRange(pageCount, currentPage, totalPage);
    }
}
