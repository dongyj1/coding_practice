package solutions.ShortestSubarraywithSumatLeastK;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by dyj on 8/7/18.
 *
 Return the length of the shortest, non-empty, contiguous subarray of A with sum at least K.

 If there is no non-empty subarray with sum at least K, return -1.



 Example 1:

 Input: A = [1], K = 1
 Output: 1
 Example 2:

 Input: A = [1,2], K = 4
 Output: -1
 Example 3:

 Input: A = [2,-1,2], K = 3
 Output: 3


 Note:

 1 <= A.length <= 50000
 -10 ^ 5 <= A[i] <= 10 ^ 5
 1 <= K <= 10 ^ 9
 */
public class Solution {
    public int shortestSubarray(int[] A, int K) {
        if (A == null || A.length == 0) return -1;
        int[] preSum = new int[A.length + 1];
        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = preSum[i - 1] + A[i - 1];
        }
        Deque<Integer> dq = new ArrayDeque<>();
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < preSum.length; i++) {

            while (!dq.isEmpty() && preSum[i] - preSum[dq.getFirst()] >= K) {
                res = Math.min(res, i - dq.pollFirst());
            }

            while (!dq.isEmpty() && preSum[dq.getLast()] >= preSum[i]) {
                dq.pollLast();
            }
            dq.addLast(i);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{84,-37,32,40,95};
        int K = 167;
        System.out.println(new Solution().shortestSubarray(nums, K));
    }
}
