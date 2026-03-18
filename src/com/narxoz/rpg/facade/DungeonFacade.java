package com.narxoz.rpg.facade;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.hero.HeroProfile;

public class DungeonFacade {
    private final PreparationService preparationService = new PreparationService();
    private final BattleService battleService = new BattleService();
    private final RewardService rewardService = new RewardService();

    public DungeonFacade setRandomSeed(long seed) {
        battleService.setRandomSeed(seed);
        return this;
    }

    public AdventureResult runAdventure(HeroProfile hero, BossEnemy boss, AttackAction action) {
        String prepSummary = preparationService.prepare(hero, boss, action);

        AdventureResult result = battleService.battle(hero, boss, action);

        String reward = rewardService.determineReward(result);
        result.setReward(reward);

        result.getLog().add(0, prepSummary);

        return result;
    }
}