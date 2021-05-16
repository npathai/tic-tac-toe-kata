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
public class TicTacToeTest {

    @Mock
    private Console mockConsole;

    @Mock
    private Game game;

    @Mock
    private GameView gameView;

    @InjectMocks
    TicTacToe ticTacToe;

    @Test
    public void displaysInstructionOnStartingTheGame() {
        when(game.state()).thenReturn(gameOverState());
        when(mockConsole.read()).thenReturn("X");

        ticTacToe.start();

        verify(gameView).displayInstructions();
    }

    private GameState gameOverState() {
        return new GameState(null, null, null, null, true);
    }

    @Test
    public void whileGameIsNotOverAsksNextPlayerForMoveAndUpdateTheGame() {
        String playerOneMark = "X";
        when(mockConsole.read()).thenReturn(playerOneMark, "1", "2");
        when(game.state()).thenReturn(notOverState(), notOverState(), gameOverState());

        ticTacToe.start();

        InOrder inOrder = Mockito.inOrder(game, gameView, mockConsole);
        inOrder.verify(gameView).displayInstructions();
        inOrder.verify(gameView).askForPlayerMark();
        inOrder.verify(mockConsole).read();
        inOrder.verify(game).start(playerOneMark.charAt(0));
        inOrder.verify(gameView).displayGameState();
        inOrder.verify(game).state();
        inOrder.verify(gameView).askForNextCellNo();
        inOrder.verify(mockConsole).read();
        inOrder.verify(game).update(1);
        inOrder.verify(gameView).displayGameState();
        inOrder.verify(game).state();
        inOrder.verify(gameView).askForNextCellNo();
        inOrder.verify(mockConsole).read();
        inOrder.verify(game).update(2);
        inOrder.verify(gameView).displayGameState();
        inOrder.verify(game).state();
    }

    private GameState notOverState() {
        return new GameState(null, null, null, null, false);
    }
}
