package cn.han.leetcode;

/**
 * 1. 两数之和
 * 给定一个整数数组 nums 和一个整数目标值 target，
 * 请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标
 * 示例 1：
 * <p>
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * 示例 2：
 * <p>
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 * 示例 3：
 * <p>
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 *
 * 1纳秒=1000 皮秒　
 * 　　1纳秒 =0.001  微秒
 * 　　1纳秒=0.000001 毫秒
 * 　　1纳秒=0.00000 0001秒
 * @Author han_s
 * @Date 2022/5/20 9:43
 * @ProName maven_test
 */
public class Solution1 {
    private static int[] ints = {1, 5, 8, 2, 6, 4, 9};
    private static int target = 6;

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        solutionOne();
        long endTime = System.nanoTime();
        System.out.println("纳秒："+(endTime-startTime));
    }

    public static void solutionOne() {
        for (int i = 0; i < ints.length - 1; i++) {//减1目的让最后两位相加
            int j = i + 1;
            for (; j < ints.length; j++) {
                int sum = ints[i] + ints[j];
                if (sum == target) {
                    System.out.println("[" + i + "," + j + "]");
                }
            }
        }
    }

    public static  void solutionTwo(){
        for (int i = 0; i < ints.length; i++) {
            //初始值
            //

        }
    }
}
