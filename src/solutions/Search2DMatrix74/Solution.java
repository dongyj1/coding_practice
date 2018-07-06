package solutions.Search2DMatrix74;

/**
 * Created by dyj on 5/7/18.
 */
public class Solution {

    public boolean findTargetInMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        // locate row
        int lo = 0, hi = m - 1;
        while (lo + 1 < hi) {
            int mid = (hi - lo) / 2 + lo;
            if (matrix[mid][0] == target) {
                return true;
            }
            if (matrix[mid][0] < target) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
        int row = lo;
        if (matrix[hi][0] < target) {
            row = hi;
        }

        lo = 0;
        hi = n - 1;
        while (lo + 1 < hi) {
            int mid = (hi - lo) / 2 + lo;
            if (matrix[row][mid] == target) {
                return true;
            }
            if (matrix[row][mid] < target) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
        if (matrix[row][lo] == target || matrix[row][hi] == target) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,   3,  5,  7}, {10, 11, 16, 20}, {23, 30, 34, 50}};
        System.out.println(new Solution().findTargetInMatrix(matrix, 50));

    }
}
