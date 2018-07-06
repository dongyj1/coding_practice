package solutions.templates;

import java.util.Random;

/**
 * Created by dyj on 6/10/18.
 */
public class QuickSelect {
    private int[] arr;

    public QuickSelect(int[] arr) {
        this.arr = arr;
    }

    /**
     * find the kth greatest value
     *
     * @param k
     * @return kth greatest value
     */
    public int select(int k) {
        return select(k - 1, 0, arr.length - 1);
    }

    private int select(int k, int left, int right) {
        if (left <= right) {
            int pivot = partition(left, right);
            if (pivot == k) {
                return arr[pivot];
            }
            if (pivot > k) {
                select(k, left, pivot - 1);
            } else {
                select(k, pivot + 1, right);
            }
        }
        return Integer.MIN_VALUE;
    }

    /**
     * partition the array to two parts,
     * left is lower than pivot and right is larger than pivot,
     * return the positoin of pivot
     *
     * @param left
     * @param right
     * @return position of pivot
     */
    private int partition(int left, int right) {
        // pivot index
        int pivot = left + new Random().nextInt(right - left + 1);
        // put pivot to the right
        swap(arr, pivot, right);
        for (int i = left; i < right; i++) {
            if (arr[i] > arr[right]) {
                swap(arr, i, left);
                left++;
            }
        }
        // put pivot back to position
        swap(arr, left, right);
        return left;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] test = {3,2,4,5,6,3,2,9,8,6,4};
        QuickSelect quickSelect = new QuickSelect(test);
        System.out.println(quickSelect.select(2));
    }
}

