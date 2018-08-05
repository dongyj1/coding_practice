package solutions.PermutationsII;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dyj on 8/4/18.
 *
 Given a collection of numbers that might contain duplicates, return all possible unique permutations.

 Example:

 Input: [1,1,2]
 Output:
 [
 [1,1,2],
 [1,2,1],
 [2,1,1]
 ]
 */
public class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0) return res;
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);
        dfs(nums, new ArrayList<Integer>(), used, res);
        return res;
    }

    private void dfs(int[] nums, ArrayList<Integer> list, boolean[] used, List<List<Integer>> res) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1] || used[i]) continue;
            used[i] = true;
            list.add(nums[i]);
            dfs(nums, list, used, res);
            list.remove(list.size() - 1);
            used[i] = false;
        }
    }
}
