package cn.han.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author han_s
 * @Date 2022/3/28 12:36
 * @ProName extdemo
 */
public class SuanTest {
    public static void main(String[] args) {
//        int[] ints = {2,4,5,7,3};
//        for (int i = 0; i < ints.length; i++) {
//            for (int j = 0; j < ints.length - i-1; j++) {
//                int temp = 0;
//                if (ints[j]>ints[j+1]){
//                    temp = ints[j];
//                    ints[j] = ints[j + 1];
//                    ints[j + 1] = temp;
//                }
//            }
//        }
//        System.out.println(Arrays.toString(ints));
        Solution solution = new Solution();
        boolean palindrome = solution.isPalindrome(10);
        System.out.println(palindrome);
    }

    static class Solution {
        public boolean isPalindrome(int x) {
            int count = 0;
            String value = String.valueOf(x);
            for (int i = 0; i < value.length() / 2; i++) {
                if (value.charAt(i) == value.charAt(value.length() - i - 1)) {//首尾比较是否相同
                    count++;
                }
            }
            return count == value.length() / 2;
        }

        public int lengthOfLongestSubstring(String s) {

            int fs = 0;//用于存取最后要返回的长度
            //1.创建一个map，key：字符，value: 字符下标 `
            Map<Character,Integer> map = new HashMap<>();
            //2.遍历字符串，并将字符串存入map中
            for (int start = 0,end = 0; end < s.length(); end++) {
                char ch = s.charAt(end);
                if (map.containsKey(ch)){ //若已经存在于map中，则将其对应的value拿出来
                    start = Math.max(map.get(ch),start);
                }
                // 取较大值
                fs = Math.max(fs,end - start +1);
                //存入map中
                map.put(ch,end + 1);
            }
            return fs;
            }
    }
}
