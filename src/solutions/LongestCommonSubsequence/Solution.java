package solutions.LongestCommonSubsequence;



class Solution{

    // DP
    public int longestCommonSubstring(String a, String b){
        int m = a.length();
        int n = b.length();

        int[][] f = new int[m + 1][n + 1];

        // init

        // iteration
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    f[i][j] = Math.max(f[i - 1][j - 1] + 1, f[i][j]);
                }
            }
        }

        return f[m][n];
    }

    // Memorization Search
    public int longestCommonSubstring1(String a, String b){
        int m = a.length(), n = b.length();
        int[][] f = new int[m + 1][n + 1];

        return helper(a, a.length() - 1, b, b.length() - 1, f);
    }

    private int helper(String a, int i, String b, int j, int[][] f) {
        if (i < 0 || j < 0) return 0;
        if (f[i][j] > 0) return f[i][j];
        if (a.charAt(i) == b.charAt(j)) {
            f[i][j] = helper(a, i - 1, b, j - 1, f) + 1;
        } else {
            f[i][j] = Math.max(helper(a, i - 1, b, j, f), helper(a, i, b, j - 1, f));
        }
        return f[i][j];
    }

    public static void main(String[] s) {
        String a = "abc", b = "bc";
        System.out.println(new Solution().longestCommonSubstring1(a, b));
    }
}