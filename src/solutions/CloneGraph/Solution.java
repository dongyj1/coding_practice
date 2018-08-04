package solutions.CloneGraph;

import java.util.*;

/**
 * Created by dyj on 8/4/18.
 *
 Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.


 OJ's undirected graph serialization:
 Nodes are labeled uniquely.

 We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
 As an example, consider the serialized graph {0,1,2#1,2#2,2}.

 The graph has a total of three nodes, and therefore contains three parts as separated by #.

 First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
 Second node is labeled as 1. Connect node 1 to node 2.
 Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
 Visually, the graph looks like the following:

 1
 / \
 /   \
 0 --- 2
 / \
 \_/
 */
// DFS
public class Solution {
    private HashMap<Integer, UndirectedGraphNode> map = new HashMap<>();

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        return helper(node);
    }

    private UndirectedGraphNode helper(UndirectedGraphNode node) {
        if (node == null) return null;
        if (map.containsKey(node.label)){
            return map.get(node.label);
        }
        UndirectedGraphNode node_ = new UndirectedGraphNode(node.label);
        map.put(node.label, node_);
        for (UndirectedGraphNode neighbor : node.neighbors) {
            node_.neighbors.add(helper(neighbor));
        }
        return node_;
    }
}

// BFS
class Solution1 {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) return null;
        Queue<UndirectedGraphNode> queue = new ArrayDeque<>();
        queue.add(node);
        Map<Integer, UndirectedGraphNode> map = new HashMap<>();// use a map to save cloned nodes
        map.put(node.label, new UndirectedGraphNode(node.label));
        while (!queue.isEmpty()) {
            UndirectedGraphNode n = queue.poll();
            UndirectedGraphNode n_ = map.get(n.label);
            for (UndirectedGraphNode nei : n.neighbors) {
                if (!map.containsKey(nei.label)) { // new node
                    map.put(nei.label, new UndirectedGraphNode(nei.label));
                    queue.offer(nei);
                }
                map.get(n.label).neighbors.add(map.get(nei.label));
            }
        }
        return map.get(node.label);
    }
}

class UndirectedGraphNode {
    int label;
    List<UndirectedGraphNode> neighbors;
    UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>();}
}
