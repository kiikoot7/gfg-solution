class Solution {
    public int solve(int n, String s) {
        return countUnoccupiedComputers(n, s);
    }

    public int countUnoccupiedComputers(int n, String s) {
        // state[i] -> 0: not arrived, 1: seated, 2: rejected
        int[] state = new int[26];
        int occupied = 0;
        int rejectedCount = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            int idx = ch - 'A';

            if (state[idx] == 0) {
                // Arrival event
                if (occupied < n) {
                    occupied++;
                    state[idx] = 1; // Seated
                } else {
                    rejectedCount++;
                    state[idx] = 2; // Rejected
                }
            } else if (state[idx] == 1) {
                // Departure event for a seated customer
                occupied--;
                state[idx] = 3; // Finished
            }
        }

        return rejectedCount;
    }
}