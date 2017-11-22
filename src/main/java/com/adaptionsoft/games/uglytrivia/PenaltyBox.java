package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.List;

public class PenaltyBox {

    private List<String> players = new ArrayList<>();

    public void add(String player) {
        if (!isIn(player)) {
            players.add(player);
        }
    }

    public void remove(String player) {
        if (isIn(player)) {
            players.remove(player);
        }
    }

    public boolean isIn(String player) {
        return players.contains(player);
    }
}
