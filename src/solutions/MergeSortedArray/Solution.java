package solutions.MergeSortedArray;

/**
 * Created by dyj on 8/6/18.
 *
 Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

 Note:

 The number of elements initialized in nums1 and nums2 are m and n respectively.
 You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
 Example:

 Input:
 nums1 = [1,2,3,0,0,0], m = 3
 nums2 = [2,5,6],       n = 3

 Output: [1,2,2,3,5,6]
 */
public class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int idx = nums1.length - 1;
        int i = m - 1, j = n - 1;
        while (idx >= 0) {
            int n1 = (i >= 0) ? nums1[i] : Integer.MIN_VALUE;
            int n2 = (j >= 0) ? nums2[j] : Integer.MIN_VALUE;
            if (n1 < n2) {
                nums1[idx] = n2;
                j--;
            } else {
                nums1[idx] = n1;
                i--;
            }
            idx--;
        }
    }
}
