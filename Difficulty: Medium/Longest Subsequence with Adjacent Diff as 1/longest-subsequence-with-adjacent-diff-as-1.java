class Solution {
    public static int longestSubseq(int[] arr) {
        int maxVal = 0;
        for (int x : arr) {
            if (x > maxVal) {
                maxVal = x;
            }
        }

        int[] dp = new int[maxVal + 2];
        int maxLength = 0;

        for (int x : arr) {
            int left = (x > 1) ? dp[x - 1] : 0;
            int right = dp[x + 1];

            dp[x] = Math.max(dp[x], 1 + Math.max(left, right));
            maxLength = Math.max(maxLength, dp[x]);
        }

        return maxLength;
    }
}