package solutions.subsetsII90;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dyj on 4/24/18.
 */
public class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        helper(nums, 0, new ArrayList<Integer>(), res);
        return res;
    }

    private void helper(int[] nums, int i, ArrayList<Integer> list, List<List<Integer>> res) {
        res.add(new ArrayList<>(list));

        for (int k = i; k < nums.length; k++) {
            if (k > i && nums[k] == nums[k - 1]) {
                continue;
            }
            list.add(nums[k]);
            helper(nums, k + 1, list, res);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,2};
        List<List<Integer>> res = new Solution().subsetsWithDup(nums);
        for(List<Integer> l : res) {
            System.out.println(l);
        }
    }
}
