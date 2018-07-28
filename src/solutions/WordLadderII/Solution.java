package solutions.WordLadderII;

import java.util.*;

/**
 * Created by dyj on 7/26/18.
 *
 Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:

 Only one letter can be changed at a time
 Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 Note:

 Return an empty list if there is no such transformation sequence.
 All words have the same length.
 All words contain only lowercase alphabetic characters.
 You may assume no duplicates in the word list.
 You may assume beginWord and endWord are non-empty and are not the same.
 Example 1:

 Input:
 beginWord = "hit",
 endWord = "cog",
 wordList = ["hot","dot","dog","lot","log","cog"]

 Output:
 [
 ["hit","hot","dot","dog","cog"],
 ["hit","hot","lot","log","cog"]
 ]
 Example 2:

 Input:
 beginWord = "hit"
 endWord = "cog"
 wordList = ["hot","dot","dog","lot","log"]

 Output: []

 Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.

 */

public class Solution {
    // BFS1     http://zxi.mytechroad.com/blog/searching/leetcode-126-word-ladder-ii/
    // start -> end: bfs
    // end -> start: dfs
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>(); // save all parents nodes for a node
        Map<String, Integer> distance = new HashMap<>(); // save distance from beginWord
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return res;
        dict.add(beginWord);
        dict.add(endWord);

        bfs(map, distance, beginWord, endWord, dict);

        List<String> path = new ArrayList<>();

        dfs(res, path, endWord, beginWord, map, distance);

        return res;
    }

    // Search the path by the graph
    private void dfs(List<List<String>> res, List<String> path, String cur, String beginWord,
                     Map<String, List<String>> map, Map<String, Integer> distance) {
        path.add(cur);
        if (cur.equals(beginWord)) {

            Collections.reverse(path); // Note that path is from end to start
            res.add(new ArrayList<>(path));
            Collections.reverse(path); // Reverse back
        } else {
            List<String> parents = map.get(cur);
            for (String p : parents) {
                // check if the p is reachable from beginWord
                if (distance.containsKey(p) && distance.get(cur) == distance.get(p) + 1) {
                    dfs(res, path, p, beginWord, map, distance);
                }
            }
        }
        path.remove(path.size() - 1); // back tracking
    }

    // Construct the node map with neighboring list saving all its parent nodes.
    private void bfs(Map<String, List<String>> map, Map<String, Integer> distance, String beginWord, String endWord, Set<String> dict) {
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        distance.put(beginWord, 0);
        for (Iterator<String> it = dict.iterator(); it.hasNext(); ) {
            String str = it.next();
            map.put(str, new ArrayList<>());
        }

        while (!q.isEmpty()) {
            String cur = q.poll();

            List<String> nextList = expand(cur, dict);

            for (String next : nextList) {
                map.get(next).add(cur);
                if (!distance.containsKey(next)) { // a node that hasn't been visited by bfs
                    distance.put(next, distance.get(cur) + 1); // distance from beginWord = parentWord + 1
                    q.offer(next);
                }
            }
        }
    }

    /**
     * Get list of all the next words in the dictionary
     *
     * @param cur
     * @param dict dictionary
     * @return words
     */
    private List<String> expand(String cur, Set<String> dict) {
        char[] chs = cur.toCharArray();
        List<String> ret = new ArrayList<>();
        for (int i = 0; i < chs.length; i++) {
            char before = chs[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c != before) {
                    chs[i] = c;
                    String nextWord = new String(chs);
                    if (dict.contains(nextWord)) {
                        ret.add(nextWord);
                    }
                }
            }
            chs[i] = before;
        }
        return ret;
    }
}

