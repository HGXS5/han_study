package com.han.comm;

public class PageRange {
    private int startPage; //起始页码
    private int endPage;  //终止页码

    /**
     * 构造器
     * @param pageCount 总共显示几个页码
     * @param currentPage 当前页码
     * @param totalPage 总页数
     */
    public PageRange(int pageCount, int currentPage, int totalPage) {
        startPage = currentPage - (pageCount - 1) / 2;
        endPage = currentPage + pageCount / 2;
        if (startPage < 1){
            startPage = 1;
            endPage = totalPage > pageCount ? pageCount : totalPage;
        }
        if (endPage > totalPage){
            endPage = totalPage;
            startPage = (endPage - pageCount > 0) ? endPage - pageCount + 1 : 1;
        }
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }
}
