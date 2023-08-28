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

public class CheerUpTestCase {
    public static void main(String[] args) {
        Troop heroTroop = new Troop(
                List.of(
                        new Hero(500, 10000, 30, "英雄", List.of(new CheerUp()), new HeroStrategy()),
                        new Role(1000, 0, 0, "Servant1", new ArrayList<>(), new AIStrategy()),
                        new Role(1000, 0, 0, "Servant2", new ArrayList<>(), new AIStrategy()),
                        new Role(1000, 0, 0, "Servant3", new ArrayList<>(), new AIStrategy()),
                        new Role(1000, 0, 0, "Servant4", new ArrayList<>(), new AIStrategy()),
                        new Role(1000, 0, 0, "Servant5", new ArrayList<>(), new AIStrategy())
                ));
        Troop enemyTroop = new Troop(
                List.of(
                        new Role(500, 0, 0, "Slime1", new ArrayList<>(), new AIStrategy())
                ));

        Battle battle = new Battle(heroTroop, enemyTroop, new TargetTypeAllyHandler(new TargetTypeEnemyHandler(new TargetTypeSelfHandler(new TargetTypeAllHandler(null)))));
        new RPG(List.of(heroTroop, enemyTroop), battle).start();
    }
}
