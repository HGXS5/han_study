package cn.han.pojo;

import java.io.Serializable;

public class Person implements Serializable {
//public class Person implements Cloneable {
    private String name;
    private Integer age;
    private Book book;
    private Apples apples;



    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", book=" + book +
                ", apples=" + apples +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Apples getApples() {
        return apples;
    }

    public void setApples(Apples apples) {
        this.apples = apples;
    }

    public Person(String name, Integer age, Book book, Apples apples) {
        this.name = name;
        this.age = age;
        this.book = book;
        this.apples = apples;
    }

    public Person(String name, Integer age, Apples apples) {
        this.name = name;
        this.age = age;
        this.apples = apples;
    }

    public Person(String name, Integer age, Book book) {
        this.name = name;
        this.age = age;
        this.book = book;
    }
}
