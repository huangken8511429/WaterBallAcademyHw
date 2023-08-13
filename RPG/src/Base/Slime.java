package Base;

import action.Action;
import BattleStrategy.BattleStrategy;

import java.util.List;

public class Slime extends Role {
    private final int hp = 100;
    private final int mp = 0;
    private final int str = 50;

    public Slime(int hp, int mp, int str, String name, List<Action> actions, BattleStrategy battleStrategy) {
        super(hp, mp, str, name, actions, battleStrategy);
    }
}
