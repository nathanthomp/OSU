package software2;

public class Board {

    private char[][] board;
    private char currentPlayer;

    public Board() {
        this.board = new char[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                this.board[row][col] = '-';
            }
        }
        this.currentPlayer = 'x';
    }

    public void print() {
        System.out.println("Current Board");
        System.out.println("-------------");
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                System.out.print(this.board[row][col] + " ");
            }
            System.out.println();
        }
    }

    public boolean isFull() {
        boolean result = true;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (this.board[row][col] == '-') {
                    result = false;
                }
            }
        }
        return result;
    }

    public boolean isWin() {
        return this.checkRows() || this.checkColumns() || this.checkDiagonals();
    }

    private boolean checkRows() {
        boolean result = false;

        for (int row = 0; row < 3; row++) {
            if (this.board[row][0] == this.currentPlayer
                    && this.board[row][1] == this.currentPlayer
                    && this.board[row][2] == this.currentPlayer) {
                result = true;
                break;
            }
        }

        return result;
    }

    private boolean checkColumns() {
        boolean result = false;

        for (int col = 0; col < 3; col++) {
            if (this.board[0][col] == this.currentPlayer
                    && this.board[1][col] == this.currentPlayer
                    && this.board[2][col] == this.currentPlayer) {
                result = true;
                break;
            }
        }

        return result;
    }

    private boolean checkDiagonals() {
        boolean result = false;
        if (this.board[0][0] == this.currentPlayer
                && this.board[1][1] == this.currentPlayer
                && this.board[2][2] == this.currentPlayer) {
            result = true;
        } else if (this.board[0][2] == this.currentPlayer
                && this.board[1][1] == this.currentPlayer
                && this.board[2][0] == this.currentPlayer) {
            result = true;
        }

        return result;
    }

    public void changePlayer() {
        if (this.currentPlayer == 'x') {
            this.currentPlayer = 'o';
        } else {
            this.currentPlayer = 'x';
        }
    }

    public boolean setRowCol(int row, int col) {
        row--;
        col--;
        boolean result = true;
        if (row < 0 || col < 0) {
            result = false;
        }
        if (row > 2 || col > 2) {
            result = false;
        }

        if (result) {
            if (!(this.board[row][col] == '-')) {
                result = false;
            }
        }

        if (result) {
            this.board[row][col] = this.currentPlayer;
        }

        return result;
    }

    public char currentPlayer() {
        return this.currentPlayer;
    }
}
