package software2;

import java.util.Scanner;

public final class TicTacToe {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Board board = new Board();
        boolean gameIsDone = false;

        while (!gameIsDone) {

            board.print();

            System.out.print(board.currentPlayer()
                    + " player: Enter row and column number:");
            String rowAndCol = input.nextLine();

            int row = Integer.parseInt(rowAndCol.substring(0, 1));
            int col = Integer.parseInt(rowAndCol.substring(2, 3));

            while (!board.setRowCol(row, col)) {
                System.out.print("Incorrect cell: Try again!");
                rowAndCol = input.nextLine();
                row = Integer.parseInt(rowAndCol.substring(0, 1));
                col = Integer.parseInt(rowAndCol.substring(2, 3));
            }

            gameIsDone = board.isWin() || board.isFull();

            if (board.isWin()) {
                board.print();
                System.out.println(board.currentPlayer() + " player wins!");
                System.out.println("Goodbye");
            }

            board.changePlayer();
        }

        input.close();
    }

}
