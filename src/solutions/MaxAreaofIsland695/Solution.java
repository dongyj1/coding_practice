package solutions.MaxAreaofIsland695;

/**
 * Created by dyj on 4/30/18.
 */
public class Solution {

    public int maxAreaOfIsland(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int max = 0, m = grid.length, n = grid[0].length;
        UnionFind uf = new UnionFind();
        uf.init(m * n);

        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    max = Math.max(1, max);
                    for (int k = 0; k < 4; k++) {
                        int x = i + dx[k];
                        int y = j + dy[k];
                        if (x >= 0 && x < grid.length
                                && y >= 0 && y < grid[0].length
                                && grid[x][y] == 1) {
                            int p1 = uf.find(n * i + j);
                            int p2 = uf.find(n * x + y);
                            uf.union(p1, p2);
                            int temp = Math.max(uf.size[p1], uf.size[p2]);
                            max = Math.max(max, temp);
                        }
                    }
                }
            }
        }

        return max;
    }
}

class UnionFind {
    int[] size, parent;
    int count;

    public void init(int n) {
        size = new int[n];
        parent = new int[n];
        count = n;
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public void union(int a, int b) {
        int ap = find(a);
        int bp = find(b);
        if (ap != bp) {
            if (size[ap] < size[bp]) {
                parent[ap] = bp;
                size[bp] += size[ap];
            } else {
                parent[bp] = ap;
                size[ap] += size[bp];
            }
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
