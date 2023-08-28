package TestCase;

import Base.*;
import BattleStrategy.AIStrategy;
import BattleStrategy.HeroStrategy;
import action.Skill.Poison;
import action.Skill.SelfExplosion;
import action.SkillTargetTypeHandler.TargetTypeAllHandler;
import action.SkillTargetTypeHandler.TargetTypeAllyHandler;
import action.SkillTargetTypeHandler.TargetTypeEnemyHandler;
import action.SkillTargetTypeHandler.TargetTypeSelfHandler;

import java.util.ArrayList;
import java.util.List;

public class SelfExplosionTestCase {
    public static void main(String[] args) {
        Troop heroTroop = new Troop(
                List.of(
                        new Hero(999999, 500, 30, "英雄", List.of(new SelfExplosion()), new HeroStrategy()),
                        new Role(100, 1000, 15, "A", new ArrayList<>(), new AIStrategy()),
                        new Role(100, 1000, 15, "B", new ArrayList<>(), new AIStrategy()),
                        new Role(100, 1000, 15, "C", new ArrayList<>(), new AIStrategy()),
                        new Role(100, 1000, 15, "D", new ArrayList<>(), new AIStrategy()),
                        new Role(100, 1000, 15, "E", new ArrayList<>(), new AIStrategy()),
                        new Role(100, 1000, 15, "F", new ArrayList<>(), new AIStrategy()),
                        new Role(100, 1000, 15, "G", new ArrayList<>(), new AIStrategy()),
                        new Role(100, 1000, 15, "H", new ArrayList<>(), new AIStrategy()),
                        new Role(100, 1000, 15, "I", new ArrayList<>(), new AIStrategy())
                ));
        Troop enemyTroop = new Troop(
                List.of(
                        new Role(100, 1000, 15, "A", new ArrayList<>(), new AIStrategy()),
                        new Role(100, 1000, 15, "B", new ArrayList<>(), new AIStrategy()),
                        new Role(100, 1000, 15, "C", new ArrayList<>(), new AIStrategy()),
                        new Role(100, 1000, 15, "D", new ArrayList<>(), new AIStrategy()),
                        new Role(100, 1000, 15, "E", new ArrayList<>(), new AIStrategy()),
                        new Role(100, 1000, 15, "F", new ArrayList<>(), new AIStrategy()),
                        new Role(100, 1000, 15, "G", new ArrayList<>(), new AIStrategy()),
                        new Role(100, 1000, 15, "H", new ArrayList<>(), new AIStrategy()),
                        new Role(100, 1000, 15, "I", new ArrayList<>(), new AIStrategy())
                ));

        Battle battle = new Battle(heroTroop, enemyTroop, new TargetTypeAllyHandler(new TargetTypeEnemyHandler(new TargetTypeSelfHandler(new TargetTypeAllHandler(null)))));
        new RPG(List.of(heroTroop, enemyTroop), battle).start();
    }
}
