package cn.han.pojo;

import cn.han.annotation.Logtype;

import cn.han.annotation.TestAnnotation;

import java.io.Serializable;
import java.lang.annotation.RetentionPolicy;

public class Book implements Serializable {

    @TestAnnotation(testVales = {Logtype.MESSAGE})
    private String name;
    @TestAnnotation(testVales = {Logtype.MESSAGE})
    private Integer price;
    @TestAnnotation(testVales = {Logtype.MESSAGE})
    public String address;

    @TestAnnotation(testVales = {Logtype.ADD,Logtype.LOGIN,Logtype.LOGOUT,Logtype.DEL})
    public void test(){
        
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Book() {
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public Book(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
