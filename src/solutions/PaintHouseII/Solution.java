package solutions.PaintHouseII;

/**
 * Created by dyj
 * 
 * 
 */

 // time O(MN^2) space O(1)
public class Solution {
    public int minCostII(int[][] costs) {
        if (costs.length == 0 || costs[0].length == 0) return 0;
        for (int i = 1; i < costs.length; i++) {
            for (int j = 0; j < costs[0].length; j++) {
                int temp = Integer.MAX_VALUE;
                for (int k = 0; k < costs[0].length; k++) {
                    if (k != j) {
                        temp = Math.min(temp, costs[i - 1][k]);
                    }
                }
                costs[i][j] += temp;
            }
        }
        int minCost = Integer.MAX_VALUE;
        for (int i = 0; i < costs[0].length; i++) {
            minCost = Math.min(minCost, costs[costs.length - 1][i]);
        }

        return minCost;
    }
}

/**
 *  we only need to know the min cost to the previous house of any color and if the color j is 
 *  used on previous house to get prev min cost, use the second min cost that are not using color 
 *  j on the previous house
 */
// time O(MN),  space O(1)
class Solution1 {
    public int minCostII(int[][] costs) {
        if(costs == null || costs.length == 0 || costs[0].length == 0) return 0;
        int n = costs.length, k = costs[0].length;
        if(k == 1) return (n==1? costs[0][0] : -1);

        int prevMin = 0, prevMinIdx = -1, prevSecMin = 0; // prevSecMin always >= prevMin
        for (int i = 0; i < n; i++){
            int min = Integer.MAX_VALUE, minIdx = -1, secMin = Integer.MAX_VALUE;
            for (int j = 0; j < k; j++) {
                int val = costs[i][j] + ((j == prevMinIdx) ? prevSecMin : prevMin);
                if (minIdx < 0) {
                    minIdx = j;
                    min = val;
                } else if (val < min) {
                    secMin = min;
                    min = val;
                    minIdx = j;
                } else if (val < secMin) {
                    secMin = val;
                }
            }
            prevMin = min;
            prevMinIdx = minIdx;
            prevSecMin = secMin;
        }
        return prevMin;
    }
}
