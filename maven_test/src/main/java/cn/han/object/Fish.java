package cn.han.object;

import lombok.Data;
import lombok.ToString;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * @Author han_s
 * @Date 2022/7/1 9:05
 * @ProName maven_test
 */
@Data
@ToString
public class Fish implements Cloneable, Comparable<Fish>, Comparator<Fish> {
    private String name;
    private String play;
    private Integer id;

    public Fish(String name, String play, Integer id) {
        this.name = name;
        this.play = play;
        this.id = id;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int compareTo(Fish o) {
        if (o.getId()<id) {
            return 1;
        }else if (o.getId()>id){
            return -1;
        }
        return 0;
    }

    @Override
    public int compare(Fish o1, Fish o2) {
        if (o1.getId()-o2.getId()>0) {
            return 1;
        }else if (o1.getId()-o2.getId()<0){
            return -1;
        }
        return 0;
    }
}

