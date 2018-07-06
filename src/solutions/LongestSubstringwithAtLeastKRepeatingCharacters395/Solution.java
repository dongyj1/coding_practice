package solutions.LongestSubstringwithAtLeastKRepeatingCharacters395;

import java.util.HashMap;

/**
 * Created by dyj on 4/22/18.
 * Find the length of the longest substring T of a given string (consists of lowercase letters only) such that every character in T appears no less than k times.
 Input:
 s = "aaabb", k = 3

 Output:
 3

sliding window : time O(n) space O(1)
 */
public class Solution {
    public int longestSubstring(String s, int k) {
        int res = 0;
        for (int numUniqueTarget = 1; numUniqueTarget <= 26; numUniqueTarget++) {
            res = Math.max(res, helper(s, k, numUniqueTarget));
        }
        return res;
    }

    private int helper(String s, int k, int numUniqueTarget) {
        int[] count = new int[128];
        int start = 0, end = 0;
        int numUnique = 0, numNoLessThanK = 0;
        int res = 0;

        while (end < s.length()) {
            if (count[s.charAt(end)]++ == 0) {
                numUnique++;
            }
            // use equal for preventing count too much
            if (count[s.charAt(end++)] == k) {
                numNoLessThanK++;
            }
            while (numUnique > numUniqueTarget) {
                if (count[s.charAt(start)]-- == k) {
                    numNoLessThanK--;
                }
                if (count[s.charAt(start++)] == 0) {
                    numUnique--;
                }
            }
            if (numUnique == numUniqueTarget && numNoLessThanK == numUnique) {
                res = Math.max(res, end - start);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "ababbc";
        int k = 2;
        Solution solution = new Solution();
        System.out.println(solution.longestSubstring(s, k));
    }
}
