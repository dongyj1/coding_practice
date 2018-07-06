package solutions.backpackProblems;

/**
 * Created by dyj on 6/9/18.
 *
 * 有N件物品和一个容量为V的背包。第i件物品的费用是c[i]，价值是w[i]。求解将哪些物品装入背包可使价值总和最大
 */
public class ZeroOnePack {

    public int maxValue(int[] weights, int[] costs) {
        int N = weights.length;
//        int[] dp = new int[N]; // first i item max value
        int maxWeight = 0;
        for (int weight : weights) {
            maxWeight += weight;
        }
        int[] dp = new int[maxWeight];
        for (int i = 0; i < N; i++) {
            for (int j = maxWeight; j >= costs[i]; j--) {
                dp[i] = Math.max(dp[i], dp[j - costs[i]] + weights[i]);
            }
        }

        return dp[dp.length - 1];
    }
}
