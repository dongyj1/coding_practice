package solutions.SmallestRange;

import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by dyj on 8/6/18.
 *
 You have k lists of sorted integers in ascending order. Find the smallest range that includes at least one number from each of the k lists.

 We define the range [a,b] is smaller than range [c,d] if b-a < d-c or a < c if b-a == d-c.

 Example 1:
 Input:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
 Output: [20,24]
 Explanation:
 List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
 List 2: [0, 9, 12, 20], 20 is in range [20,24].
 List 3: [5, 18, 22, 30], 22 is in range [20,24].
 Note:
 The given list may contain duplicates, so ascending order means >= here.
 1 <= k <= 3500
 -105 <= value of elements <= 105.
 For Java users, please note that the input type has been changed to List<List<Integer>>. And after you reset the code template, you'll see this point.
 */

public class Solution {
    public int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<Element> pq = new PriorityQueue<>(nums.size(), (a, b) -> (a.num - b.num));
        int range, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;

        for (int row = 0; row < nums.size(); row++) {
            int num = nums.get(row).get(0);
            Element e = new Element(row, 0, num);
            max = Math.max(max, e.num);
            min = Math.min(min, e.num);
            pq.offer(e);
        }
        range = max - min;
        System.out.println(max + " " + min);

        int start = min, end = min;
        while (pq.size() == nums.size()) {
            Element cur = pq.poll();
            if (range > max - cur.num) { // in the range
                range = max - cur.num; // update range
                start = cur.num;
                end = max;
            }
            if (cur.idx < nums.get(cur.row).size()) {
                cur.idx++;
                cur.num = nums.get(cur.row).get(cur.idx);
                if (cur.num > max) {
                    max = cur.num;
                }
                pq.offer(cur);
            }
        }
        return new int[]{start, end};
    }

    class Element {
        int row;
        int idx;
        int num;
        Element(int row, int idx, int num) {
            this.row = row;
            this.idx = idx;
            this.num = num;
        }
    }
}
