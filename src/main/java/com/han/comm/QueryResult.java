package com.han.comm;

import java.util.List;

public class QueryResult<T> {
    private List<T> result;  //持有查询结果的列表容器
    private Long totalRecords; //查询到的总记录数

    public QueryResult() {
    }

    /**
     * 构造器
     * @param result 持有查询结果的列表容器
     * @param totalRecords 查询到的总记录数
     */
    public QueryResult(List<T> result, Long totalRecords) {
        this.result = result;
        this.totalRecords = totalRecords;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public Long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(Long totalRecords) {
        this.totalRecords = totalRecords;
    }
}
