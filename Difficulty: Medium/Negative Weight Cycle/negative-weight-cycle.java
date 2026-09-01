class Solution {
    public boolean isNegativeWeightCycle(int V, int[][] edges) {
        long[] dist = new long[V];

        for (int i = 0; i < V - 1; i++) {
            boolean updated = false;
            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                long w = edge[2];
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    updated = true;
                }
            }
            if (!updated) {
                break;
            }
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            long w = edge[2];
            if (dist[u] + w < dist[v]) {
                return true;
            }
        }

        return false;
    }
}