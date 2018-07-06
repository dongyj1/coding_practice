package solutions.SumofLeftLeaves404;

/**
 * Created by dyj on 6/2/18.
 */
class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int cur = 0;
        if (root.left != null) {
            if (root.left.left == null && root.left.right == null) {
                cur = root.left.val;
            }
        }
        return cur + sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
    }
}
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
        left = null;
        right = null;
    }
}
