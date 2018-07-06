package impl.HungarianAlgorithm;

import java.util.Arrays;

/**
 * Created by dyj on 6/12/18.
 */
public class HungarianAlgorithm {

    int[][] graph;
    int size;
    int[] matching;
    boolean[] used;

    public HungarianAlgorithm() {
    }

    public HungarianAlgorithm(int[][] graph, int size) {
        this.graph = graph;
        this.size = size;
        matching = new int[size];
        Arrays.fill(matching, -1);
    }

    // Recursion
    public int hungarian1() {
        used = new boolean[size];
        int res = 0;
        for (int i = 0; i < size; i++) {
            if (matching[i] == -1) {
//                used = new boolean[size];
                System.out.println("Start matching : " + (i + 1));
                if (find(i)) {
                    res += 1;
                }
            }
        }
        return res;
    }
    private boolean find(int i) {
        for (int j = 0; j < size; j++) {
            if (graph[i][j] == 1 && !used[j]) {
                used[j] = true;
                if (matching[i] == -1 || find(i) ) {
                    matching[i] = j;
                    matching[j] = i;
                    System.out.println((i + 1) + " -> " + (j + 1));
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] graph = new int[][]{
                {0,0,0,0,1,0,1,0},
                {0,0,0,0,1,0,0,0},
                {0,0,0,0,1,1,0,0},
                {0,0,0,0,0,0,1,1},
                {1,1,1,0,0,0,0,0},
                {0,0,1,0,0,0,0,0},
                {1,0,0,1,0,0,0,0},
                {0,0,0,1,0,0,0,0}};
        HungarianAlgorithm h = new HungarianAlgorithm(graph, graph.length);
        System.out.println(h.hungarian1());

        graph = new int[][]{
                {0,0,0,0,1,0,1,0},
                {0,0,0,0,1,0,0,0},
                {0,0,0,0,1,1,0,0},
                {0,0,0,0,0,0,1,1},
                {1,1,1,0,0,0,0,0},
                {0,0,1,0,0,0,0,0},
                {1,0,0,1,0,0,0,0},
                {0,0,0,1,0,0,0,0}
        };
        h = new HungarianAlgorithm(graph, graph.length);
        System.out.println(h.hungarian1());
    }
}
