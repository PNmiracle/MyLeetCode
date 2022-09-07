package ch05_binarySearch.c_minEatingSpeed;

/**
 * @Author mapKey
 * @Date 7/25/2022 11:23 AM
 * @Since version-1.0
 * @Description 875. 爱吃香蕉的珂珂
 */
class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1, right = 1000000000 + 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (f(piles, mid) <= h) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private int f(int[] piles, int speed) {
        int hours= 0;
        for (int i = 0; i < piles.length; i++) {
            //if (speed > piles[i]) {
            //    hours ++;
            //} else {
            //    hours += piles[i] / speed + 1;
            //}
            hours += piles[i] / speed;
            if (piles[i] % speed > 0) {
                hours ++;
            }
        }

        return hours;
    }
}