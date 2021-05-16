package org.npathai.tictactoe;

public class GameState {


    private final char[][] board;
    private final Player currentPlayer;
    private final Player nextPlayer;
    private final Player winnerPlayer;
    private final boolean isOver;

    public GameState(char[][] board, Player currentPlayer, Player nextPlayer, Player winnerPlayer) {
        this(board, currentPlayer, nextPlayer, winnerPlayer, false);
    }

    public GameState(char[][] board, Player currentPlayer, Player nextPlayer, Player winnerPlayer, boolean isOver) {
        this.board = board;
        this.currentPlayer = currentPlayer;
        this.nextPlayer = nextPlayer;
        this.winnerPlayer = winnerPlayer;
        this.isOver = isOver;
    }

    public Player currentPlayer() {
        return nextPlayer;
    }


    public Player lastPlayer() {
        return currentPlayer;
    }

    public char[][] board() {
        return board;
    }

    public Player winnerPlayer() {
        return winnerPlayer;
    }

    public boolean isOver() {
        return isOver;
    }
}
