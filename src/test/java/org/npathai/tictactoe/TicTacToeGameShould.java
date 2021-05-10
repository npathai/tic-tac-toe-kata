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
public class TicTacToeGameShould {

    @Mock
    private Console mockConsole;

    @Mock
    private Game game;

    @InjectMocks
    TicTacToe ticTacToe;

    @Test
    public void displaysInstructionOnStartingTheGame() {
        when(game.isOver()).thenReturn(true);
        when(game.state()).thenReturn(new GameState(null, null, null,
                new Player(1, "X")));

        ticTacToe.start();

        InOrder inOrder = Mockito.inOrder(mockConsole);
        inOrder.verify(mockConsole).write(" 1 | 2 | 3 ");
        inOrder.verify(mockConsole).write("---+---+---");
        inOrder.verify(mockConsole).write(" 4 | 5 | 6 ");
        inOrder.verify(mockConsole).write("---+---+---");
        inOrder.verify(mockConsole).write(" 7 | 8 | 9 ");
    }

    @Test
    public void whileGameIsNotOverAsksNextPlayerForMoveAndUpdateTheGame() {
        String playerOneMark = "X";
        String playerTwoMark = "O";

        when(mockConsole.read()).thenReturn(playerOneMark, "1");
        when(game.isOver()).thenReturn(false, true);
        Player playerOne = new Player(1, playerOneMark);
        Player playerTwo = new Player(2, playerTwoMark);
        when(game.state()).thenReturn(new GameState(new char[][] {
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        },  null, playerOne, null));

        when(game.update(1)).thenReturn(new GameState(new char[][] {
                {'X', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        }, playerOne, playerTwo, playerOne));

        ticTacToe.start();

        verify(mockConsole).write("Player one choose your mark [X or O] >> ");
        verify(game).start(playerOneMark);
        verify(mockConsole).write(String.format("Player %s's turn. "
                + "To place %s on the board, enter cell number >> ", 1, playerOneMark));

        InOrder inOrder = Mockito.inOrder(mockConsole);
        inOrder.verify(mockConsole).write(" X |   |   ");
        inOrder.verify(mockConsole).write("---+---+---");
        inOrder.verify(mockConsole).write("   |   |   ");
        inOrder.verify(mockConsole).write("---+---+---");
        inOrder.verify(mockConsole).write("   |   |   ");
    }

    @Test
    public void displaysWinnerPlayerWhenGameIsOver() {

        String playerOneMark = "X";

        when(mockConsole.read()).thenReturn(playerOneMark);
        when(game.isOver()).thenReturn(true);
        Player winnerPlayer = new Player(1, playerOneMark);
        when(game.state()).thenReturn(new GameState(null,  null, null, winnerPlayer));

        ticTacToe.start();

        verify(mockConsole).write(String.format("Player %s is the winner.", winnerPlayer.number()));
    }
}
