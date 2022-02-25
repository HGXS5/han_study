package cn.han.exmale;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * 1.声明参数：
 *      设置字符串数组：用来排序的数组
 *      设置字符串数组长度；数组中的个数
 *      设置一个boolean数组；用来表示数组位置的数值是否被使用过，长度要与字符串数组长度一致，方便比较
 *      设置一个空字符串参数；用来拼接筛选过的参数
 *      设置一个空的TreeSet集合：
 *      设置一个二维数组：用来表示判断非当前索引,长度都与字符串数组长度相同
 *
 */
public class Demo2 {
    String[] stra = {"1", "2", "2", "3", "4", "5"};
    int n = stra.length;
    boolean[] visited = new boolean[n];
    String result = "";
    TreeSet<String> ts = new TreeSet<String>();
    int[][] a = new int[n][n];

    /**
     * 1.用双循环的方式，设置二维数组。单纯的就是将索引组合方式放在一起，并将两者都是当前索引或者3和5（5或3）索引位置设置为0，其他的情况全部设置为1
     * 2.定义好索引位置规则后，开始进行查询满足条件的值
     */
    private void searchMap() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    a[i][j] = 0;
                } else {
                    a[i][j] = 1;
                }
            }
        }
        //3 和 5 不能相连
        a[3][5] = 0;
        a[5][3] = 0;
        //开始遍历
        for (int i = 0; i < n; i++) {
            search(i);
        }
        Iterator<String> it = ts.iterator();
        while (it.hasNext()) {
            String str = it.next();
            //4 不能在第三位
            if (str.indexOf("4") != 2) {
                System.out.println(str);
            }
        }
    }

    /**
     * 1.根据要进行筛选的索引，将对应索引位置的boolean数组设置为true，并获取当前索引位置的字符串数组的值和result空字符串拼接
     * 2.判断当前result的长度是否等于字符串长度，相等就添加到treeSet集合中
     * 3.使用回调函数用来判断当前的索引，依次组合成二维数组，并判断是否等于1且尚未使用过，满足条件继续回调search函数
     *      往复完成result拼接，直至满足条件后，把该result放入treeMap集合
     *
     */
    private void search(int startIndex) {
        visited[startIndex] = true;
        result = result + stra[startIndex];
        if (result.length() == n) {
            ts.add(result);
        }
        //回调函数
        for (int j = 0; j < n; j++) {
            if (a[startIndex][j] == 1 && visited[j] == false) {
                search(j);
            } else {
                continue;
            }
        }
        //一个 result 结束后踢掉最后一个，寻找别的可能性，若没有的话，则继续向前踢掉当前最后一个
        result = result.substring(0, result.length() - 1);
        visited[startIndex] = false;

    }

    public static void main(String[] args){
        new Demo2().searchMap();
    }
}
