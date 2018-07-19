package solutions.SuperWashingMachines517;

/**
 * Created by dyj on 7/19/18.

 You have n super washing machines on a line. Initially, each washing machine has some dresses or is empty.

 For each move, you could choose any m (1 ≤ m ≤ n) washing machines, and pass one dress of each washing machine to one of its adjacent washing machines at the same time .

 Given an integer array representing the number of dresses in each washing machine from left to right on the line, you should find the minimum number of moves to make all the washing machines have the same number of dresses. If it is not possible to do it, return -1.

 Example1

 Input: [1,0,5]

 Output: 3

 Explanation:
 1st move:    1     0 <-- 5    =>    1     1     4
 2nd move:    1 <-- 1 <-- 4    =>    2     1     3
 3rd move:    2     1 <-- 3    =>    2     2     2
 Example2

 Input: [0,3,0]

 Output: 2

 Explanation:
 1st move:    0 <-- 3     0    =>    1     2     0
 2nd move:    1     2 --> 0    =>    1     1     1
 Example3

 Input: [0,2,0]

 Output: -1

 Explanation:
 It's impossible to make all the three washing machines have the same number of dresses.

 */
public class Solution {
    // time O(n), space O(n)
    public int findMinMoves(int[] machines) {
        if (machines.length < 2) {
            return 0;
        }
        int len = machines.length;
        int[] sum = new int[len + 1];
        for (int i = 0; i < len; i++) {
            sum[i+1] = sum[i] + machines[i];
        }
        if(sum[len] % len != 0) {
            return -1;
        }
        int avg = sum[len] / len;
        int res = 0;
        for (int i = 0; i < len; i++) {
            // num of dresses move to left
            int l = i * avg - sum[i];
            // num of dresses move to right
            int r = (len - i - 1)*avg - (sum[len] - sum[i + 1]);
            if (l > 0 && r > 0) {
                // Both sides need dresses, move one dress each time.
                res = Math.max(res, l + r);
            } else {
                // L < 0 && R < 0: both sides contains too many dresses,
                //      and we can import dresses from both sides at the same time, so result is max(abs(L), abs(R))
                // L < 0 && R > 0 or L >0 && R < 0: the side with a larger absolute value will import/export its extra dresses from/to current machine or other side,
                //      so result is max(abs(L), abs(R))
                res = Math.max(res, Math.max(Math.abs(l), Math.abs(r)));
            }
        }

        return res;
    }

    // time O(n) space O(1)
    public int findMinMoves1(int[] machines) {
        int total = 0;
        for (int i : machines) {
            total += i;
        }
        if (total % machines.length != 0) {
            return -1;
        }
        int avg = total / machines.length, count = 0, max = 0;

        for (int load : machines) {
            count += load - avg; // gain/lose
            max = Math.max(Math.max(Math.abs(count), max), load - avg);
        }
        return max;
    }
}
