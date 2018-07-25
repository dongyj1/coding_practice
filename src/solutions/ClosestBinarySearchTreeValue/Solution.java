package solutions.ClosestBinarySearchTreeValue;

import solutions.TreeNode;

/**
 * Created by dyj on 7/19/18.
 *
 Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

 Note:

 Given target value is a floating point.
 You are guaranteed to have only one unique value in the BST that is closest to the target.
 Example:

 Input: root = [4,2,5,1,3], target = 3.714286

 4
 / \
 2   5
 / \
 1   3

 Output: 4

 */
public class Solution {
    public int closestValue(TreeNode root, double target) {
        if (root == null) {
            return -1;
        }
        TreeNode cur = root;
        int L = root.val, R = root.val;

        while (cur != null) {
            if (cur.val==target){
                return cur.val;
            } else if (cur.val > target) {
                R = cur.val;
                cur = cur.left;
            } else {
                L = cur.val;
                cur = cur.right;
            }
        }

        return (Math.abs(R - target) <= Math.abs(target - L)) ? R : L;
    }
}
