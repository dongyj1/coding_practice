package solutions.findLargestAndSecondLargest;

import java.util.Arrays;

/**
 * Created by dyj on 4/25/18.
 */
public class Solution {
    public static int[] findLargestAndSecondLargest(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int largest, secondLargest;
        largest = nums[0];
        secondLargest = largest;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > largest) {
                secondLargest = largest;
                largest = nums[i];
            } else if (nums[i] > secondLargest) {
                secondLargest = nums[i];
            }
        }

        return new int[]{largest, secondLargest};
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,2,1,5,4};
        int[] res = findLargestAndSecondLargest(arr);
        System.out.println(res[0] + " " + res[1]);
    }
}
