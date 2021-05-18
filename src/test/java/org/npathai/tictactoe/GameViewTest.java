package org.npathai.tictactoe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.npathai.tictactoe.PlayerMark.O;
import static org.npathai.tictactoe.PlayerMark.X;

@ExtendWith(MockitoExtension.class)
class GameViewTest {

    @Mock
    Game mockGame;

    @Mock
    Console mockConsole;

    @InjectMocks
    GameView gameView;
    private Player playerOne;
    private Player playerTwo;

    @BeforeEach
    void setUp() {
        playerOne = new Player(1, X);
        playerTwo = new Player(2, O);
    }

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