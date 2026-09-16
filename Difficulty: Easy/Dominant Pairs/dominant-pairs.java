import java.util.Arrays;

class Solution {
    public static int dominantPairs(int[] arr) {
        int n = arr.length;
        int mid = n / 2;

        // Extract and sort the first half and the second half
        int[] firstHalf = Arrays.copyOfRange(arr, 0, mid);
        int[] secondHalf = Arrays.copyOfRange(arr, mid, n);

        Arrays.sort(firstHalf);
        Arrays.sort(secondHalf);

        int count = 0;
        int j = 0;

        // Two-pointer approach
        for (int i = 0; i < mid; i++) {
            // Find how many elements in secondHalf satisfy firstHalf[i] >= 5 * secondHalf[j]
            while (j < secondHalf.length && firstHalf[i] >= 5 * secondHalf[j]) {
                j++;
            }
            // All elements from index 0 to j-1 in secondHalf are valid for the current firstHalf[i]
            count += j;
        }

        return count;
    }
}