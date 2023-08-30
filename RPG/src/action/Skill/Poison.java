package action.Skill;

import Base.Role;

import java.util.List;

public class Poison extends Skill {
    public Poison() {
        super("下毒", 80, 1, 0, TargetType.ENEMY);
    }

    @Override
    public void doPerform(Role role, List<Role> targets) {
        for (Role target : targets) {
            target.setState(new State.Poisoned(target));
        }
    }
}
