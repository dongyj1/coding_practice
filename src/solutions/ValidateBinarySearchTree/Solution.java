package solutions.ValidateBinarySearchTree;

import solutions.TreeNode;

/**
 * Created by dyj on 7/18/18.
 *
 Given a binary tree, determine if it is a valid binary search tree (BST).

 Assume a BST is defined as follows:

 The left subtree of a node contains only nodes with keys less than the node's key.
 The right subtree of a node contains only nodes with keys greater than the node's key.
 Both the left and right subtrees must also be binary search trees.
 Example 1:

 Input:
 2
 / \
 1   3
 Output: true
 Example 2:

 5
 / \
 1   4
 / \
 3   6
 Output: false
 Explanation: The input is: [5,1,4,null,null,3,6]. The root node's value
 is 5 but its right child's value is 4.
 */
public class Solution {
    class Result{
        boolean isBST;
        Integer min;
        Integer max;
        Result(boolean isbst, Integer min, Integer max) {
            isBST = isbst;
            this.min = min;
            this.max = max;
        }
    }
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        Result res = helper(root);
        return res.isBST;
    }
    private Result helper(TreeNode root) {
        if (root == null){
            return new Result(true, null, null);
        }
        Result left = helper(root.left);
        Result right = helper(root.right);
        if (!left.isBST || !right.isBST) {
            return new Result(false, null, null);
        }

        if (left.max != null && left.max >= root.val) {
            return new Result(false, null, null);
        }
        if (right.min != null && right.min <= root.val) {
            return new Result(false, null, null);
        }

        int min = root.val, max = root.val;
        if (left.min != null) {
            min = Math.min(min, left.min);
        }
        if (right.max != null) {
            max = Math.max(max, right.max);
        }
        return new Result(true, min, max);
    }
}
class Solution1{
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    private boolean isValidBST(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }
        if (root.val <= min || root.val >= max) {
            return false;
        }
        return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
    }
}
