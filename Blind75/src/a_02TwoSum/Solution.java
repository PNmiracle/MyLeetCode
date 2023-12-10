package a_02TwoSum;

import java.util.HashMap;

/**
 * @Author mapKey
 * @Date 2023-12-08-20:30.
 */
class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> prevMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            if (prevMap.containsKey(target - n)) {
                Integer prev = prevMap.get(target - n);
                return new int[]{i, prev};
            }
            prevMap.put(i, n);
        }
        return null;
    }
}
