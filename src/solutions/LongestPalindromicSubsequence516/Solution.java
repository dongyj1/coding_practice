package solutions.LongestPalindromicSubsequence516;

/**
 * Created by dyj on 4/25/18.
 *
 * Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.
 *
 * Input:
 "bbbab"
 Output:
 4
 */
public class Solution {

    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        int[][] dp = new int[len][len]; // from i to j longest seq
        int max = 0;

        for (int i = len - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < len; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j == i + 1) {
                        dp[i][j] = Math.max(dp[i][j], 2);
                    } else {
                        dp[i][j] = Math.max(dp[i][j], dp[i + 1][j - 1] + 2);
                    }
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][len - 1];
    }

    public static void main(String[] args) {
        String test = "bbbab";
        System.out.println(new Solution().longestPalindromeSubseq(test));
    }
}
