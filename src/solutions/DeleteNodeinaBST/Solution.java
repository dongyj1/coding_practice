package solutions.DeleteNodeinaBST;

class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (root.val == key) return delete(root);
        return delete(root, key);   
    }
    private TreeNode delete(TreeNode root, int key) {
        if (root == null) return null;
        if (root.val < key) {
            root.right = delete(root.right, key);
        } else if (root.val > key) {
            root.left = delete(root.left, key);
        } else {
            return delete(root);
        }
        return root;
    }

    private TreeNode delete(TreeNode root) {
        if (root.left == null) return root.right;
        if (root.right == null) return root.left;
        TreeNode new_left = root.right;
        while (new_left.left != null) {
            new_left = new_left.left;
        }
        new_left.left = root.left;
        return root.right;
    }
}