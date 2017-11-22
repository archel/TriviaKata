package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.trivia.runner.GameRunner;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class GameRunnerShould {

    private ConsoleTestable console;

    @Before
    public void setUp() {
        console = new ConsoleTestable();
    }

    @Test
    @Parameters({"1", "2", "3", "4", "5"})
    public void output_the_game_result_when_it_finish(int seed) throws IOException {
        String expectedGameResult = loadGameResultFromFile(seed);

        GameRunner.runGame(new Random(seed), console);

        assertEquals(expectedGameResult, console.toString());
    }

    private String loadGameResultFromFile(int seed) throws IOException {
        String gameResultFile = String.format("game-result-for-game-%d.txt", seed);
        return IOUtils.toString(this.getClass().getResourceAsStream(gameResultFile), "UTF-8");
    }
}
