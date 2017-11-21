package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.uglytrivia.Console;

import java.util.LinkedList;
import java.util.List;

import static java.util.stream.Collectors.joining;

public class ConsoleTestable extends Console {
    private List<String> messages = new LinkedList<>();

    @Override
    public void printLine(String message) {
        messages.add(message);
    }

    public String toString() {
        return messages
                .stream()
                .collect(joining("\n"))
                .concat("\n");

    }
}
