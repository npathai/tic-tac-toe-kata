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

    // Move game loop to Game class
    // Create Player abstraction which will return the move to be made, opens door for future abstractions
    // Can we experiment with listener mechanism, like GameStateChanged. But will have to think about user input getting
    // Imagine how would the design be if game was being played online and game state had to be maintained without blocking execution thread
    // Support for different types of players, StaticStrategic and AI based using Player abstraction
    // Play with UI if possible
    public void start() {
        gameView.displayInstructions();

        gameView.askForPlayerMark();

        String playerOneMarkChoice = console.read();

        PlayerMark playerOneMark = PlayerMark.from(playerOneMarkChoice);

        game.start(playerOneMark);
    }

    public static void main(String[] args) {
        Console console = new Console();
        GameView gameView = new GameView(console);
        Game game = new Game(new Board(), gameView, console);
        TicTacToe ticTacToe = new TicTacToe(console, game, gameView);
        ticTacToe.start();
    }
}
