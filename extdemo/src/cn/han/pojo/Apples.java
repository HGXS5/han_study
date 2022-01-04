package cn.han.pojo;

import java.io.Serializable;

public class Apples implements Serializable {
    private String name;
    private Integer price;

    public Apples(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    public Apples() {
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

    @Override
    public String toString() {
        return "Apples{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
