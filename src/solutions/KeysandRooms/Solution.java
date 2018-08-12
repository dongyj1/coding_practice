package solutions.KeysandRooms;

import java.util.HashSet;

/*

Input: [[1],[2],[3],[]]
Output: true
Explanation:  
We start in room 0, and pick up key 1.
We then go to room 1, and pick up key 2.
We then go to room 2, and pick up key 3.
We then go to room 3.  Since we were able to go to every room, we return true.

*/

// DFS
class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        HashSet<Integer> visited = new HashSet<>();
        visited.add(0);
        return dfs(rooms, visited, 0);  
    }

    private boolean dfs(List<List<Integer>> rooms, HashSet<Integer> visited, int id) {
        if (visited.size() == rooms.size()) return true;
        List<Integer> rm_list = rooms.get(id);
        for (int rm_id : rm_list) {
            if (visited.contains(rm_id)){
                continue;
            }
            visited.add(rm_id);
            if(dfs(rooms, visited, rm_id)) return true;
            // visited.remove(rm_id);
        }
        return false;
    }
}