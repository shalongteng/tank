package com.slt.tank.test;

/**
 * https://leetcode-cn.com/problems/first-missing-positive/
 * 给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。
 *
 * 示例 1:
 * 输入: [1,2,0]
 * 输出: 3
 *
 * 示例 2:
 * 输入: [3,4,-1,1]
 * 输出: 2
 *
 * 示例 3:
 * 输入: [7,8,9,11,12]
 * 输出: 1
 *
 * 提示：
 * 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的额外空间。
 */
public class Solution {
    public static void main(String[] args) {
        int [] a = {1,2,0};
        int [] a2 = {3,4,-1,1};
        int [] a3 = {1,7,8,9};

        System.out.println(firstMissingPositive(a));
        System.out.println(firstMissingPositive(a2));
        System.out.println(firstMissingPositive(a3));
    }
    public static int firstMissingPositive(int[] nums) {
        int[] temp = new int[nums.length];
        System.arraycopy(nums, 0, temp, 0, nums.length);

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 1 && nums[i] <= nums.length) {
                temp[nums[i] - 1] = nums[i];
            }
        }

        int i;
        for (i = 0; i < nums.length; i++) {
            if (temp[i] != (i + 1)) {
                break;
            }
        }

        return i + 1;
    }


}