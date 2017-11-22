package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.uglytrivia.Console;
import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(MockitoJUnitRunner.class)
public class GameShould {

    @Mock private Console console;

    private static final int ODD_NUMBER = 1;
    private static final int EVEN_NUMBER = 2;
    private Game game;

    @Before
    public void setUp() {
        game = new Game(console);
    }

    @Test public void
    sends_player_to_the_penalty_box() {
        game.add("PLAYER_NAME");

        game.wrongAnswer();

        InOrder inOrder = inOrder(console);
        inOrder.verify(console).printLine("PLAYER_NAME was added");
        inOrder.verify(console).printLine("They are player number 1");
        inOrder.verify(console).printLine("Question was incorrectly answered");
        inOrder.verify(console).printLine("PLAYER_NAME was sent to the penalty box");
        verifyNoMoreInteractions(console);
    }

    @Test
    public void get_player_out_of_the_penalty_box() {
        game.add("PLAYER_NAME");
        game.wrongAnswer();
        game.roll(ODD_NUMBER);

        game.wasCorrectlyAnswered();

        InOrder inOrder = inOrder(console);
        inOrder.verify(console).printLine("PLAYER_NAME was added");
        inOrder.verify(console).printLine("They are player number 1");
        inOrder.verify(console).printLine("Question was incorrectly answered");
        inOrder.verify(console).printLine("PLAYER_NAME was sent to the penalty box");
        inOrder.verify(console).printLine("PLAYER_NAME is the current player");
        inOrder.verify(console).printLine("They have rolled a " + ODD_NUMBER);
        inOrder.verify(console).printLine("PLAYER_NAME is getting out of the penalty box");
        inOrder.verify(console).printLine("PLAYER_NAME's new location is 1");
        inOrder.verify(console).printLine("The category is Science");
        inOrder.verify(console).printLine("Science Question 0");
        inOrder.verify(console).printLine("Answer was correct!!!!");
        inOrder.verify(console).printLine("PLAYER_NAME now has 1 Gold Coins.");
        verifyNoMoreInteractions(console);
    }

    @Test
    public void the_player_is_not_getting_out_of_penalty_box() {
        game.add("PLAYER_NAME");
        game.wrongAnswer();
        game.roll(EVEN_NUMBER);

        game.wasCorrectlyAnswered();

        InOrder inOrder = inOrder(console);
        inOrder.verify(console).printLine("PLAYER_NAME was added");
        inOrder.verify(console).printLine("They are player number 1");
        inOrder.verify(console).printLine("Question was incorrectly answered");
        inOrder.verify(console).printLine("PLAYER_NAME was sent to the penalty box");
        inOrder.verify(console).printLine("PLAYER_NAME is the current player");
        inOrder.verify(console).printLine("They have rolled a " + EVEN_NUMBER);
        inOrder.verify(console).printLine("PLAYER_NAME is not getting out of the penalty box");
        verifyNoMoreInteractions(console);
    }
}
