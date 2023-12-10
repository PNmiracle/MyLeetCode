package a_01ContainsDuplicate;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @Author mapKey
 * @Date 2023-12-08-16:14
 */
class Solution1 {
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }
}

class Solution {
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> hSet = new HashSet<>();
        for (int num : nums) {
            if (hSet.contains(num)) {
                return true;
            }
            hSet.add(num);

        }
        return false;
    }
}