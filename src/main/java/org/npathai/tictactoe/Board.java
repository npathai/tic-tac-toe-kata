package org.npathai.tictactoe;

public class Board {

    private final char[][] cells;

    public Board() {
        this.cells = new char[][]{
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };
    }

    public char[][] getCells() {
        return cells;
    }

    public void update(int cellNo, char playerMark) {
        cellNo--;
        int row = cellNo / 3;
        int col = cellNo % 3;
        cells[row][col] = playerMark;
    }

    public boolean hasLineMarked(char playerMark) {
        for (int row = 0; row < cells.length; row++) {
            char[] boardRow = cells[row];
            if (boardRow[0] == playerMark
                    && boardRow[0] == boardRow[1]
                    && boardRow[0] == boardRow[2]) {
                return true;
            }
        }

        boolean isColumnMarked;
        for (int col = 0; col < cells.length; col++) {
            isColumnMarked = true;
            for (int row = 0; row < cells.length; row++) {
                if (cells[row][col] != playerMark) {
                    isColumnMarked = false;
                    break;
                }
            }

            if (isColumnMarked) {
                return true;
            }
        }

        // Diagonal checks
        return isPrimaryDiagonalMarked(playerMark) || isSecondaryDiagonalMarked(playerMark);
    }

    private boolean isPrimaryDiagonalMarked(char playerMark) {
        boolean isDiagonalMarked = true;
        for (int i = 0; i < cells.length; i++) {
            if (cells[i][i] != playerMark) {
                isDiagonalMarked = false;
                break;
            }
        }
        return isDiagonalMarked;
    }

    private boolean isSecondaryDiagonalMarked(char playerMark) {
        boolean isDiagonalMarked = true;
        for (int i = 0; i < cells.length; i++) {
            if (cells[i][cells.length - 1 - i] != playerMark) {
                isDiagonalMarked = false;
                break;
            }
        }
        return isDiagonalMarked;
    }
}
