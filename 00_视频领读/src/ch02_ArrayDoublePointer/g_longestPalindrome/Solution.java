package ch02_ArrayDoublePointer.g_longestPalindrome;
/**
 * @Author mapKey
 * @Date 7/22/2022 9:23 AM
 * @Since version-1.0
 * @Description
 * 5最长回文子串
 回文串:正反着读都一样
 */
class Solution {
    public String longestPalindrome(String s) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            //以s.charAt[i]为中心得最长回文子串
            String s1 = palindrome(s, i, i);
            // 以 s[i] 和 s[i+1] 为中心的最长回文子串
            String s2 = palindrome(s, i, i + 1);
            // res = longest(res, s1, s2)
            res = res.length() >= s1.length() ? res : s1;
            res = res.length() >= s2.length() ? res : s2;

        }
        return res;
    }

    String palindrome(String s, int l, int r) {
        // 防止索引越界
        while (l >= 0 && r < s.length()
                && s.charAt(l) == s.charAt(r)) {
            // 向两边展开
            /*相背而行的左右指针*/
            l--;
            r++;
        }
        // 返回以 s[l] 和 s[r] 为中心的最长回文串
        //substring左闭右开, l和r此时都比最长子串的边界要扩大一步
        return s.substring(l + 1, r);
    }
}