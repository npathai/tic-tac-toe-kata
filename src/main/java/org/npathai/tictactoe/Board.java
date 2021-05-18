package org.npathai.tictactoe;

public class Board {

    private final char[][] cells;
    private int filledCells;

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

    public void update(int cellNo, PlayerMark playerMark) {
        cellNo--;
        int row = cellNo / 3;
        int col = cellNo % 3;
        cells[row][col] = playerMark.getMark();
        filledCells++;
    }

    public boolean hasLineMarked(PlayerMark playerMark) {
        for (int row = 0; row < cells.length; row++) {
            char[] boardRow = cells[row];
            if (boardRow[0] == playerMark.getMark()
                    && boardRow[0] == boardRow[1]
                    && boardRow[0] == boardRow[2]) {
                return true;
            }
        }

        boolean isColumnMarked;
        for (int col = 0; col < cells.length; col++) {
            isColumnMarked = true;
            for (int row = 0; row < cells.length; row++) {
                if (cells[row][col] != playerMark.getMark()) {
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

    private boolean isPrimaryDiagonalMarked(PlayerMark playerMark) {
        boolean isDiagonalMarked = true;
        for (int i = 0; i < cells.length; i++) {
            if (cells[i][i] != playerMark.getMark()) {
                isDiagonalMarked = false;
                break;
            }
        }
        return isDiagonalMarked;
    }

    private boolean isSecondaryDiagonalMarked(PlayerMark playerMark) {
        boolean isDiagonalMarked = true;
        for (int i = 0; i < cells.length; i++) {
            if (cells[i][cells.length - 1 - i] != playerMark.getMark()) {
                isDiagonalMarked = false;
                break;
            }
        }
        return isDiagonalMarked;
    }

    public boolean isFull() {
        return filledCells == 9;
    }
}
