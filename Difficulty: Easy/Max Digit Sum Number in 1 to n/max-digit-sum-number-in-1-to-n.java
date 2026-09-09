class Solution {
    // Function to calculate the sum of digits of a number
    static int digitSum(int x) {
        int sum = 0;
        while (x > 0) {
            sum += x % 10;
            x /= 10;
        }
        return sum;
    }

    static int findMax(int n) {
        int ans = n;
        int maxSum = digitSum(n);

        int x = n;
        int b = 1;

        // Generate potential candidates by reducing prefix and filling right with 9s
        while (x > 0) {
            int cur = (x - 1) * b + (b - 1);

            if (cur > 0) {
                int sum = digitSum(cur);
                // Update if we find a strictly greater sum, or a tie with a larger number
                if (sum > maxSum || (sum == maxSum && cur > ans)) {
                    maxSum = sum;
                    ans = cur;
                }
            }

            x /= 10;
            b *= 10;
        }

        return ans;
    }
}