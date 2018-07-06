package solutions.NondecreasingArray665;

/**
 * Created by dyj on 4/30/18.
 *
 *
 Given an array with n integers, your task is to check if it could become non-decreasing by modifying at most 1 element.

 We define an array is non-decreasing if array[i] <= array[i + 1] holds for every i (1 <= i < n).

 Example 1:
 Input: [4,2,3]
 Output: True
 Explanation: You could modify the first 4 to 1 to get a non-decreasing array.
 */
public class Solution {

    public boolean checkPossibility(int[] nums) {
        if (nums.length == 0) {
            return true;
        }
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                if (count == 1) {
                    return false;
                }
                if ((i > 0 && nums[i - 1] > nums[i + 1])
                        && (i + 2 < nums.length && nums[i] > nums[i + 2])) {
                    return false;
                } else {
                    count++;
                }
            }
        }
        return count <= 1;
    }

    public static void main(String[] args) {
        int[] arr = {3,4,2,3};
        System.out.println(new Solution().checkPossibility(arr));
    }
}
