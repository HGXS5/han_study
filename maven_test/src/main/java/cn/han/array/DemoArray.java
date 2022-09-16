package cn.han.array;


import cn.han.object.Animal;
import cn.han.object.Fish;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.MultiValueMap;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @Author han_s
 * @Date 2022/5/31 14:45
 * @ProName maven_test
 */
public class DemoArray {

    public static void main(String[] args) throws IOException {
        List<String> listIds = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            listIds.add(String.valueOf(i));
        }
        String fileIds = "5,8";
        List list = lastList(listIds, fileIds);
        System.out.println(list.toString());
    }

    private static List lastList(List listIds, String fileIds) {
        if (!listIds.isEmpty() && StringUtils.isNoneBlank(fileIds)) {
            String[] strings = fileIds.split(",");
            for (int i1 = 0; i1 < strings.length; i1++) {
                for (int i2 = 0; i2 < listIds.size(); i2++) {
                    if (strings[i1].equals(listIds.get(i2))) {
                        listIds.remove(i2);
                    }
                }
            }
        }
        return listIds;
    }

    public static boolean add() {
        throw new UnsupportedOperationException();
    }

    public static void test7() {
        if (.75f == 0.75F) {
            System.out.println("相等");
        }
        boolean modified = false;
        if (add())
            modified = true;

        System.out.println(modified);


        System.out.println();
    }

    public static void test6() {
        String kj = "name";
        int i;
        for (i = 0; i < 3; i++) {
            int hash = hash(kj);
            System.out.println(hash);
        }
        HashMap map = new HashMap();
        map.put("it", 1);
        map.put("iu", 2);
        String s = null;
        try {
            s = test5();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(s);
//        test4();
//        test2();
//        String as = "1234567";
//        StringBuilder sb = new StringBuilder();
//        char[] chars = as.toCharArray();
//        for (int i = chars.length-1 ; i >=0; i--) {
//            sb.append(chars[i]);
//        }
//        System.out.println(sb.toString());
//        System.out.println(chars.toString());
//        streamDemo();
//        Map<String, Object> map = new HashMap<>(1);
//        map.put("insertAssetFileList", 111);
//        map.put("injectNewAssetFileList", 222);
//        System.out.println(map.toString());

//        List<String> list1 = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            list1.add("时候"+i);
//        }
//        String s = feedbackAssembly(list1, false);
//        System.out.println(s);
//        System.out.println("list1添加完成：" + list1.toString());
//        list1.subList(0, 8).clear();
//        System.out.println("list1过滤之后："+list1.toString());
//        jsonArry();
//        List<Integer> list2 = new ArrayList<>();
//        for (int i = 13; i < 50; i++) {
//            list2.add(i);
//        }
//        System.out.println("list2添加完成：" + list2.toString());
//        demo(list1, list2);
    }

    public static String test5() throws InterruptedException {
        while (true) {
            Thread.sleep(10000);
            return "测试";
        }
    }

    private static void test1() {
        List<Integer> fileIdList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            fileIdList.add(i);
        }
        System.out.println(fileIdList.get(fileIdList.size() - 1));
    }

    private static void jsonArry() {
        List<Map<String, Object>> assetList = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("assetId", "2" + i);
            map.put("msgCode", 200);
            map.put("msg", "成功");
            assetList.add(map);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("assetList", assetList);
        System.out.println(jsonObject.toString());

    }

    private static String feedbackAssembly(List<String> ugcDataList, boolean flag) {
        List<Map<String, Object>> assetList = new ArrayList<>();
        if (ugcDataList.size() > 0) {
            if (flag) {
                for (String str : ugcDataList) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("assetId", str);
                    map.put("msgCode", 200);
                    map.put("msg", "成功");
                    assetList.add(map);
                }
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("assetList", assetList);
                return jsonObject.toString();
            } else {
                for (String str : ugcDataList) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("assetId", str);
                    map.put("msgCode", 500);
                    map.put("msg", "失败");
                    assetList.add(map);
                }
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("assetList", assetList);
                return jsonObject.toString();
            }

        }
        return "";
    }

    public static void test(List<Integer> list1, List<Integer> list2) {
        for (Integer integer : list2) {
            for (int i = 0; i < list1.size(); i++) {
                if (integer.equals(list1.get(i))) {
                    list1.remove(i);
                    break;
                }
            }
        }
        System.out.println(list1.toString());
    }

    public static void demo(List<Integer> list1, List<Integer> list2) {

        for (Integer integer : list2) {
            for (int i = 0; i < list1.size(); i++) {
                if (list1.get(i).equals(integer)) {
                    System.out.println("break时两者得值：" + "list1=" + list1.get(i) + "---" + "list2=" + integer);
                    list1.remove(list1.get(i));
                    break;
//                } else if (list2.get(list2.size() - 1).equals(integer)) {
//                    System.out.println("remove：" + list1.get(i));
//                    list1.remove(list1.get(i));
                }
            }
        }
        System.out.println(list1.toString());
    }

    public static void streamDemo() {
//        //创建一个Stream流
//        Stream<String> stream = Stream.of("张三丰", "张翠山", "赵敏", "周芷若", "张无忌");
//        //对Stream流中的元素进行过滤,只要姓张的人
//        Stream<String> stream2 = stream.filter((String name)->{return name.startsWith("张");});
//        //遍历stream2流
//        stream2.forEach(name-> System.out.println(name));
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("test", "" + i);
            list.add(map);
        }
        System.out.println(list.toString());
        Map<String, Object> mapFinal = list.stream().filter(x -> "5".equals(x.get("test"))).findFirst().get();
        System.out.println(mapFinal.toString());
        /*
            Stream流属于管道流,只能被消费(使用)一次
            第一个Stream流调用完毕方法,数据就会流转到下一个Stream上
            而这时第一个Stream流已经使用完毕,就会关闭了
            所以第一个Stream流就不能再调用方法了
            IllegalStateException: stream has already been operated upon or closed
         */
        //遍历stream流
//        stream.forEach(name-> System.out.println(name));
    }

    public static void test2() throws IOException {
        File filePath = new File("D:\\free\\");
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        String path = filePath.getAbsolutePath();
        System.out.println(path);
        String s = path + "\\" + System.currentTimeMillis() + ".txt";
        System.out.println(s);
        File downFile = new File(s);
        if (!downFile.exists()) {
            downFile.createNewFile();
        }
        List<String> content = new ArrayList<>();
        List<Fish> list = new ArrayList<>();
        list.add(new Fish("金鱼", "吃", 1));
        list.add(new Fish("黑鱼", "喝", 2));
        list.add(new Fish("草鱼", "玩", 3));
        for (Fish aiqyAssetInfo : list) {
            StringBuffer sb = new StringBuffer();
            Class<? extends Fish> aClass = aiqyAssetInfo.getClass();
            Field[] declaredFields = aClass.getDeclaredFields();
            Method[] methods = aClass.getMethods();
            for (Method method : methods) {

                method.setAccessible(true);
                String methodName = method.getName();
                if (methodName.contains("get")) {
                    try {
                        Object invoke = method.invoke(aiqyAssetInfo);
                        String value = invoke.toString();
                        int index = methodName.indexOf("get");
                        String filedMe = methodName.substring(index + 3).toLowerCase();
                        for (Field field : declaredFields) {
                            field.setAccessible(true);
                            String fieldName = field.getName();
                            String filedFe = fieldName.toLowerCase();
                            if (filedMe.equals(filedFe)) {
                                sb.append(fieldName).append(":").append(value);
                                sb.append("\t");
                            }
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
            content.add(sb.toString());
        }
        FileUtil.writeFile(downFile, content, true);

        System.out.println(content.toString());
    }

    public void tes3() {
//    for (AiqyAssetInfo aiqyAssetInfo : aiqyAssetInfoList) {
//        StringBuffer sb = new StringBuffer();
//        Class<? extends AiqyAssetInfo> aClass = aiqyAssetInfo.getClass();
//        Field[] declaredFields = aClass.getDeclaredFields();
//        Method[] methods = aClass.getMethods();
//        for (Method method : methods) {
//            method.setAccessible(true);
//            String methodName = method.getName();
//            if (methodName.contains("get")) {
//                try {
//                    String value = "";
//                    Object invoke = method.invoke(aiqyAssetInfo);
//                    if (invoke!=null) {
//                        value = invoke.toString();
//                    }
//                    int index = methodName.indexOf("get");
//                    String filedMe = methodName.substring(index+3).toLowerCase();
//                    for (Field field : declaredFields) {
//                        field.setAccessible(true);
//                        String fieldName = field.getName();
//                        String filedFe = fieldName.toLowerCase();
//                        if (filedMe.equals(filedFe)) {
//                            sb.append(fieldName).append(":").append(value);
//                            sb.append("\t");
//                        }
//                    }
//                } catch (IllegalAccessException e) {
//                    logger.error("不允许访问");
//                    e.printStackTrace();
//                } catch (InvocationTargetException e) {
//                    logger.error("调用方法异常");
//                    e.printStackTrace();
//                }
//            }
//        }
//        content.add(sb.toString());
//    }
    }

    public static void test4() {
        List<List<Integer>> cutList = new ArrayList<>();
        List<Integer> lists = new ArrayList<>();
        for (int i = 1; i <= 34; i++) {
            lists.add(i);
        }

        int cutNum = lists.size() / 10;
        cutNum = cutNum + 1;
        int range = 10;
        for (int i = 1; i <= cutNum; i++) {
            if (i == cutNum) {
//                0-9 10-19 20-29
                List<Integer> subList = lists.subList((i - 1) * range, lists.size());
                cutList.add(subList);
                break;
            }
            List<Integer> subList = lists.subList((i - 1) * range, i * range);
            cutList.add(subList);
        }

        System.out.println(cutList);
    }

    public static int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}
