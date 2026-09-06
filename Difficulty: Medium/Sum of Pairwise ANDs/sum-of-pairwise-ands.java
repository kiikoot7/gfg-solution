class Solution {
    public static long pairAndSum(int[] arr) {
        int n = arr.length;
        long totalSum = 0;

        for (int bit = 0; bit < 31; bit++) {
            long count = 0;
            for (int i = 0; i < n; i++) {
                if ((arr[i] & (1 << bit)) != 0) {
                    count++;
                }
            }

            if (count >= 2) {
                long pairs = (count * (count - 1)) / 2;
                totalSum += pairs * (1L << bit);
            }
        }

        return totalSum;
    }
}