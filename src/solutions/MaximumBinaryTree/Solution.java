package solutions.MaximumBinaryTree;

import solutions.TreeNode;

/**
 * Created by dyj on 7/20/18.
 *
 Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:

 The root is the maximum number in the array.
 The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
 The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
 Construct the maximum tree by the given array and output the root node of this tree.

 Example 1:
 Input: [3,2,1,6,0,5]
 Output: return the tree root node representing the following tree:

    6
 /   \
 3     5
 \    /
  2  0
   \
    1
 */
public class Solution {
    // divide and conquer O(n)
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null) return null;
        return helper(nums, 0, nums.length - 1);
    }

    private TreeNode helper(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }

        int idMax = start;
        for (int i = start + 1; i <= end; i++) {
            if (nums[i] > nums[idMax]) {
                idMax = i;
            }
        }

        TreeNode root = new TreeNode(nums[idMax]);
        root.left = helper(nums, start, idMax - 1);
        root.right = helper(nums, idMax + 1, end);

        return root;
    }
}
