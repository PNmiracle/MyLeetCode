package ch04_slidingWindow.d_lengthOfLongestSubstring;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

/**
 * @Author mapKey
 * @Date 7/24/2022 9:36 AM
 * @Since version-1.0
 * @Description
 * 3. 无重复字符的最长子串
 */
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int res = 0;
        HashMap<Character, Integer> window = new HashMap<>();
        int left = 0, right = 0;
        //外部的外部循环走一步,一定执行right扩大窗口,
        // 不一定left缩小窗口,但有可能left移动几步,缩得很厉害
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            window.put(c, window.getOrDefault(c, 0) + 1);
            Collection<Integer> values = window.values();
            //boolean isFlag = false;
            //for (Integer value : values) {
            //    if (value > 1) {
            //        isFlag = true;
            //        break;
            //    }
            //}
            //if (isFlag) {
            while (window.get(c) > 1) {
                char d = s.charAt(left);
                left++;
                //res = Math.max(right - left - 1, res);
                window.put(d, window.get(d) - 1);
            }
            //扩大窗口和缩小窗口都执行完毕后,更新结果数据
            res = Math.max(res, right - left);
        }

        return res;
    }
}
