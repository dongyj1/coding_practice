package solutions.MinimumWindowSubsequence;

/**
 * Created by dyj on 8/7/18.
 *
 Given strings S and T, find the minimum (contiguous) substring W of S, so that T is a subsequence of W.

 If there is no such window in S that covers all characters in T, return the empty string "". If there are multiple such minimum-length windows, return the one with the left-most starting index.

 Example 1:

 Input:
 S = "abcdebdde", T = "bde"
 Output: "bcde"
 Explanation:
 "bcde" is the answer because it occurs before "bdde" which has the same length.
 "deb" is not a smaller window because the elements of T in the window must occur in order.


 Note:

 All the strings in the input will only contain lowercase letters.
 The length of S will be in the range [1, 20000].
 The length of T will be in the range [1, 100].
 */
public class Solution {
    /**
     * Idea 1: Brute force: sliding window check all windows contains sequence
     * Idea 2: DP 2d
     */
    public String minWindow(String S, String T) {
        if (S.length() < T.length()) {
            return "";
        }
        int N = S.length(), M = T.length();
        int[][] dp = new int[M + 1][N + 1]; // dp[i][j] 0-i S contains 0-j T start index
        // init DP
        for (int i = 0; i <= N; i++) {
            dp[0][i] = i + 1;
        }


        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                if (T.charAt(i - 1) == S.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        int start = 0, len = N + 1;
        for (int i = 1; i <= N; i++) {
            if (dp[M][i] != 0 && i - dp[M][i] + 1 < len) {
                len = i - dp[M][i] + 1;
                start = dp[M][i] - 1;
            }
        }
        return (len == N + 1) ? "" : S.substring(start, start + len);
    }

    public static void main(String[] args) {
        String S = "abcdebdde";
        String T = "bde";
        System.out.println(new Solution().minWindow(S, T));
    }
}
/**
 * S abaa 
 * T aa
 *  0 0 0
 *  0 1 0
 *  0 1 0
 *  0 1 3
 *  0 1 2
 */