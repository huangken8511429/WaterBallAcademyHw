package action.Skill;

import Base.Role;

import java.util.List;

public class Curse extends Skill {
    public Curse() {
        super("詛咒", 100, 1, 0, TargetType.ENEMY);
    }

    @Override
    public void doPerform(Role role, List<Role> targets) {
        for (Role target : targets) {
            target.addMonitor(role);
        }
    }
}
