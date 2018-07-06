//package solutions;
//
///**
// * Created by dyj on 4/10/18.
// */
//
///**
// * Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in diagonal order as shown in the below image.
// * Input:
// [
// [ 1, 2, 3, q],
// [ 4, 5, 6, q],
// [ 7, 8, 9, e]
// ]
// Output:  [1,2,4,7,5,3,6,8,9]
// Explanation:
// */
//public class DiagonalTraverse498 {
//
//    public int[] findDiagonalOrder(int[][] matrix) {
//        if (matrix.length == 0 || matrix[0].length == 0) {
//            return null;
//        }
//        int M = matrix.length, N = matrix[0].length;
//        int count = 0;
//        int[] res = new int[M * N];
//        int[] dx = new int[]{0, 1, 0, -1};
//        int[] dy = new int[]{1, 0, -1, 0};
//        int x = 0, y = 0, startIndex = 0;
//        while (startIndex < M * N) {
//            res[startIndex++] = matrix[x][y];
//            if (x == 0) {
//                int[] temp = null;
//                if (y < N - 1) {
//                    temp = addDiagonal(matrix, res, startIndex, x, y + 1, true);
//                } else {
//                    temp = addDiagonal(matrix, res, startIndex, x + 1, y, true);
//                }
//                x = temp[0];
//                y = temp[1];
//                startIndex = temp[2];
//            } else if (y == 0) {
//                int[] temp = null;
//                if (x < M - 1) {
//                    temp = addDiagonal(matrix, res, startIndex, x + 1, y, false);
//                } else {
//                    temp = addDiagonal(matrix, res, startIndex, x, y + 1, false);
//                }
//                x = temp[0];
//                y = temp[1];
//                startIndex = temp[2];
//            } else if (x == M - 1) {
//                int[] temp = null;
//                if (x < M - 1) {
//                    temp = addDiagonal(matrix, res, startIndex, x, y, false);
//                } else {
//                    temp = addDiagonal(matrix, res, startIndex, x, y, false);
//                }
//                x = temp[0];
//                y = temp[1];
//                startIndex = temp[2];
//            }
//        }
//    }
//    private int[] addDiagonal(int[][] matrix, int[] res, int startIndex, int x, int y, boolean isUp) {
//        int[] lastPos = new int[3];
//        if (isUp) {
//            while (x < matrix.length && y < matrix[0].length) {
//                res[startIndex++] = matrix[x++][y++];
//            }
//            lastPos[0] = x - 1;
//            lastPos[1] = y - 1;
//            lastPos[2] = startIndex;
//        } else {
//            while (x >=0 && y >= 0) {
//                res[startIndex++] = matrix[x--][y--];
//            }
//            lastPos[0] = x + 1;
//            lastPos[1] = y + 1;
//            lastPos[2] = startIndex;
//        }
//        return res;
//    }
//}
