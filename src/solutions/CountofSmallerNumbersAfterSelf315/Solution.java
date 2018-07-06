package solutions.CountofSmallerNumbersAfterSelf315;

import java.util.*;

/**
 * Created by dyj on 6/5/18.
 */
public class Solution {

    private static int lowbit(int x) { return x & (-x); }

    class FenwickTree {
        private int[] sums;

        public FenwickTree(int n) {
            sums = new int[n + 1]; // size!
        }
        // O(logn)
        public void update(int i, int delta) {
            while (i < sums.length) {
                sums[i] += delta;
                i += lowbit(i);
            }
        }
        // O(logn)
        public int query(int i) {
            int sum = 0;
            while (i > 0) {
                sum += sums[i];
                i -= lowbit(i);
            }
            return sum;
        }
    }

    public List<Integer> countSmaller(int[] nums) {
        int[] sorted = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sorted);
        Map<Integer, Integer> ranks = new HashMap<>();
        int rank = 0;
        for (int i = 0; i < sorted.length; i++) {
            if (i == 0 || sorted[i] != sorted[i - 1]) {
                ranks.put(sorted[i], ++rank);
            }
        }

        FenwickTree fenwickTree = new FenwickTree(ranks.size());
        List<Integer> res = new ArrayList<>();

        for (int i = nums.length - 1; i >= 0; i--) {
            int sum = fenwickTree.query(ranks.get(nums[i]) - 1);
            res.add(sum);
            fenwickTree.update(ranks.get(nums[i]), 1);
        }

        Collections.reverse(res);

        return res;
    }
}
