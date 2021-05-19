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
}
