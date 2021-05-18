package org.npathai.tictactoe;

import static org.npathai.tictactoe.PlayerMark.O;
import static org.npathai.tictactoe.PlayerMark.X;

public class Game {

    private final Board board;
    private Player playerOne;
    private Player playerTwo;
    private Player lastPlayer;
    private Player currentPlayer;
    private Player winnerPlayer;

    public Game(Board board) {
        this.board = board;
    }

    public void start(PlayerMark playerOneMark) {
        this.playerOne = new Player(1, playerOneMark);
        this.playerTwo = new Player(2, playerOneMark == X ? O: X);
        this.currentPlayer = playerOne;
    }

    private void decideWinner() {
        if (board.hasLineMarked(lastPlayer.mark())) {
            winnerPlayer = lastPlayer;
        }
    }

    public void update(int cellNo) {
        board.update(cellNo, currentPlayer.mark());
        switchPlayer();
        decideWinner();
    }

    private void switchPlayer() {
        lastPlayer = currentPlayer;
        currentPlayer = currentPlayer == playerOne ? playerTwo : playerOne;
    }

    public GameState state() {
        // FIXME game state should not expose original array, it should expose a copy
        return new GameState(board.getCells(), lastPlayer, currentPlayer, winnerPlayer, winnerPlayer != null || board.isFull());
    }
}
