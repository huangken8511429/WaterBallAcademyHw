package action.SkillTargetTypeHandler;

import Base.Battle;
import Base.Role;
import action.Action;

import java.util.ArrayList;
import java.util.List;

public class TargetTypeEnemyHandler extends SkillTargetTypeHandler {

    public TargetTypeEnemyHandler(SkillTargetTypeHandler next) {
        super(next);
    }

    @Override
    protected boolean match(Action action, Role role) {
        return action.getTargetType().equals(Action.TargetType.ENEMY);
    }

    @Override
    protected List<Role> doHandle(Action action, Role role, Battle battle) {
        List<Role> targets = new ArrayList<>();
        if (role.getTroop().equals(battle.getAllyTroop())) {
            targets = role.chooseTargets(action, battle.getAliveEnemies(role));
        } else {
            targets = role.chooseTargets(action, battle.getAliveAllys(role));
        }
        return targets;
    }
}
