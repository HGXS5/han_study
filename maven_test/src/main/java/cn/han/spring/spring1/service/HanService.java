package cn.han.spring.spring1.service;

import cn.han.spring.spring1.dao.HanDao;

public class HanService {

   private HanDao handao;

    /**
     * set方式注入
     * @param handao
     */
    public void setHandao(HanDao handao) {
        this.handao = handao;
    }

    /**
     * 构造器注入方式
     * @param dao
     */
    public HanService(HanDao dao){
        this.handao = dao;
    }



    public void serviceTest(){
        handao.daoTest();
    }


}
