package solutions.KillProcess;

/*
pid =  [1, 3, 10, 5]
ppid = [3, 0, 5, 3]
kill = 5
Output: [5,10]


*/
class Solution {
    public List<Integer> killProcess1(List<Integer> pid, List<Integer> ppid, int kill) {
        List<Integer> r = new ArrayList<>();
        HashMap<Integer, Set<Integer>> map = new HashMap<>();
        
        for (int i = 0; i < pid.size(); i++) {
            int p_id = pid.get(i);
            int pp_id = ppid.get(i);
            if (!map.containsKey(pp_id)) {
                map.put(pp_id, new HashSet<Integer>());
            }
            map.get(pp_id).add(p_id);
        }

        helper(r, map, kill);

        return r;
    }

    private void helper(List<Integer> r, HashMap<Integer, Set<Integer>> map, int kill) {
        
        r.add(kill);
        if (!map.containsKey(kill)) {
            return;
        }
        Set<Integer> set = map.get(kill);
        
        for (Integer i : set) {
            helper(res, map, i);
        }
    }
}