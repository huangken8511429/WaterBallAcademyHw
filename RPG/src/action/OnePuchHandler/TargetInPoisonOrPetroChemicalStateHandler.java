package action.OnePuchHandler;

import Base.Role;
import State.Petrochemical;
import State.Poisoned;

import java.util.List;

public class TargetInPoisonOrPetroChemicalStateHandler extends OnePunchHandler {
    public TargetInPoisonOrPetroChemicalStateHandler(OnePunchHandler next) {
        super(next);
    }

    @Override
    protected boolean match(List<Role> targets) {
        Role role = targets.get(0);
        return role.getState() instanceof Poisoned || role.getState() instanceof Petrochemical;
    }

    @Override
    protected void doHandle(Role attacker, List<Role> targets) {
        Role target = targets.get(0);
        for (int i = 0; i < 3; i++) {
            target.damage(attacker, 80);
            if (target.isDead()) break;
        }
    }
}
