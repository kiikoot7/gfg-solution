class Solution {
    int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    int pairCount(int x, int y) {
        if (y % x != 0) {
            return 0;
        }

        int k = y / x;
        int count = 0;

        for (int d = 1; d * d <= k; d++) {
            if (k % d == 0) {
                int other = k / d;
                if (gcd(d, other) == 1) {
                    if (d == other) {
                        count += 1;
                    } else {
                        count += 2;
                    }
                }
            }
        }

        return count;
    }
}