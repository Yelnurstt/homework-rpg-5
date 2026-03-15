package com.narxoz.rpg.facade;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.hero.HeroProfile;

public class PreparationService {
    public String prepare(HeroProfile hero, BossEnemy boss, AttackAction action) {
        if (hero == null || !hero.isAlive() || boss == null || action == null) {
            return "Preparation failed: Invalid or dead participants.";
        }

        return String.format(
                "Preparation complete: %s (HP: %d) is ready to fight %s (HP: %d) using '%s' (Damage: %d, Effects: %s)",
                hero.getName(), hero.getHealth(),
                boss.getName(), boss.getHealth(),
                action.getActionName(), action.getDamage(), action.getEffectSummary()
        );
    }
}