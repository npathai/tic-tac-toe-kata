package org.npathai.tictactoe;

public class TicTacToe {

    private final Console console;
    private final Game game;

    TicTacToe(Console console, Game game) {
        this.console = console;
        this.game = game;
    }

    public void start() {
        displayInstructions();

        askMarkFromPlayer();

        String playerOneMark = console.read();

        game.start(playerOneMark);
        // ask for mark for player 1 - X or O

        GameState state = game.state();
        // Game starts and shows current board status till the game is over
        while (!game.isOver()) {
            console.write("Player " + state.nextPlayer().number() + "'s turn. " +
                    "To place " + state.nextPlayer().mark() + " on the board, enter cell number >> ");
            int chosenCell = Integer.parseInt(console.read());
            state = game.update(chosenCell);
            displayBoard(state.board());
        }

        console.write(String.format("Player %s is the winner.", state.winnerPlayer().number()));
        // Declare the winner -- current player is winner or we have to maintain state ???
    }

    private void displayInstructions() {
        displayBoard(new char[][] {
                {'1', '2', '3'},
                {'4', '5', '6'},
                {'7', '8','9'}
        });
    }

    private void askMarkFromPlayer() {
        console.write("Player one choose your mark [X or O] >> ");

    }

    private void displayBoard(char[][] board) {
        console.write(String.format(" %s | %s | %s ", board[0][0], board[0][1], board[0][2]));
        console.write("---+---+---");
        console.write(String.format(" %s | %s | %s ", board[1][0], board[1][1], board[1][2]));
        console.write("---+---+---");
        console.write(String.format(" %s | %s | %s ", board[2][0], board[2][1], board[2][2]));
        console.write("---+---+---");
    }

    public static void main(String[] args) {
        Console console = new Console();
        Game game = new Game();
        TicTacToe ticTacToe = new TicTacToe(console, game);
        ticTacToe.start();
    }

    //Move
     //know upfront
        // position to expect(1-9)
    // Read moves from player and store it so that game can fetch input from it.

    //Game
        // Board
            // hasFinishedLine(currentPlayer)
        // Current player



}
