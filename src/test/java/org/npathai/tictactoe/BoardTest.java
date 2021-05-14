package org.npathai.tictactoe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.Index.atIndex;
import static org.npathai.tictactoe.BoardTest.Mark.oMarkAt;
import static org.npathai.tictactoe.BoardTest.Mark.xMarkAt;

class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    public void aNewlyCreatedBoardShouldHaveNoMarkedCells() {
        assertThat(board.getCells())
                .contains(new char[]{' ', ' ', ' '}, atIndex(0))
                .contains(new char[]{' ', ' ', ' '}, atIndex(1))
                .contains(new char[]{' ', ' ', ' '}, atIndex(2));
    }

    @Test
    public void updatesTheCellWithPlayersMark() {
        board.update(1, 'X');
        board.update(5, 'O');
        board.update(9, 'I');

        assertThat(board.getCells())
                .contains(new char[]{'X', ' ', ' '}, atIndex(0))
                .contains(new char[]{' ', 'O', ' '}, atIndex(1))
                .contains(new char[]{' ', ' ', 'I'}, atIndex(2));
    }

    // FIXME need to add the reverse test, where return value is false
    @Test
    public void returnsTrueWhenAnyRowIsSelectedWithPlayersMark() {
        board.update(1, 'X');
        board.update(4, 'O');
        board.update(2, 'X');
        board.update(5, 'O');
        board.update(3, 'X');

        assertThat(board.hasLineMarked('X')).isTrue();
    }

    // FIXME need to add the reverse test, where return value is false
    @Test
    public void returnsTrueWhenAnyColumnIsSelectedWithPlayersMark() {
        board.update(1, 'X');
        board.update(2, 'O');
        board.update(4, 'X');
        board.update(8, 'O');
        board.update(7, 'X');

        assertThat(board.hasLineMarked('X')).isTrue();
    }

    @ParameterizedTest
    @MethodSource
    public void returnsTrueWhenAnyDiagonalIsSelectedWithPlayersMarks(List<Mark> marks, char checkChar) {
        marks.forEach(mark -> board.update(mark.cellNo, mark.playerMark));

        assertThat(board.hasLineMarked(checkChar)).isTrue();
    }

    @SuppressWarnings("unused")
    public static Stream<Arguments> returnsTrueWhenAnyDiagonalIsSelectedWithPlayersMarks() {
        return Stream.of(
                // First diagonal
                Arguments.of(asList(xMarkAt(1), oMarkAt(2), xMarkAt(5), oMarkAt(4), xMarkAt(9)), 'X'),
                Arguments.of(asList(oMarkAt(1), xMarkAt(2), oMarkAt(5), xMarkAt(4), oMarkAt(9)), 'O'),
                // Second diagonal
                Arguments.of(asList(xMarkAt(3), oMarkAt(2), xMarkAt(5), oMarkAt(4), xMarkAt(7)), 'X'),
                Arguments.of(asList(oMarkAt(3), xMarkAt(2), oMarkAt(5), xMarkAt(4), oMarkAt(7)), 'O')
        );
    }

    @ParameterizedTest
    @MethodSource
    public void returnsFalseWhenAnyDiagonalIsNotSelectedWithPlayersMarks(List<Mark> marks, char checkChar) {
        marks.forEach(mark -> board.update(mark.cellNo, mark.playerMark));

        assertThat(board.hasLineMarked(checkChar)).isFalse();
    }

    @SuppressWarnings("unused")
    public static Stream<Arguments> returnsFalseWhenAnyDiagonalIsNotSelectedWithPlayersMarks() {
        return Stream.of(
                // First diagonal
                Arguments.of(asList(xMarkAt(1), oMarkAt(5), xMarkAt(9)), 'X'),
                Arguments.of(asList(oMarkAt(1), xMarkAt(5), oMarkAt(9)), 'O'),
                // Second diagonal
                Arguments.of(asList(xMarkAt(3), oMarkAt(5), xMarkAt(7)), 'X'),
                Arguments.of(asList(oMarkAt(3), xMarkAt(5), oMarkAt(7)), 'O')
        );
    }

    // FIXME There should be a better way to write this test case
    @Test
    public void shouldNotBeFullWhenAllCellsAreMarkedWithoutAnyLineSelected() {
        board.update(1, 'X');
        board.update(3, 'O');
        board.update(5, 'X');
        board.update(9, 'O');
        board.update(2, 'X');
        board.update(8, 'O');
        board.update(6, 'X');
        board.update(7, 'X');

        assertThat(board.isFull()).isFalse();
    }

    @Test
    public void shouldBeFullWhenAllCellsAreMarkedWithoutAnyLineSelected() {
        board.update(1, 'X');
        board.update(3, 'O');
        board.update(5, 'X');
        board.update(9, 'O');
        board.update(2, 'X');
        board.update(8, 'O');
        board.update(6, 'X');
        board.update(4, 'O');
        board.update(7, 'X');

        assertThat(board.isFull()).isTrue();
    }

    static class Mark {
        int cellNo;
        char playerMark;

        public Mark(int cellNo, char playerMark) {
            this.cellNo = cellNo;
            this.playerMark = playerMark;
        }

        @Override
        public String toString() {
            return playerMark + "->" + cellNo;
        }

        static Mark xMarkAt(int cellNo) {
            return new Mark(cellNo, 'X');
        }

        static Mark oMarkAt(int cellNo) {
            return new Mark(cellNo, 'O');
        }
    }
}