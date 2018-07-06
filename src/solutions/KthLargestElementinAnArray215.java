package solutions; /**
 * Created by dyj on 4/10/18.
 */
import java.util.Random;

/**
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 For example,
 Given [3,2,1,5,6,4] and k = 2, return 5.
 */
public class KthLargestElementinAnArray215 {

    Random rand = new Random();

    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
        }
        return quickSelect(nums, nums.length - k, 0, nums.length - 1);
    }

    public int quickSelect(int[] nums, int k, int left, int right) {
        if (left == right) {
            return nums[left];
        }
        int pivotIndex = partition(nums, left, right);
        if (pivotIndex == k) {
            return nums[pivotIndex];
        }
        if (pivotIndex < k) {
            return quickSelect(nums, k, pivotIndex + 1, right);
        } else {
            return quickSelect(nums, k, left, pivotIndex - 1);
        }


    }

    public int partition(int[] nums, int left, int right) {
        int pivotIndex = rand.nextInt(right - left + 1) + left;
        int pivotValue = nums[pivotIndex];

        swap(nums, pivotIndex, right);

        int firstIndex = left;

        for (int i = left; i < right; i++) {
            if (nums[i] < pivotValue) {
                swap(nums, firstIndex++, i);
            }
        }
        swap(nums, firstIndex, right);
        return firstIndex;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] A = {21, 3, 34, 5, 13, 8, 2, 55, 1, 19};
        KthLargestElementinAnArray215 search = new KthLargestElementinAnArray215();
        System.out.println(search.findKthLargest(A, 2));
    }
}
class A {
    public int equals(int a) {
        return a;
    }
}
