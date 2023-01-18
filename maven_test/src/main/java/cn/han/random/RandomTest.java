package cn.han.random;

import java.util.*;

/**
 * @Author han_s
 * @Date 2023/1/4 10:24
 * @ProName maven_test
 */
public class RandomTest {
    /*随机选择的字符串字符*/
    private final static String chinese = "赵钱孙李周吴郑王小大符可流习";
    /*存放已被使用的chinese字符数组索引*/
    private static HashSet<Integer> indexList = new HashSet<>();
    /*随机选择的数字,设定在chinese字符数组长度下随机选择*/
    private static Random random = new Random();

    public static void main(String[] args) {
        for (int i1 = 0; i1 < 3; i1++) {
            /*组成名字*/
            StringBuffer sb = new StringBuffer();
        /*选择指定名字长度，
        1. 获取不重复索引
        2. 组合名字
        */
            int regionNum = getRegionNum(2, 4);
            System.out.println(regionNum);
            for (int i2 = 0; i2 < regionNum; i2++) {
                int addIndex = getAddIndex();
                if (addIndex == -1) {
                    break;
                }
                sb.append(chinese.charAt(addIndex));
            }


            String name = sb.toString();
            System.out.println(name);
            System.out.println("清空之前：" + indexList.toString());
            indexList.clear();
            System.out.println("清空之后：" + indexList.toString());
            int regionAge = getRegionNum(10, 100);
            System.out.println(regionAge);
        }

    }

    /**
     * 获取指定范围的值
     *
     * @param start
     * @param end
     * @return
     */
    private static int getRegionNum(int start, int end) {
        Random nameSize = new Random();
        return (int) (nameSize.nextFloat() * (end - start + 1) + start);
    }

    private static int getAddIndex() {
        /*获得在chinese字符数组长度下的随机索引*/
        int i = random.nextInt(chinese.length());
        /*indexList集合未存放数据时，在indexList集合中添加获得的索引值*/
        if (indexList != null && indexList.size() == 0) {
            indexList.add(i);
            return i;
        }
        return getOnlyIndex(i);
    }

    /**
     * 递归方式，获取唯一索引值
     *
     * @param i
     * @return
     */
    private static int getOnlyIndex(int i) {
        /*遍历indexList,比较新获得的索引值与indexList中的索引值是否相同*/
        for (Integer index : indexList) {
            /*最新获得索引值在indexList中存在相同的值，则需要重新获得新的索引值*/
            if (i == index) {
                /*当存放索引indexList集合大小与chinese字符数组长度一致，返回-1*/
                if (indexList.size() == chinese.toCharArray().length) {
                    return -1;
                }
                /*重新获取最新索引值*/
                i = random.nextInt(chinese.length());
                return getOnlyIndex(i);
            }
        }
        /*比较完成后，不存在相同的索引值，则进行添加*/
        indexList.add(i);
        return i;
    }
}
