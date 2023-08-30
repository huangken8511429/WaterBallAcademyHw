package action.Skill;

import Base.Role;

import java.util.List;

public class Petrochemical extends Skill {
    public Petrochemical() {
        super("石化", 100, 1, 0, TargetType.ENEMY);
    }

    @Override
    public void doPerform(Role role, List<Role> targets) {
        for (Role target : targets) {
            target.setState(new State.Petrochemical(target));
        }
    }
}
