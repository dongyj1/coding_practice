package solutions.NumberofIslandsII;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dyj on 7/28/18.
 *
 A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

 Example:

 Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
 Output: [1,1,2,3]
 Explanation:

 Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).

 0 0 0
 0 0 0
 0 0 0
 Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.

 1 0 0
 0 0 0   Number of islands = 1
 0 0 0
 Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.

 1 1 0
 0 0 0   Number of islands = 1
 0 0 0
 Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.

 1 1 0
 0 0 1   Number of islands = 2
 0 0 0
 Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.

 1 1 0
 0 0 1   Number of islands = 3
 0 1 0
 Follow up:

 Can you do it in time complexity O(k log mn), where k is the length of the positions?
 */
public class Solution {
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        int[] parents = new int[m * n];
        int[] size = new int[m * n];
        int[] count = new int[1];
        Arrays.fill(parents, -1);
        List<Integer> res = new ArrayList<>();

        for (int[] position : positions) {
            int pos = findIndex(position[0], position[1], m, n);
            parents[pos] = pos; // set it a new island
            size[pos]++;
            count[0]++;

            for (int[] dir : new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}}) {
                int x = position[0] + dir[0], y = position[1] + dir[1];
                int neighbor = findIndex(x, y, m, n);
                if (x < 0 || x >= m || y < 0 || y >= n || parents[neighbor] == -1) {
                    continue; // skip invalid position
                }
                union(parents, size, pos, neighbor, count);
            }
            res.add(count[0]);
        }
        return res;
    }

    private int find(int[] parents, int i) {
        while (i != parents[i]) {
            parents[i] = parents[parents[i]]; // path compression
            i = parents[i];
        }
        return i;
    }

    // union by size
    private void union(int[] parents, int[] size, int p, int q, int[] count) {
        int rootP = find(parents, p);
        int rootQ = find(parents, q);

        if (rootP == rootQ) return;
        if (size[rootP] > size[rootQ]) { // move the smaller one to big one
            parents[rootQ] = rootP;
            size[rootP] += size[rootQ];
        } else {
            parents[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
        count[0]--;
    }

    private int findIndex(int x, int y, int m, int n) {
        return n * x + y;
    }
}
