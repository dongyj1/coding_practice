package solutions.isSubsequence392;

/**
 * Created by dyj on 4/21/18.
 * Given a string s and a string t, check if s is subsequence of t.

 You may assume that there is only lower case English letters in both s and t. t is potentially a very long (length ~= 500,000) string, and s is a short string (<=100).

 A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ace" is a subsequence of "abcde" while "aec" is not).
 */
class Solution {
    public boolean isSubsequence(String s, String t) {
        if (s.length() == 0) return true;
        int indexS = 0, indexT = 0;
        while (indexT < t.length() && indexS < s.length()) {
            if (t.charAt(indexT++) == s.charAt(indexS)) {
                indexS++;
            }
        }
        return indexS == s.length();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "abc", t = "ahbgdc";
        System.out.println(solution.isSubsequence(s, t));
    }
}
