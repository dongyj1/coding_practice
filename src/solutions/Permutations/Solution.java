package solutions.Permutations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by dyj on 8/4/18.
 */
// recursive backtracking
public class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;

        helper(nums, new ArrayList<>(), res, new HashSet<Integer>());
        return res;
    }

    private void helper(int[] nums, List<Integer> list, List<List<Integer>> res, Set<Integer> visited) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            if (!visited.contains(n)) {
                visited.add(n);
                list.add(n);
                helper(nums, list, res, visited);
                list.remove(list.size() - 1);
                visited.remove(n);
            }
        }
    }
}

// swap on all positions
class Solution1 {
    List<List<Integer>> ans = new ArrayList<List<Integer>>();
    public List<List<Integer>> permute(int[] nums) {
        helper(nums, 0);
        return ans;
    }

    private void helper(int[] nums, int start) {
        if (start == nums.length) {
            ans.add(getList(nums));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            swap(nums, start, i);
            helper(nums, start + 1);
            swap(nums, start, i);
        }
    }

    private void swap(int[] nums, int j, int i) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private List<Integer> getList(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        for (int n : nums) {
            ans.add(n);
        }
        return ans;
    }

}
