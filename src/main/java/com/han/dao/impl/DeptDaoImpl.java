package com.han.dao.impl;

import com.han.comm.QueryBean;
import com.han.comm.QueryResult;
import com.han.dao.DeptDao;
import com.han.entity.Dept;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class DeptDaoImpl extends BaseDaoHibernateImpl<Dept,Integer> implements DeptDao {
    private static final String HQL_FIND_TOP_DEPT = " from Dept as d where d.superiorDept is null";
    @Override
    public QueryResult<Dept> findTopDeptByPage(int page, int size) {
        List<Dept> list = findByHQLAndPage(HQL_FIND_TOP_DEPT, page, size);
        Long totalRecords = getCountByHQL(" select count(*) " + HQL_FIND_TOP_DEPT);
        return new QueryResult<Dept>(list,totalRecords);
    }

    @Override
    public Integer save(Dept entity) {
        return null;
    }

    @Override
    public void delete(Dept entity) {

    }

    @Override
    public boolean deleteById(Integer id) {
        return false;
    }

    @Override
    public void update(Dept entity) {

    }

    @Override
    public Dept findById(Integer id) {
        return null;
    }

    @Override
    public Dept findById(Integer id, boolean lazy) {
        return null;
    }

    @Override
    public List<Dept> findAll() {
        return null;
    }

    @Override
    public QueryResult<Dept> findByPage(int page, int size) {
        return null;
    }

    @Override
    public QueryResult<Dept> findByPage(QueryBean queryBean, int page, int size) {
        return null;
    }
}
