package ch05_binarySearch.a_searchRange;

/**
 * @Author mapKey
 * @Date 7/25/2022 10:13 AM
 * @Since version-1.0
 * @Description
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 */
class Solution {
    public int[] searchRange(int[] nums, int target) {


        return new int[] {leftBound(nums, target), rightBound(nums, target)};

    }

    int leftBound(int[] nums, int target) {
        //corner case
        if (nums == null || nums.length == 0) return -1;
        //搜索的左闭右闭区间
        int left = 0, right = nums.length - 1;
        //循环结束条件:left = right + 1
        while (left <= right) {
            int mid = left + (right - left) / 2;
            //要求搜索左侧边界,所以锁定左侧边界,不断收缩右侧边界,确定right=...;确定条件:nums[mid]偏右
            if (nums[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        //left的范围:[0, nums.length],确保数组角标不越界
        if (left >= nums.length) {
            return -1;
        }
        return nums[left] == target ? left : -1;
    }

    int rightBound(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) {
                //收缩左侧边界
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        if (left - 1 < 0) {
            return -1;
        }

        return nums[left - 1] == target ? left - 1 : -1;
    }
}