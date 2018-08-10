package solutions.InorderPredecessorinBST;

import solutions.TreeNode;

/**
 * Created by dyj on 8/10/18.
 * 
 * Find the next predecessor if node p in in order traversal.
 */
// Recursive
public class Solution {
    public TreeNode predecessor(TreeNode root, TreeNode p) {
        if (root == null) {
            return null;
        }
        if (root.val >= p.val) {
            predecessor(root.left, p);
        } else {
            TreeNode right = predecessor(root.right, p);
            return (right == null) ? root : right;
        }
    }
}
