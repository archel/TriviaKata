package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.uglytrivia.PenaltyBox;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PenaltyBoxShould {
    private static final String PLAYER_ONE = "PLAYER_ONE";
    private static final String PLAYER_TWO = "PLAYER_TWO";

    private PenaltyBox penaltyBox;

    @Before
    public void setUp() throws Exception {
        penaltyBox = new PenaltyBox();
    }

    @Test
    public void have_no_players_inside() {
        assertFalse(penaltyBox.isIn(PLAYER_ONE));
    }

    @Test
    public void add_a_player_to_penalty_box() {
        penaltyBox.add(PLAYER_ONE);

        assertTrue(penaltyBox.isIn(PLAYER_ONE));
    }
    
    @Test
    public void remove_second_player_from_penalty_box() {
        penaltyBox.add(PLAYER_ONE);
        penaltyBox.add(PLAYER_TWO);

        penaltyBox.remove(PLAYER_TWO);

        assertFalse(penaltyBox.isIn(PLAYER_TWO));
    }
}
