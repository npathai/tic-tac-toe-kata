package org.npathai.tictactoe;

public class TicTacToe {

    private final Console console;
    private final Game game;
    private final GameView gameView;

    TicTacToe(Console console, Game game, GameView gameView) {
        this.console = console;
        this.game = game;
        this.gameView = gameView;
    }

    public void start() {
        gameView.displayInstructions();

        gameView.askForPlayerMark();

        String playerOneMark = console.read();

        game.start(playerOneMark);

        gameView.displayGameState();
        // ask for mark for player 1 - X or O

        // Game starts and shows current board status till the game is over
        while (!game.isOver()) {
//            console.write("Player " + state.nextPlayer().number() + "'s turn. " +
//                    "To place " + state.nextPlayer().mark() + " on the board, enter cell number >> ");
            gameView.askForNextCellNo();
            int chosenCell = Integer.parseInt(console.read());
            game.update(chosenCell);
            gameView.displayGameState();
        }

        // This should also be done by Game view
//        console.write(String.format("Player %s is the winner.", state.winnerPlayer().number()));
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
        GameView gameView = new GameView(console, game);
        TicTacToe ticTacToe = new TicTacToe(console, game, gameView);
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
