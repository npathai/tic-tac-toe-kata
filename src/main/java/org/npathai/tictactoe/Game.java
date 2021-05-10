package org.npathai.tictactoe;

public class Game {

    private Player playerOne;
    private Player playerTwo;
    private Player lastPlayer;
    private Player currentPlayer;
    private Player winnerPlayer;

    private char[][] board;

    public void start(String playerOneMark) {
        this.playerOne = new Player(1, playerOneMark);
        this.playerTwo = new Player(2, playerOneMark.equals("O") ? "X": "O");
        this.currentPlayer = playerOne;

        this.board = new char[][] {
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };
    }

    public boolean isOver() {
        return winnerPlayer != null;
    }

    private void decideWinner() {
        for (int row = 0; row < board.length; row++) {
            char[] boardRow = board[row];
            if (boardRow[0] == lastPlayer.mark().charAt(0)
                    && boardRow[0] == boardRow[1]
                    && boardRow[0] == boardRow[2]) {
                winnerPlayer = lastPlayer;
            }
        }

        boolean isColumnMarked;
        for (int col = 0; col < board.length; col++) {
            isColumnMarked = true;
            for (int row = 0; row < board.length; row++) {
                if (board[row][col] != lastPlayer.mark().charAt(0)) {
                    isColumnMarked = false;
                    break;
                }
            }

            if (isColumnMarked) {
                winnerPlayer = lastPlayer;
                break;
            }
        }
    }

    public void update(int cellNo) {
        cellNo--;
        int row = cellNo / 3;
        int col = cellNo % 3;
        board[row][col] = currentPlayer.mark().charAt(0);
        switchPlayer();
        decideWinner();
    }

    private void switchPlayer() {
        lastPlayer = currentPlayer;
        currentPlayer = currentPlayer == playerOne ? playerTwo : playerOne;
    }

    public GameState state() {
        // FIXME game state should not expose original array, it should expose a copy
        return new GameState(board, lastPlayer, currentPlayer, winnerPlayer);
    }
}
