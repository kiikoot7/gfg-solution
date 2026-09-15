import java.util.ArrayList;
import java.util.Collections;

class Solution {
    public int getCount(Node root, int k) {
        ArrayList<Integer> leafCosts = new ArrayList<>();
        dfs(root, 1, leafCosts);

        Collections.sort(leafCosts);

        int count = 0;
        int currentCost = 0;
        for (int cost : leafCosts) {
            if (currentCost + cost <= k) {
                currentCost += cost;
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    private void dfs(Node node, int level, ArrayList<Integer> leafCosts) {
        if (node == null) return;

        if (node.left == null && node.right == null) {
            leafCosts.add(level);
            return;
        }

        dfs(node.left, level + 1, leafCosts);
        dfs(node.right, level + 1, leafCosts);
    }
}