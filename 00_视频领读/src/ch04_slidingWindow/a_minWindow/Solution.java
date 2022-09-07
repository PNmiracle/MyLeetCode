package ch04_slidingWindow.a_minWindow;

import java.time.Instant;
import java.util.HashMap;

/**
 * @Author mapKey
 * @Date 7/23/2022 9:53 AM
 * @Since version-1.0
 * @Description 76. 最小覆盖子串
 */
class Solution {
    public String minWindow(String s, String t) {
        //记录滑动窗口中所有字符对应的次数
        HashMap<Character, Integer> window = new HashMap<>();
        //记录目标
        HashMap<Character, Integer> need = new HashMap<>();
        //初始化need哈希表
        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        //维护窗口的左右指针
        int left = 0, right = 0;
        //valid 变量表示窗口window中满足 need 条件的字符个数,最大值为need.size()
        int valid = 0;
        // 结果数据:记录最小覆盖子串的起始,末尾索引及长度
        int start = 0, end = 0, len = Integer.MAX_VALUE;
        while (right < s.length()) {
            // c 是将移入窗口的字符
            char c = s.charAt(right);
            //右移窗口
            right++;
            // 进行窗口内数据的一系列更新
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c,0) + 1);
                if (window.get(c).equals(need.get(c))){
                    //if (window.get(c) == (need.get(c))){
                    /*Integer是对象啊
                    Integer会缓存频繁使用的数值，
                    数值范围为-128到127，在此范围内直接返回缓存值。
                    超过该范围就会new 一个对象。*/
                    valid++;
                }
            }

            // 判断左侧窗口是否要收缩
            while (valid == need.size()) {
                /*缩小窗口前,满足need,更新结果数据*/
                if (right - left < len) {
                    start = left;
                    end = right;
                    len = right - left;
                }
                //右移左指针,缩小窗口
                char d = s.charAt(left);
                left++;
                // 进行窗口内数据的一系列更新
                if (need.containsKey(d)) {
                    if (window.get(d).equals(need.get(d))){
                        valid--;
                    }
                    window.put(d,window.getOrDefault(d,0) - 1);
                }
            }


        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, end);
    }
}
