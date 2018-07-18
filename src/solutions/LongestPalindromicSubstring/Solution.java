package solutions.LongestPalindromicSubstring;

/**
 * Created by dyj on 7/17/18.
 *
 Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

 Example 1:

 Input: "babad"
 Output: "bab"
 Note: "aba" is also a valid answer.
 Example 2:

 Input: "cbbd"
 Output: "bb"
 */
public class Solution {
    private int lo, maxLen;

    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char[] chs = s.toCharArray();
        if(chs.length < 2){
            return s;
        }

        for (int i = 0; i < chs.length - 1; i++) {
            helper(chs, i, i);
            helper(chs, i, i + 1);
        }
        return s.substring(lo, lo + maxLen);
    }

    private void helper(char[] chs, int left, int right) {
        while (left >= 0 && right < chs.length && (chs[left] == chs[right])) {
            left--;
            right++;
        }
        if (right - left - 1 > maxLen) {
            maxLen = right - left - 1;
            lo = left + 1;
        }
    }
}
