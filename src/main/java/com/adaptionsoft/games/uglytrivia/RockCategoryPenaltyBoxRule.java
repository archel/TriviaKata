package com.adaptionsoft.games.uglytrivia;

public class RockCategoryPenaltyBoxRule extends PenaltyBoxRule {
    public int execute(String category) {
        if (category.equals("Rock")) {
            return 5;
        }

        return 0;
    }
}
