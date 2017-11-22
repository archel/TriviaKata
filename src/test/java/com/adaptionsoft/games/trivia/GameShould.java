package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.uglytrivia.Console;
import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.inOrder;

@RunWith(MockitoJUnitRunner.class)
public class GameShould {

    @Mock private Console console;

    @Test public void
    sends_player_to_penalty_box() {
        Game game = new Game(console);

        game.add("PLAYER_NAME");
        game.wrongAnswer();

        InOrder inOrder = inOrder(console);
        inOrder.verify(console).printLine("PLAYER_NAME was added");
        inOrder.verify(console).printLine("They are player number 1");
        inOrder.verify(console).printLine("Question was incorrectly answered");
        inOrder.verify(console).printLine("PLAYER_NAME was sent to the penalty box");
    }
}
