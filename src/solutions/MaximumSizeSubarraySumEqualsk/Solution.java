package solutions.MaximumSizeSubarraySumEqualsk;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dyj on 7/17/18.
 */
class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] sums = new int[nums.length + 1];

        for (int i = 1; i <= nums.length; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }
        // System.out.println(Arrays.toString(sums));
        Map<Integer, Integer> map = new HashMap<>();
        int maxLen = 0;

        for (int i = 0; i < sums.length; i++) {
            //
            int sum0 = sums[i] - k;

            if (map.containsKey(sum0)) {
                // System.out.println(map.get(sum0));
                maxLen = Math.max(maxLen, i - map.get(sum0));
            }
            // record the first value
            if (!map.containsKey(sums[i])) {
                map.put(sums[i], i);
            }

        }

        return maxLen;
    }
}
