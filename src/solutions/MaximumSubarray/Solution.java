package solutions.MaximumSubarray;

/**
 * Created by dyj on 7/22/18.
 *
 Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

 Example:

 Input: [-2,1,-3,4,-1,2,1,-5,4],
 Output: 6
 Explanation: [4,-1,2,1] has the largest sum = 6.
 Follow up:

 If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.


 */
public class Solution {
    // DP, time O(n) space O(n)
    public int maxSubArray(int[] nums) {
        int[] preSum = new int[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
        // get the largest sum diff
        int lowest = preSum[0], res = preSum[1];
        for (int i = 1; i < preSum.length; i++) {
            res = Math.max(res, preSum[i] - lowest);
            lowest = Math.min(lowest, preSum[i]);
        }
        return res;
    }

    // Divide & Conquer time O(nlogn) space O(1)
    public int maxSubArray1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        return helper(nums, 0, nums.length - 1);
    }

    private int helper(int[] nums, int start, int end) {
        if (start > end) return Integer.MIN_VALUE;
        if (start == end) return nums[start];
        int mid = start + (end - start) / 2;
        int L = helper(nums, start, mid - 1);
        int R = helper(nums, mid + 1, end);

        // merge , must contains at least one element in case of all negative number
        int leftSum = 0, temp = 0;
        for (int i = mid - 1; i >= start; i--) {
            temp += nums[i];
            leftSum = Math.max(leftSum, temp);
        }
        int rightSum = 0;
        temp = 0;
        for (int i = mid + 1; i <= end; i++) {
            temp += nums[i];
            rightSum = Math.max(rightSum, temp);
        }
        return Math.max(leftSum + rightSum + nums[mid], Math.max(L, R));
    }

    // DP O(n) space O(1)
    public int maxSubArray2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < nums.length; i++){
            dp[i] = nums[i] + (dp[i - 1] > 0 ? dp[i - 1] : 0);
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
