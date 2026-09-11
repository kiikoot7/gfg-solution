import java.util.Arrays;

class Solution {
    public long maxProduct(int[] arr, int k) {
        int n = arr.length;
        // dp[idx][kLeft] stores {maxProduct, minProduct} choosing kLeft elements from index idx onwards.
        long[][][] memo = new long[n + 1][k + 1][2];
        for (long[][] matrix : memo) {
            for (long[] row : matrix) {
                Arrays.fill(row, -2); // -2 represents unvisited state
            }
        }

        long[] result = solve(arr, 0, k, memo);
        return result[0];
    }

    private long[] solve(int[] arr, int idx, int kLeft, long[][][] memo) {
        if (kLeft == 0) {
            return new long[]{1, 1}; // Product of 0 elements is 1
        }
        if (idx == arr.length || arr.length - idx < kLeft) {
            return new long[]{Long.MIN_VALUE / 2, Long.MAX_VALUE / 2}; // Invalid state
        }

        if (memo[idx][kLeft][0] != -2) {
            return memo[idx][kLeft];
        }

        // Option 1: Exclude the current element
        long[] exclude = solve(arr, idx + 1, kLeft, memo);

        // Option 2: Include the current element
        long[] includeSub = solve(arr, idx + 1, kLeft - 1, memo);
        long incMax = Long.MIN_VALUE / 2;
        long incMin = Long.MAX_VALUE / 2;

        if (includeSub[0] != Long.MIN_VALUE / 2) {
            long p1 = includeSub[0] * arr[idx];
            long p2 = includeSub[1] * arr[idx];
            incMax = Math.max(p1, p2);
            incMin = Math.min(p1, p2);
        }

        long maxP = Math.max(exclude[0], incMax);
        long minP = Math.min(exclude[1], incMin);

        return memo[idx][kLeft] = new long[]{maxP, minP};
    }
}