#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
    long long maxFruits(vector<int>& arr, int m) {
        int n = arr.size();
        if (m >= n) {
            long long total = 0;
            for (int x : arr) total += x;
            return total;
        }

        vector<long long> extended(2 * n);
        for (int i = 0; i < n; ++i) {
            extended[i] = arr[i];
            extended[i + n] = arr[i];
        }

        long long current_sum = 0;
        for (int i = 0; i < m; ++i) {
            current_sum += extended[i];
        }

        long long max_sum = current_sum;

        for (int i = m; i < n + m - 1; ++i) {
            current_sum += extended[i] - extended[i - m];
            max_sum = max(max_sum, current_sum);
        }

        return max_sum;
    }
};