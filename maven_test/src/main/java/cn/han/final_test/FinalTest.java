package cn.han.final_test;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author han_s
 * @Date 2022/6/13 8:57
 * @ProName maven_test
 */
@Getter
@Setter
@ToString
public class FinalTest {
     String encoding;
    private final Integer age;

    public FinalTest(String encoding, Integer age) {
        this.encoding = encoding;
        this.age = age;
    }

}
