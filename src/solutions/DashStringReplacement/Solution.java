package solutions.DashStringReplacement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dyj on 8/7/18.
 *
 Given a string, replace all consecutive same character with +, and if character besides - are the same, replace
 them and dashes with +.
 Example:
 a---adbbbc --> +++++d+++c
 +
 */
public class Solution {
    // the idea is:
    public String convert(String input) {
        if (input == null || input.length() == 0) return "";
        if (input.length() == 1) return input;
        StringBuilder sb = new StringBuilder();
        char[] chs = input.toCharArray();
        char[] res1 = new char[input.length()];
        char[] res2 = new char[input.length()];

        // condition1: consecutive characters
        for (int i = 1; i < chs.length; i++) {
            if (chs[i - 1] == chs[i]) {
                res1[i] = '+';
                res1[i - 1] = '+';
            } else {
                res1[i] = chs[i];
            }
        }
        if (chs[chs.length - 1] == chs[chs.length - 2]) {
            res1[chs.length - 1] = '+';
        } else {
            res1[chs.length - 1] = chs[chs.length - 1];
        }

        if (input.length() <= 2) return new String(res1);

        // condition2: -
        int prev = -1;
        for (int i = 0; i < chs.length; i++) {
            res2[i] = chs[i];
            if (prev == -1 && i < chs.length - 1 && (chs[i + 1] == '-') && chs[i] != '-') {
                prev = i;
            }
            if (i > 0 && chs[i - 1] == '-' && chs[i - 1] == '-' && chs[i] != '-') {
                while (prev <= i) {
                    res2[prev++] = '+';
                }
                prev = -1;
            }
        }
        System.out.println(Arrays.asList(res2));

        for (int i = 0; i < chs.length; i++) {
            if (res1[i] == '+' || res2[i] == '+') {
                sb.append('+');
            } else {
                sb.append(chs[i]);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String input = "a---adbbbc";
        System.out.println(new Solution().convert(input));
    }
}
