package solutions.LetterCombinationsofaPhoneNumber;

import java.util.*;

/**
 * Created by dyj on 7/29/18.
 */
class Solution {
    public List<String> letterCombinations(String digits) {
        Deque<String> res = new ArrayDeque<String>();
        if (digits.isEmpty()) return new ArrayList<String>(res);
        String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        res.add("");

        for (int i = 0; i < digits.length(); i++) {
            int k = Character.getNumericValue(digits.charAt(i));
            while (res.peekFirst().length() == i) {
                String t = res.pollFirst();
                for (char c : mapping[k].toCharArray()) {
                    res.addLast(t + c);
                }
            }
        }
        return new ArrayList<String>(res);
    }

    // recursive
    private static final String[] KEYS = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

    public List<String> letterCombinations1(String digits) {
        List<String> ret = new LinkedList<String>();
        combination("", digits, 0, ret);
        return ret;
    }

    private void combination(String prefix, String digits, int idx, List<String> ret) {
        if (idx >= digits.length()) {
            ret.add(prefix);
            return;
        }
        String letters = KEYS[Character.getNumericValue(digits.charAt(idx))];
        for (char c : letters.toCharArray()) {
            combination(prefix + c, digits, idx + 1, ret);
        }
    }


}
