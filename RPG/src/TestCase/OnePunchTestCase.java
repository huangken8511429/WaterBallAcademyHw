package TestCase;

import Base.*;
import BattleStrategy.AIStrategy;
import BattleStrategy.HeroStrategy;
import action.OnePuchHandler.TargetHpBiggerThen500Handler;
import action.OnePuchHandler.TargetInCheerUpStateHandler;
import action.OnePuchHandler.TargetInNormalStateHandler;
import action.OnePuchHandler.TargetInPoisonOrPetroChemicalStateHandler;
import action.Skill.CheerUp;
import action.Skill.OnePunch;
import action.Skill.Petrochemical;
import action.Skill.Poison;
import action.SkillTargetTypeHandler.TargetTypeAllHandler;
import action.SkillTargetTypeHandler.TargetTypeAllyHandler;
import action.SkillTargetTypeHandler.TargetTypeEnemyHandler;
import action.SkillTargetTypeHandler.TargetTypeSelfHandler;

import java.util.ArrayList;
import java.util.List;

public class OnePunchTestCase {
    public static void main(String[] args) {
        OnePunch onePunch = new OnePunch(new TargetHpBiggerThen500Handler(new TargetInPoisonOrPetroChemicalStateHandler(new TargetInCheerUpStateHandler(new TargetInNormalStateHandler(null)))));
        OnePunch anotherOnePunch = new OnePunch(new TargetHpBiggerThen500Handler(new TargetInPoisonOrPetroChemicalStateHandler(new TargetInCheerUpStateHandler(new TargetInNormalStateHandler(null)))));
        Troop heroTroop = new Troop(
                List.of(
                        new Hero(1000, 10000, 0, "英雄", List.of(onePunch, new Poison(), new Petrochemical(), new CheerUp()), new HeroStrategy())
                ));
        Troop enemyTroop = new Troop(
                List.of(
                        new Role(601, 0, 0, "Slime1", new ArrayList<>(), new AIStrategy()),
                        new Role(241, 0, 0, "Slime2", new ArrayList<>(), new AIStrategy()),
                        new Role(101, 999, 0, "Slime3", List.of(onePunch, anotherOnePunch, new CheerUp()), new AIStrategy())
                ));
        Battle battle = new Battle(heroTroop, enemyTroop, new TargetTypeAllyHandler(new TargetTypeEnemyHandler(new TargetTypeSelfHandler(new TargetTypeAllHandler(null)))));
        new RPG(List.of(heroTroop, enemyTroop), battle).start();
    }
}
