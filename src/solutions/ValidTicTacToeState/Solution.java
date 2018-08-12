package solutions.ValidTicTacToeState;

/*

Example 1:
Input: board = ["O  ", "   ", "   "]
Output: false
Explanation: The first player always plays "X".

Example 2:
Input: board = ["XOX", " X ", "   "]
Output: false
Explanation: Players take turns making moves.

Example 3:
Input: board = ["XXX", "   ", "OOO"]
Output: false

Example 4:
Input: board = ["XOX", "O O", "XOX"]
Output: true

*/


class Solution {
    public boolean validTicTacToe(String[] board) {
        int turn = 0; // 1: X moved, 0: O moved
        boolean xwin = false, owin = false;
        int[] rows = new int[3], cols = new int[3]; 
        int diag = 0, antidiag = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i].charAt(j) == 'X') {
                    turn++;
                    rows[i]++;
                    cols[j]++;
                    if (i == j) diag++;
                    if (i + j == 2) antidiag++;
                } else if (board[i].charAt(j) == 'O') {
                    turn--;
                    rows[i]--;
                    cols[j]--;
                    if (i == j) diag--;
                    if (i + j == 2) antidiag--;
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            xwin |= (rows[i] == 3 || cols[i] == 3);
            owin |= (rows[i] == -3 || cols[i] == -3);
        }
        xwin |= (diag == 3 || antidiag == 3);
        owin |= (diag == -3 || antidiag == -3);

        if (xwin && turn == 0 || owin && turn == 1) {
            return false;
        }
        return (turn == 0 || turn == 1) && (!owin || !xwin);
    }

    public static void main(String[] arg) {
        String[] test = new String[]{"XO ","XO ","XO "};
        new Solution().validTicTacToe(test);
    }
}