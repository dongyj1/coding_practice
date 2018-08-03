package solutions.ConstructBinaryTreefromPreorderandInorderTraversal;

import solutions.TreeNode;

/**
 * Created by dyj on 8/3/18.
 *
 Given preorder and inorder traversal of a tree, construct the binary tree.

 Note:
 You may assume that duplicates do not exist in the tree.

 For example, given

 preorder = [3,9,20,15,7]
 inorder = [9,3,15,20,7]
 Return the following binary tree:

 3
 / \
 9  20
 /  \
 15   7

 */
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTreeHelper(preorder, inorder, 0, 0, inorder.length - 1);
    }

    private TreeNode buildTreeHelper(int[] preorder, int[] inorder, int index, int begin, int end) {
        if (begin > end) {
            return null;
        }
        if (begin == end) {
            return new TreeNode(inorder[begin]);
        }
        TreeNode node = new TreeNode(preorder[index]);

        // find position of root in inorder
        int i;
        for (i = begin; i <= end; i++) {
            if (inorder[i] == preorder[index]) break;
        }
        node.left = buildTreeHelper(preorder, inorder, index + 1, begin, i - 1);
        node.right = buildTreeHelper(preorder, inorder, index + (i - begin + 1), i + 1, end);

        return node;
    }
}