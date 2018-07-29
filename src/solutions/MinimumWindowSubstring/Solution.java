package solutions.MinimumWindowSubstring;

/**
 * Created by dyj on 7/29/18.
 *
 Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

 Example:

 Input: S = "ADOBECODEBANC", T = "ABC"
 Output: "BANC"
 Note:

 If there is no such window in S that covers all characters in T, return the empty string "".
 If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 */
public class Solution {
    public String minWindow(String s, String t) {
        int[] arr = new int[58]; // saving t characters left in range.
        int count = t.length();
        int minLen = Integer.MAX_VALUE;
        int start = 0; // record the start of min window

        for (char c : t.toCharArray()) {
            arr[c - 'A']++;
        }

        for (int begin = 0, end = 0; end < s.length(); end++) {
            if (arr[s.charAt(end) - 'A']-- > 0) {
                count--;
            }

            while (count == 0) { // when range contains all t characters
                if (end - begin + 1 < minLen) {
                    minLen = end - begin + 1;
                    start = begin;
                }
                if (arr[s.charAt(begin++) - 'A']++ == 0) {
                    count++;
                }
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }
}
