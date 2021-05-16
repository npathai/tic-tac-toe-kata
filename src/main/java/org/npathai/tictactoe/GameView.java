package org.npathai.tictactoe;

public class GameView {
    private final Console console;
    private final Game game;

    public GameView(Console console, Game game) {
        this.console = console;
        this.game = game;
    }
    public void displayInstructions() {
        console.write(" 1 | 2 | 3 ");
        console.write("---+---+---");
        console.write(" 4 | 5 | 6 ");
        console.write("---+---+---");
        console.write(" 7 | 8 | 9 ");
    }

    public void askForPlayerMark() {
        console.write("Player one choose your mark [X or O] >> ");
    }

    public void askForNextCellNo() {
        GameState state = game.state();
        Player nextPlayer = state.currentPlayer();
        console.write(String.format("Player %s's turn. "
                + "To place %s on the board, enter cell number >> ", nextPlayer.number(), nextPlayer.mark()));
    }

    public void displayGameState() {
        GameState gameState = game.state();
        char[][] board = gameState.board();

        printRow(board[0]);
        console.write("---+---+---");
        printRow(board[1]);
        console.write("---+---+---");
        printRow(board[2]);

        // FIXME How can we avoid adding this logic to view?
        if (gameState.isOver()) {
            if (gameState.winnerPlayer() != null) {
                console.write(String.format("Player %s is the winner.", gameState.winnerPlayer().number()));
            } else {
                console.write("No winner. Game is over.");
            }
        }
    }

    private void printRow(char[] boardRow) {
        console.write(String.format(" %s | %s | %s ", boardRow[0], boardRow[1], boardRow[2]));
    }
}
