package ch02_ArrayDoublePointer.d_twoSum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @Author mapKey
 * @Date 7/21/2022 8:58 AM
 * @Since version-1.0
 * @Description
 *  Sorted	167. 两数之和 II - 输入有序数组
 *
 *  给你一个下标从 1 开始的整数数组 numbers ，该数组已按 非递减顺序排列 
 *  ，请你从数组中找出满足相加之和等于目标数 target 的两个数。如果设这两个数分别是 numbers[index1]
 *  和 numbers[index2] ，则 1 <= index1 < index2 <= numbers.length 。

 */


class Solution1 {
    public int[] twoSum(int[] numbers, int target) {
        //int left = 0, right = numbers.length - 1;
        //int sum = numbers[left] + numbers[right];
        //while (sum != target) {
        //    if (sum < target) {
        //        left++;
        //    } else {
        //        right--;
        //    }
        //}
        //

        /*左右指针技巧*/
        int left = 0, right = numbers.length - 1;
        int sum = 0;//声明在外部,避免每进一次循环都要重新声明并初始化一次
        //循环条件:
        while (left < right) {
            //循坏开头:给sum赋值
            sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[]{left + 1, right + 1};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        //遍历完也没找到sum==target的情况
        return new int[]{-1, -1};
    }
}
/**
 * @Author mapKey
 * @Date 7/21/2022 9:25 AM
 * @Since version-1.0
 * @Description 
 * 1.两数之和
 */
/*暴力枚举*/
class Solution2 {
    public int[] twoSum(int[] nums, int target) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                sum = nums[i] + nums[j];
                if (sum == target) {
                    return new int[] {i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }
}
/*排序后双指针*/
class Solution3 {
    public int[] twoSum(int[] nums, int target) {
        Arrays.sort(nums);
        /*左右指针技巧*/
        int left = 0, right = nums.length - 1;
        int sum = 0;//声明在外部,避免每进一次循环都要重新声明并初始化一次
        //循环条件:
        while (left < right) {
            //循坏开头:给sum赋值
            sum = nums[left] + nums[right];
            if (sum == target) {
                return new int[]{left, right};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        //遍历完也没找到sum==target的情况
        return new int[]{-1, -1};
    }
}


class Solution4 {
    public int[] twoSum(int[] nums, int target) {

        // 维护 val -> index 的映射
        HashMap<Integer, Integer> valToIndex = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            /*// 存入 val -> index 的映射
            valToIndex.put(nums[i], i);
            若放在上面位置,则在输出结果时need的范围把自身也给算上了*/
            // 查表，看看是否有能和 nums[i] 凑出 target 的元素
            int need = target - nums[i];
            if (valToIndex.containsKey(need)) {
                return new int[]{valToIndex.get(need), i};
            }
            // 存入 val -> index 的映射
            valToIndex.put(nums[i], i);

        }
        return null;
    }
}
/**
 * @Author mapKey
 * @Date 7/22/2022 9:03 AM
 * @Since version-1.0
 * @Description
 * 15.三数之和
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
 * 使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 */
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        return null;
    }
}

