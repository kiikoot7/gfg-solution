import java.util.Arrays;

class Solution {
    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public int sameMod(int[] arr) {
        int n = arr.length;
        Arrays.sort(arr);

        int g = 0;
        for (int i = 1; i < n; i++) {
            g = gcd(g, arr[i] - arr[0]);
        }

        if (g == 0) {
            return -1; // All elements are equal, infinitely many k
        }

        int count = 0;
        for (int i = 1; i * i <= g; i++) {
            if (g % i == 0) {
                count++;
                if (i * i != g) {
                    count++;
                }
            }
        }
        return count;
    }
}