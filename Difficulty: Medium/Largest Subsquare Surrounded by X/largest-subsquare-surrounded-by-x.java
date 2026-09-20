class Solution {
    int largestSubsquare(char[][] mat) {
        int n = mat.length;
        if (n == 0) return 0;

        // horiz[i][j] stores consecutive 'X's to the left ending at (i, j)
        // vert[i][j] stores consecutive 'X's upwards ending at (i, j)
        int[][] horiz = new int[n][n];
        int[][] vert = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 'X') {
                    horiz[i][j] = (j == 0) ? 1 : horiz[i][j - 1] + 1;
                    vert[i][j] = (i == 0) ? 1 : vert[i - 1][j] + 1;
                }
            }
        }

        int max_len = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // Maximum possible side length from current bottom-right corner (i, j)
                int mi = Math.min(horiz[i][j], vert[i][j]);

                // Check sizes downwards from mi until we find a valid square or go below max_len
                while (mi > max_len) {
                    // Check top and left edges of the square
                    if (horiz[i - mi + 1][j] >= mi && vert[i][j - mi + 1] >= mi) {
                        max_len = Math.max(max_len, mi);
                        break;
                    }
                    mi--;
                }
            }
        }

        return max_len;
    }
}