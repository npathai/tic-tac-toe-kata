package org.npathai.tictactoe;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameViewTest {

    @Mock
    Game mockGame;

    @Mock
    Console mockConsole;

    @InjectMocks
    GameView gameView;

    @Test
    public void displaysMessageToAskForPlayersMark() {
        gameView.askForPlayerMark();

        verify(mockConsole).write("Player one choose your mark [X or O] >> ");
    }

    @Test
    public void displaysInstructions() {
        gameView.displayInstructions();

        InOrder inOrder = Mockito.inOrder(mockConsole);
        inOrder.verify(mockConsole).write(" 1 | 2 | 3 ");
        inOrder.verify(mockConsole).write("---+---+---");
        inOrder.verify(mockConsole).write(" 4 | 5 | 6 ");
        inOrder.verify(mockConsole).write("---+---+---");
        inOrder.verify(mockConsole).write(" 7 | 8 | 9 ");
    }

    @Test
    public void displaysMessageToAskForNextCellNo() {
        Player playerOne = new Player(1, "X");
        Player playerTwo = new Player(2, "O");

        GameState gameState = new GameState(new char[][]{
                {'X', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        }, playerOne, playerTwo, playerOne);

        when(mockGame.state()).thenReturn(gameState);

        gameView.askForNextCellNo();

        verify(mockConsole).write(String.format("Player %s's turn. "
                + "To place %s on the board, enter cell number >> ", 2, playerTwo.mark()));
    }

    @Test
    public void displaysBoardOnlyWhenGameIsNotOver() {
        Player playerOne = new Player(1, "X");
        Player playerTwo = new Player(2, "O");

        GameState gameState = new GameState(new char[][]{
                {'X', ' ', ' '},
                {' ', 'O', ' '},
                {'X', ' ', 'O'}
        }, playerOne, playerTwo, playerOne);
        when(mockGame.state()).thenReturn(gameState);

        gameView.displayGameState();

        InOrder inOrder = Mockito.inOrder(mockGame, mockConsole);
        inOrder.verify(mockGame).state();
        inOrder.verify(mockConsole).write(" X |   |   ");
        inOrder.verify(mockConsole).write("---+---+---");
        inOrder.verify(mockConsole).write("   | O |   ");
        inOrder.verify(mockConsole).write("---+---+---");
        inOrder.verify(mockConsole).write(" X |   | O ");
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void displaysBoardWithWinnerWhenGameIsOverWithAWinner() {
        Player playerOne = new Player(1, "X");
        Player playerTwo = new Player(2, "O");


        GameState gameState = new GameState(new char[][]{
                {'X', ' ', ' '},
                {' ', 'O', ' '},
                {'X', ' ', 'O'}
        }, playerOne, playerTwo, playerOne, true);
        when(mockGame.state()).thenReturn(gameState);

        gameView.displayGameState();

        InOrder inOrder = Mockito.inOrder(mockGame, mockConsole);
        inOrder.verify(mockGame).state();
        inOrder.verify(mockConsole).write(" X |   |   ");
        inOrder.verify(mockConsole).write("---+---+---");
        inOrder.verify(mockConsole).write("   | O |   ");
        inOrder.verify(mockConsole).write("---+---+---");
        inOrder.verify(mockConsole).write(" X |   | O ");
        verify(mockConsole).write(String.format("Player %s is the winner.", playerOne.number()));

    }

    @Test
    public void displaysBoardWithoutWinnerWhenGameIsOverNoWinner() {
        Player playerOne = new Player(1, "X");
        Player playerTwo = new Player(2, "O");


        GameState gameState = new GameState(new char[][]{
                {'X', ' ', ' '},
                {' ', 'O', ' '},
                {'X', ' ', 'O'}
        }, playerOne, playerTwo, null, true);
        when(mockGame.state()).thenReturn(gameState);

        gameView.displayGameState();

        InOrder inOrder = Mockito.inOrder(mockGame, mockConsole);
        inOrder.verify(mockGame).state();
        inOrder.verify(mockConsole).write(" X |   |   ");
        inOrder.verify(mockConsole).write("---+---+---");
        inOrder.verify(mockConsole).write("   | O |   ");
        inOrder.verify(mockConsole).write("---+---+---");
        inOrder.verify(mockConsole).write(" X |   | O ");
        verify(mockConsole).write("No winner. Game is over.");

    }
}