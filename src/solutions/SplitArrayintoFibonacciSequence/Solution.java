package solutions.SplitArrayintoFibonacciSequence;

/*

Input: "123456579"
Output: [123,456,579]



*/


class Solution {
    public List<Integer> splitIntoFibonacci(String S) {
        List<Integer> r = new ArrayList<>();
        if (S == null || S.length() == 0) return r;
        
        if (dfs(S, r, 0)) return r;
        
        return new ArrayList<Integer>();
    }

    private boolean dfs(String s, List<Integer> r, int idx){
        if (idx >= s.length() && r.size() > 2) return true;
        for (int j = idx + 1; j <= s.length(); j++) {
            if (s.charAt(idx) == '0' && j > idx + 1) {
                break;
            }
            long next = Long.parseLong(s.substring(idx, j));
            if (next > Integer.MAX_VALUE) {
                break;
            }
            if (r.size() < 2) {
                r.add((int)next);
                if (dfs(s, r, j)) return true;
                r.remove(r.size() - 1);
            } else {
                int size = r.size();
                if (r.get(size - 2) + r.get(size - 1) == next) {
                    r.add((int)next);
                    if (dfs(s, r, j)) return true;
                    r.remove(r.size() - 1);
                }
            }
        }
        return false;
    }
}