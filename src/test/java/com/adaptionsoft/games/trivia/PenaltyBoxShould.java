package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.uglytrivia.PenaltyBox;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class PenaltyBoxShould {

    @Test
    public void at_first_it_dont_have_players_inside() {
        PenaltyBox penaltyBox = new PenaltyBox();
        assertFalse(penaltyBox.isIn("PLAYER_NAME"));
    }
}
