package TestCase;

import Base.*;
import BattleStrategy.AIStrategy;
import BattleStrategy.HeroStrategy;
import action.Skill.CheerUp;
import action.Skill.Poison;
import action.SkillTargetTypeHandler.TargetTypeAllHandler;
import action.SkillTargetTypeHandler.TargetTypeAllyHandler;
import action.SkillTargetTypeHandler.TargetTypeEnemyHandler;
import action.SkillTargetTypeHandler.TargetTypeSelfHandler;

import java.util.ArrayList;
import java.util.List;

public class PoisonTestCase {
    public static void main(String[] args) {
        Troop heroTroop = new Troop(
                List.of(
                        new Hero(1000, 500, 0, "英雄", List.of(new Poison()), new HeroStrategy())
                ));
        Troop enemyTroop = new Troop(
                List.of(
                        new Role(120, 90, 50, "Slime1", new ArrayList<>(), new AIStrategy()),
                        new Role(120, 90, 50, "Slime2", new ArrayList<>(), new AIStrategy()),
                        new Role(120, 9000, 50, "Slime3", new ArrayList<>(), new AIStrategy())
                ));

        Battle battle = new Battle(heroTroop, enemyTroop, new TargetTypeAllyHandler(new TargetTypeEnemyHandler(new TargetTypeSelfHandler(new TargetTypeAllHandler(null)))));
        new RPG(List.of(heroTroop, enemyTroop), battle).start();
    }
}
