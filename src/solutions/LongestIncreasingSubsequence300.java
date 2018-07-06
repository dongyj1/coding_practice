package solutions;

/**
 * Created by dyj on 4/11/18.
 *
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 */
public class LongestIncreasingSubsequence300 {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] tail = new int[nums.length];
        int size = 0;
        for (int num : nums) {
            int i = 0, j = size;
            while (i < j) {
                int mid = (i + j) >> 1;
                if (tail[mid] < num) {
                    i = mid + 1;
                } else {
                    j = mid;
                }
            }
            tail[i] = num;
            if (i == size) {
                size++;
            }
        }
        return size;
    }
}

// class Solution {
//     public int lengthOfLIS(int[] nums) {
//         // tails is an array storing the smallest tail of all increasing subsequences with length i+1 in tails[i]
//         // tails is monotone.(can use binary search)
//         int[] tails = new int[nums.length];
//         int size = 0;
//         for (int num : nums) {
//             int i = 0, j = size;
//             while (i < j) {
//                 int mid = (i + j) / 2;
//                 if (tails[mid] < num) {
//                     i = mid + 1; // if i = mid can cause infinite loop
//                 } else {
//                     j = mid;
//                 }
//             }
//             tails[i] = num;
//             if (i == size) ++size; // has reached the longest size
//         }
//         return size;
//     }
// }