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

        // Game starts and shows current board status till the game is over
        while (!game.isOver()) {
            gameView.askForNextCellNo();
            int chosenCell = Integer.parseInt(console.read());
            game.update(chosenCell);
            gameView.displayGameState();
        }
    }

    public static void main(String[] args) {
        Console console = new Console();
        Game game = new Game(new Board());
        GameView gameView = new GameView(console, game);
        TicTacToe ticTacToe = new TicTacToe(console, game, gameView);
        ticTacToe.start();
    }
}
