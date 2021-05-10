package org.npathai.tictactoe;

public class GameState {


    private final char[][] board;
    private final Player currentPlayer;
    private final Player nextPlayer;
    private final Player winnerPlayer;

    public GameState(char[][] board, Player currentPlayer, Player nextPlayer, Player winnerPlayer) {
        this.board = board;
        this.currentPlayer = currentPlayer;
        this.nextPlayer = nextPlayer;
        this.winnerPlayer = winnerPlayer;
    }

    public Player nextPlayer() {
        return nextPlayer;
    }


    public Player currentPlayer() {
        return currentPlayer;
    }

    public char[][] board() {
        return board;
    }

    public Player winnerPlayer() {
        return winnerPlayer;
    }
}
