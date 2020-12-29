package com.han.biz.impl;

import com.han.biz.DeptService;
import com.han.comm.PageBean;
import com.han.comm.QueryResult;
import com.han.dao.DeptDao;
import com.han.entity.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional  //声明式事务注解
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptDao deptDao;

    @Override
    public boolean creatNewDepartment(Dept department) {
        return deptDao.save(department) != null;
    }

    @Override
    public boolean deleteDepartment(Integer id) {
        return deptDao.deleteById(id);
    }

    @Override
    public PageBean<Dept> getTopDeptByPage(int page, int size) {
        QueryResult<Dept> queryResult = deptDao.findTopDeptByPage(page, size);
        PageBean<Dept> pageBean = new PageBean<Dept>(page, size);
        pageBean.transferQueryResult(queryResult);
        return pageBean;
    }
}
