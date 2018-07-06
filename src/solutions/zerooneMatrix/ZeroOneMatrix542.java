
package solutions.zerooneMatrix;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by dyj on 4/20/18.
 * Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.

 The distance between two adjacent cells is 1.
 */
public class ZeroOneMatrix542 {

    public int[][] updateMatrix(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return null;
        }
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    queue.add(new int[]{i, j});
                } else {
                    matrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        int[][] dir = new int[][]{{1,0}, {-1,0}, {0,1}, {0,-1}};
        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            for (int[] d : dir) {
                int[] temp = new int[2];
                temp[0] = d[0] + pos[0];
                temp[1] = d[1] + pos[1];
                if (temp[0] < 0 || temp[0] >= matrix.length
                        || temp[1] < 0 || temp[1] >= matrix[0].length
                        || matrix[temp[0]][temp[1]] <= matrix[pos[0]][pos[1]] + 1) {
                    continue;
                }
                matrix[temp[0]][temp[1]] = matrix[pos[0]][pos[1]] + 1;
                queue.add(temp);
            }
        }
        return matrix;
    }

    public static void main(String[] args) {
        int[][] a = {{0,0,0},{0,1,0},{1,1,1}};
        ZeroOneMatrix542 solution = new ZeroOneMatrix542();
        int[][] ans = solution.updateMatrix(a);
        for (int[] arr : ans) {
            System.out.println(Arrays.toString(arr));
        }
    }
}
