package solutions.DecodeWays;

/**
 * Created by dyj on 8/7/18.
 *
 A message containing letters from A-Z is being encoded to numbers using the following mapping:

 'A' -> 1
 'B' -> 2
 ...
 'Z' -> 26
 Given a non-empty string containing only digits, determine the total number of ways to decode it.

 Example 1:

 Input: "12"
 Output: 2
 Explanation: It could be decoded as "AB" (1 2) or "L" (12).
 Example 2:

 Input: "226"
 Output: 3
 Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).

 */
public class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.charAt(0) == '0') return 0;
        int[] dp = new int[s.length() + 1];
        dp[1] = 1;
        dp[0] = 1;
        for (int i = 1; i <= s.length(); i++) {
            char c = s.charAt(i - 1);
            if (c == '0') {
                if (i >= 2 && s.charAt(i - 2) <= '2' && s.charAt(i - 2) > '0') {
                    dp[i] = dp[i - 2];
                } else {
                    return 0;
                }
            } else {
                if (i >= 2 && s.charAt(i - 2) > '0' && ((c <= '6' && s.charAt(i - 2) <= '2') || (s.charAt(i - 2) == '1'))) {
                    dp[i] = dp[i - 1] + dp[i - 2];
                } else {
                    dp[i] = dp[i - 1];
                }
            }
        }
        return dp[s.length()];
    }
}
