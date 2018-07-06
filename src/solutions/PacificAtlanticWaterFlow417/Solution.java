package solutions.PacificAtlanticWaterFlow417;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by dyj on 6/3/18.
 *
 Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.

 Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.

 Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.
 *
 Given the following 5x5 matrix:

 Pacific ~   ~   ~   ~   ~
 ~  1   2   2   3  (5) *
 ~  3   2   3  (4) (4) *
 ~  2   4  (5)  3   1  *
 ~ (6) (7)  1   4   5  *
 ~ (5)  1   1   2   4  *
 *   *   *   *   * Atlantic

 Return:

 [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).
 */
class Solution {

    // BFS
    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> res = new LinkedList<>();
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return res;
        }
        int M = matrix.length, N = matrix[0].length;
        Queue<int[]> queue1 = new LinkedList<>();
        Queue<int[]> queue2 = new LinkedList<>();

        boolean[][] visited1 = new boolean[M][N];
        boolean[][] visited2 = new boolean[M][N];

        for (int i = 0; i < M; i++) {
            queue1.offer(new int[]{i, 0});
            queue2.offer(new int[]{i, N - 1});
            visited1[i][0] = true;
            visited2[i][N - 1] = true;
        }
        for (int i = 0; i < N; i++) {
            queue1.offer(new int[]{0, i});
            queue2.offer(new int[]{M - 1, i});
            visited1[0][i] = true;
            visited2[M - 1][i] = true;
        }

        bfs(matrix, queue1, visited1);
        bfs(matrix, queue2, visited2);

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (visited1[i][j] && visited2[i][j]) {
                    res.add(new int[]{i, j});
                }
            }
        }

        return res;
    }

    private void bfs(int[][] matrix, Queue<int[]> queue, boolean[][] visited) {
        int M = matrix.length, N = matrix[0].length;
        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int x = pos[0], y = pos[1];
            int[] dx = {0, 1, 0, -1};
            int[] dy = {1, 0, -1, 0};

            for (int i = 0; i < 4; i++) {
                int xx = x + dx[i];
                int yy = y + dy[i];

                if (xx < 0 || xx >= M || yy < 0 || yy >= N || visited[xx][yy] || matrix[xx][yy] < matrix[x][y]) continue;
                visited[xx][yy] = true;
                queue.offer(new int[]{xx, yy});
            }
        }
    }
}
