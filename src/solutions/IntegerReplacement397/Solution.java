package solutions.IntegerReplacement397;

/**
 * Created by dyj on 5/6/18.
 */
public class Solution {
    public int integerReplacement(int n) {
        long N = n;
        int res = 0;
        while (N > 1) {
            if ((N & 1) == 0) {
                N >>= 1;
            } else if (N == 3) {
                res += 2;
                break;
            } else if ((N & 3) == 3) {
                N += 1;
            } else {
                N -= 1;
            }
            res += 1;
        }
        return res;
    }

//    public int integerReplacement1(int n) {
//        if (n == Integer.MAX_VALUE) return 32;
//
//    }
}
