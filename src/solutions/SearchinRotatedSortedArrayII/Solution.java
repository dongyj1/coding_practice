package solutions.SearchinRotatedSortedArrayII;

/**
 * Created by dyj on 8/6/18.
 *
 Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

 (i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).

 You are given a target value to search. If found in the array return true, otherwise return false.

 Example 1:

 Input: nums = [2,5,6,0,0,1,2], target = 0
 Output: true
 Example 2:

 Input: nums = [2,5,6,0,0,1,2], target = 3
 Output: false
 Follow up:

 This is a follow up problem to Search in Rotated Sorted Array, where nums may contain duplicates.
 Would this affect the run-time complexity? How and why?
 */
public class Solution {
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0)return false;
        int begin = 0, end = nums.length - 1;
        while (begin < end) {
            int mid = (end - begin) / 2 + begin;
            if (nums[mid] == target) return true;
            if (nums[mid] == nums[end] && nums[mid] == nums[begin]) {
                begin++;
                end--;
            } else if (nums[begin] <= nums[mid]) {
                if (target < nums[mid] && target >= nums[begin]) {
                    end = mid - 1;
                } else {
                    begin = mid + 1;
                }
            } else {
                if (target < nums[mid] || target >= nums[begin]) {
                    end = mid - 1;
                } else {
                    begin = mid + 1;
                }
            }
        }
        return nums[begin] == target;
    }

    public static void main(String[] args) {
        int[] input = new int[]{1,1,1,3};
        int n = 0;
        System.out.println(new Solution().search(input, n));
    }
}
