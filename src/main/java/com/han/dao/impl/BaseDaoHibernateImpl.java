package com.han.dao.impl;

import com.han.comm.QueryBean;
import com.han.comm.QueryResult;

import com.han.dao.BaseDaoAdapter;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings(value = {"unchecked"})
public class BaseDaoHibernateImpl<E,K extends Serializable> extends BaseDaoAdapter<E,K> {

    @Autowired
    protected SessionFactory sessionFactory;

    private Class<?> entityClass; //业务实体的类对象
    private String entityName; //业务实体的名字

    public BaseDaoHibernateImpl() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        entityClass = (Class<?>) pt.getActualTypeArguments()[0];
        entityName = entityClass.getSimpleName();
    }

    @Override
    public K save(E entity) {
        return (K) sessionFactory.getCurrentSession().save(entity);
    }

    @Override
    public void delete(E entity) {
        sessionFactory.getCurrentSession().delete(entity);

    }

    @Override
    public void update(E entity) {
        sessionFactory.getCurrentSession().update(entity);
    }

    @Override
    public E findById(K id) {
        return findById(id, false);
    }

    @Override
    public E findById(K id, boolean lazy) {
        Session session = sessionFactory.getCurrentSession();
        return (E) (lazy? session.load(entityClass, id) : session.get(entityClass, id));
    }

    @Override
    public List<E> findAll() {
        return sessionFactory.getCurrentSession().createCriteria(entityClass).list();
    }

    @Override
    public QueryResult<E> findByPage(int page, int size) {

        return new QueryResult<E>(
                findByHQLAndPage("from"+entityName,page,size),
                getCountByHQL("select count(*) from "+entityName)
        );
    }

    @Override
    public QueryResult<E> findByPage(QueryBean queryBean, int page, int size) {
        return super.findByPage(queryBean, page, size);
    }

    /**
     * 根据HQL和可变参数列表进行查询
     * @param hql 基于HQL的查询语句
     * @param params 可变参数列表
     * @return 持有查询结果的列表容器或空列表容器
     */
    protected List<E> findByHQL(String hql, Object... params){
        return this.findByHQL(hql, getParamList(params));
    }

    /**
     * 根据HQL和参数列表进行查询
     * @param hql 基于HQL的查询语句
     * @param params 查询参数列表
     * @return 持有查询结果的列表容器或空列表
     */
    protected List<E> findByHQL(String hql, List<Object> params){
        List<E> list = createQuery(hql,params).list();
        return list != null && list.size() > 0 ? list : Collections.EMPTY_LIST;
    }

    /**
     * 根据HQL和参数列表进行分页查询
     * @param hql 基于HQL的查询语句
     * @param page 页码
     * @param size 每页显示的条数
     * @param params 可变参数列表
     * @return 持有查询结果的列表容器或空列表
     */
    protected List<E> findByHQLAndPage(String hql, int page, int size,Object... params){
        return this.findByHQLAndPage(hql, page, size, getParamList(params));
    }

    /**
     *根据HQL和参数列表进行分页查询
     * @param hql 基于HQL的查询语句
     * @param page 页码
     * @param size 每页显示的条数
     * @param params 查询参数列表
     * @return 持有查询结果的列表容器或者空列表容器
     */
    protected List<E> findByHQLAndPage(String hql,int page, int size, List<Object> params){
        List<E> list = createQuery(hql,params)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .list();
        return list != null && list.size() > 0 ? list : Collections.EMPTY_LIST;
    }

    /**
     * 查询满足条件的记录数
     * @param hql hql基于HQL查询语句
     * @param params 可变参数列表
     * @return 满足查询条件的总记录数
     */
    protected Long getCountByHQL(String hql,Object... params){
        return this.getCountByHQL(hql, getParamList(params));
    }

    /**
     * 满足条件的记录数
     * @param hql 基于HQL的查询语句
     * @param params 参数列表容器
     * @return 满足查询条件的总记录数
     */
     protected Long getCountByHQL(String hql, List<Object> params){
         return (Long) createQuery(hql, params).uniqueResult();
     }

    /**
     * 创建Hibernate查询对象（Query)
     * @param hql
     * @param params
     * @return
     */
     private Query createQuery(String hql,List<Object> params){
       Query query = sessionFactory.getCurrentSession().createQuery(hql);
         for (int i = 0; i < params.size(); i++) {
             query.setParameter(i, params.get(i));
         }
         return query;
     }

     private List<Object> getParamList(Object... params){
         List<Object> paramList = new ArrayList<Object>();
         if (params != null){
             for (int i = 0; i < params.length; i++) {
                 paramList.add(params[i]);
             }
         }
         return paramList.size() == 0 ? Collections.EMPTY_LIST : paramList;
     }
}