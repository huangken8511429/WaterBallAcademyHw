package action.Skill;

import Base.Role;
import action.OnePuchHandler.OnePunchHandler;

import java.util.List;

public class OnePunch extends Skill {
    private final OnePunchHandler onePunchHandler;

    public OnePunch(OnePunchHandler onePunchHandler) {
        super("一拳攻擊", 180, 1, 0, TargetType.ENEMY);
        this.onePunchHandler = onePunchHandler;
    }

    @Override
    public void doPerform(Role role, List<Role> targets) {
        onePunchHandler.handle(role, targets);
    }
}
