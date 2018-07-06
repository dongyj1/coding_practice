package solutions;

import java.util.HashMap;

/**
 * Created by dyj on 4/16/18.
 *
 * 。一个矩阵中的元素和它周围八个元素是相邻的。如果两个元素相邻切相等就认为他们是一类。
 输入m*n矩阵A，输出分类后的m*n矩阵B。int -> char
 */
public class MatrixConversion {

    public static char[][] convertMatrix(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return null;
        }
        char[][] res = new char[matrix.length][matrix[0].length];
        HashMap<Integer, Character> map = new HashMap<>();
        char head = 'a';
        int[] dx = new int[]{-1, 0, 1};
        int[] dy = new int[]{-1, 0, 1};
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        if (k == 1 && l == 1) {
                            continue;
                        }
                        int xx = dx[k] + i;
                        int yy = dy[l] + j;
                        if (xx < matrix.length && xx >= 0 && yy < matrix[0].length && yy >= 0
                                && matrix[xx][yy] == matrix[i][j] && res[xx][yy] != 0) {
                            res[i][j] = res[xx][yy];
                            continue;
                        }
                    }
                }
                if (res[i][j] == 0) {
                    res[i][j] = head++;
                }
            }
        }
        for (int i = matrix.length - 1; i >= 0; i--) {
            for (int j = matrix[0].length - 1; j >= 0; j--) {
                for (int k = 2; k >= 0; k--) {
                    for (int l = 2; l >= 0; l--) {
                        if (k == 1 && l == 1) {
                            continue;
                        }
                        int xx = dx[k] + i;
                        int yy = dy[l] + j;
                        if (xx < matrix.length && xx >= 0 && yy < matrix[0].length && yy >= 0
                                && matrix[xx][yy] == matrix[i][j]) {
                            res[i][j] = res[xx][yy];
                            continue;
                        }
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] test = new int[4][4];
        test[0] = new int[]{1, 2, 3, 3};
        test[1] = new int[]{5, 6, 7, 3};
        test[2] = new int[]{1, 2, 0, 3};
        test[3] = new int[]{3, 3, 3, 5};
        char[][] res = MatrixConversion.convertMatrix(test);
        System.out.println(res[0]);
        System.out.println(res[1]);
        System.out.println(res[2]);
        System.out.println(res[3]);
    }
}
