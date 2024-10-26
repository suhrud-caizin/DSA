package Recursion.backtracking;

public class Backtracking {

    public static  int nQueens(boolean[][] board,int row){
        if(row == board.length){
            printBoard(board);
            System.out.println();
            return 1;
        }
        int cnt = 0;
        for (int c = 0; c < board[0].length; c++) {
                if(isSafe(board,row,c)){
                    board[row][c] = true;
                    cnt += nQueens(board, row+1);
                    board[row][c] = false;
                }
        }
        return cnt;
    }

    public static int nKnights(boolean[][]board, int r, int c, int knights){
        if(knights == 0){
            printKnightsBoard(board);
            return 1;
        }

        int cnt = 0;

        if(r == board.length) return 0;
        if(c == board.length){
            return nKnights(board,r+1,0,knights);
        }

        if(isKnightSafe(board,r,c)){
            board[r][c] = true;
            cnt += nKnights(board,r,c+1,knights-1);
            board[r][c] = false;
        }

        cnt += nKnights(board,r,c+1,knights);

        return cnt;
    }

    private static boolean isKnightSafe(boolean[][] board, int r, int c) {
        if(isValid(board,r-2,c+1)){
            if(board[r-2][c+1]) return false;
        }

        if(isValid(board,r-2,c-1)){
            if(board[r-2][c-1]) return false;
        }
        if(isValid(board,r-1,c-2)){
            if(board[r-1][c-2]) return false;
        }
        if(isValid(board,r-1,c+2)){
            if(board[r-1][c+2]) return false;
        }
        return true;
    }

    private static boolean isValid(boolean[][] board, int r, int c){
        if(r>=0 && r< board.length && c >= 0 && c< board.length) return true;
        return  false;
    }

    private static void printKnightsBoard(boolean[][] board) {
        for (boolean[] row:board){
            for(boolean col: row){
                if(col){
                    System.out.print("K ");
                }else{
                    System.out.print("X ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static boolean isSafe(boolean[][] board, int r, int c) {
        // check for column in previous rows
        for (int i = 0; i < r; i++) {
            if(board[i][c]) return false;
        }

        // check for column in previous rows
        for (int i = 0; i < c; i++) {
            if(board[r][i]) return false;
        }

        int min = Integer.min(r,c);

        // checking left diagonal
        for (int i = 1; i <= min; i++) {
            if(board[r-i][c-i]) return false;
        }

        // checking right diagonal
        min = Integer.min(r, board[0].length-c-1);
        for (int i = 1; i <= min; i++) {
                if(board[r-i][c+i]) return false;
        }

        return true;
    }

    private static void printBoard(boolean[][] board) {
        for (boolean[] row:board){
            for(boolean col: row){
                if(col){
                    System.out.print("Q ");
                }else{
                    System.out.print("X ");
                }
            }
            System.out.println();
        }
    }
}
