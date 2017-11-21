package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.trivia.runner.GameRunner;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class GameRunnerShould {

    @Test
    @Parameters({"1", "2", "3", "4", "5"})
    public void output_the_game_result_when_it_finish(int seed) throws IOException {
        ByteArrayOutputStream inMemorySystemOut = redirectSystemOutputToInMemory();
        GameRunner runner = new GameRunner();
        Random random = new Random(seed);
        String gameResultFile = String.format("game-result-for-game-%d.txt", seed);
        String expectedGameResult = IOUtils.toString(this.getClass().getResourceAsStream(gameResultFile), "UTF-8");

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
