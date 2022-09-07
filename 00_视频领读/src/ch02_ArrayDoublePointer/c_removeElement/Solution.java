package ch02_ArrayDoublePointer.c_removeElement;
/**
 * @Author mapKey
 * @Date 7/21/2022 8:49 AM
 * @Since version-1.0
 * @Description 
 * 27. 移除元素
 * 283. 移动零
 */
class Solution1 {
    public int removeElement(int[] nums, int val) {
        int slow = 0, fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }
}

class Solution {
    public void moveZeroes(int[] nums) {
        int slow = removeElement(nums, 0);
        for (int i = slow; i < nums.length; i++){
            nums[i] = 0;
        }

    }

    public int removeElement(int[] nums, int val) {
        int slow = 0, fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }
}