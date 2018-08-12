package solutions.MagicSquaresInGrid;

/*

Input: [[4,3,8,4],
        [9,5,1,9],
        [2,7,6,2]]
Output: 1
*/

class Solution {
    public int numMagicSquaresInside(int[][] grid) {
        int r = 0;
        for (int i = 0; i < grid.length - 2; i++){
            for(int j = 0; j < grid[0].length - 2; j++){
                if (isMagic(grid, i, j)){
                    r++;
                }
            }
        }
        return r;
    }
    private boolean isMagic(int[][] grid, int x, int y){
        boolean[] visited = new boolean[10];
        for (int i = x; i < x + 3; i++){
            for(int j = y; j < y + 3; j++){
                int n = grid[i][j];
                if (n <= 9 && n >= 0 && !visited[n]){
                    visited[n] = true;
                } else {
                    return false;
                }
            }
        }
        // diagonal
        int sum1 = 0, sum2 = 0;
        sum1 = grid[x][y] + grid[x + 1][y + 1] + grid[x + 2][y + 2];
        sum2 = grid[x][y + 2] + grid[x + 1][y + 1] + grid[x + 2][y];
        if (sum1 != sum2) return false;
        // row , col
        for (int i = 0; i < 3; i++) {
            if (grid[x + i][y] + grid[x + i][y + 1] + grid[x + i][y + 2] != sum1) return false;
            if (grid[x][y + i] + grid[x + 1][y + i] + grid[x + 2][y + i] != sum1) return false;
        }
        return true;
    }
}