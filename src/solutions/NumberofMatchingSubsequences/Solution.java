package solutions.NumberofMatchingSubsequences;


class Solution {
    public int numMatchingSubseq(String S, String[] words) {
        Map<Character, Deque<String>> map = new HashMap<>();
        for (char c = 'a'; c <= 'z'; c++){
            map.put(c, new ArrayDeque<String>());
        }
        for (String w : words){
            map.get(w.charAt(0)).addLast(w);
        }
        int count = 0;
        for (int i = 0; i < S.length(); i++) {
            Deque<String> dq = map.get(S.charAt(i));
            int size = dq.size();
            for (int j = 0; j < size; j++) {
                String w = dq.pollFirst();
                if (w.length() == 1) count++;
                else{
                    map.get(w.charAt(1)).addLast(w.substring(1));
                }
            }
        }
        return count;
    }
}