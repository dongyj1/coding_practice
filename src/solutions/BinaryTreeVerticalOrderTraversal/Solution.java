package solutions.BinaryTreeVerticalOrderTraversal;

import solutions.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by dyj on 7/21/18.
 *
 Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

 If two nodes are in the same row and column, the order should be from left to right.

 Examples 1:

 Input: [3,9,20,null,null,15,7]

 3
 /\
 /  \
 9  20
 /\
 /  \
 15   7

 Output:

 [
 [9],
 [3,15],
 [20],
 [7]
 ]
 Examples 2:

 Input: [3,9,8,4,0,1,7]

 3
 /\
 /  \
 9   8
 /\  /\
 /  \/  \
 4  01   7

 Output:

 [
 [4],
 [9],
 [3,0,1],
 [8],
 [7]
 ]
 Examples 3:

 Input: [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5)

 3
 /\
 /  \
 9   8
 /\  /\
 /  \/  \
 4  01   7
 /\
 /  \
 5   2

 Output:

 [
 [4],
 [9,5],
 [3,0,1],
 [8,2],
 [7]
 ]
 *
 */
public class Solution {

    int min = 0, max = 0;

    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        if (root == null) return lists;
        // find range of buckets
        computeRange(root, 0);
        // add buckets
        for (int i = min; i <= max; i++) lists.add(new ArrayList<>());

        LinkedList<TreeNode> q = new LinkedList<>();
        LinkedList<Integer> idx = new LinkedList<>();

        idx.add(-min);
        q.add(root);

        while(!q.isEmpty()){
            TreeNode node = q.poll();
            int i = idx.poll();
            lists.get(i).add(node.val);
            if (node.left != null){
                q.offer(node.left);
                idx.offer(i - 1);
            }
            if (node.right != null){
                q.offer(node.right);
                idx.offer(i + 1);
            }
        }

        return lists;
    }

    private void computeRange(TreeNode root, int i) {
        if (root == null)return;
        min = Math.min(i, min);
        max = Math.max(i, max);
        computeRange(root.left, i - 1);
        computeRange(root.right, i + 1);
    }
}
