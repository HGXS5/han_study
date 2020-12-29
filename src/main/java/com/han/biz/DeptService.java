package com.han.biz;

import com.han.comm.PageBean;
import com.han.entity.Dept;

public interface DeptService {

    /**
     * 创建新的部门
     * @param department 部门对象
     * @return 创建成功返回true否则返回false
     */
    public boolean creatNewDepartment(Dept department);

    /**
     * 删除指定部门
     * @param id 要删除的部门的编号
     * @return 删除成功返回true否则返回false
     */
    public boolean deleteDepartment(Integer id);

    /**
     * 分页获取顶级部门
     * @param page 页码
     * @param size 当前页显示的条数
     * @return 部门对象的分页器对象
     */
    public PageBean<Dept> getTopDeptByPage(int page, int size);
}
