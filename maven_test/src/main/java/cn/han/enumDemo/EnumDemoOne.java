package cn.han.enumDemo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @Author han_s
 * @Date 2022/7/22 15:03
 * @ProName maven_test
 */

public enum EnumDemoOne {
    COLUMN("栏目","column"),
    NEWS("新闻","news");

    private String name;
    private String value;

    EnumDemoOne(String name,String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }
    public String getValue(){
        return value;
    }
}
