package solutions.GraphValidTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dyj on 7/21/18.
 *
 Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.

 Example 1:

 Input: n = 5, and edges = [[0,1], [0,2], [0,3], [1,4]]
 Output: true
 Example 2:

 Input: n = 5, and edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
 Output: false
 Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0,1] is the same as [1,0] and thus will not appear together in edges.
 */
public class Solution {
    // Adjacent list
    public boolean validTree(int n, int[][] edges) {
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < n;i++){
            adjList.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++){
            int u = edges[i][0], v = edges[i][1];
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }
        boolean[] visited = new boolean[n];
        if (hasCycle(adjList, visited, 0, -1)){
            return false;
        }
        for (int i = 0; i < n;i++){
            if (!visited[i]) return false;
        }
        return true;
    }

    private boolean hasCycle(List<List<Integer>> adjList, boolean[] visited, int u, int parent) {
        visited[u] = true;
        for (int i = 0; i < adjList.get(u).size(); i++){
            int v = adjList.get(u).get(i);
            if ((visited[v] && parent != v) || (!visited[v] && hasCycle(adjList, visited, v, u))){
                return true;
            }
        }
        return false;
    }

    // Adjacent Matrix O(n^2)
    public boolean validTree1(int n, int[][] edges) {
        int[][] matrix = new int[n][n];
        for (int i = 0; i < edges.length; i++){
            int u = edges[i][0], v = edges[i][1];
            matrix[u][v] = 1;
            matrix[v][u] = 1;
        }
        boolean[] visited = new boolean[n];
        if (hasCycle1(matrix, visited, 0, -1)){
            return false;
        }
        for (int i = 0; i < visited.length; i++){
            if (!visited[i]){
                return false;
            }
        }
        return true;
    }

    private boolean hasCycle1(int[][] matrix, boolean[] visited, int u, int parent) {
        visited[u] = true;
        for (int v = 0; v < matrix[u].length; v++){
            if (matrix[u][v] == 0){
                continue;
            }
            if ((visited[v] && v != parent) || (!visited[v] && hasCycle1(matrix, visited, v, u))){
                return true;
            }
        }
        return false;
    }

    // Union Find
    public boolean validTree2(int n, int[][] edges) {
        int[] nums = new int[n];
        for (int i = 0; i < n; i++){
            nums[i] = i;
        }

        // perform union and find
        for (int i = 0; i < edges.length; i++){
            int x = find(nums, edges[i][0]);
            int y = find(nums, edges[i][1]);

            // if nodes connects together
            if (x == y) return false;

            // union
            nums[x] = y;
        }
        return edges.length == n - 1;
    }

    // find with compressed path
    private int find(int[] nums, int i) {
        if (nums[i] == i) return i;
        int temp = find(nums, nums[i]);
        nums[i] = temp;
        return temp;
    }


    public static void main(String[] args) {
        System.out.println(new Solution().validTree(5, new int[][]{{0,1}, {0,2}, {0,3}, {1,4}}));
        System.out.println(new Solution().validTree2(5, new int[][]{{0,1}, {1,2}, {2,3}, {1,4}, {1,3}}));
    }
}
