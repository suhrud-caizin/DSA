package Recursion.backtracking;

public class SudokuSolver {
    public static boolean solve(int[][] board, int r, int c){

            if(r == board.length){
                printBoard(board);
                return true;
            }

            if(c == board.length) {
                return solve(board,r+1,0);
            }

            if(board[r][c] != 0){
                return solve(board,r, c+1);
            }

        for (int i = 1; i <= 9; i++) {
            if(isSafe(board,r,c,i)){
                board[r][c] = i;
                if(solve(board,r,c+1)){
                    return true;
                }else{
                    board[r][c] = 0;
                }
            }
        }
        return false;
    }

    private static void printBoard(int[][] board) {
        for (int[] row: board){
            for (int col : row){
                System.out.print(col+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static boolean isSafe(int[][] board, int r, int c, int num) {
        for (int j = 0; j < board.length; j++) {
                if(board[r][j] == num){
                    return false;
                }
        }

        for (int i = 0; i < board.length; i++) {
                if(board[i][c] == num) return false;
        }

        int sqrt = (int) Math.sqrt(board.length);
        int rowStart = r - r%sqrt;
        int colStart = c - c% sqrt;

        for (int i = rowStart; i < rowStart+sqrt; i++) {
                for (int j = colStart; j < colStart+sqrt; j++) {
                    if(board[i][j] == num) return false;

            }
        }

        return true;
    }
}
