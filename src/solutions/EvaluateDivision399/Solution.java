package solutions.EvaluateDivision399;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by dyj on 5/19/18.
 *
 * Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.

 Example:
 Given a / b = 2.0, b / c = 3.0.
 queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
 return [6.0, 0.5, -1.0, 1.0, -1.0 ].

 The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.

 According to the example above:

 equations = [ ["a", "b"], ["b", "c"] ],
 values = [2.0, 3.0],
 queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
 The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.
 */

public class Solution {
    /**
     * Graph + DFS
     *
     * @param equations
     * @param values
     * @param queries
     * @return
     */
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        HashMap<String, HashMap<String, Double>> g = new HashMap<>();
        // Construct the graph
        for (int i = 0; i < equations.length; i++) {
            String a = equations[i][0];
            String b = equations[i][1];
            double k = values[i];

            g.computeIfAbsent(a, l -> new HashMap<String, Double>()).put(b, k);
            g.computeIfAbsent(b, l -> new HashMap<String, Double>()).put(a, 1.0 / k);
        }

        double[] ans = new double[queries.length];

        // DFS
        for (int i = 0; i < queries.length; i++) {
            String X = queries[i][0];
            String Y = queries[i][1];

            if (!g.containsKey(X) || !g.containsKey(Y)) {
                ans[i] = -1;
            }
            else {
                ans[i] = divide(X, Y, g, new HashSet<String>());
            }
        }

        return ans;
    }

    private double divide(String X, String Y, HashMap<String, HashMap<String, Double>> g, HashSet<String> visited) {
        if (X.equals(Y)) {
            return 1.0;
        }
        visited.add(X);
        if (!g.containsKey(X)) {
            return -1;
        }
        for (String a : g.get(X).keySet()) {
            if (visited.contains(a)) {
                continue;
            }
            visited.add(a);
            double d = divide(a, Y, g, visited);
            if (d > 0) {
                return d * g.get(X).get(a);
            }
        }
        return -1;
    }

    /**
     * Union Find
     *
     * @param equations
     * @param values
     * @param queries
     * @return
     */
    public double[] calcEquation1(String[][] equations, double[] values, String[][] queries) {
        UnionFindSet u = new UnionFindSet();

        for (int i = 0; i < equations.length; i++) {
            u.union(equations[i][0], equations[i][1], values[i]);
        }

        double ans[] = new double[queries.length];

        for (int i = 0; i < queries.length; i++) {
            Node rx = u.find(queries[i][0]);
            Node ry = u.find(queries[i][1]);
            if (rx == null || ry == null || !rx.parent.equals(ry.parent)) {
                ans[i] = -1.0;
            } else {
                ans[i] = rx.ratio / ry.ratio;
            }
        }

        return ans;
    }

    class Node {
        public String parent;
        public double ratio;
        public Node(String parent, double ratio) {
            this.parent = parent;
            this.ratio = ratio;
        }
    }

    class UnionFindSet {
        private Map<String, Node> parents = new HashMap<>();

        public Node find(String s) {
            if (!parents.containsKey(s)) {
                return null;
            }
            Node n = parents.get(s);
            if (!n.parent.equals(s)) {
                Node p = find(n.parent);
                n.parent = p.parent;
                n.ratio = p.ratio;
            }
            return n;
        }

        public void union(String s, String p, double ratio) {
            boolean hasS = parents.containsKey(s);
            boolean hasP = parents.containsKey(p);

            if (!hasS && !hasP) {
                parents.put(s, new Node(p, ratio));
                parents.put(p, new Node(p, 1.0 ));
            } else if (!hasS) {
                parents.put(s, new Node(p, ratio));
            } else if (!hasP) {
                parents.put(p, new Node(s, 1.0 / ratio));
            } else {
                Node rS = find(s);
                Node rP = find(p);
                rS.parent = rP.parent;
                rS.ratio = rP.ratio * ratio / rS.ratio;
            }
        }
    }
}
