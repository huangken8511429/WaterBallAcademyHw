package TestCase;

import Base.*;
import BattleStrategy.AIStrategy;
import BattleStrategy.HeroStrategy;
import action.Skill.SelfHealing;
import action.SkillTargetTypeHandler.TargetTypeAllHandler;
import action.SkillTargetTypeHandler.TargetTypeAllyHandler;
import action.SkillTargetTypeHandler.TargetTypeEnemyHandler;
import action.SkillTargetTypeHandler.TargetTypeSelfHandler;

import java.util.ArrayList;
import java.util.List;

public class SelfHealingTestCase {
    public static void main(String[] args) {
        Troop heroTroop = new Troop(
                List.of(
                        new Hero(500, 500, 40, "英雄", new ArrayList<>(), new HeroStrategy())
                ));
        Troop enemyTroop = new Troop(
                List.of(
                        new Role(100, 100, 30, "Slime1", List.of(new SelfHealing()), new AIStrategy()))
                );
        Battle battle = new Battle(heroTroop, enemyTroop, new TargetTypeAllyHandler(new TargetTypeEnemyHandler(new TargetTypeSelfHandler(new TargetTypeAllHandler(null)))));
        new RPG(List.of(heroTroop, enemyTroop), battle).start();
    }
}
