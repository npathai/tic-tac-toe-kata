package org.npathai.tictactoe;

import java.util.Scanner;

public class Console {

    Scanner scanner = new Scanner(System.in);

    String read() {
        return scanner.nextLine();
    }

    void write(String line) {
        System.out.println(line);
    }
}
