package solutions.RemoveKDigits402;

import java.util.Stack;

/**
 * Created by dyj on 5/30/18.
 * Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.
 *
 Input: num = "1432219", k = 3
 Output: "1219"
 Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.

 Input: num = "10200", k = 1
 Output: "200"
 Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
 */

public class Solution {
    public String removeKdigits(String num, int k) {
        if (k >= num.length()) {
            return "0";
        }

        char[] stk = new char[num.length()];
        int top = 0;
        int digits = num.length() - k;

        for (int i = 0; i < num.length(); i++) {

            while (top > 0 && stk[top - 1] > num.charAt(i) && k > 0) {
                k--;
                top--;
            }

            stk[top++] = num.charAt(i);
        }

        int index = 0;
        while (index < digits && stk[index] == '0') {
            index++;
        }

        return (index == digits) ? "0" : new String(stk, index, digits - index);
    }

    public static void main(String[] args) {
        String num = "1432219";
        int k = 2;
        System.out.println(new Solution().removeKdigits(num, k));
    }
}
