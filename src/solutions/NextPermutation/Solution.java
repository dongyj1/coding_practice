package solutions.NextPermutation;

/**
 * Created by dyj on 7/29/18.
 * <p>
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 * <p>
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending
 * order).
 * <p>
 * The replacement must be in-place and use only constant extra memory.
 * <p>
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand
 * column.
 * <p>
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 * <p>
 * 1,2,3,4,5 -> 1,2,3,5,4 -> 1,2,4,3,5 -> 1,2,4,5,3 -> 1,2,5,3,4
 */
public class Solution {
    /**
     * 1. Start from its last element, traverse backward to find the first one with index i that satisfy num[i-1] <
     * num[i].
     * So, elements from num[i] to num[n-1] is reversely sorted.
     * <p>
     * 2. To find the next permutation, we have to swap some numbers at different positions, to minimize the
     * increased amount,
     * we have to make the highest changed position as high as possible. Notice that index larger than or equal to i
     * is not possible
     * as num[i,n-1] is reversely sorted. So, we want to increase the number at index i-1, clearly, swap it with the
     * smallest number
     * between num[i,n-1] that is larger than num[i-1]. For example, original number is 121543321, we want to swap
     * the '1' at position
     * 2 with '2' at position 7.
     * <p>
     * 3.The last step is to make the remaining higher position part as small as possible, we just have to reversely
     * sort the num[i,n-1]
     *
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        if (nums.length == 0) return;
        int n = nums.length;
        if (n < 2) {
            return;
        }
        int i = nums.length - 1;
        for (; i > 0; i--) {
            if (nums[i] > nums[i - 1]) { // find the first number with ascending
                break;
            }
        }

        if (i != 0) {
            swapHelper(nums, i - 1);
        }
        reverseHelper(nums, i);
    }

    // reverse nums from i to the end
    private void reverseHelper(int[] nums, int i) {
        int left = i, right = nums.length - 1;
        while (left < right) {
            swapHelper(nums, left++, right--);
        }
    }

    // find the first element larger than i and swap value
    private void swapHelper(int[] nums, int i) {
        for (int j = nums.length - 1; j > i; j--) {
            if (nums[j] > nums[i]) {
                swapHelper(nums, j, i);
                return;
            }
        }
    }

    private void swapHelper(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
