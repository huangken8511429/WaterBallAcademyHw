package BattleStrategy;

import action.Action;
import Base.Role;

import java.util.ArrayList;
import java.util.List;

import static Util.util.printActionName;

public class AIStrategy implements BattleStrategy {
    private int seed = 0;

    @Override
    public Action selectAction(Role role) {
        printActionName(role);
        List<Action> actions = role.getActions();
        Action action = actions.get(seed % actions.size());
        seed++;
        return action;
    }

    @Override
    public List<Role> selectTarget(Action action, List<Role> enemies) {
        List<Role> targets = new ArrayList<>();
        int tempSeed = seed;
        int targetNumber = action.getTargetNumber();
        if (enemies.size() > targetNumber) {
            for (int i = 0; i < targetNumber; i++) {
                targets.add(enemies.get(tempSeed % enemies.size()));
                tempSeed = (tempSeed + 1) % enemies.size();
            }
            seed++;
        } else {
            targets.addAll(enemies);
        }
        return targets;
    }
}
