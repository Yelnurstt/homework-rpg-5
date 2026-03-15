package com.narxoz.rpg.facade;

public class RewardService {
    public String determineReward(AdventureResult battleResult) {
        if (battleResult == null || battleResult.getWinner() == null) {
            return "No reward (Invalid state)";
        }

        if (battleResult.getWinner().equals("Draw") || battleResult.getWinner().equals("Timeout")) {
            return "A handful of rusty coins.";
        }

        return "Epic Loot Box and 1000 Gold!";
    }
}