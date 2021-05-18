package org.npathai.tictactoe;

import java.util.Objects;

public class Player {
    private final int playerNo;
    private final PlayerMark mark;

    public Player(int playerNo, PlayerMark mark) {
        this.playerNo = playerNo;
        this.mark = mark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return playerNo == player.playerNo && Objects.equals(mark, player.mark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerNo, mark);
    }

    public PlayerMark mark() {
        return mark;
    }

    public int number() {
        return playerNo;
    }
}
