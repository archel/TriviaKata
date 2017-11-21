
package com.adaptionsoft.games.trivia.runner;

import com.adaptionsoft.games.uglytrivia.Console;
import com.adaptionsoft.games.uglytrivia.Game;

import java.util.Random;


public class GameRunner {

    private static boolean notAWinner;

    public static void main(String[] args) {
        Random rand = new Random();
        Console console = new Console();
        runGame(rand, console);
    }

    public static void runGame(Random rand, Console console) {
        Game game = new Game(console);
        game.add("Chet");
        game.add("Pat");
        game.add("Sue");
        do {
            game.roll(rand.nextInt(5) + 1);

            if (rand.nextInt(9) == 7) {
                notAWinner = game.wrongAnswer();
            } else {
                notAWinner = game.wasCorrectlyAnswered();
            }
        } while (notAWinner);
    }
}
