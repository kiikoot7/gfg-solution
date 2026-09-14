import java.util.*;

class Solution {
    public int findShortestPath(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;

        boolean[][] ok = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(ok[i], true);
        }

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        // Step 1: Mark landmines and their 4-directional neighbors as unsafe
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 0) {
                    ok[i][j] = false;
                    for (int k = 0; k < 4; k++) {
                        int ni = i + dr[k];
                        int nj = j + dc[k];
                        if (ni >= 0 && ni < n && nj >= 0 && nj < m) {
                            ok[ni][nj] = false;
                        }
                    }
                }
            }
        }

        // Step 2: Initialize Multi-Source BFS queue with all safe cells in the first column
        Queue<int[]> q = new LinkedList<>();
        int[][] dist = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], -1);
        }

        for (int i = 0; i < n; i++) {
            if (ok[i][0]) {
                q.add(new int[]{i, 0});
                dist[i][0] = 0;
            }
        }

        // Step 3: Run BFS to find the shortest path to any cell in the last column
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0];
            int c = curr[1];

            if (c == m - 1) {
                return dist[r][c] + 1; // Return total number of cells in the path
            }

            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];

                if (nr >= 0 && nr < n && nc >= 0 && nc < m && ok[nr][nc] && dist[nr][nc] == -1) {
                    dist[nr][nc] = dist[r][c] + 1;
                    q.add(new int[]{nr, nc});
                }
            }
        }

        return -1;
    }

    public int shortestPath(int[][] mat) {
        return findShortestPath(mat);
    }
}