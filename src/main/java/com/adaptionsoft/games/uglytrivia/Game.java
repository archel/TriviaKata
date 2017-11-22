package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Game {
    private final Console console;

    private List<String> players = new ArrayList<>();
    private int[] tiles = new int[6];
    private int[] coins = new int[6];
    private PenaltyBox penaltyBox = new PenaltyBox();

    private LinkedList<String> popQuestions = new LinkedList<>();
    private LinkedList<String> scienceQuestions = new LinkedList<>();
    private LinkedList<String> sportsQuestions = new LinkedList<>();
    private LinkedList<String> rockQuestions = new LinkedList<>();

    private int currentPlayer = 0;
    private boolean isGettingOutOfPenaltyBox;

    public Game(Console console) {
        this.console = console;
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

        print(playerName);
        console.printLine("They are player number " + players.size());
        return true;
    }

    private void print(String playerName) {
        console.printLine(playerName + " was added");
    }

    public int howManyPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        console.printLine(getCurrentPlayerName() + " is the current player");
        console.printLine("They have rolled a " + roll);

        if (isCurrentPlayerInPenaltyBox()) {
            if (isOddNumber(roll)) {
                isGettingOutOfPenaltyBox = true;
                console.printLine(getCurrentPlayerName() + " is getting out of the penalty box");
                tiles[currentPlayer] = tiles[currentPlayer] + roll;
                if (tiles[currentPlayer] > 11) {
                    tiles[currentPlayer] = tiles[currentPlayer] - 12;
                }
                console.printLine(getCurrentPlayerName() + "'s new location is " + tiles[currentPlayer]);
                console.printLine("The category is " + currentCategory());
                askQuestion();
            } else {
                console.printLine(getCurrentPlayerName() + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }
        } else {
            tiles[currentPlayer] = tiles[currentPlayer] + roll;
            if (tiles[currentPlayer] > 11) {
                tiles[currentPlayer] = tiles[currentPlayer] - 12;
            }
            console.printLine(getCurrentPlayerName()
                    + "'s new location is "
                    + tiles[currentPlayer]);
            console.printLine("The category is " + currentCategory());
            askQuestion();
        }
    }

    private boolean isOddNumber(int roll) {
        return roll % 2 != 0;
    }

    private void askQuestion() {
        if (currentCategory().equals("Pop"))
            console.printLine(popQuestions.removeFirst());
        if (currentCategory().equals("Science"))
            console.printLine(scienceQuestions.removeFirst());
        if (currentCategory().equals("Sports"))
            console.printLine(sportsQuestions.removeFirst());
        if (currentCategory().equals("Rock"))
            console.printLine(rockQuestions.removeFirst());
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
        if (isCurrentPlayerInPenaltyBox()) {
            if (isGettingOutOfPenaltyBox) {
                printAnswerWasCorrect();
                rewardPlayerWithCoin();
                printCurrentPlayerCoins();
                boolean winner = didPlayerWin();
                passTurn();
                return winner;
            } else {
                passTurn();
                return true;
            }
        } else {
            printAnswerWasCorrent();
            rewardPlayerWithCoin();
            printCurrentPlayerCoins();
            boolean winner = didPlayerWin();
            passTurn();
            return winner;
        }
    }

    private boolean isCurrentPlayerInPenaltyBox() {
        return penaltyBox.isIn(getCurrentPlayerName());
    }

    private void printAnswerWasCorrect() {
        console.printLine("Answer was correct!!!!");
    }

    private void printAnswerWasCorrent() {
        console.printLine("Answer was corrent!!!!");
    }

    private void rewardPlayerWithCoin() {
        coins[currentPlayer]++;
    }

    private void printCurrentPlayerCoins() {
        console.printLine(getCurrentPlayerName() + " now has " + coins[currentPlayer] + " Gold Coins.");
    }

    private void passTurn() {
        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
    }

    public boolean wrongAnswer() {
        console.printLine("Question was incorrectly answered");
        //Loop of rules, sum all turns and add the player X turns to the penalty box
        sendCurrentPlayerToPenaltyBox();
        passTurn();
        return true;
    }

    private void sendCurrentPlayerToPenaltyBox() {
        console.printLine(getCurrentPlayerName() + " was sent to the penalty box");
        penaltyBox.add(getCurrentPlayerName());
    }

    private String getCurrentPlayerName() {
        return players.get(currentPlayer);
    }

    private boolean didPlayerWin() {
        return coins[currentPlayer] != 6;
    }
}
