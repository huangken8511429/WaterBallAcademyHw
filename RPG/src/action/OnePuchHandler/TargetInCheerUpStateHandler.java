package action.OnePuchHandler;

import Base.Role;
import State.CheerUp;
import State.Normal;

import java.util.List;

public class TargetInCheerUpStateHandler extends OnePunchHandler {
    public TargetInCheerUpStateHandler(OnePunchHandler next) {
        super(next);
    }

    @Override
    protected boolean match(List<Role> targets) {
        Role role = targets.get(0);
        return role.getState() instanceof CheerUp;
    }

    @Override
    protected void doHandle(Role attacker, List<Role> targets) {
        Role target = targets.get(0);
        target.damage(attacker, 100);
        target.setState(new Normal(target));
    }

}