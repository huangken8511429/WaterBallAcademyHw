package action.OnePuchHandler;

import Base.Role;

import java.util.List;

public class TargetHpBiggerThen500Handler extends OnePunchHandler {
    public TargetHpBiggerThen500Handler(OnePunchHandler next) {
        super(next);
    }

    @Override
    protected boolean match(List<Role> targets) {
        Role role = targets.get(0);
        return role.getHp() >= 500;
    }

    @Override
    protected void doHandle(Role attacker, List<Role> targets) {
        Role target = targets.get(0);
        target.damage(attacker, 300);
    }
}
