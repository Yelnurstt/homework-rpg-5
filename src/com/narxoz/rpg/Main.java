package com.narxoz.rpg;

import com.narxoz.rpg.decorator.*;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.facade.AdventureResult;
import com.narxoz.rpg.facade.DungeonFacade;
import com.narxoz.rpg.hero.HeroProfile;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Homework 5 Demo: Decorator + Facade ===\n");

        HeroProfile hero = new HeroProfile("Arthur", 150);
        BossEnemy boss = new BossEnemy("Dragon King", 300, 25);

        AttackAction basic = new BasicAttack("Sword Strike", 15);

        AttackAction enhanced = new CriticalFocusDecorator(
                new PoisonCoatingDecorator(
                        new FireRuneDecorator(basic)
                )
        );

        System.out.println("--- Decorator Preview ---");
        System.out.println("Base action:  " + basic.getActionName());
        System.out.println("Base damage:  " + basic.getDamage());
        System.out.println("Base effects: " + basic.getEffectSummary());
        System.out.println();
        System.out.println("Enhanced action:  " + enhanced.getActionName());
        System.out.println("Enhanced damage:  " + enhanced.getDamage());
        System.out.println("Enhanced effects: " + enhanced.getEffectSummary());

        System.out.println("\n--- Facade Preview ---");
        DungeonFacade facade = new DungeonFacade().setRandomSeed(42L);
        AdventureResult result = facade.runAdventure(hero, boss, enhanced);

        System.out.println("Winner: " + result.getWinner());
        System.out.println("Rounds: " + result.getRounds());
        System.out.println("Reward: " + result.getReward());

        System.out.println("\n--- Battle Log ---");
        for (String line : result.getLog()) {
            System.out.println(line);
        }

        System.out.println("\n=== Demo Complete ===");
    }
}