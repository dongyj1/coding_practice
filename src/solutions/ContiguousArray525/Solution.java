package solutions.ContiguousArray525;

import java.util.HashMap;

/**
 * Created by dyj on 5/1/18.
 */
public class Solution {

    public int findMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                nums[i] = -1;
            }
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0, max = 0;
        map.put(0, -1);

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            if (map.containsKey(sum)) {
                int pre = map.get(sum);
                max = Math.max(max, i - pre);
            } else {
                map.put(sum, i);
            }

        }
        return max;
    }
}
