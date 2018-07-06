package solutions.WordSquares425;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by dyj on 7/4/18.
 */
public class Solution {

    public List<List<String>> wordSquares(String[] words) {
        if (words == null || words.length == 0) {
            return Collections.EMPTY_LIST;
        }
        Trie trie = new Trie(words);
        List<String> ansBuilder = new ArrayList<>();
        int len = words[0].length();
        List<List<String>> res = new ArrayList<>();

        for (String word : words) {
            ansBuilder.add(word);
            search(len, words, trie, ansBuilder, res);
            ansBuilder.remove(ansBuilder.size() - 1);
        }

        return res;
    }

    private void search(int len, String[] words, Trie trie, List<String> ansBuilder, List<List<String>> res) {
        if (ansBuilder.size() == len) {
            res.add(new ArrayList<>(ansBuilder));
            return;
        }

        int idx = ansBuilder.size();
        StringBuilder prefixStringBuilder = new StringBuilder();
        for (String s : ansBuilder) {
            prefixStringBuilder.append(s.charAt(idx));
        }
        List<String> startWith = trie.findByPrefix(prefixStringBuilder.toString());
        for (String s : startWith) {
            ansBuilder.add(s);
            search(len, words, trie, ansBuilder, res);
            ansBuilder.remove(ansBuilder.size() - 1);
        }
    }


    class TrieNode {
        List<String> startWith;
        TrieNode[] children;

        public TrieNode() {
            startWith = new ArrayList<>();
            children = new TrieNode[26];
        }
    }

    class Trie {
        TrieNode root;

        Trie(String[] words) {
            root = new TrieNode();
            for (String word : words) {
                char[] chs = word.toCharArray();
                TrieNode node = root;
                for (int i = 0; i < chs.length; i++) {
                    char c = chs[i];
                    if (node.children[c - 'a'] == null) {
                        node.children[c - 'a'] = new TrieNode();
                    }
                    node.children[c - 'a'].startWith.add(word);
                    node = node.children[c - 'a'];
                }
            }
        }

        List<String> findByPrefix(String prefix) {
            List<String> ret = new ArrayList<>();
            char[] prefixchs = prefix.toCharArray();
            TrieNode node = root;
            for (int i = 0; i < prefixchs.length; i++) {
                char c = prefixchs[i];
                if (node.children[c - 'a'] == null) {
                    return Collections.EMPTY_LIST;
                }
                node = node.children[c - 'a'];
            }
            return new ArrayList<>(node.startWith);
        }
    }
}
