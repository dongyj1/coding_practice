package solutions.RecoverBinarySearchTree;

import solutions.TreeNode;

/**
 * Created by dyj on 8/6/18.
 *
 Two elements of a binary search tree (BST) are swapped by mistake.

 Recover the tree without changing its structure.

 Example 1:

 Input: [1,3,null,null,2]

 1
 /
 3
 \
 2

 Output: [3,1,null,null,2]

 3
 /
 1
 \
 2
 Example 2:

 Input: [3,1,4,null,null,2]

 3
 / \
 1   4
 /
 2

 Output: [2,1,4,null,null,3]

 2
 / \
 1   4
 /
 3
 Follow up:

 A solution using O(n) space is pretty straight forward.
 Could you devise a constant space solution?
 */
public class Solution {
    TreeNode firstElement = null;
    TreeNode secondElement = null;
    TreeNode prevElement = new TreeNode(Integer.MIN_VALUE);

    public void recoverTree(TreeNode root) {
        if (root == null) {
            return ;
        }
        inorder(root);

        // swap
        int temp = firstElement.val;
        firstElement.val = secondElement.val;
        secondElement.val = temp;

        return;
    }

    private void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        if (firstElement == null && prevElement.val >= root.val) {
            firstElement = prevElement;
        }

        if (firstElement != null && prevElement.val >= root.val) {
            secondElement = root;
        }
        prevElement = root;

        inorder(root.right);
    }
}
