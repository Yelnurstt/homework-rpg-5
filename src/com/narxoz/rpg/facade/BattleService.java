package com.narxoz.rpg.facade;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.hero.HeroProfile;

import java.util.Random;

public class BattleService {
    private Random random = new Random(1L);
    private static final int MAX_ROUNDS = 20;

    public BattleService setRandomSeed(long seed) {
        this.random = new Random(seed);
        return this;
    }

    public AdventureResult battle(HeroProfile hero, BossEnemy boss, AttackAction action) {
        AdventureResult result = new AdventureResult();
        int round = 0;

        result.addLine("--- Battle Starts ---");

        while (hero.isAlive() && boss.isAlive() && round < MAX_ROUNDS) {
            round++;
            result.addLine("\n[Round " + round + "]");

            int heroDamage = action.getDamage();

            if (random.nextInt(100) < 10) {
                result.addLine(hero.getName() + " tries to use " + action.getActionName() + " but misses!");
                heroDamage = 0;
            } else {
                result.addLine(hero.getName() + " attacks with " + action.getActionName() + " for " + heroDamage + " damage!");
            }

            boss.takeDamage(heroDamage);

            if (!boss.isAlive()) {
                result.addLine(boss.getName() + " is defeated!");
                break;
            }

            int bossDamage = boss.getAttackPower();
            result.addLine(boss.getName() + " strikes back for " + bossDamage + " damage!");
            hero.takeDamage(bossDamage);

            if (!hero.isAlive()) {
                result.addLine(hero.getName() + " has fallen in battle...");
            }
        }

        result.setRounds(round);

        if (!hero.isAlive() && !boss.isAlive()) {
            result.setWinner("Draw");
        } else if (hero.isAlive()) {
            result.setWinner(hero.getName());
        } else if (boss.isAlive()) {
            result.setWinner(boss.getName());
        } else {
            result.setWinner("Timeout");
            result.addLine("\nBattle reached max rounds.");
        }

        return result;
    }
}