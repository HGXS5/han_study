package cn.han.object;

import lombok.Data;
import lombok.ToString;

/**
 * @Author han_s
 * @Date 2022/7/1 9:47
 * @ProName maven_test
 */
@Data
@ToString
public class Animal implements Cloneable{
    private String name;
    private String play;
    private Fish fish;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Animal clone = (Animal) super.clone();
        clone.setFish((Fish) clone.getFish().clone());
        return clone;
    }
}
