package TestCase;

import Base.*;
import BattleStrategy.AIStrategy;
import BattleStrategy.HeroStrategy;
import action.Skill.Petrochemical;
import action.SkillTargetTypeHandler.TargetTypeAllHandler;
import action.SkillTargetTypeHandler.TargetTypeAllyHandler;
import action.SkillTargetTypeHandler.TargetTypeEnemyHandler;
import action.SkillTargetTypeHandler.TargetTypeSelfHandler;

import java.util.ArrayList;
import java.util.List;

public class OnlyBasicAttackTestCase {
    public static void main(String[] args) {
        Troop heroTroop = new Troop(
                List.of(
                        new Hero(500, 500, 100, "英雄", new ArrayList<>(), new HeroStrategy()),
                        new Role(200, 200, 70, "WaterTA", new ArrayList<>(), new AIStrategy())
                ));
        Troop enemyTroop = new Troop(
                List.of(
                        new Role(200, 90, 50, "Slime1", new ArrayList<>(), new AIStrategy()),
                        new Role(200, 90, 50, "Slime2", new ArrayList<>(), new AIStrategy()),
                        new Role(200, 9000, 50, "Slime3", new ArrayList<>(), new AIStrategy())

                ));
        Battle battle = new Battle(heroTroop, enemyTroop, new TargetTypeAllyHandler(new TargetTypeEnemyHandler(new TargetTypeSelfHandler(new TargetTypeAllHandler(null)))));
        new RPG(List.of(heroTroop, enemyTroop), battle).start();
    }
}
