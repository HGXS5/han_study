package cn.han.enumDemo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.EnumSet;
import java.util.HashMap;

/**
 * @Author han_s
 * @Date 2022/7/22 15:06
 * @ProName maven_test
 */
public class DemoOnew {


    public static void main(String[] args) {
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("contentType", "column");
//        String con = jsonObject.toString();
//        UGCMessage ugcMessage = JSONObject.parseObject(con, UGCMessage.class);
////        UGCMessage ugcMessage = JSON.parseObject(con, UGCMessage.class);
//        EnumDemoOne demoOne = ugcMessage.getDemoOne();
//        String name = demoOne.getName();
//        System.out.println(name);
//        System.out.println(ugcMessage.toString());

        String type = "news";
        String contentType = "";
        for (EnumDemoOne anEnum : EnumDemoOne.values()) {
            if (anEnum.getValue().equals(type)) {
                contentType = anEnum.getName();
            }
        }
        System.out.println(contentType);
    }


}
