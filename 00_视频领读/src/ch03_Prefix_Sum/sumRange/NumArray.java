package ch03_Prefix_Sum.sumRange;

class NumArray {
    //私有属性:前缀和数组,默认初始化全0;索引偏移量为+1(往左偏一格),
    // 所以preSum[]比nums[]相对应的元素索引要大+1
    private int[] preSum;
    //在构造器中传入一个数组nums[],构造前缀和数组
    public NumArray(int[] nums) {
        // preSum[0] = 0，便于计算累加和
        preSum = new int[nums.length + 1];

        for (int i = 0; i < preSum.length; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
    }
    /* 查询闭区间 [left, right] 的累加和 */
    public int sumRange(int left, int right) {
        return preSum[right + 1] -preSum[left];
        //return 0;
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */
