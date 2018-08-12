package solutions.PartitiontoKEqualSumSubsets;

/*
Given an array of integers nums and a positive integer k, find whether it's possible to divide 
this array into k non-empty subsets whose sums are all equal.

Example 1:
Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
Output: True
Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
*/


// Straightforward dfs solution
public class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for(int num:nums)sum += num;
        if(k <= 0 || sum % k != 0)return false;
        int[] visited = new int[nums.length];
        return canPartition(nums, visited, 0, k, 0, 0, sum/k);
    }

    private boolean canPartition(int[] nums, int[] visited, int start_idx, int k, 
                                int cur_sum, int cur_num, int target) {
        if (k == 1)return true;
        if (cur_sum == target && cur_num > 0) {
            return canPartition(nums, visited, 0, k - 1, 0, 0, target);
        }
        for (int i = start_idx; i < nums.length; i++) {
            if(visited[i] == 0) {
                visited[i] = 1;
                if (canPartition(nums, visited, i + 1, k, cur_sum + nums[i], cur_num+1, target)) {
                    return true;
                }
                visited[i] = 0;
            }
        }
        return false;
    }
}

// DP 
// TODO Understand
class Solution1 {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for(int num:nums)sum += num;
        if(k <= 0 || sum % k != 0)return false;
        int n = nums.length;
        int[][] dp = new int[sum / k + 1][n + 1]; // sum, num

        for (int i = 1; i <= n; i++) {
            int wi=nums[i-1];
            for(int w = 1;w <= sum/k; w++){
                dp[w][i] = dp[w][i - 1];    
                if(w >= wi) {   // if w>wi then it use previous value
                    dp[w][i] = Math.max(dp[w - wi][i - 1] + wi, dp[w][i - 1]);
                }
            }
        }
        return dp[sum / k][n]==sum/k;
    }
}