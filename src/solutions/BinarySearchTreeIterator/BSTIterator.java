package solutions.BinarySearchTreeIterator;

import solutions.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by dyj on 7/26/18.
 *
 Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

 Calling next() will return the next smallest number in the BST.

 Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.

 Credits:
 Special thanks to @ts for adding this problem and creating all test cases.
 */
public class BSTIterator {

    Deque<TreeNode> dq;

    public BSTIterator(TreeNode root) {
        dq = new ArrayDeque<>();
        pushLeft(root);
    }

    private void pushLeft(TreeNode root) {
        while (root != null){
            dq.offerFirst(root);
            root = root.left;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !dq.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode cur = dq.pollFirst();
        pushLeft(cur.right);
        return cur.val;
    }
}
