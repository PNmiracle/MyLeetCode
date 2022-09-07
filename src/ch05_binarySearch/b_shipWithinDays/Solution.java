package ch05_binarySearch.b_shipWithinDays;

/**
 * @Author mapKey
 * @Date 7/25/2022 10:56 AM
 * @Since version-1.0
 * @Description 1011. 在 D 天内送达包裹的能力
 */
class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int left = 0, right = 0;
        //left,right指针实际含义为所要求的运载能力cap
        //cap最小为一天装一件包裹,即为包裹重量的最大值
        //cap最大为一天装了所有包裹,即为包裹重量总和
        for (int w : weights) {
            left = Math.max(left, w);
            right += w;
        }
        //确定好了搜索区间

        //框架走起
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (f(weights, mid) <= days) {
            //    求最小cap, 即为搜索target(days)的左侧边界
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }


        return left;
    }

    private int f(int[] weights, int x) {
        int days = 0;
        for (int i = 0; i < weights.length; ) {
            int cap = x;
            //while (cap >= weights[i]) { 角标越界
            while (i < weights.length) {
                if (cap >= weights[i]) {
                    cap -= weights[i];
                    i++;
                } else {
                    break;
                }

            }
            //新的一天,cap重新赋值,刷新充满
            days++;

        }
        return days;
    }
}