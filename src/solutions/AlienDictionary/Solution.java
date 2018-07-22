package solutions.AlienDictionary;

import sun.awt.image.ImageWatched;

import java.util.*;

/**
 * Created by dyj on 7/22/18.
 *
 There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

 Example 1:

 Input:
 [
 "wrt",
 "wrf",
 "er",
 "ett",
 "rftt"
 ]

 Output: "wertf"
 Example 2:

 Input:
 [
 "z",
 "x"
 ]

 Output: "zx"
 Example 3:

 Input:
 [
 "z",
 "x",
 "z"
 ]

 Output: ""

 Explanation: The order is invalid, so return "".
 Note:

 You may assume all letters are in lowercase.
 You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
 If the order is invalid, return an empty string.
 There may be multiple valid order of letters, return any one of them is fine.
 */
public class Solution {
    // Adjacent list and topological sort
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        Map<Character, Set<Character>> map = new HashMap<>();
        Map<Character, Integer> indegree = new HashMap<>();
        // record all characters
        for (String w : words) {
            for (char c : w.toCharArray()) {
                indegree.put(c, 0);
            }
        }

        // save next to neighbors map
        for (int i = 0; i < words.length - 1; i++) {
            String cur = words[i], next = words[i + 1];
            for (int j = 0; j < Math.min(cur.length(), next.length()); j++) {
                char c1 = cur.charAt(j), c2 = next.charAt(j);
                if (c1 != c2) {
                    Set<Character> set = map.getOrDefault(c1, new HashSet<>());
                    if (!set.contains(c2)) {
                        set.add(c2);
                        // update the map
                        map.put(c1, set);
                        // update indegree
                        indegree.put(c2, indegree.get(c2) + 1);
                    }
                    break;
                }
            }
        }

        LinkedList<Character> q = new LinkedList<>();
        for (char c : indegree.keySet()){
            // add start nodes to queue
            if (indegree.get(c) == 0) {
                q.add(c);
            }
        }
        String result = "";
        while (!q.isEmpty()) {
            char c = q.poll();
            result += c;
            if (map.containsKey(c)) {
                for (char cc : map.get(c)) {
                    indegree.put(cc, indegree.get(cc) - 1);
                    // add node with no indegree for iteration
                    if (indegree.get(cc) == 0) q.add(cc);
                }
            }
        }
        // if there's circle existing, not all characters can be added to result.
        if (result.length()  != indegree.size()) return "";
        return result;
    }

    // Adjacent matrix and DFS
    /*
    A topological ordering is possible if and only if the graph has no directed cycles
    
    Let's build a graph and perform a DFS. The following states made things easier.

        visited[i] = -1. Not even exist.
        visited[i] = 0. Exist. Non-visited.
        visited[i] = 1. Visiting.
        visited[i] = 2. Visited.
    * */
    public String alienOrder1(String[] words) {
        int N = 26;
        boolean[][] adj = new boolean[N][N];
        int visited[] = new int[N];
        buildGraph(words, adj, visited);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            if (visited[i] == 0) {
                if (!dfs(sb, visited, adj, i)) {
                    return "";
                }
            }
        }
        return sb.reverse().toString();
    }

    private void buildGraph(String[] words, boolean[][] adj, int[] visited) {
        // init all letters unexisting
        Arrays.fill(visited, -1);

        for (int i = 0; i < words.length; i++) {
            for (char c : words[i].toCharArray()){
                visited[c - 'a'] = 0; // mark existing letter as 0
            }
            if (i > 0) {
                String prev = words[i - 1];
                String cur = words[i];
                int len = Math.min(prev.length(), cur.length());
                for (int j = 0; j < len; j++) {
                    char c1 = prev.charAt(j), c2 = cur.charAt(j);
                    if (c1 != c2){
                        adj[c1 - 'a'][c2 - 'a'] = true;
                        break;
                    }
                }
            }
        }
    }
    // check if there's no circle existing
    private boolean dfs(StringBuilder sb, int[] visited, boolean[][] adj, int idx){
        visited[idx] = 1; // visiting
        for (int i = 0; i < 26; i++) {
            if (adj[idx][i]) {
                if (visited[i] == 1) return false; // visiting in current cycle
                if (visited[i] == 0) { // unvisited
                    if (!dfs(sb, visited, adj, i)) {
                        return false;
                    }
                }
            }
        }
        visited[idx] = 2; // visited and has been added
        sb.append((char)('a' + idx));
        return true;
    }
}
