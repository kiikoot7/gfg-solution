class Solution {
    public ArrayList<ArrayList<Integer>> searchWord(char[][] mat, String word) {
        int n = mat.length;
        int m = mat[0].length;
        int len = word.length();

        int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == word.charAt(0)) {
                    boolean found = false;
                    for (int d = 0; d < 8; d++) {
                        int r = i, c = j;
                        int k;
                        for (k = 0; k < len; k++) {
                            if (r < 0 || r >= n || c < 0 || c >= m || mat[r][c] != word.charAt(k)) {
                                break;
                            }
                            r += dr[d];
                            c += dc[d];
                        }
                        if (k == len) {
                            found = true;
                            break;
                        }
                    }
                    if (found) {
                        ArrayList<Integer> coord = new ArrayList<>();
                        coord.add(i);
                        coord.add(j);
                        result.add(coord);
                    }
                }
            }
        }

        return result;
    }
}