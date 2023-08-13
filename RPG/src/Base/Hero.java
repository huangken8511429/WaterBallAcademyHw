package Base;

import action.Action;
import BattleStrategy.BattleStrategy;

import java.util.List;


public class Hero extends Role {

    public Hero(int hp, int mp, int str, String name, List<Action> actions, BattleStrategy battleStrategy) {
        super(hp, mp, str, name, actions, battleStrategy);
    }
}
