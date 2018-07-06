package solutions.templates;

/**
 * Created by dyj on 6/10/18.
 */
public class UnionFind {
    private int[] parent;
    private int[] rank;
    private int count;

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        count = n;
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int find(int i) {
        if (parent[i] == i) {
            return i;
        }
        parent[i] = find(parent[i]);
        return parent[i];
    }

    public int find1(int p) {
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];    // path compression by halving
            p = parent[p];
        }
        return p;
    }

    public void union(int p, int q) {
        int rootP = find1(p);
        int rootQ = find1(q);

        if (rootP == rootQ) {
            return ;
        }

        // smaller rank branch point to larger rank
        if (rank[rootP] < rank[rootQ]) {
            parent[rootP] = rootQ;
        } else if (rank[rootP] > rank[rootQ]) {
            parent[rootQ] = rootP;
        } else {
            parent[rootP] = rootQ;
            rank[rootQ]++;
        }
        count--;
    }

    public int getCount() {
        return count;
    }
}
