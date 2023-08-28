package action.SkillTargetTypeHandler;

import Base.Battle;
import Base.Role;
import action.Action;

import java.util.ArrayList;
import java.util.List;

public class TargetTypeAllyHandler extends SkillTargetTypeHandler {

    public TargetTypeAllyHandler(SkillTargetTypeHandler next) {
        super(next);
    }

    @Override
    protected boolean match(Action action, Role role) {
        return action.getTargetType().equals(Action.TargetType.AllY);
    }

    @Override
    protected List<Role> doHandle(Action action, Role role, Battle battle) {
        List<Role> targets = new ArrayList<>();
        if (role.getTroop().equals(battle.getAllyTroop())) {
            targets = role.chooseTargets(action, battle.getAliveAllys(role));
        } else {
            targets = role.chooseTargets(action, battle.getAliveEnemies(role));
        }
        return targets;
    }
}
