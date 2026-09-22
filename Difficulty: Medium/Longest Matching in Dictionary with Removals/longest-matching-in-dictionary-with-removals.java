import java.util.*;

class Solution {
    public String findLongestWord(String s, List<String> d) {
        // Step 1: Precompute index lists for each character in s
        List<Integer>[] charIndices = new ArrayList[26];
        for (int i = 0; i < 26; i++) {
            charIndices[i] = new ArrayList<>();
        }
        for (int i = 0; i < s.length(); i++) {
            charIndices[s.charAt(i) - 'a'].add(i);
        }

        String bestWord = "";

        // Step 2: Check each word in the dictionary
        for (String word : d) {
            if (isSubsequence(word, charIndices)) {
                if (word.length() > bestWord.length()) {
                    bestWord = word;
                } else if (word.length() == bestWord.length()) {
                    if (word.compareTo(bestWord) < 0) {
                        bestWord = word;
                    }
                }
            }
        }

        return bestWord;
    }

    // Helper method to check if 'word' is a subsequence using binary search
    private boolean isSubsequence(String word, List<Integer>[] charIndices) {
        int currIdx = -1;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            List<Integer> indices = charIndices[ch - 'a'];
            int target = currIdx + 1;

            // Find the first index >= target using binary search
            int pos = binarySearch(indices, target);
            if (pos == -1) {
                return false;
            }
            currIdx = indices.get(pos);
        }
        return true;
    }

    // Custom binary search to find the smallest element >= target
    private int binarySearch(List<Integer> list, int target) {
        int low = 0, high = list.size() - 1;
        int ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (list.get(mid) >= target) {
                ans = mid;
                high = mid - 1; // Look for a smaller valid index on the left
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }
}