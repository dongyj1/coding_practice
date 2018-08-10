package solutions;

/**
 * Created by dyj on 4/10/18.
 */

/**
 * Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.
 *
 */

public class CombinationSumIV377 {
    public int combinationSum4(int[] nums, int target) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] <= i) {
                    dp[i] += dp[i - nums[j]];
                }
            }
        }
        return dp[target];
    }
}

/**
 * Follow up: If there are negatives in array?
 *
 * For this problem, we must add a restriction to it, that each number is used only at most one time.
 * For example, given {-1, 1}, target = 1, it's obvious that we can choose n 1s and (n - 1) -1, we can sum up to
 * 1.
 *
 * I don't think recursion will work if we don;t add extra requirement. basically, DP is to memorize the
 * results of sub problems, which is exactly what recursion will re-calculate instead.
 */
class FollowUp {
}
