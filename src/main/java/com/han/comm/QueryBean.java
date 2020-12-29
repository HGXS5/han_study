package com.han.comm;

import javax.management.Query;
import java.util.List;

public interface QueryBean {
    /**
     * 添加排序字段
     * @param fieldName 用于排序的字段
     * @param asc 升序还是降序
     * @return 查询条件对象自身（方便级联编程）
     */
    public QueryBean addOrder(String fieldName, boolean asc);

    /**
     * 添加排序字段
     * @param available 是否添加此排序字段
     * @param fieldName 用于排序的字段
     * @param asc 升序还是降序
     * @return 查询条件对象自身（方便级联编程）
     */
    public QueryBean addOrder(Boolean available, String fieldName, boolean asc);

    /**
     * 添加查询条件
     * @param condition 条件
     * @param params 替换掉条件中参数占位符的参数
     * @return 查询条件对象自身（方便级联编程）
     */
    public QueryBean addCondition(String condition, Object... params);

    /**
     * 添加查询条件
     * @param available 是否需要添加此条件
     * @param condition 条件
     * @param params 替换掉条件中参数占位符的参数
     * @return 查询条件对象自身（方便级联编程）
     */
    public QueryBean addCondition(boolean available, String condition, Object... params);

    /**
     * 获得查询语句
     * @return 查询语句
     */
    public String getQueryString();

    /**
     *获取查询记录数的查询语句
     * @return 查询记录数的查询语句
     */
    public String getCountString();

    /**
     * 获得查询参数
     * @return 查询参数的列表容器
     */
    public List<Object> getParameters();
}
