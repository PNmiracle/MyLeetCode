package ch03_Prefix_Sum.DifferenceArray;

/**
 * @Author mapKey
 * @Date 7/23/2022 8:52 AM
 * @Since version-1.0
 * @Description 370. 区间加法🔒
 */
class Solution1 {
    public int[] getModifiedArray(int length, int[][] updates) {
        //res[]初始化全为0;默认初始化
        int[] res = new int[length];
        //构造差分工具类的对象
        Difference diff = new Difference(res);
        for (int[] update : updates) {
            int i = update[0];
            int j = update[1];
            int val = update[2];
            diff.increment(i, j, val);
        }
        return diff.result();
    }
}

//构造差分工具类的对象
class Difference {
    //私有属性:差分数组
    private int[] diff;

    public Difference(int[] nums) {
        //assert意为断言的意思，这个关键字可以判断布尔值的结果是否和预期的一样，
        // 如果一样就正常执行，否则会抛出AssertionError。
        assert nums.length > 0;
        diff = new int[nums.length];
        diff[0] = nums[0];
        for (int i = 1; i < diff.length; i++) {
            diff[i] = nums[i] - nums[i - 1];
        }
    }

    public void increment(int i, int j, int val) {
        diff[i] += val;
        if (j + 1 < diff.length) {
            diff[j + 1] -= val;
        }
    }

    public int[] result() {
        int[] res = new int[diff.length];
        res[0] = diff[0];
        for (int i = 1; i < diff.length; i++) {
            res[i] = res[i - 1] + diff[i];
        }

        return res;
    }
}

/**
 * @Author mapKey
 * @Date 7/23/2022 9:09 AM
 * @Since version-1.0
 * @Description 1109. 航班预订统计
 */

class Solution2 {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] res = new int[n];

        Difference diff = new Difference(res);
        for (int[] booking : bookings) {
            int i = booking[0];
            int j = booking[1];
            int seats = booking[2];
            diff.increment(i - 1, j - 1, seats);
        }

        return diff.result();
    }
}

//1094. 拼车
class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        //boolean isFlag = true;
        int[] nums = new int[1001];
        Difference diff = new Difference(nums);
        for (int[] trip : trips) {
            int numP = trip[0];
            int i = trip[1];
            //trip[2]站乘客已经下车,所以乘客在车上的区间为[trip[1]..trip[2] - 1]
            int j = trip[2] - 1;
            diff.increment(i, j, numP);
        }
        int[] result = diff.result();
        for (int i : result) {
            if (i > capacity) {
                return false;
            }
        }

        return true;
    }
}