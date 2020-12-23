package com.han.test8;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MyThreadLocal<T> {
    /**
     * 1. synchronizedMap(Map<k,V> m)方法，返回由指定映射支持的同步（线程安全）映射。
     *                                  为了保证串行访问，至关重要的是，对后备映射的所有访问必须通过返回的映射来完成。
     *                      参数：
     *                          k: 键
     *                          v: 值
     *                      返回：
     *                          指定map的同步map
     * 2. put(K key, V value)将指定值与此映射中的指定键关联
     * 3. get(Object key)返回指定键所映射到的值；如果此映射不包含键的映射关系，则返回null。
     * 4. remove(Object key)如果存在，则从此映射中删除键的映射。
     */
    private Map<Thread, T> map = Collections.synchronizedMap(new HashMap<Thread, T>());
    public void set(T newValue){
        map.put(Thread.currentThread(), newValue);
    }
    public T get(){
        return map.get(Thread.currentThread());
    }
    public void remove(){
        map.remove(Thread.currentThread());
    }
}
