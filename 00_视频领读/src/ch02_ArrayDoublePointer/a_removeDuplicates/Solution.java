package ch02_ArrayDoublePointer.a_removeDuplicates;

class Solution {
    public int removeDuplicates(int[] nums) {
        //int count = 0;
        if (nums.length == 0) {
            return 0;
        }
        int slow = 0, fast = 0;
        //while (fast + 1 < nums.length) {
        //    //slow++;
        //    if (nums[fast] == nums[fast + 1]) {
        //        fast++;
        //    } else {
        //        nums[slow] = nums[fast];
        //        slow++;
        //        fast++;
        //    }
        //}
        while (fast < nums.length) {
            if (nums[fast] != nums[slow]) {
                slow++;
                //维护nums[0..slow]无重复
                nums[slow] = nums[fast];
            }

            fast++;
        }

        return slow + 1;
    }
}