package org.npathai.tictactoe;

import java.util.Arrays;

public enum PlayerMark {
    X('X'),
    O('O');


    private final char mark;

    PlayerMark(char mark) {
        this.mark = mark;
    }

    public char getMark() {
        return mark;
    }

    // FIXME add validation for length of mark
    public static PlayerMark from(String mark) {
        return Arrays.stream(PlayerMark.values())
                .filter(each -> each.mark == mark.charAt(0))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown mark: " + mark));
    }
}
