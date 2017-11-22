package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.uglytrivia.PenaltyBox;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PenaltyBoxShould {

    @Test
    public void at_first_it_dont_have_players_inside() {
        PenaltyBox penaltyBox = new PenaltyBox();
        assertFalse(penaltyBox.isIn("PLAYER_NAME"));
    }

    @Test
    public void adds_a_player_to_penalty_box() {
        PenaltyBox penaltyBox = new PenaltyBox();
        penaltyBox.add("PLAYER_NAME");
        assertTrue(penaltyBox.isIn("PLAYER_NAME"));
    }

    @Test
    public void get_out_a_player_from_penalty_box() {
        PenaltyBox penaltyBox = new PenaltyBox();
        penaltyBox.add("PLAYER_NAME");
        penaltyBox.remove("PLAYER_NAME");

        assertFalse(penaltyBox.isIn("PLAYER_NAME"));
    }
}
