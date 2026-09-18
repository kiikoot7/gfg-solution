class Solution {
    int minDiff = Integer.MAX_VALUE;
    Integer prevVal = null;

    // Function to find the minimum absolute difference between any two nodes in a BST.
    public int absDiff(Node root) {
        inorder(root);
        return minDiff;
    }

    private void inorder(Node node) {
        if (node == null) {
            return;
        }

        // Traverse the left subtree
        inorder(node.left);

        // Process the current node
        if (prevVal != null) {
            minDiff = Math.min(minDiff, node.data - prevVal);
        }
        prevVal = node.data;

        // Traverse the right subtree
        inorder(node.right);
    }
}