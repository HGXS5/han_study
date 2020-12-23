package com.han.test9;

/**
 * 懒学生
 */
public class LazyStudent implements Candidate{
    private String name;

    public LazyStudent(String name) {
        this.name = name;
    }

    @Override
    public void answerTheQuestions() {
        //又丑又懒的学生只能写出自己的名字不会答题
        System.out.println("姓名："+name);
    }
}
