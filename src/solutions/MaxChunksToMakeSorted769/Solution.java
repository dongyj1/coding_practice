package solutions.MaxChunksToMakeSorted769;

/**
 * Created by dyj on 5/20/18.
 *
 *
 Given an array arr that is a permutation of [0, 1, ..., arr.length - 1], we split the array into some number of "chunks" (partitions), and individually sort each chunk.  After concatenating them, the result equals the sorted array.

 What is the most number of chunks we could have made?
 arr = [1,0,2,3,4]
 4
 */

public class Solution {
    public int maxChunksToSorted(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = 0, count = 0;

        for (int i = 0; i < arr.length; i++) {
            max = Math.max(arr[i], max);

            if (max == i) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[] arr = {1,0,2,3,4};
        System.out.println(new Solution().maxChunksToSorted(arr));
    }
}
