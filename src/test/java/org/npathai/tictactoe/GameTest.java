package org.npathai.tictactoe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.Index.atIndex;

import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GameTest {

    @InjectMocks
    private Game game;
    private final Player playerOne = new Player(1, "X");
    private final Player playerTwo = new Player(2, "O");


    @BeforeEach
    public void setUp() {
        game.start(playerOne.mark());
    }

    @Test
    public void updateBoardCellWithCurrentPlayerMark() {
        game.update(3);
        game.update(5);

        GameState state = game.state();

        assertThat(state.board()).contains(new char[]{' ', ' ', 'X'}, atIndex(0))
                .contains(new char[]{' ', 'O', ' '}, atIndex(1));
    }

    @ParameterizedTest(name = "When player one has mark {0}, then player two should have mark {1}")
    @CsvSource({
            "X,O",
            "O,X"
    })
    public void secondPlayerGetsOppositeMarkOnStart(String playerOneMark, String playerTwoMark) {
        Game game = new Game();

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
        assertThat(game.isOver()).isFalse();
    }

    @Test
    public void isOverWhenAnyRowIsFullWithSamePlayersMark() {
        game.update(1);
        assertThat(game.isOver()).isFalse();

        game.update(4);
        assertThat(game.isOver()).isFalse();

        game.update(2);
        assertThat(game.isOver()).isFalse();

        game.update(5);
        assertThat(game.isOver()).isFalse();

        game.update(3);

        assertThat(game.isOver()).isTrue();
        // This can be broken down in nested classes testing
        GameState state = game.state();
        assertThat(state.winnerPlayer()).isEqualTo(playerOne);
    }
}
