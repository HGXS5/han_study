package cn.han.workdemo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author han_s
 * @Date 2022/4/22 13:50
 * @ProName maven_test
 */
public class DemoTest {
    static File filePath;
    static File moveFile;

    public static void main(String[] args) {
//        String jmflParameter ="{\"Type\":1,\"CategoryInfos\":[{\"Type\":\"jmfl\",\"Code\":\"\",\"Name\":\"" + "programType" + ","+"type2" + "\"}]}";
//        Map map = JSONObject.parseObject(jmflParameter, Map.class);
//        String s = JSONObject.toJSONString(map);
//        System.out.println(s);
//        System.out.println(map.toString());
//        remoteTo();
//        lamdaTest();
//        dateTest();
//        utilClass();
//       moreTest();
        splitStr();
    }

    public static void splitStr() {
//        Map<String,Object> map = new HashMap();
//        StringBuffer sb = new StringBuffer();
//        if (sb.toString()==null){
//            System.out.println("猜猜");
//        }
//
//        map.put("failIds", sb.toString());
//        System.out.println(map.toString());
//        String ids = (String) map.get("failIds");
//        System.out.println("ids："+ids);
//        System.out.println(ids.length());
//        if (ids.length()>0){
//            if (ids.endsWith(",")){
//                ids = ids.substring(0, ids.lastIndexOf(","));
//            }
//            String[] injectionFailIds = ids.split(",");
//            System.out.println(injectionFailIds);
//        }else {
//            System.out.println("可以");
//        }
        String ids = "1111,222";
//        System.out.println(ids);
//        if (ids.endsWith(",")){
//            ids = ids.substring(0, ids.lastIndexOf(","));
//        }
//        System.out.println(ids);

        String jsonString = JSON.toJSONString(ids);
        System.out.println(jsonString);
    }

    public static void moreTest() {
        for (int i = 0; i < 5; i++) {
            String wasuId = InteractiveQuery.getWasuId().getString("data");
            System.out.println(wasuId);
        }

    }

    public static void utilClass() {
        GuidUtils guidUtils = new GuidUtils(true);
        String valueAfterMD5 = guidUtils.valueAfterMD5;
        System.out.println(guidUtils.valueBeforeMD5);
        System.out.println(valueAfterMD5);
    }

    public static void remoteTo() {
        String path = "D:\\huashu\\2022-05-06-BAKUP";
        File file = new File(path);
        int i = path.lastIndexOf(File.separator);
        String changePath = path.substring(0, i + 1);
        System.out.println(changePath);
        if (file.exists() && file.isDirectory()) {
            File[] files = file.listFiles();
            for (File file1 : files) {
                System.out.println("执行");
                int indexOf = file1.getName().lastIndexOf(".");
                if ("error".equals(file1.getName().substring(indexOf + 1)) || "bakup".equals(file1.getName().substring(indexOf + 1))) {
                    String pathFile = path + File.separator + file1.getName();
                    System.out.println(pathFile);
                    filePath = new File(pathFile);
                    int i1 = file1.getName().lastIndexOf(".");
                    String changeFileName = file1.getName().substring(0, i1);
                    System.out.println(changeFileName);
                    String movePath = changePath + File.separator + changeFileName;
                    System.out.println(movePath);
                    moveFile = new File(movePath);
                    filePath.renameTo(moveFile);
                }
                System.out.println("结束");
            }

        }
    }

    public static void dateTest() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String format = sdf.format(new Date());
        System.out.println(format);
        try {
            Date parse = sdf.parse("2017");
            System.out.println(parse.toString());
            System.out.println(sdf.format(parse));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void lamdaTest() {
        List<Student> list = new ArrayList<>();
        list.add(new Student("红", 20));
        list.add(new Student("红", 21));
        list.add(new Student("红", 22));
        list.add(new Student("蓝", 24));
        list.add(new Student("绿", 18));

        Student s = list.stream().filter(student -> "红".equals(student.getName())).findFirst().get();
        System.out.println(s);
    }

    static class Student {
        private String name;
        private Integer age;

        public Student(String name, Integer age) {
            this.name = name;
            this.age = age;
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

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
