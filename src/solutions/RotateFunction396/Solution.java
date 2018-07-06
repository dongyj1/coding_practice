package solutions.RotateFunction396;

/**
 * Created by dyj on 4/23/18.
 * Given an array of integers A and let n to be its length.

 Assume Bk to be an array obtained by rotating the array A k positions clock-wise, we define a "rotation function" F on A as follow:

 F(k) = 0 * Bk[0] + 1 * Bk[1] + ... + (n-1) * Bk[n-1].

 Calculate the maximum value of F(0), F(1), ..., F(n-1).
 */
public class Solution {

    public int maxRotateFunction(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int sum = 0;
        int temp = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
            temp += i * A[i];
        }

        int max = temp;
        for (int i = 0; i < A.length - 1; i++) {
            temp = temp + sum - A.length * A[A.length - i - 1];
            max = Math.max(temp, max);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] A = {4, 3, 2, 6}; // 15
        System.out.println(new Solution().maxRotateFunction(A));
    }
}
