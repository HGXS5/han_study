package cn.han;


import cn.han.pojo.Student;
import cn.han.workdemo.PropertyManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.regex.Pattern.CASE_INSENSITIVE;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class Demo {

    @Test
    public void testList() {
        Set<Student> hashSet = new HashSet<Student>();
        ArrayList<String> ar = new ArrayList<String>();
        Student s1 = new Student("小米", 3000);
        Student s2 = new Student("小米", 3000);
        Student s3 = new Student("华为", 5000);
        Student s4 = new Student("华为", 5000);
        Student s5 = new Student("苹果", 6000);
        hashSet.add(s1);
        hashSet.add(s2);
        hashSet.add(s3);
        hashSet.add(s4);
        hashSet.add(s5);

        System.out.println(hashSet.toString());
//        for (String s : ar) {
//            if ("3".equals(s)){
//                ar.remove(s);
//            }
//        }
//        for (int i = 0; i < ar.size(); i++) {
//            if ("3".equals(ar.get(i))){
//                ar.remove(i);
//            }
//        }
        System.out.println(ar.toString());
    }

    @Test
    public void testMap() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        map.put("c", 3);
        map.put("c", 3);
        System.out.println(map.toString());
//        Set<Map.Entry<String, Integer>> entries = map.entrySet();
//        Iterator<Map.Entry<String, Integer>> iterator = entries.iterator();
//        while (iterator.hasNext()){
//            Map.Entry<String, Integer> next = iterator.next();
//            System.out.println(next.toString());
//        }
    }

    @Test
    public void testRandom() {
        Random rd = new Random();
        for (int i = 0; i < 50; i++) {
            System.out.print(rd.nextInt(9) + " ");
        }
    }

    @Test
    public void testThread() {
        ThreadLocal<String> tl = new ThreadLocal<>();

        System.out.println(Thread.currentThread());
    }

    @Test
    public void JDK8Test() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        List<Student> list = new ArrayList<>();
        list.add(new Student("a", 1));
        list.add(new Student("a", 2));
        list.add(new Student("c", 3));
        list.add(new Student("c", 4));
        List<Integer> collect = list.stream().peek(student -> System.out.println(student.getName())).map(Student::getAge).collect(Collectors.toList());
        list.stream().forEach(student -> hashMap.merge(student.getName(), student.getAge(), Integer::sum));
        System.out.println(hashMap);
    }

    @Test
    public void hostTest() {
        InetAddress localHost = null;
        try {
            localHost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        System.out.println(localHost);
    }

    @Test
    public void washuTest() {
        SecureRandom mySecureRand = new SecureRandom();
        long secureInitializer = mySecureRand.nextLong();
        Random random = new Random(secureInitializer);
        long aLong = random.nextLong();
        System.out.println(secureInitializer);
        System.out.println(aLong);
        List<String> nicList = getNICList();
        for (String s : nicList) {
            System.out.println(s);
        }
    }

    public static List<String> getNICList() {
        List<String> ipList = new ArrayList<>();
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress ip = (InetAddress) addresses.nextElement();
                    if (ip instanceof Inet4Address
                            && !ip.isLoopbackAddress() //loopback地址即本机地址，IPv4的loopback范围是127.0.0.0 ~ 127.255.255.255
                            && !ip.getHostAddress().contains(":")) {
                        ipList.add(ip.getHostAddress());
                    }
                }
            }

        } catch (Exception e) {
//            logger.error("获取ip异常,返回127.0.0.1",e);
            ipList.clear();
            ipList.add("127.0.0.1");
            return ipList;
        }
        return ipList;
    }

    @Test
    public void strTest() {
//        String path = "ftp:10.2.2.2:60//er";
//        String value = PropertyManager.getProperty("test123");
//        int i = value.indexOf(value.toString());
//        System.out.println(i);
//        System.out.println(value.toString());
//        if (path.contains(value) && value.length() > 0) {
//            System.out.println("ok");
//        }
//
//        int i1 = path.trim().indexOf(value);
//        System.out.println(i1);
        String json = "[{\"assetId\":\"50CP33010020220516703746\",\"programName\":\"生活服务\",\"programStartTime\":\"2022-05-23 00:00:00\",\"programEndTime\":\"2022-05-23 00:30:00\",\"shStatus\":2,\"pubStatus\":1,\"md5\":\"fbbc80b74d73075c\",\"insertTime\":1652669995107},{\"assetId\":\"50CP33010020220516703747\",\"programName\":\"纪录片\",\"programStartTime\":\"2022-05-23 00:30:00\",\"programEndTime\":\"2022-05-23 01:00:00\",\"shStatus\":2,\"pubStatus\":1,\"md5\":\"238457ae10545e23\",\"insertTime\":1652669995107}]";
        List<Map> maps = JSON.parseArray(json, Map.class);
//        maps.stream().distinct().forEach();
////        int size = jsonArray.size();
//        System.out.println(size);
    }
    @Test
    public void number() {
        long l = 5L + 100 + (100 / 10);
        System.out.println(l);
    }
    @Test
    public void code() {
//        int UNIX_LINES = 0x01;
//        System.out.println(UNIX_LINES);
//        int CASE_INSENSITIVE = 0x02;
//        System.out.println(CASE_INSENSITIVE);
//        int COMMENTS = 0x04;
//        System.out.println(COMMENTS);
//        int MAX_VALUE = 0x7fffffff;
//        System.out.println(MAX_VALUE);
    }

    private static boolean isStaticResource(String path) {
        return Pattern.compile("\\.(html|css|js|jpg|png|gif|jsp)$", CASE_INSENSITIVE).matcher(path).find();
    }
}
