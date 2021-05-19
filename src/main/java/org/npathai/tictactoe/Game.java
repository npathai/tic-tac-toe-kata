package org.npathai.tictactoe;

import static org.npathai.tictactoe.PlayerMark.O;
import static org.npathai.tictactoe.PlayerMark.X;

public class Game {

    private final Board board;
    private final GameView gameView;
    private final Console console;
    private Player playerOne;
    private Player playerTwo;
    private Player lastPlayer;
    private Player currentPlayer;
    private Player winnerPlayer;

    public Game(Board board, GameView gameView, Console console) {
        this.board = board;
        this.gameView = gameView;
        this.console = console;
    }

    public void start(PlayerMark playerOneMark) {
        this.playerOne = new Player(1, playerOneMark);
        this.playerTwo = new Player(2, playerOneMark == X ? O: X);
        this.currentPlayer = playerOne;

        gameView.displayGameState(state());

        // Game starts and shows current board status till the game is over
        while (!isOver()) {
            gameView.askForNextCellNo(currentPlayer);
            int chosenCell = Integer.parseInt(console.read());
            update(chosenCell);
            gameView.displayGameState(state());
        }
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
        return new GameState(board.getCells(), lastPlayer, currentPlayer, winnerPlayer, isOver());
    }

    private boolean isOver() {
        return winnerPlayer != null || board.isFull();
    }
}
