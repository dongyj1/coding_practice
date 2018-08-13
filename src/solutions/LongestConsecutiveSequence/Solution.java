package solutions.LongestConsecutiveSequence;

class Solution {
    public int longestConsecutive(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for(int n : nums){
            if(!map.containsKey(n)){
                int leftLen = (map.containsKey(n - 1))? map.get(n - 1): 0;
                int rightLen = (map.containsKey(n + 1))? map.get(n + 1): 0;

                int len = leftLen + rightLen + 1; // cur len
                res = Math.max(res, len);
                map.put(n, len);
                map.put(n - leftLen, len);
                map.put(n + rightLen, len);
            }
        }
        return res;
    }
}