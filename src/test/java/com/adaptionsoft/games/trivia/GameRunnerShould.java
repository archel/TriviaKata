package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.trivia.runner.GameRunner;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class GameRunnerShould {

    @Test
    public void output_the_game_result_when_it_finish() throws IOException {
        ByteArrayOutputStream inMemorySystemOut = redirectSystemOutputToInMemory();
        GameRunner runner = new GameRunner();
        Random random = new Random(1);
        String expectedGameResult = IOUtils.toString(
                this.getClass().getResourceAsStream("game-result-for-game-1.txt"), "UTF-8"
        );

        runner.runGame(random);

        assertEquals(expectedGameResult, inMemorySystemOut.toString());
    }

    private ByteArrayOutputStream redirectSystemOutputToInMemory() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
        return baos;
    }
}
