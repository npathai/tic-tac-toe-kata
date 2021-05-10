package org.npathai.tictactoe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.Index.atIndex;

class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    public void aNewlyCreatedBoardShouldHaveNoMarkedCells() {
        assertThat(board.getCells())
                .contains(new char[] {' ', ' ', ' '}, atIndex(0))
                .contains(new char[] {' ', ' ', ' '}, atIndex(1))
                .contains(new char[] {' ', ' ', ' '}, atIndex(2));
    }

    @Test
    public void updatesTheCellWithPlayersMark() {
        board.update(1, 'X');
        board.update(5, 'O');
        board.update(9, 'I');

        assertThat(board.getCells())
                .contains(new char[] {'X', ' ', ' '}, atIndex(0))
                .contains(new char[] {' ', 'O', ' '}, atIndex(1))
                .contains(new char[] {' ', ' ', 'I'}, atIndex(2));
    }

    @Test
    public void returnsTrueWhenAnyRowIsSelectedWithPlayersMark() {
        board.update(1, 'X');
        board.update(4, 'O');
        board.update(2, 'X');
        board.update(5, 'O');
        board.update(3, 'X');

        assertThat(board.hasLineMarked('X')).isTrue();
    }

    @Test
    public void returnsTrueWhenAnyColumnIsSelectedWithPlayersMark() {
        board.update(1, 'X');
        board.update(2, 'O');
        board.update(4, 'X');
        board.update(8, 'O');
        board.update(7, 'X');

        assertThat(board.hasLineMarked('X')).isTrue();
    }
}