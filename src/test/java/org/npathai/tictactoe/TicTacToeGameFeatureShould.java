package org.npathai.tictactoe;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

public class TicTacToeGameFeatureShould {

    private PrintStream realPrintStream;
    private final ByteArrayOutputStream simulatedByteArrayOutputStream = new ByteArrayOutputStream();
    private PrintStream simulatedPrintStream = new PrintStream(simulatedByteArrayOutputStream);

    @BeforeEach
    public void simulateConsole() {
        realPrintStream = System.out;
        System.setOut(simulatedPrintStream);
    }

    // 1 - When game starts, the board is empty
    @Test
    public void showEmptyBoardWhenGameStarts() {
        TicTacToe ticTacToe = new TicTacToe();

        ticTacToe.start();

        String expectedBoardLines =
                "_|_|_" + System.lineSeparator()
                + "_|_|_" + System.lineSeparator()
                + "_|_|_" + System.lineSeparator();

        String actualBoard = simulatedByteArrayOutputStream.toString();

        assertThat(actualBoard).isEqualTo(expectedBoardLines);
    }

    @AfterEach
    public void revertConsole() {
        System.setOut(realPrintStream);
    }
}
