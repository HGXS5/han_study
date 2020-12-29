package com.han.dao;

import com.han.comm.QueryBean;
import com.han.comm.QueryResult;

import java.io.Serializable;
import java.util.List;

public class BaseDaoAdapter <E,K extends Serializable> implements BaseDao<E,K> {

    @Override
    public K save(E entity) {
        return null;
    }

    @Override
    public void delete(E entity) {

    }

    @Override
    public boolean deleteById(K id) {
         E entity = findById(id);
         if (entity != null){
             delete(entity);
             return true;
         }
        return false;
    }

    @Override
    public void update(E entity) {

    }

    @Override
    public E findById(K id) {
        return null;
    }

    @Override
    public E findById(K id, boolean lazy) {
        return null;
    }

    @Override
    public List<E> findAll() {
        return null;
    }

    @Override
    public QueryResult<E> findByPage(int page, int size) {
        return null;
    }

    @Override
    public QueryResult<E> findByPage(QueryBean queryBean, int page, int size) {
        return null;
    }
}
