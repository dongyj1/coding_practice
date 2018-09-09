package solutions.SerializeandDeserializeBinaryTree;




public class CodeC{
    private final String SEP = "/";
    private final String NUL = "#";
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }
    
    private void buildString(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(NUL).append(SEP);
            return;
        }
        sb.append(root.val).append(SEP);
        buildString(root.left, sb);
        buildString(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        List<String> nodes = Arrays.asList(data.split("/"));
        Collections.reverse(nodes);
        Stack<String> stack = new Stack();
        for (String s : nodes) {
            stack.push(s);
        }
        return buildTree(stack);
    }
    
    private TreeNode buildTree(Stack<String> stk) {
        String str = stk.pop();
        if (str.equals(NUL)) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(str));
        root.left = buildTree(stk);
        root.right = buildTree(stk);
        return root;
    }
}