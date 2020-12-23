package com.han.dao;

import com.han.comm.QueryBean;
import com.han.comm.QueryResult;

import java.io.Serializable;
import java.util.List;

/**
 * 数据访问对象接口（以对象为单位封装CRUD操作）
 * @param <E>实体类型
 * @param <K>实体标识字段类型
 */
public interface BaseDao<E,K extends Serializable> {
    /**
     * 新增
     * @param entity 业务实体对象
     * @return 增加成功返回实体对象的标识
     */
    public K save(E entity);

    /**
     * 删除
     * @param entity 业务实体对象
     */
    public void delete(E entity);
    /**
     * 根据ID删除
     * @param id 业务实体对象的标识
     * @return  删除成功返回true否则返回false
     */
    public boolean deleteById(K id);

    /**
     * 修改
     * @param entity 业务实体对象
     */
    public void update(E entity);

    /**
     * 根据ID查找业务实体对象
     * @param id 业务实体对象的标识
     * @return 业务实体对象或null
     */
    public E findById(K id);

    /**
     * 根据ID查找业务实体对象
     * @param id 业务实体对象的标识
     * @param lazy 是否使用延迟加载
     * @return 业务实体对象
     */
    public E findById(K id, boolean lazy);

    /**
     * 查找所有业务实体对象
     * @return 装所有业务实体对象的列表容器
     */
    public List<E> findAll();

    /**
     * 分页查询找业务实体对象
     * @param page 页码
     * @param size 每页显示的条数
     * @return
     */
    public QueryResult<E> findByPage(int page, int size);

    /**
     * 分页查找业务实体对象
     * @param queryBean 查询条件对象
     * @param page 页码
     * @param size 每页显示的条数
     * @return 查询结果
     */
    public QueryResult<E> findByPage(QueryBean queryBean, int page, int size);
}
