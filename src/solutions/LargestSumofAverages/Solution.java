package solutions.LargestSumofAverages;



class Solution {
    // Memorization DFS search
    public double largestSumOfAverages(int[] A, int K) {
        
        int n = A.length;
        double[] presum = new double[n + 1];
        double[][] f = new double[n][K + 1];

        return helper(A, n, presum, 0, K, f);
    }

    private double helper(int[] A, int n, double[] presum, int idx, int K, double[][] f) {
        if (K == 1) return (presum[n] - presum[idx]) / (n - idx);
        if (f[idx][K] != 0) return f[idx][K];

        for (int i = idx; i < n; i++) {
            f[i][K] = Math.max(f[i][K], 
                        (presum[i + 1] - presum[idx]) / (i - idx + 1)) + helper(A, n, presum, i + 1, K - 1, f);
        }

        return f[idx][K];
    }

    // DP

    public double largestSumOfAverages1(int[] A, int K) {
        int n = A.length;
        double[][] f = new double[n][K + 1];
        double [] preSum = new double[n + 1];

        for (int i = 0; i < n; i++){
            preSum[i + 1] = preSum[i] + A[i];
            f[i][1] = preSum[i + 1] / (i + 1);
        }

        for (int j = 2; j <= K; j++) {
            for (int i = 0; i < n; i++) {
                for (int p = 0; p < i; p++) {
                    f[i][j] = Math.max(f[i][j], (preSum[i + 1] - preSum[p + 1])/(i - p) + f[p][j - 1]);
                }
            }
        }

        return f[n - 1][K];
    }
}