package TestCase;

import Base.*;
import BattleStrategy.AIStrategy;
import BattleStrategy.HeroStrategy;
import Observer.CurseObserver;
import action.Skill.CheerUp;
import action.Skill.Curse;
import action.SkillTargetTypeHandler.TargetTypeAllHandler;
import action.SkillTargetTypeHandler.TargetTypeAllyHandler;
import action.SkillTargetTypeHandler.TargetTypeEnemyHandler;
import action.SkillTargetTypeHandler.TargetTypeSelfHandler;

import java.util.ArrayList;
import java.util.List;

public class CurseTestCase {
    public static void main(String[] args) {
        Troop heroTroop = new Troop(
                List.of(
                        new Hero(300, 10000, 100, "英雄", List.of(new Curse()), new HeroStrategy()),
                        new Role(600, 100, 30, "Ally", List.of(new Curse(), new Curse()), new AIStrategy())
                ));
        Troop enemyTroop = new Troop(
                List.of(
                        new Role(200, 999, 50, "Slime1", new ArrayList<>(), new AIStrategy()),
                        new Role(200, 999, 100, "Slime2", new ArrayList<>(), new AIStrategy())
                ));
        heroTroop.getRoles().forEach(role -> role.register(List.of(new CurseObserver())));
        enemyTroop.getRoles().forEach(role -> role.register(List.of(new CurseObserver())));

        Battle battle = new Battle(heroTroop, enemyTroop, new TargetTypeAllyHandler(new TargetTypeEnemyHandler(new TargetTypeSelfHandler(new TargetTypeAllHandler(null)))));
        new RPG(List.of(heroTroop, enemyTroop), battle).start();
    }
}
