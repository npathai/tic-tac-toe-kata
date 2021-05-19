package org.npathai.tictactoe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.Index.atIndex;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.npathai.tictactoe.PlayerMark.O;
import static org.npathai.tictactoe.PlayerMark.X;

@ExtendWith(MockitoExtension.class)
public class GameTest {

    @Mock
    Board mockBoard;

    @Mock
    GameView gameView;

    @Mock
    Console mockConsole;

    @InjectMocks
    private Game game;

    private final Player playerOne = new Player(1, X);
    private final Player playerTwo = new Player(2, O);

    @BeforeEach
    public void setUp() {
        when(mockBoard.isFull()).thenReturn(false, true);
        game.start(playerOne.mark());
    }

    @Test
    public void updateBoardCellWithCurrentPlayerMark() {
        game.update(3);
        game.update(5);
        game.update(1);
        game.update(6);

        verify(mockBoard).update(3, X);
        verify(mockBoard).update(5, O);
        verify(mockBoard).update(1, X);
        verify(mockBoard).update(6, O);
    }

    @Test
    public void returnsGameStatusWithCurrentStateOfBoardCells() {
        char[][] cells = {
                {'X', 'O', ' '},
                {'O', ' ', 'X'},
                {'X', ' ', 'O'},
        };
        when(mockBoard.getCells()).thenReturn(cells);

        assertThat(game.state().board())
                .contains(cells[0], atIndex(0))
                .contains(cells[1], atIndex(1))
                .contains(cells[2], atIndex(2));
        verify(mockBoard).getCells();
    }

    @ParameterizedTest(name = "When player one has mark {0}, then player two should have mark {1}")
    @CsvSource({
            "X,O",
            "O,X"
    })
    public void secondPlayerGetsOppositeMarkOnStart(char playerOneMarkChar, char playerTwoMarkChar) {
        PlayerMark playerOneMark = PlayerMark.from(String.valueOf(playerOneMarkChar));
        PlayerMark playerTwoMark = PlayerMark.from(String.valueOf(playerTwoMarkChar));

        game.start(playerOneMark);

        game.update(1);

        GameState state = game.state();

        assertThat(state.lastPlayer().mark()).isEqualTo(playerOneMark);
        assertThat(state.currentPlayer().mark()).isEqualTo(playerTwoMark);
    }

    @Test
    public void switchesCurrentPlayerAfterEachMove() {
        GameState state = game.state();
        assertThat(state.lastPlayer()).isNull();
        assertThat(state.currentPlayer()).isEqualTo(playerOne);

        game.update(1);

        state = game.state();
        assertThat(state.lastPlayer()).isEqualTo(playerOne);
        assertThat(state.currentPlayer()).isEqualTo(playerTwo);

        game.update(2);

        state = game.state();
        assertThat(state.lastPlayer()).isEqualTo(playerTwo);
        assertThat(state.currentPlayer()).isEqualTo(playerOne);
    }

    @Test
    public void aNewlyCreatedGameIsNotOver() {
        assertThat(game.state().isOver()).isFalse();
    }

    @Test
    public void aNewlyCreatedGameHasNoWinner() {
        assertThat(game.state().winnerPlayer()).isNull();
    }

    @Test
    public void isNotOverWhenBoardHasNoLineSelected() {
        when(mockBoard.hasLineMarked(X)).thenReturn(false);

        game.update(3);

        assertThat(game.state().isOver()).isFalse();

        // This can be broken down in nested classes testing
        GameState state = game.state();
        assertThat(state.winnerPlayer()).isNull();
    }

    @Test
    public void isOverWhenBoardHasAnyLineSelected() {
        when(mockBoard.hasLineMarked(X)).thenReturn(true);

        game.update(3);

        assertThat(game.state().isOver()).isTrue();

        // This can be broken down in nested classes testing
        GameState state = game.state();
        assertThat(state.winnerPlayer()).isEqualTo(playerOne);
    }

    @Test
    public void isOverWhenBoardIsFilledWithoutAnyWinner() {
        when(mockBoard.isFull()).thenReturn(true);

        assertThat(game.state().isOver()).isTrue();
    }

    @Test
    public void whileGameIsNotOverAsksNextPlayerForMoveAndUpdateTheGame() {
        PlayerMark playerOneMark = PlayerMark.X;
        when(mockConsole.read()).thenReturn(String.valueOf(playerOneMark), "1", "2");
        GameState state1 = notOverState();
        GameState state2 = notOverState();
        GameState state3 = gameOverState();
        when(game.state()).thenReturn(state1, state2, state3);

        game.start(playerOneMark);

        InOrder inOrder = Mockito.inOrder(game, gameView, mockConsole);
        inOrder.verify(gameView).displayInstructions();
        inOrder.verify(gameView).askForPlayerMark();
        inOrder.verify(mockConsole).read();
        inOrder.verify(game).start(playerOneMark);
        inOrder.verify(gameView).displayGameState(state1);
        inOrder.verify(game).state();
        // FIXME to be improved
        inOrder.verify(gameView).askForNextCellNo(null);
        inOrder.verify(mockConsole).read();
        inOrder.verify(game).update(1);
        inOrder.verify(gameView).displayGameState(state2);
        inOrder.verify(game).state();
        inOrder.verify(gameView).askForNextCellNo(null);
        inOrder.verify(mockConsole).read();
        inOrder.verify(game).update(2);
        inOrder.verify(gameView).displayGameState(state3);
        inOrder.verify(game).state();
    }

    private GameState notOverState() {
        return new GameState(null, null, null, null, false);
    }

    private GameState gameOverState() {
        return new GameState(null, null, null, null, true);
    }
}
