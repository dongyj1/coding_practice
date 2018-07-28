package solutions.WordLadderII;

import com.sun.tools.corba.se.idl.StringGen;

import java.util.*;

/**
 * Created by dyj on 7/27/18.
 */
public class Solution1 {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return res;
        Set<String> begin = new HashSet<>();
        Set<String> end = new HashSet<>();

        begin.add(beginWord);
        end.add(endWord);

        Map<String, List<String>> map = new HashMap<>();
        bfs(dict, begin, end, map);

        List<String> path = new ArrayList<>();
        path.add(beginWord);
        dfs(res, path, beginWord, endWord, map);

        return res;
    }

    private void dfs(List<List<String>> res, List<String> path, String beginWord, String endWord, Map<String, List<String>> map) {
        if (beginWord.compareTo(endWord) == 0) {
            res.add(new ArrayList<String>(path));
            return;
        }
        if (!map.containsKey(beginWord)) return;

        List<String> next = map.get(beginWord);
        for (String s : next) {
            path.add(s);
            dfs(res, path, s, endWord, map);
            path.remove(path.size() - 1); // back tracking
        }
    }

    private void bfs(Set<String> dict, Set<String> begin, Set<String> end, Map<String,List<String>> map) {
        boolean reversed = false;
        boolean reached = false;
        while (!begin.isEmpty()) {
            dict.removeAll(begin);
            dict.removeAll(end);
            if (begin.size() > end.size()) {
                reversed = !reversed;
                Set<String> temp = begin;
                begin = end;
                end = temp;
            }
            Set<String> next = new HashSet<>();
            for (String s : begin) {
                for (int i = 0; i < s.length(); i++) {
                    char[] ch = s.toCharArray();
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (ch[i] == c) {
                            continue;
                        }
                        ch[i] = c;
                        String word = new String(ch);
                        if (end.contains(word) || dict.contains(word)) {
                            if (end.contains(word)) {
                                reached = true;
                            } else {
                                next.add(word);
                            }
                            if (!reversed) {
                                map.putIfAbsent(s, new ArrayList<>());
                                map.get(s).add(word);
                            } else {
                                map.putIfAbsent(word, new ArrayList<>());
                                map.get(word).add(s);
                            }
                        }
                    }
                }
            }
            begin = next;
            if (reached) {
                return;
            }
        }
    }
}
