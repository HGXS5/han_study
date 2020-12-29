package com.han.dao;

import com.han.comm.QueryResult;
import com.han.entity.Dept;

public interface DeptDao extends BaseDao<Dept,Integer>{
    /**
     * 分页查询顶级部门
     * @param page 页码
     * @param size 页码大小
     * @return 查询结果对象
     */
    public QueryResult<Dept> findTopDeptByPage(int page, int size);
}
