package solutions.SerializeAndDeserializeBST;

import solutions.TreeNode;

import java.util.Arrays;

/**
 * Created by dyj on 7/23/18.
 *
 Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

 Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary search tree can be serialized to a string and this string can be deserialized to the original tree structure.

 The encoded string should be as compact as possible.

 Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
 */
public class Codec {
    private static final String SEP = ",";
    private static final String NULL = "#";
    // Encodes a tree to a single string.
    // Post order
    public String serialize(TreeNode root) {
        if (root == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }
    // Post order
    private void serialize(TreeNode root, StringBuilder sb) {
        if (root == null ) return;
        serialize(root.left, sb);
        serialize(root.right, sb);
        sb.append(Integer.valueOf(root.val)).append(SEP);
    }


    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) return null;
        String[] nodes = data.split(SEP);
        int[] index = new int[]{nodes.length - 1}; // index of root
        return deserialize(nodes, index, Integer.MAX_VALUE);
    }

    private TreeNode deserialize(String[] nodes, int[] index, int min) {
        if (index[0] < 0 || Integer.valueOf(nodes[index[0]]) <= min) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(nodes[index[0]--]));
        root.right = deserialize(nodes, index, root.val);
        root.left = deserialize(nodes, index, min);
        return root;
    }

    // Encodes a tree to a single string.
    // PreOrder
    public String serialize1(TreeNode root) {
        if (root == null) return "";
        StringBuilder sb = new StringBuilder();
        serialize1(root, sb);
        return sb.toString();
    }

    private void serialize1(TreeNode root, StringBuilder sb) {
        if (root == null) return;
        sb.append(Integer.valueOf(root.val)).append(SEP);
        serialize1(root.left, sb);
        serialize1(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize1(String data) {
        if (data == null || data.length() == 0) return null;
        String[] nodes = data.split(SEP);
        int[] index = new int[]{0};
        return deserialize1(nodes, index, Integer.MIN_VALUE);
    }

    private TreeNode deserialize1(String[] nodes, int[] index, int max) {
        if (index[0] >= nodes.length || Integer.valueOf(nodes[index[0]]) >= max ) return null;
        TreeNode root = new TreeNode(Integer.valueOf(nodes[index[0]++]));
        root.left = deserialize1(nodes, index, max);
        root.right = deserialize1(nodes, index, root.val);
        return root;
    }

    // Encodes a tree to a single string.
    public String serialize2(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preorder(root, sb);
        return sb.toString();
    }

    private void preorder(TreeNode root, StringBuilder sb) {
        if (root == null) return;
        sb.append(root.val).append(SEP);
        preorder(root.left, sb);
        preorder(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize2(String data) {
        if (data == null || data.length() == 0) return null;
        int[] preorder = Arrays.stream(data.split(SEP)).mapToInt(Integer::parseInt).toArray();
        int[] inorder = preorder.clone();
        Arrays.sort(inorder);
        return buildTree(preorder, inorder);
    }

    private TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        return buildTree(preorder, inorder, 0, 0, n - 1);
    }

    private TreeNode buildTree(int[] preorder, int[] inorder, int idx, int start, int end) {
        if (start > end) return null;
        if (start == end) return new TreeNode(inorder[start]);
        int val = preorder[idx];
        int pos;
        for (pos = start; pos <= end; pos++){
            if (inorder[pos] == val) {
                break;
            }
        }
        TreeNode root = new TreeNode(val);
        root.left = buildTree(preorder, inorder, idx + 1, start, pos - 1);
        root.right = buildTree(preorder, inorder, idx + pos - start + 1, pos + 1, end);
        return root;
    }
}
