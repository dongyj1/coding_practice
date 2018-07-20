package solutions.SymmetricTree;

import apple.laf.JRSUIUtils;
import solutions.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by dyj on 7/19/18.
 *
 Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

 For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

 1
 / \
 2   2
 / \ / \
 3  4 4  3
 But the following [1,2,2,null,3,null,3] is not:
 1
 / \
 2   2
 \   \
 3    3
 Note:
 Bonus points if you could solve it both recursively and iteratively.
 */
public class Solution {
    // iteratively BFS
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        LinkedList<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            if (!isSymList(q)) {
                return false;
            }
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (node != null) {
                    q.offer(node.left);
                    q.offer(node.right);
                }
            }
        }
        return true;
    }
    // check if list is synm
    private boolean isSymList(LinkedList<TreeNode> q) {
        LinkedList<TreeNode> qq = new LinkedList<>(q);
        while (qq.size() > 1) {
            TreeNode head = qq.pollFirst();
            TreeNode tail = qq.pollLast();
            if (head == null && tail == null) {
                continue;
            }
            if (head == null || tail == null) {
                return false;
            }
            if (head.val != tail.val) {
                return false;
            }
        }
        return true;
    }


    // recursively
    public boolean isSymmetric1(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetric1(root.left, root.right);
    }

    private boolean isSymmetric1(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        return isSymmetric1(left.left, right.right) && isSymmetric1(left.right, right.left);
    }
}
