package solutions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dyj on 4/18/18.
 */

class IntervalTreeNode {
    IntervalTreeNode left, right;
    Interval interval ;

    public IntervalTreeNode(Interval interval) {
        this.interval = interval;
    }
}

public class DataStreamasDisjointIntervals352_Treeimpl {
    IntervalTreeNode root;
    public DataStreamasDisjointIntervals352_Treeimpl() {
        root = null;
    }
    public void addNum(int val) {
        if (root == null) {
            root = new IntervalTreeNode(new Interval(val, val));
            return ;
        }
        addValToTree(val);
    }

    public List<Interval> getIntervals() {
        List<Interval> list = new ArrayList<Interval>();
        inorder(root, list);
        return list;
    }
    private void inorder(IntervalTreeNode root, List<Interval> list) {
        if (root == null) return;
        inorder(root.left, list);
        list.add(root.interval);
        inorder(root.right, list);
    }
    private void addValToTree(int val) {
        IntervalTreeNode parent = null;
        IntervalTreeNode cur = root;
        while (cur != null && (cur.interval.start > val || cur.interval.end < val)) {
            if (cur.interval.start > val + 1) {
                parent = cur;
                cur = cur.left;
            } else if (cur.interval.end + 1 < val) {
                parent = cur;
                cur = cur.right;
            } else if (cur.interval.start == val + 1) {
                cur.interval.start = val;
                cur.left = findLargest(cur.left, val, cur);
                return ;
            } else if (cur.interval.end + 1 == val) {
                cur.interval.end = val;
                cur.right = findSmallest(cur.right, val, cur);
                return ;
            } else {
                return ;
            }
        }

        if (cur == null) {
            if (parent.interval.start - 1 > val) {
                parent.left = new IntervalTreeNode(new Interval(val, val));
            } else {
                parent.right = new IntervalTreeNode(new Interval(val, val));
            }
        }
    }

    private IntervalTreeNode findSmallest(IntervalTreeNode startRoot, int val, IntervalTreeNode node) {
        if (startRoot == null) {
            return null;
        }
        IntervalTreeNode parent = null;
        IntervalTreeNode cur = startRoot;
        while (cur.left != null) {
            parent = cur;
            cur = cur.left;
        }
        if (cur.interval.start == val + 1) {
            node.interval.end = cur.interval.end;
            // remove cur.left
            if (parent != null) {
                parent.left = cur.right;
                return startRoot;
            } else {
                return cur.right;
            }
        }
        return startRoot;
    }

    private IntervalTreeNode findLargest(IntervalTreeNode startRoot, int val, IntervalTreeNode node) {
        if (startRoot == null) return null;
        IntervalTreeNode parent = null;
        IntervalTreeNode cur = startRoot;
        while (cur.right != null) {
            parent = cur;
            cur = cur.right;
        }
        if (cur.interval.end + 1 == val) {
            node.interval.start = cur.interval.start;
            if (parent != null) {
                parent.right = cur.left;
                return startRoot;
            } else return cur.left;
        }
        return startRoot;
    }
}
