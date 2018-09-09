

package solutions.TernaryExpressionParser;

import java.util.Deque;

/*
Example 1:

Input: "T?2:3"

Output: "2"

Explanation: If true, then result is 2; otherwise result is 3.
Example 2:

Input: "F?1:T?4:5"

Output: "4"

Explanation: The conditional expressions group right-to-left. Using parenthesis, it is read/evaluated as:

             "(F ? 1 : (T ? 4 : 5))"                   "(F ? 1 : (T ? 4 : 5))"
          -> "(F ? 1 : 4)"                 or       -> "(T ? 4 : 5)"
          -> "4"                                    -> "4"
Example 3:

Input: "T?T?F:5:3"

Output: "F"

Explanation: The conditional expressions group right-to-left. Using parenthesis, it is read/evaluated as:

             "(T ? (T ? F : 5) : 3)"                   "(T ? (T ? F : 5) : 3)"
          -> "(T ? F : 3)"                 or       -> "(T ? F : 5)"
          -> "F"                                    -> "F"
*/

class Solution {
    public String parseTernary(String expr) {
        if (expr == null || expr.length() == 0) return "";
        
        Deque<Character> stack = new LinkedList<>();

        for (int i = expr.length() - 1; i >= 0; i--) {
            char c = expr.charAt(i);
            if (!stack.isEmpty() && stack.peek() == '?') {
                stack.pop();
                char first = stack.pop();
                stack.pop();
                char sec = stack.pop();
                stack.push((c == 'T' ? first : sec));
            } else {
                stack.push(c);
            }
        }
        
        return String.valueOf(stack.pop());
    }
    
    public String parseTernary1(String expr) {
        if (expr == null || expr.length() == 0) return "";
        if (expr.indexOf('?') == -1) return expr;
        int i = 0, count = 0;
        for (; i < expr.length(); i++) {
            char c = expr.charAt(i);
            if (c == '?') {
                count++;
            } else if (c == ':') {
                count--;
                if (count == 0) break;
            }
        }
        return expr.charAt(0) == 'T' ? parseTernary(expr.substring(2, i)) : parseTernary(expr.substring(i + 1));
    }
    
}
