package solutions.PartitionEqualSubsetSum416;

import java.util.stream.IntStream;

/**
 * Created by dyj on 6/3/18.
 *
 Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

 Note:
 Each of the array element will not exceed 100.
 The array size will not exceed 200.
 Example 1:

 Input: [1, 5, 11, 5]

 Output: true

 Explanation: The array can be partitioned as [1, 5, 5] and [11].
 Example 2:

 Input: [1, 2, 3, 5]

 Output: false

 Explanation: The array cannot be partitioned into equal sum subsets.
 *
 */


public class Solution {

    // basic dp
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if ((sum & 1) == 1) {
            return false;
        }
        int target = sum / 2;
        // dp[i][j] : can partition with nums[i] in number j
        boolean[][] dp = new boolean[nums.length + 1][target + 1];

        for (int i = 0; i <= nums.length; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i <= nums.length; i++) {
            for (int j = 1; j <= target; j++) {
                dp[i][j] = dp[i - 1][j];
                if (nums[i - 1] <= j) {
                    dp[i][j] = (dp[i][j] || dp[i - 1][j - nums[i - 1]]);
                }
            }
        }

        return dp[nums.length][target];
    }

    // 01 knapsack
    public boolean canPartition1(int[] nums) {
        int sum = IntStream.of(nums).sum();
        if ((sum & 1) == 1) {
            return false;
        }
        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        for (int i = 0; i < nums.length; i++) {
            for (int j = target; j >= nums[i]; j--) {
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }

        return dp[target];
    }

    // Memorized DFS
    public boolean canPartition2(int[] nums) {
        int sum = IntStream.of(nums).sum();
        if ((sum & 1) == 1) {
            return false;
        }
        int target = sum / 2;
        boolean[] used = new boolean[nums.length];

        // start from end
        sum = nums[nums.length - 1];
        used[nums.length - 1] = true;

        return dfs(nums, used, sum, target, nums.length - 1);
    }

    private boolean dfs(int[] nums, boolean[] used, int sum, int target, int n) {
        if (sum == target) {
            return true;
        }
        for (int i = n; i >= 0; i--) {
            if (used[i]) continue;
            if (sum + nums[i] > target) continue;

            used[i] = true;
            if (dfs(nums, used, sum + nums[i], target, i - 1)) {
                return true;
            }
            used[i] = false;
        }
        return false;
    }


    public static void main(String[] args) {
        int[] input = new int[]{1, 5, 11, 5};

        System.out.println(new Solution().canPartition2(input));
    }
}
