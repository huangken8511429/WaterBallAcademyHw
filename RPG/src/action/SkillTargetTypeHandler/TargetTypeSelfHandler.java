package action.SkillTargetTypeHandler;

import Base.Battle;
import Base.Role;
import action.Action;

import java.util.List;

public class TargetTypeSelfHandler extends SkillTargetTypeHandler{

    public TargetTypeSelfHandler(SkillTargetTypeHandler next) {
        super(next);
    }

    @Override
    protected boolean match(Action action, Role role) {
        return action.getTargetType().equals(Action.TargetType.SELF);
    }

    @Override
    protected List<Role> doHandle(Action action, Role role, Battle battle) {
        return role.chooseTargets(action, List.of(role));
    }
}
