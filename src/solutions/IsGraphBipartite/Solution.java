package solutions.IsGraphBipartite;


// DFS (NO backtracking!!!)
class Solution{
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] colors = new int[n];
        Arrays.fill(colors, -1);

        for (int i = 0; i < n; i++) {
            if (colors[i] == -1 && !isValid(graph, colors, i, 0))
                return false;
        }
        return true;
    }

    private boolean isValid(int[][] graph, int[] colors, int i, int color) {
        if (colors[i] != -1) return colors[i] == color;
        colors[i] = color;
        for (int j : graph[i]) {
            if(!isValid(graph, colors, j, 1 - color)){
                return false;
            }
        }
        return true;
    }
}


// BFS
class Solution1 {
    public boolean isBipartite(int[][] graph) {
        int[] visited = new int[graph.length];
        Arrays.fill(visited, -1);
        for (int i = 0; i < graph.length; i++){
            if (visited[i] == -1 && !bfs(graph, visited, i, 0)){
                return false;
            }
        }
        return true;
    }

    private boolean bfs(int[][] graph, int[] visited, int i, int color) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(i);

        while (!q.isEmpty()) {
            int size = q.size();
            while(size-- > 0){
                int n = q.poll();
                if (visited[n] != -1 && visited[n] == color) {
                    return false;
                }
                if (visited[n] != -1) continue;
                visited[n] = 1 - color;
                for (int k : graph[n]) {
                    q.offer(k);
                }
            }
            color = 1 - color;
        }

        return true;
    }
}