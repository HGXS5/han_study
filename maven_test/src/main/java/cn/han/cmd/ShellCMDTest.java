package cn.han.cmd;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

public class ShellCMDTest {
    public static void main(String[] args) {
//        int i = Runtime.getRuntime().availableProcessors();
//        System.out.println(i);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("han", "1111");
//        jsonObject.put("han", "2222");
//        jsonObject.put("han", "3333");
        System.out.println(jsonObject.toJSONString());
        System.out.println(jsonObject.toString());
//        String str = "{\"han\":\"2222\"}";
//        System.out.println(JSONObject.parseObject(jsonObject.toJSONString()).get("han"));
//        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
//        try {
//            queue.put("one");
//            queue.put("two");
//            queue.poll();
//            ShellCMDTest cmdTest = new ShellCMDTest();
//            cmdTest.test();
//            Iterator<String> iterator = queue.iterator();
//            while (iterator.hasNext()){
//                String next = iterator.next();
//                System.out.println(next);
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        String s = "qwee123";
//        String a = s.replaceAll("123", "a");
//        System.out.println(a);
    }

    public  String test(){
        System.out.println("run..");
        return "111";
    }
    public void demo(){
        Runnable test = this::test;
    }
}
