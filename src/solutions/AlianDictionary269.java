package solutions;

/**
 * Created by dyj on 4/11/18.
 */

import java.util.*;

/**
 * There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of words from the dictionary, wherewords are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.
 */
public class AlianDictionary269 {

    public static String alienOrder(List<String> words) {
        if (words == null || words.size() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Map<Character, Set<Character>> graph = new HashMap<>();
        Set<Character> set = new HashSet<>();
        int[] inDegree = new int[26];
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                set.add(word.charAt(i));
            }
        }
        for (int i = 1; i < words.size(); i++) {
            String preStr = words.get(i - 1);
            String curStr = words.get(i);
            for (int j = 0; j < Math.min(preStr.length(), curStr.length()); j++) {
                char cur = curStr.charAt(j);
                char pre = preStr.charAt(j);
                if (pre == cur) {
                    continue;
                }
                if (!graph.containsKey(pre)) {
                    graph.put(pre, new HashSet<>());
                }
                if (!graph.get(pre).contains(cur)) {
                    inDegree[cur - 'a']++;
                    graph.get(pre).add(cur);
                }
                break;
            }
        }

        HashSet<Character> visited = new HashSet<>();
        Queue<Character> queue = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0 && set.contains((char)('a' + i))) {
                queue.add((char)('a' + i));
            }
        }

        while (!queue.isEmpty()) {
            char c = queue.poll();
            if (visited.contains(c)) {
                continue;
            }
            sb.append(c);
            if (graph.containsKey(c)) {
                for (Character next : graph.get(c)) {
                    inDegree[next - 'a']--;
                    if (inDegree[next - 'a'] == 0) {
                        queue.offer(next);
                    }
                }
            }
        }
        return sb.length() == set.size() ? sb.toString() : "";
    }

    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        words.add("wrt");
        words.add("wrf");
        words.add("er");
        words.add("ett");
        words.add("rftt");
        AlianDictionary269 solution = new AlianDictionary269();
        System.out.println(solution.alienOrder(words));
    }
}

