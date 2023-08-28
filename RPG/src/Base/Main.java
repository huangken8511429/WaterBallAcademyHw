package Base;

import Observer.CurseObserver;
import Observer.SummonObserver;
import action.OnePuchHandler.TargetHpBiggerThen500Handler;
import action.OnePuchHandler.TargetInCheerUpStateHandler;
import action.OnePuchHandler.TargetInNormalStateHandler;
import action.OnePuchHandler.TargetInPoisonOrPetroChemicalStateHandler;
import action.Skill.*;
import BattleStrategy.AIStrategy;
import BattleStrategy.HeroStrategy;
import action.SkillTargetTypeHandler.TargetTypeAllHandler;
import action.SkillTargetTypeHandler.TargetTypeAllyHandler;
import action.SkillTargetTypeHandler.TargetTypeEnemyHandler;
import action.SkillTargetTypeHandler.TargetTypeSelfHandler;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        OnePunch onePunch = new OnePunch(new TargetHpBiggerThen500Handler(new TargetInPoisonOrPetroChemicalStateHandler(new TargetInCheerUpStateHandler(new TargetInNormalStateHandler(null)))));
        Troop heroTroop = new Troop(
                List.of(
                        new Hero(400, 99999, 30, "英雄", List.of(new Petrochemical()), new HeroStrategy())
                ));
        Troop enemyTroop = new Troop(
                List.of(
                        new Role(270, 9999, 399, "攻擊力超強的BOSS", List.of(new Petrochemical()), new AIStrategy())
                        ));
//        heroTroop.getRoles().forEach(role -> role.register(List.of(new CurseObserver())));
//        enemyTroop.getRoles().forEach(role -> role.register(List.of(new CurseObserver())));
        Battle battle = new Battle(heroTroop, enemyTroop, new TargetTypeAllyHandler(new TargetTypeEnemyHandler(new TargetTypeSelfHandler(new TargetTypeAllHandler(null)))));
        new RPG(List.of(heroTroop, enemyTroop), battle).start();
    }
}