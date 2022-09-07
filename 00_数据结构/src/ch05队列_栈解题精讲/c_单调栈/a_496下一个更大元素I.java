package ch05队列_栈解题精讲.c_单调栈;

import java.util.HashMap;
import java.util.Stack;

/**
 * @Author mapKey
 * @Date 2022-08-30-1:43 PM
 */

class a1_Solution {
    int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // 记录 nums2 中每个元素的下一个更大元素
        int[] greater = nextGreaterElement(nums2);
        // 转化成映射：元素 x -> x 的下一个最大元素
        HashMap<Integer, Integer> greaterMap = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            greaterMap.put(nums2[i], greater[i]);
        }
        // nums1 是 nums2 的子集，所以根据 greaterMap 可以得到结果
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            res[i] = greaterMap.get(nums1[i]);
        }
        return res;
    }

    int[] nextGreaterElement(int[] nums) {
        int n = nums.length;
        // 存放答案的数组
        int[] res = new int[n];
        Stack<Integer> s = new Stack<>();
        // 倒着往栈里放, 栈先见先出(FIFO), 靠近后面的更大元素要压在栈底来备用
        // res[i]越大,不确定性越低, 所以倒着遍历
        // stk目标: 比nums[i]大的所有数从小到大堆在从栈顶到栈底
        for (int i = n - 1; i >= 0; i--) {
            // 判定个子高矮
            while (!s.isEmpty() && s.peek() <= nums[i]) {
                // 矮个起开，反正也被挡着了。。。
                s.pop();
            }
            // 此处更新答案, nums[i] 身后的更大元素
            res[i] = s.isEmpty() ? -1 : s.peek();
            //相当于迭代条件i++, 必须要做的
            // 循环最后push, 循环开始pop
            s.push(nums[i]);
        }
        return res;
    }
}

class a_mySolution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] greater = nextGreaterElement(nums2);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            map.put(nums2[i], greater[i]);
        }

        int[] ans = new int[nums1.length];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = map.get(nums1[i]);
        }
        return ans;
    }

    private int[] nextGreaterElement(int[] nums) {
        int[] res = new int[nums.length];
        Stack<Integer> stk = new Stack<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stk.isEmpty() && nums[i] >= stk.peek()) {
                stk.pop();
            }

            res[i] = stk.isEmpty() ? -1 : stk.peek();
            stk.push(nums[i]);
        }

        return res;
    }
}