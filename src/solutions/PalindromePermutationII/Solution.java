package solutions.PalindromePermutationII;


/*

Input: "aabb"
Output: ["abba", "baab"]

*/

class Solution {
    public List<String> generatePalindromes(String s) {
        List<String> res = new ArrayList<>();

        HashMap<Character, Integer> even = new HashMap<>();
        HashMap<Character, Integer> odd = new HashMap<>();
        int[] chs = new int[256];
        for (char c : s.toCharArray()) {
            chs[c]++;
        }
        for (int i = 0; i < 256; i++) {
            char c = (char)(i);
            if (chs[i] % 2 == 1) {
                odd.put(c, 1);
                chs[i]--;
            }
            if (chs[i] > 0 && chs[i] % 2 == 0 ) {
                even.put(c, chs[i]);
            }
        }

        if (odd.size() > 1) return res;
        String ss = (odd.size() == 1) ? odd.keySet().iterator().next().toString() : "";

        helper(ss, res, even, s.length());

        return res;
    }

    private void helper(String s, List<String> res, HashMap<Character, Integer> even, int n) {
        if (s.length() >= n) {
            res.add(s);
        }
        for (Map.Entry<Character, Integer> entry : even.entrySet()) {
            if (entry.getValue() > 0) {
                entry.setValue(entry.getValue() - 2);
                helper(entry.getKey() + s + entry.getKey(), res, even, n);
                entry.setValue(entry.getValue() + 2);
            }
        }
    }
}