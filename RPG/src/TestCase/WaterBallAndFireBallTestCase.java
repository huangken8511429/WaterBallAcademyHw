package TestCase;

import Base.*;
import BattleStrategy.AIStrategy;
import BattleStrategy.HeroStrategy;
import action.Skill.FireBall;
import action.Skill.WaterBall;
import action.SkillTargetTypeHandler.TargetTypeAllHandler;
import action.SkillTargetTypeHandler.TargetTypeAllyHandler;
import action.SkillTargetTypeHandler.TargetTypeEnemyHandler;
import action.SkillTargetTypeHandler.TargetTypeSelfHandler;

import java.util.List;

public class WaterBallAndFireBallTestCase {
    public static void main(String[] args) {
        Troop heroTroop = new Troop(
                List.of(
                        new Hero(300, 500, 100, "英雄", List.of(new FireBall(), new WaterBall()), new HeroStrategy())
                ));
        Troop enemyTroop = new Troop(
                List.of(
                        new Role(200, 60, 49, "Slime1", List.of(new FireBall()), new AIStrategy()),
                        new Role(200, 200, 50, "Slime2", List.of(new FireBall(), new WaterBall()), new AIStrategy()))
        );
        Battle battle = new Battle(heroTroop, enemyTroop, new TargetTypeAllyHandler(new TargetTypeEnemyHandler(new TargetTypeSelfHandler(new TargetTypeAllHandler(null)))));
        new RPG(List.of(heroTroop, enemyTroop), battle).start();
    }
}
