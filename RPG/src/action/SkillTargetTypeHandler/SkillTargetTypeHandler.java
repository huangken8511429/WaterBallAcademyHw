package action.SkillTargetTypeHandler;

import Base.Battle;
import Base.Role;
import action.Action;

import java.util.List;

public abstract class SkillTargetTypeHandler {
    private final SkillTargetTypeHandler next;
    public SkillTargetTypeHandler(SkillTargetTypeHandler next) {
        this.next = next;
    }

    public List<Role> handle(Action action, Role role, Battle battle) {
        List<Role> roles = null;
        if (match(action, role)) {
            roles = doHandle(action, role, battle);
        } else if (next != null) {
            roles = next.handle(action, role, battle);
        }
        return roles;
    }

    protected abstract boolean match(Action action, Role role);

    protected abstract List<Role> doHandle(Action action, Role role, Battle battle);
}
