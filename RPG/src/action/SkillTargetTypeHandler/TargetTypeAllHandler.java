package action.SkillTargetTypeHandler;

import Base.Battle;
import Base.Role;
import action.Action;

import java.util.List;

public class TargetTypeAllHandler extends SkillTargetTypeHandler {
    public TargetTypeAllHandler(SkillTargetTypeHandler next) {
        super(next);
    }

    @Override
    protected boolean match(Action action, Role role) {
        return action.getTargetType() == Action.TargetType.ALL;
    }

    @Override
    protected List<Role> doHandle(Action action, Role role, Battle battle) {
        return battle.getAllAliveRoles(role);
    }
}
