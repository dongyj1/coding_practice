package solutions.TwoSumIVInputisaBST;

import solutions.TreeNode;

import java.util.Stack;

/**
 * Created by dyj on 7/21/18.
 *
 Given a Binary Search Tree and a target number, return true if there exist two elements in the BST such that their sum is equal to the given target.

 Example 1:
 Input:
 5
 / \
 3   6
 / \   \
 2   4   7

 Target = 9

 Output: True
 Example 2:
 Input:
 5
 / \
 3   6
 / \   \
 2   4   7

 Target = 28

 Output: False
 *
 */
class Solution {
    // time O(n) space O(logn)
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) return false;
        Stack<TreeNode> l_stack = new Stack<>();
        Stack<TreeNode> r_stack = new Stack<>();
        // add tree nodes to left stack and right stack
        stackAdd(root, l_stack, true);
        stackAdd(root, r_stack, false);

        while(l_stack.peek() != r_stack.peek()){
            int n = l_stack.peek().val + r_stack.peek().val;
            if (n == k) {
                return true;
            } else if (n < k) {
                // go to left stack
                stackNext(l_stack, true);
            } else {
                stackNext(r_stack, false);
            }
        }
        return false;
    }

    // add the non null tree node to the stack. Largest/smallest node on top.
    private void stackAdd(TreeNode node, Stack<TreeNode> stack, boolean isLeft) {
        while(node != null){
            stack.push(node);
            node = (isLeft) ? node.left : node.right;
        }
    }

    // move stack further
    private void stackNext(Stack<TreeNode> stack, boolean isLeft) {
        TreeNode node = stack.pop();
        if (isLeft) {
            // add the right sub tree to the stack. Nodes added are also larger than the original top node.
            stackAdd(node.right, stack, isLeft);
        } else {
            stackAdd(node.left, stack, isLeft);
        }
    }
}

