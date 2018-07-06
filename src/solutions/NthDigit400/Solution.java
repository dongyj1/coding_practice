package solutions.NthDigit400;

/**
 * Created by dyj on 5/20/18.
 *
 * Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...
 */

public class Solution {
    public int findNthDigit(int n) {
        int digitType = 1;
        long digitNum = 9;

        while (n > digitType * digitNum) {
            n -= (int)digitType * digitNum;
            digitType++;
            digitNum *= 10;
        }

        int indexInSumRange = (n - 1) / digitType;
        int indexInNum = (n - 1) % digitType;

        int num = (int)Math.pow(10, digitType - 1) + indexInSumRange;
        int result = Integer.parseInt(("" + num).charAt(indexInNum) + "");

        return result;
    }
}
