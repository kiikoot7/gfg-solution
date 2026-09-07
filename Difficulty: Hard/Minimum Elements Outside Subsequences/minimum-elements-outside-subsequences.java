import java.util.Arrays;

class Solution {
    private static int n;
    private static int[] arr;
    private static int[][][] dp;

    private static int solve(int idx, int incLast, int decLast) {
        if (idx == n) {
            return 0;
        }

        int incIdx = incLast + 1;
        int decIdx = decLast + 1;

        if (dp[idx][incIdx][decIdx] != -1) {
            return dp[idx][incIdx][decIdx];
        }

        // Choice 1: Skip the current element (adds 1 to unselected count)
        int ans = 1 + solve(idx + 1, incLast, decLast);

        // Choice 2: Include in the strictly increasing subsequence
        if (incLast == -1 || arr[idx] > incLast) {
            ans = Math.min(ans, solve(idx + 1, arr[idx], decLast));
        }

        // Choice 3: Include in the strictly decreasing subsequence
        if (decLast == -1 || arr[idx] < decLast) {
            ans = Math.min(ans, solve(idx + 1, incLast, arr[idx]));
        }

        return dp[idx][incIdx][decIdx] = ans;
    }

    public int minCount(int[] a) {
        arr = a;
        n = arr.length;

        dp = new int[n][102][102];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 102; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        return solve(0, -1, -1);
    }
}