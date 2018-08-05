package solutions.Subsets;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dyj on 8/4/18.
 *
 Given a set of distinct integers, nums, return all possible subsets (the power set).

 Note: The solution set must not contain duplicate subsets.

 Example:

 Input: nums = [1,2,3]
 Output:
 [
 [3],
 [1],
 [2],
 [1,2,3],
 [1,3],
 [2,3],
 [1,2],
 []
 ]
 */
public class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        helper(nums, res, new ArrayList<Integer>(), 0);
        return res;
    }

    private void helper(int[] nums, List<List<Integer>> res, ArrayList<Integer> list, int i) {
        res.add(new ArrayList<>(list));

        for (; i < nums.length; i++) {
            list.add(nums[i]);
            helper(nums, res, list, i + 1);
            list.remove(list.size() - 1);
        }
    }

}
