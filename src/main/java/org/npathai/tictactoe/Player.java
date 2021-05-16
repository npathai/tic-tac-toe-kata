package org.npathai.tictactoe;

import java.util.Objects;

public class Player {
    private final int playerNo;
    private final char playerMark;

    public Player(int playerNo, char playerMark) {
        this.playerNo = playerNo;
        this.playerMark = playerMark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return playerNo == player.playerNo && Objects.equals(playerMark, player.playerMark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerNo, playerMark);
    }

    public char mark() {
        return playerMark;
    }

    public int number() {
        return playerNo;
    }
}
