package solutions.SubtreeofAnotherTree;

import solutions.TreeNode;

/**
 * Created by dyj on 7/21/18.
 *
 Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.

 Example 1:
 Given tree s:

 3
 / \
 4   5
 / \
 1   2
 Given tree t:
 4
 / \
 1   2
 Return true, because t has the same structure and node values with a subtree of s.
 Example 2:
 Given tree s:

 3
 / \
 4   5
 / \
 1   2
 /
 0
 Given tree t:
 4
 / \
 1   2
 Return false.
 */
public class Solution {


    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;
        if (isSameTree(s, t)) {
            return true;
        }
        return isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    private boolean isSameTree(TreeNode s, TreeNode t) {
        if (s == null && t == null)return true;
        if (s == null || t == null) return false;
        if (s.val != t.val) return false;
        return isSameTree(s.left, t.left) && isSameTree(s.right, t.right);
    }

    // slow because the substring check
    public boolean isSubtree1(TreeNode s, TreeNode t) {
        String s1 = convert(s);
        String s2 = convert(t);
        return s1.contains(s2);
    }

    private String convert(TreeNode node){
        if (node != null) {
            return "^" + node.val + "#" + convert(node.left) + "#" + convert(node.right);
        }
        return "$";
    }
}
