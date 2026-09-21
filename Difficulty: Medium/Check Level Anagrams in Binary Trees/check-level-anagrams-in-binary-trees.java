import java.util.*;

class Solution {
    public boolean areAnagrams(Node root1, Node root2) {
        // If both trees are empty, they are identical/anagrams at all levels
        if (root1 == null && root2 == null) return true;
        // If only one is empty, they don't match
        if (root1 == null || root2 == null) return false;

        Queue<Node> q1 = new LinkedList<>();
        Queue<Node> q2 = new LinkedList<>();

        q1.add(root1);
        q2.add(root2);

        while (!q1.isEmpty() && !q2.isEmpty()) {
            int size1 = q1.size();
            int size2 = q2.size();

            // If the number of nodes at the current level differs, they aren't anagrams
            if (size1 != size2) return false;

            List<Integer> level1 = new ArrayList<>();
            List<Integer> level2 = new ArrayList<>();

            for (int i = 0; i < size1; i++) {
                Node node1 = q1.poll();
                Node node2 = q2.poll();

                level1.add(node1.data);
                level2.add(node2.data);

                // Add children of tree 1
                if (node1.left != null) q1.add(node1.left);
                if (node1.right != null) q1.add(node1.right);

                // Add children of tree 2
                if (node2.left != null) q2.add(node2.left);
                if (node2.right != null) q2.add(node2.right);
            }

            // Check if the current level's values are anagrams
            if (!isAnagram(level1, level2)) {
                return false;
            }
        }

        // Ensure both queues are fully exhausted
        return q1.isEmpty() && q2.isEmpty();
    }

    private boolean isAnagram(List<Integer> l1, List<Integer> l2) {
        if (l1.size() != l2.size()) return false;
        Collections.sort(l1);
        Collections.sort(l2);
        return l1.equals(l2);
    }
}