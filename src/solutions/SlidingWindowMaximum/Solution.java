package solutions.SlidingWindowMaximum;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by dyj on 7/29/18.
 *
 Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.

 Example:

 Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
 Output: [3,3,5,5,6,7]
 Explanation:

 Window position                Max
 ---------------               -----
 [1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
 Note:
 You may assume k is always valid, 1 ≤ k ≤ input array's size for non-empty array.

 Follow up:
 Could you solve it in linear time?
 */
public class Solution {

    // Monotonous Queue: Saving indices from large value to small
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) return new int[0];
        int n = nums.length;
        int[] res = new int[n - k + 1];
        Deque<Integer> dq = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            while (dq.size() > 0 && nums[dq.getFirst()] <= nums[i]) {
                dq.pollFirst();
            }
            dq.addLast(i);
            if (i - k + 1 >= 0) res[i - k + 1] = nums[dq.getFirst()];
            if (i - k + 1 >= dq.getLast()) { // the index is larger than the smallest index of potential usable number
                dq.pollLast();
            }
        }
        return res;
    }

    // 2 pass with buckets

    /**
     * For Example: A = [2,1,3,4,6,3,8,9,10,12,56], w=4

     partition the array in blocks of size w=4. The last block may have less then w.
     2, 1, 3, 4 | 6, 3, 8, 9 | 10, 12, 56|

     Traverse the list from start to end and calculate max_so_far. Reset max after each block boundary (of w elements).
     left_max[] = 2, 2, 3, 4 | 6, 6, 8, 9 | 10, 12, 56

     Similarly calculate max in future by traversing from end to start.
     right_max[] = 4, 4, 4, 4 | 9, 9, 9, 9 | 56, 56, 56

     now, sliding max at each position i in current window, sliding-max(i) = max{right_max(i), left_max(i+w-1)}
     sliding_max = 4, 6, 6, 8, 9, 10, 12, 56

     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow1(int[] nums, int k) {
        final int[] max_left = new int[nums.length];
        final int[] max_right = new int[nums.length];

        int n = nums.length;
        max_left[0] = nums[0];
        max_right[n - 1] = nums[n- 1];

        for (int i = 1; i < n; i++) {
            max_left[i] = (i % k == 0) ? nums[i] : Math.max(max_left[i - 1], nums[i]);

            final int j = n - i - 1;
            max_right[j] = (j % k == 0) ? nums[j] : Math.max(max_right[j + 1], nums[j]);
        }
        final int[] sliding_max = new int[n - k + 1];
        for (int i = 0, j = 0; i < n - k + 1; i++) {
            sliding_max[j++] = Math.max(max_left[i], max_right[i + k - 1]);
        }
        return sliding_max;
    }
}
