package solutions.NumberofIslands200;

/**
 * Created by dyj on 4/30/18.
 *
 Input:
 11110
 11010
 11000
 00000

 Output: 1

 Input:
 11000
 11000
 00100
 00011

 Output: 3
 */
public class Solution {

    /**
     *  Union & Find
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        int count = 0, M = grid.length;
        if (M == 0 || grid[0].length == 0) {
            return count;
        }
        int N = grid[0].length;
        UN un = new UN(M * N);

        int[] dx = new int[] {-1, 1, 0, 0};
        int[] dy = new int[] {0, 0, -1, 1};

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == '1') {
                    un.parent[i * M + j] = i * M + j;
                    count++;
                } else {
                    un.parent[i * M + j] = -1;
                }
                un.size[i * M + j] = 1;
            }
        }
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (i >= 0 && i < M && j >= 0 && j < N && grid[i][j] == '1') {
                    for (int k = 0; k < 4; k++) {
                        int xx = i + dx[k];
                        int yy = j + dy[k];
                        if (xx >= 0 && xx < M && yy >= 0 && yy < N && grid[xx][yy] == '1') {
                            int p1 = un.find(i * M + j);
                            int p2 = un.find(xx * M + yy);
                            if (p1 != p2) {
                                un.union(i * M + j, xx * M + yy);
                                un.n--;
                            }
                        }
                    }
                }
            }
        }
        return un.n;
    }

    public static void main(String[] args) {
        char[][] test = new char[][]{{'1','1','0','0','0'}, {'1','1','0','0','0'}, {'0','0','1','0','0'}, {'0','0','0','1','1'}};
        System.out.println(new Solution().numIslands(test));
    }
}
class UN {
    int[] size, parent;
    int n;

    public UN(int n) {
        this.n = n;
        size = new int[n];
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public void union(int a, int b) {
        int ap = find(a);
        int bp = find(b);
        if (ap == bp) {
            return;
        }
        if (size[ap] < size[bp]) {
            size[bp] += size[ap];
            parent[ap] = parent[bp];
        } else {
            size[ap] += size[bp];
            parent[bp] = parent[ap];
        }
    }

    public int find(int a) {
        while (a != parent[a]) {
            parent[a] = parent[parent[a]];
            a = parent[a];
        }
        return a;
    }
}
