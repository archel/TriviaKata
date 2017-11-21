package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
    private ArrayList players = new ArrayList();
    private int[] tiles = new int[6];
    private int[] coins = new int[6];
    private boolean[] inPenaltyBox = new boolean[6];

    private LinkedList popQuestions = new LinkedList();
    private LinkedList scienceQuestions = new LinkedList();
    private LinkedList sportsQuestions = new LinkedList();
    private LinkedList rockQuestions = new LinkedList();

    private int currentPlayer = 0;
    private boolean isGettingOutOfPenaltyBox;

    public Game() {
        for (int i = 0; i < 50; i++) {
            popQuestions.addLast("Pop Question " + i);
            scienceQuestions.addLast(("Science Question " + i));
            sportsQuestions.addLast(("Sports Question " + i));
            rockQuestions.addLast("Rock Question " + i);
        }
    }

    public boolean isPlayable() {
        return (howManyPlayers() >= 2);
    }

    public boolean add(String playerName) {
        players.add(playerName);
        tiles[howManyPlayers()] = 0;
        coins[howManyPlayers()] = 0;
        inPenaltyBox[howManyPlayers()] = false;

        print(playerName);
        Console.printLine("They are player number " + players.size());
        return true;
    }

    private void print(String playerName) {
        Console.printLine(playerName + " was added");
    }

    public int howManyPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        Console.printLine(players.get(currentPlayer) + " is the current player");
        Console.printLine("They have rolled a " + roll);

        if (inPenaltyBox[currentPlayer]) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;

                Console.printLine(players.get(currentPlayer) + " is getting out of the penalty box");
                tiles[currentPlayer] = tiles[currentPlayer] + roll;
                if (tiles[currentPlayer] > 11) {
                    tiles[currentPlayer] = tiles[currentPlayer] - 12;
                }

                Console.printLine(players.get(currentPlayer)
                        + "'s new location is "
                        + tiles[currentPlayer]);
                Console.printLine("The category is " + currentCategory());
                askQuestion();
            } else {
                Console.printLine(players.get(currentPlayer) + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }

        } else {
            tiles[currentPlayer] = tiles[currentPlayer] + roll;
            if (tiles[currentPlayer] > 11) {
                tiles[currentPlayer] = tiles[currentPlayer] - 12;
            }

            Console.printLine(players.get(currentPlayer)
                    + "'s new location is "
                    + tiles[currentPlayer]);
            Console.printLine("The category is " + currentCategory());
            askQuestion();
        }

    }

    private void askQuestion() {
        if (currentCategory() == "Pop")
            System.out.println(popQuestions.removeFirst());
        if (currentCategory() == "Science")
            System.out.println(scienceQuestions.removeFirst());
        if (currentCategory() == "Sports")
            System.out.println(sportsQuestions.removeFirst());
        if (currentCategory() == "Rock")
            System.out.println(rockQuestions.removeFirst());
    }


    private String currentCategory() {
        if (tiles[currentPlayer] == 0) return "Pop";
        if (tiles[currentPlayer] == 4) return "Pop";
        if (tiles[currentPlayer] == 8) return "Pop";
        if (tiles[currentPlayer] == 1) return "Science";
        if (tiles[currentPlayer] == 5) return "Science";
        if (tiles[currentPlayer] == 9) return "Science";
        if (tiles[currentPlayer] == 2) return "Sports";
        if (tiles[currentPlayer] == 6) return "Sports";
        if (tiles[currentPlayer] == 10) return "Sports";
        return "Rock";
    }

    public boolean wasCorrectlyAnswered() {
        if (inPenaltyBox[currentPlayer]) {
            if (isGettingOutOfPenaltyBox) {
                Console.printLine("Answer was correct!!!!");
                coins[currentPlayer]++;
                Console.printLine(players.get(currentPlayer)
                        + " now has "
                        + coins[currentPlayer]
                        + " Gold Coins.");

                boolean winner = didPlayerWin();
                currentPlayer++;
                if (currentPlayer == players.size()) currentPlayer = 0;

                return winner;
            } else {
                currentPlayer++;
                if (currentPlayer == players.size()) currentPlayer = 0;
                return true;
            }
        } else {
            print();
            coins[currentPlayer]++;
            Console.printLine(players.get(currentPlayer)
                    + " now has "
                    + coins[currentPlayer]
                    + " Gold Coins.");

            boolean winner = didPlayerWin();
            currentPlayer++;
            if (currentPlayer == players.size()) {
                currentPlayer = 0;
            }

            return winner;
        }
    }

    private void print() {
        Console.printLine("Answer was corrent!!!!");
    }

    public boolean wrongAnswer() {
        Console.printLine("Question was incorrectly answered");
        Console.printLine(players.get(currentPlayer) + " was sent to the penalty box");
        inPenaltyBox[currentPlayer] = true;
        currentPlayer++;
        if (currentPlayer == players.size()) {
            currentPlayer = 0;
        }
        return true;
    }

    private boolean didPlayerWin() {
        return coins[currentPlayer] != 6;
    }
}
