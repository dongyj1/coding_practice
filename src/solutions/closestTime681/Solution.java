package solutions.closestTime681;

import java.util.HashMap;

/**
 * Created by dyj on 4/20/18.
 */
public class Solution {
    public String nextClosestTime(String time) {
//        HashMap<Integer, Integer> map = new HashMap<>();
//        for (int i = 0; i < time.length(); i++) {
//            if (i == 2) continue;
//            int key = Integer.parseInt(String.valueOf(time.charAt(i)))
//            map.put(key, map.getOrDefault(key, 0) + 1);
//        }
        int hour = Integer.parseInt(time.substring(0, 2));
        int min = Integer.parseInt(time.substring(3, 5));
        while (true) {
            if (++min == 60) {
                min = 0;
                ++hour;
                hour %= 24;
            }
            String curr = String.format("%02d:%02d", hour, min);
            Boolean valid = true;
            for (int i = 0; i < curr.length(); ++i)
                if (time.indexOf(curr.charAt(i)) < 0) {
                    valid = false;
                    break;
                }
            if (valid) return curr;
        }
    }

    public static void main(String[] args) {
        String time1 = "14:34";
        Solution solution = new Solution();
        System.out.println(solution.nextClosestTime(time1));
    }
}
