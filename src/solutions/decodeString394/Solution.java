package solutions.decodeString394;

import java.util.Stack;

/**
 * Created by dyj on 4/22/18.
 *
 * Given an encoded string, return it's decoded string.

 The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

 You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

 Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].
 s = "3[a]2[bc]", return "aaabcbc".
 s = "3[a2[c]]", return "accaccacc".
 s = "2[abc]3[cd]ef", return "abcabccdcdcdef".


 */
public class Solution {

    public String decodeString(String s) {
        if (s.length() == 0) {
            return "";
        }
        Stack<String> stack = new Stack<>();
        Stack<Integer> count = new Stack<>();
        StringBuilder res = new StringBuilder();
        int index = 0;
        while (index < s.length()) {
            char c = s.charAt(index);
            if (Character.isDigit(c)) {
                int num = 0;
                while (Character.isDigit(s.charAt(index))) {
                    num = num * 10 + (s.charAt(index) - '0');
                    index++;
                }
                count.push(num);
            } else if (c == '[') {
                index++;
                stack.push(res.toString());
                res = new StringBuilder();
            } else if (c == ']') {
                StringBuilder temp = new StringBuilder(stack.pop());
                int num = count.pop();
                while (num-- > 0) {
                    temp.append(res);
                }
                res = temp;
                index++;
            } else {
                res.append(s.charAt(index++));
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        String s = "3[a2[c]]";
        System.out.println(new Solution().decodeString(s));
    }
}
