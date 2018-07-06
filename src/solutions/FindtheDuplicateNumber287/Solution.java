package solutions.FindtheDuplicateNumber287;

/**
 * Created by dyj on 5/1/18.
 *
 *
 Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

 Note:
 You must not modify the array (assume the array is read only).
 You must use only constant, O(1) extra space.
 Your runtime complexity should be less than O(n2).
 There is only one duplicate number in the array, but it could be repeated more than once.
 [1,2,3,1]
 */
public class Solution {
    public int findDuplicate(int[] nums) {
        int slow = nums[0], fast = nums[slow];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[slow]];
        }

        fast = 0;
        while (fast != slow) {
            fast = nums[fast];
            slow = nums[slow];
        }
        return slow;
    }

    public int findDuplicate1(int[] nums) {
        int min = 1, max = nums.length - 1;
        while (min <= max) {
            int mid = (max + min) / 2;
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= mid) {
                    count++;
                }
            }

            if (count > mid) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return min;
    }
}
