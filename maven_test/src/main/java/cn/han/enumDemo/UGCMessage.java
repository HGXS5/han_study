package cn.han.enumDemo;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @Author han_s
 * @Date 2022/7/22 15:18
 * @ProName maven_test
 */
public class UGCMessage {
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    private EnumDemoOne demoOne;

    public EnumDemoOne getDemoOne() {
        return demoOne;
    }

    public void setDemoOne(EnumDemoOne demoOne) {
        this.demoOne = demoOne;
    }

    @Override
    public String toString() {
        return "UGCMessage{" +
                "demoOne=" + demoOne +
                '}';
    }
}
