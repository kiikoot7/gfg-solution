import java.util.*;

class Solution {
    static class Node {
        int v, weight;
        Node(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }
    }

    public static int minimumEdgeReversal(int[][] edges, int n, int src, int dst) {
        // Adjacency list to store neighbors and edge weights (0 for original, 1 for reversed)
        List<List<Node>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(new Node(v, 0)); // Original edge: cost 0
            adj.get(v).add(new Node(u, 1)); // Reverse edge: cost 1
        }

        // Distance array to store the minimum reversals to reach each node
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        // 0-1 BFS using Deque
        Deque<Integer> deque = new LinkedList<>();
        dist[src] = 0;
        deque.addFirst(src);

        while (!deque.isEmpty()) {
            int u = deque.pollFirst();

            if (u == dst) {
                return dist[dst];
            }

            for (Node neighbor : adj.get(u)) {
                int v = neighbor.v;
                int weight = neighbor.weight;

                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    if (weight == 0) {
                        deque.addFirst(v); // 0-cost edge goes to the front
                    } else {
                        deque.addLast(v);  // 1-cost edge goes to the back
                    }
                }
            }
        }

        // If destination is unreachable
        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
    }
}