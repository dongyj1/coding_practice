package solutions.KInversePairsArray629;

/**
 * Created by dyj on 5/6/18.
 */


public class Solution {
    public int kInversePairs(int n, int k) {
        return n * k;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().kInversePairs(1000000, 1000000));
    }
}
