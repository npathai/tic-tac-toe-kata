package org.npathai.tictactoe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import static org.assertj.core.api.Assertions.assertThat;

public class TicTacToeAcceptanceTests {

    public static final char X = 'X';
    public static final char O = 'O';
    private TicTacToeRunner ticTacToeRunner;
    private PlayerMoves playerX;
    private PlayerMoves playerO;

    @BeforeEach
    void setUp() throws IOException {
        ticTacToeRunner = new TicTacToeRunner();
        ticTacToeRunner.start();
    }

    @ParameterizedTest
    @CsvSource({
            "X,O",
            "O,X"
    })
    public void whenPlayerIsAbleToSelectFullRowThenPlayerWinsAndGameIsOver(String playerOneMark, String playerTwoMark) throws IOException {
        ticTacToeRunner.checkInstructionsVisible();
        ticTacToeRunner.checkPlayerOneAskedToChooseMark();
        ticTacToeRunner.checkNoMoreOutput();

        PlayerMoves playerOne = new PlayerMoves(ticTacToeRunner, playerOneMark, 1);
        PlayerMoves playerTwo = new PlayerMoves(ticTacToeRunner, playerTwoMark, 2);

        playerOne.selectsMark();

        ticTacToeRunner.checkDisplaysBoard(new char[] {
                ' ', ' ', ' ',
                ' ', ' ', ' ',
                ' ', ' ', ' '
        });

        playerOne.checkIsCurrentPlayer();

        playerOne.selectsCell("1");

        ticTacToeRunner.checkDisplaysBoard(new char[] {
                playerOne.playerMark.charAt(0), ' ', ' ',
                ' ', ' ', ' ',
                ' ', ' ', ' '
        });

        playerTwo.checkIsCurrentPlayer();

        playerTwo.selectsCell("4");

        ticTacToeRunner.checkDisplaysBoard(new char[] {
                playerOne.playerMark.charAt(0), ' ', ' ',
                playerTwo.playerMark.charAt(0), ' ', ' ',
                ' ', ' ', ' '
        });

        playerOne.checkIsCurrentPlayer();

        playerOne.selectsCell("2");

        ticTacToeRunner.checkDisplaysBoard(new char[] {
                playerOne.playerMark.charAt(0), playerOne.playerMark.charAt(0), ' ',
                playerTwo.playerMark.charAt(0), ' ', ' ',
                ' ', ' ', ' '
        });

        playerTwo.checkIsCurrentPlayer();

        playerTwo.selectsCell("7");

        ticTacToeRunner.checkDisplaysBoard(new char[] {
                playerOne.playerMark.charAt(0), playerOne.playerMark.charAt(0), ' ',
                playerTwo.playerMark.charAt(0), ' ', ' ',
                playerTwo.playerMark.charAt(0), ' ', ' '
        });

        playerOne.checkIsCurrentPlayer();

        playerOne.selectsCell("3");

        ticTacToeRunner.checkDisplaysBoard(new char[] {
                playerOne.playerMark.charAt(0), playerOne.playerMark.charAt(0), playerOne.playerMark.charAt(0),
                playerTwo.playerMark.charAt(0), ' ', ' ',
                playerTwo.playerMark.charAt(0), ' ', ' '
        });

        playerOne.checkIsWinner();

        ticTacToeRunner.checkGameIsOver();
    }

    @Test
    public void whenPlayerIsAbleToSelectFullColumnThenPlayerWinsAndGameIsOver() throws IOException {
        ticTacToeRunner.checkInstructionsVisible();
        ticTacToeRunner.checkPlayerOneAskedToChooseMark();
        ticTacToeRunner.checkNoMoreOutput();

        PlayerMoves playerOne = new PlayerMoves(ticTacToeRunner, "X", 1);
        PlayerMoves playerTwo = new PlayerMoves(ticTacToeRunner, "O", 2);

        playerOne.selectsMark();

        ticTacToeRunner.checkDisplaysBoard(new char[] {
                ' ', ' ', ' ',
                ' ', ' ', ' ',
                ' ', ' ', ' '
        });

        playerOne.checkIsCurrentPlayer();

        playerOne.selectsCell("1");

        ticTacToeRunner.checkDisplaysBoard(new char[] {
                playerOne.playerMark.charAt(0), ' ', ' ',
                ' ', ' ', ' ',
                ' ', ' ', ' '
        });

        playerTwo.checkIsCurrentPlayer();

        playerTwo.selectsCell("9");

        ticTacToeRunner.checkDisplaysBoard(new char[] {
                playerOne.playerMark.charAt(0), ' ', ' ',
                ' ', ' ', ' ',
                ' ', ' ', playerTwo.playerMark.charAt(0)
        });

        playerOne.checkIsCurrentPlayer();

        playerOne.selectsCell("4");

        ticTacToeRunner.checkDisplaysBoard(new char[] {
                playerOne.playerMark.charAt(0), ' ', ' ',
                playerOne.playerMark.charAt(0), ' ', ' ',
                ' ', ' ', playerTwo.playerMark.charAt(0)
        });

        playerTwo.checkIsCurrentPlayer();

        playerTwo.selectsCell("5");

        ticTacToeRunner.checkDisplaysBoard(new char[] {
                playerOne.playerMark.charAt(0), ' ', ' ',
                playerOne.playerMark.charAt(0), playerTwo.playerMark.charAt(0), ' ',
                ' ', ' ', playerTwo.playerMark.charAt(0)
        });

        playerOne.checkIsCurrentPlayer();

        playerOne.selectsCell("7");

        ticTacToeRunner.checkDisplaysBoard(new char[] {
                playerOne.playerMark.charAt(0), ' ', ' ',
                playerOne.playerMark.charAt(0), playerTwo.playerMark.charAt(0), ' ',
                playerOne.playerMark.charAt(0), ' ', playerTwo.playerMark.charAt(0)
        });

        playerOne.checkIsWinner();

        ticTacToeRunner.checkGameIsOver();
    }

    @Test
    public void whenPlayerIsAbleToSelectFullDiagonalThenPlayerWinsAndGameIsOver() throws IOException {
        ticTacToeRunner.checkInstructionsVisible();
        ticTacToeRunner.checkPlayerOneAskedToChooseMark();
        ticTacToeRunner.checkNoMoreOutput();

        PlayerMoves playerOne = new PlayerMoves(ticTacToeRunner, "X", 1);
        PlayerMoves playerTwo = new PlayerMoves(ticTacToeRunner, "O", 2);

        playerOne.selectsMark();

        ticTacToeRunner.checkDisplaysBoard(new char[] {
                ' ', ' ', ' ',
                ' ', ' ', ' ',
                ' ', ' ', ' '
        });

        playerOne.checkIsCurrentPlayer();

        playerOne.selectsCell("1");

        ticTacToeRunner.checkDisplaysBoard(new char[] {
                playerOne.playerMark.charAt(0), ' ', ' ',
                ' ', ' ', ' ',
                ' ', ' ', ' '
        });

        playerTwo.checkIsCurrentPlayer();

        playerTwo.selectsCell("3");

        ticTacToeRunner.checkDisplaysBoard(new char[] {
                playerOne.playerMark.charAt(0), ' ', playerTwo.playerMark.charAt(0),
                ' ', ' ', ' ',
                ' ', ' ', ' '
        });

        playerOne.checkIsCurrentPlayer();

        playerOne.selectsCell("5");

        ticTacToeRunner.checkDisplaysBoard(new char[] {
                playerOne.playerMark.charAt(0), ' ', playerTwo.playerMark.charAt(0),
                ' ', playerOne.playerMark.charAt(0), ' ',
                ' ', ' ', ' '
        });

        playerTwo.checkIsCurrentPlayer();

        playerTwo.selectsCell("6");

        ticTacToeRunner.checkDisplaysBoard(new char[] {
                playerOne.playerMark.charAt(0), ' ', playerTwo.playerMark.charAt(0),
                ' ', playerOne.playerMark.charAt(0), playerTwo.playerMark.charAt(0),
                ' ', ' ', ' '
        });

        playerOne.checkIsCurrentPlayer();

        playerOne.selectsCell("9");

        ticTacToeRunner.checkDisplaysBoard(new char[] {
                playerOne.playerMark.charAt(0), ' ', playerTwo.playerMark.charAt(0),
                ' ', playerOne.playerMark.charAt(0), playerTwo.playerMark.charAt(0),
                ' ', ' ', playerOne.playerMark.charAt(0)
        });

        playerOne.checkIsWinner();

        ticTacToeRunner.checkGameIsOver();
    }

    @Test
    public void whenAllCellsAreMarkedAndNoPlayerHasALineThenGameIsOverWithNoWinner() throws IOException {
        ticTacToeRunner.checkInstructionsVisible();
        ticTacToeRunner.checkPlayerOneAskedToChooseMark();
        ticTacToeRunner.checkNoMoreOutput();

        playerX = new PlayerMoves(ticTacToeRunner, String.valueOf(X), 1);
        playerO = new PlayerMoves(ticTacToeRunner, String.valueOf(O), 2);

        playerX.selectsMark();

        ticTacToeRunner.checkDisplaysBoard(new char[] {
                ' ', ' ', ' ',
                ' ', ' ', ' ',
                ' ', ' ', ' '
        });

        playerX.checkIsCurrentPlayer();

        playerX.selectsCell("1");

        ticTacToeRunner.checkDisplaysBoard(new char[] {
                 X , ' ', ' ',
                ' ', ' ', ' ',
                ' ', ' ', ' '
        });

        playerO.checkIsCurrentPlayer();

        playerO.selectsCell("3");

        ticTacToeRunner.checkDisplaysBoard(new char[] {
                X, ' ', O,
                ' ', ' ', ' ',
                ' ', ' ', ' '
        });

        playerX.checkIsCurrentPlayer();

        playerX.selectsCell("5");

        ticTacToeRunner.checkDisplaysBoard(new char[] {
                 X , ' ', O ,
                ' ', X , ' ',
                ' ', ' ', ' '
        });

        playerO.checkIsCurrentPlayer();

        playerO.selectsCell("9");

        ticTacToeRunner.checkDisplaysBoard(new char[] {
                 X , ' ', O ,
                ' ', X , ' ',
                ' ', ' ', O
        });

        playerX.checkIsCurrentPlayer();

        playerX.selectsCell("2");

        ticTacToeRunner.checkDisplaysBoard(new char[] {
                 X , X , O ,
                ' ', X , ' ',
                ' ', ' ', O
        });

        playerO.checkIsCurrentPlayer();

        playerO.selectsCell("8");

        ticTacToeRunner.checkDisplaysBoard(new char[] {
                 X , X , O ,
                ' ', X , ' ',
                ' ', O , O
        });

        playerX.checkIsCurrentPlayer();

        playerX.selectsCell("6");

        ticTacToeRunner.checkDisplaysBoard(new char[] {
                 X , X , O ,
                ' ', X , X ,
                ' ', O , O
        });

        playerO.checkIsCurrentPlayer();

        playerO.selectsCell("4");

        ticTacToeRunner.checkDisplaysBoard(new char[] {
                X , X , O ,
                O , X , X ,
                ' ', O , O
        });

        playerX.checkIsCurrentPlayer();

        playerX.selectsCell("7");

        ticTacToeRunner.checkDisplaysBoard(new char[] {
                X , X , O ,
                O , X , X ,
                X , O , O
        });

        ticTacToeRunner.checkGameIsOverWithNoWinner();
    }

    class TicTacToeRunner {

        private Process process;
        private BufferedReader processOut;
        private PrintWriter input;

        void start() throws IOException {
            process = Runtime.getRuntime().exec("java -jar build\\libs\\tic-tac-toe-kata-1.0-SNAPSHOT.jar");
            processOut = new BufferedReader(new InputStreamReader(process.getInputStream()));
            input = new PrintWriter(process.getOutputStream());
        }

        void checkInstructionsVisible() throws IOException {
            assertThat(processOut.readLine()).isEqualTo(" 1 | 2 | 3 ");
            assertThat(processOut.readLine()).isEqualTo("---+---+---");
            assertThat(processOut.readLine()).isEqualTo(" 4 | 5 | 6 ");
            assertThat(processOut.readLine()).isEqualTo("---+---+---");
            assertThat(processOut.readLine()).isEqualTo(" 7 | 8 | 9 ");
        }

        public void send(String command) {
            input.write(command + System.lineSeparator());
            input.flush();
        }

        public void checkPlayerOneAskedToChooseMark() throws IOException {
            assertThat(processOut.readLine()).isEqualTo("Player one choose your mark [X or O] >> ");
        }

        public void checkNoMoreOutput() throws IOException {
            assertThat(processOut.ready()).isFalse();
        }

        public void checkDisplaysBoard(char[] values) throws IOException {
            assertThat(processOut.readLine())
                    .isEqualTo(String.format(" %s | %s | %s ", values[0], values[1], values[2]));
            assertThat(processOut.readLine()).isEqualTo("---+---+---");
            assertThat(processOut.readLine())
                    .isEqualTo(String.format(" %s | %s | %s ", values[3], values[4], values[5]));
            assertThat(processOut.readLine()).isEqualTo("---+---+---");
            assertThat(processOut.readLine())
                    .isEqualTo(String.format(" %s | %s | %s ", values[6], values[7], values[8]));
        }

        public void checkGameIsOver() throws IOException {
            assertThat(processOut.readLine()).isNull();
            assertThat(process.isAlive()).isFalse();
        }

        public void checkGameIsOverWithNoWinner() throws IOException {
            assertThat(processOut.readLine()).isEqualTo("No winner. Game is over.");
            checkGameIsOver();
        }
    }

    class PlayerMoves {
        private final TicTacToeRunner runner;
        private final String playerMark;
        private final int playerNo;

        PlayerMoves(TicTacToeRunner runner, String playerMark, int playerNo) {
            this.runner = runner;
            this.playerMark = playerMark;
            this.playerNo = playerNo;
        }


        public void selectsMark() {
            runner.send(playerMark);
        }

        public void selectsCell(String cellNumber) {
            runner.send(cellNumber);
        }

        public void checkIsCurrentPlayer() throws IOException {
            assertThat(runner.processOut.readLine())
                    .isEqualTo(String.format("Player %s's turn. "
                            + "To place %s on the board, enter cell number >> ", playerNo, playerMark));
        }

        public void checkIsWinner() throws IOException {
            assertThat(runner.processOut.readLine())
                    .isEqualTo(String.format("Player %s is the winner.", playerNo));
        }
    }
}
