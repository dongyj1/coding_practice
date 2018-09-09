package solutions.EvaluateDivision;

import java.awt.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*

equations = [ ["a", "b"], ["b", "c"] ],
values = [2.0, 3.0],
queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ]. 

*/

// DFS
class Solution {
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        HashMap<String, List<String>> ps = new HashMap<>();
        HashMap<String, List<Double>> vs = new HashMap<>();

        for (int i = 0; i < equations.length; i++){
            String[] e = equations[i];
            if (!ps.containsKey(e[0])) {
                ps.put(e[0], new ArrayList<String>());
                vs.put(e[0], new ArrayList<Double>());
            }
            if (!ps.containsKey(e[1])) {
                ps.put(e[1], new ArrayList<String>());
                vs.put(e[1], new ArrayList<Double>());
            }
            ps.get(e[0]).add(e[1]);
            ps.get(e[1]).add(e[0]);

            vs.get(e[0]).add(values[i]);
            vs.get(e[1]).add(1.0/values[i]);
        }

        double[] res = new double[queries.length];
        for (int i = 0; i < queries.length; i++) {
            String[] qry = queries[i];
            res[i] = dfs(qry[0], qry[1], ps, vs, new HashSet<String>(), 1);
            if (res[i] == 0.0) res[i] = -1.0;
        }
        return res;
    }

    private double dfs(String start, String target, HashMap<String, List<String>> ps, 
        HashMap<String, List<Double>> vs, Set<String> visited, double value) {
            if (visited.contains(start)) {
                return 0;
            }
            if (!ps.containsKey(start)) return 0.0;
            if (start.equals(target)) return value;

            List<String> pList = ps.get(start);
            List<Double> vList = vs.get(start);
            visited.add(start);
            double dist = 0;
            for (int i = 0; i < pList.size(); i++) {
                dist = dfs(pList.get(i), target, ps, vs, visited, value * vList.get(i));
                if (dist != 0) {
                    break;
                }
            }
            visited.remove(start);
            return dist;
    }
}

class Solution1 {
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        Set<String> set = new HashSet<>();
        for (String[] e : equations) {
            set.add(e[0]);
            set.add(e[1]);
        }
        Map<String, Node> root = new HashMap<>();
        for (String s : set) {
            root.put(s, new Node(s, 1));
        }

        for (int i = 0; i < equations.length; i++) {
            String[] e = equations[i];
            double v = values[i];
            union(e[0], e[1], v, root);
        }

        double[] res = new double[queries.length];
        for (int i = 0; i < queries.length; i++) {
            Node q1 = find(queries[i][0], root);
            Node q2 = find(queries[i][1], root);
            if (q1 == null || q2 == null || !q1.parent.equals(q2.parent)) {
                System.out.println(q1.parent + " " + q2.parent);
                res[i] = -1;
            }
            else res[i] = q1.value / q2.value;
        }
        return res;
    }

    private void union(String s1, String s2, double v, Map<String, Node> root) {
        Node p1 = find(s1, root);
        Node p2 = find(s2, root);

        if (p1.parent.equals(p2.parent)) {
            return;
        }
        p1.parent = p2.parent;
        p1.value = p2.value * v / p1.value;
    }

    private Node find(String s, Map<String, Node> root) {
        if (!root.containsKey(s)) {
            return null;
        } else {
            Node p = root.get(s);
            if (!p.parent.equals(s)) {
                Node pp = find(p.parent, root);
                p.parent = pp.parent;
                p.value *= pp.value;
            }
            return p;
        }
    }

    class Node {
        String parent;
        double value;
        public Node(String from, double value) {
            this.parent = from;
            this.value = value;
        }
    }
}