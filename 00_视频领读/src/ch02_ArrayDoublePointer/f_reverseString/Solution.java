package ch02_ArrayDoublePointer.f_reverseString;

class Solution {
    public void reverseString(char[] s) {
        int left = 0, right = s.length - 1;
        char temp = 0;
        while (left < right) {
            temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }
}
