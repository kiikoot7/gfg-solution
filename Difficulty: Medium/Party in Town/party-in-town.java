import java.util.*;

class Solution {
    static int partyHouse(ArrayList<ArrayList<Integer>> adj) {
        int n = adj.size();
        if (n <= 1) return 0;

        // Step 1: Node 1 se sabse door wala node (farthest node) nikalo
        int[] res1 = getMaxDistAndFarthest(1, n, adj);
        int farthestFrom1 = res1[0];

        // Step 2: Us farthest node se dubara BFS karke tree ka diameter nikalo
        int[] res2 = getMaxDistAndFarthest(farthestFrom1, n, adj);
        int diameter = res2[1];

        // Step 3: Minimum maximum distance hamesha ceil(diameter / 2) hoti hai
        return (diameter + 1) / 2;
    }

    private static int[] getMaxDistAndFarthest(int start, int n, ArrayList<ArrayList<Integer>> adj) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);

        queue.add(start);
        dist[start] = 0;

        int farthestNode = start;
        int maxDist = 0;

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            for (int neighbor : adj.get(curr - 1)) {
                if (dist[neighbor] == -1) {
                    dist[neighbor] = dist[curr] + 1;
                    queue.add(neighbor);
                    if (dist[neighbor] > maxDist) {
                        maxDist = dist[neighbor];
                        farthestNode = neighbor;
                    }
                }
            }
        }
        return new int[] { farthestNode, maxDist };
    }
}