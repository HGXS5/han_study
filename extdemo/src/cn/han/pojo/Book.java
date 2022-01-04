package cn.han.pojo;

import java.io.Serializable;

public class Book implements Serializable {
    private String name;
    private Integer price;

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
