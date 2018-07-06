package solutions.ShortestSubstringContainsAllChars;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by dyj on 4/29/18.
 */


public class Solution {

    public static List<String> shortestSubstringContainsAllChars(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        List<String> res = new ArrayList<>();
        if (s.length() == 1) {
            res.add(s);
            return res;
        }
        char[] chs = s.toCharArray();
        HashSet<Character> set = new HashSet<>();
        for (char c : chs) {
            set.add(c);
        }
        int count = set.size(), minLen = s.length();
        HashMap<Character, Integer> map = new HashMap<>();
        int i = 0, j = 0;
        while (i < chs.length && j < chs.length) {
            while(map.keySet().size() < count && j < chs.length) {
                map.put(chs[j], map.getOrDefault(chs[j], 0) + 1);
                j++;
            }
            if (map.keySet().size() == count && j - i <= minLen) {
                if (j - i < minLen) {
                    res = new ArrayList<>();
                    minLen = j - i;
                }
                res.add(s.substring(i, j));
            }
            while (i < j && map.keySet().size() == count) {
                if (map.get(chs[i]) == 1) {
                    map.remove(chs[i]);
                } else {
                    map.put(chs[i], map.get(chs[i]) - 1);
                }
                i++;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        String test = "abgdbsbsbs";
        System.out.println(Solution.shortestSubstringContainsAllChars(test));
    }
}
